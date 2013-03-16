package strategy.controller;

import java.util.Date;
import java.util.Random;

import strategy.logs.PackLog;
import strategy.xml.ZIP;


/**
 * 
 * Klasa zajmujaca sie obsluga strategii ZIP
 * 
 * @author Kaatarzyna Kucharczyk
 * 
 */

public class ZIPController {

	/* cel: sprzedawca/kupujacy */
	protected purpose purpose;

	/* stan: greedy/fear */
	protected state state;

	/* profit margin */
	protected double margin;
	/* lambda do progu zmiany marginu (im wieksza tym drastyczniejsza zmiana */
	protected double gamma;
	/* wspolczynnik uczenia sie */
	protected double beta;
	/* ograniczenie A i B na chciwosc i strach */
	protected double constraintA;
	protected double constraintR;

	/* wspolczynniki do stanow fear/greedy */
	protected double Ai;
	protected double Ri;

	/* strategia ktorej sie tyczy kontroler */
	protected ZIP zip;

	protected double updateT;

	/* informacja historyczna */
	protected double limit;
	protected double lastStrategyOffer;
	protected double lastMarketOffer;

	protected PackLog logList;
	
	protected int logUse;

	Offer offer;
	
	public ZIPController(ZIP s, PackLog list) {
		this.zip = s;
		this.logList = list;
		
		offer = new Offer();

		setStartMargin();
		setStartLambda();
		setStartBeta();
		setStartConstraintA();
		setStartConstraintB();
		
		
		logList.logStrategyStarters(this.beta, this.gamma, this.margin, this.zip.getStrategyName());
		this.updateT = 0.0;
	}

	/* wybor strartowego profit marginu z przedzialu */
	private void setStartMargin() {
		double min = zip.getMarginStartMin();
		double max = zip.getMarginStartMax();
		this.margin = min + (double) (Math.random() * ((max - min)));

	}

	/* wybor progu zmiany profit marginu z przedzialu */
	private void setStartLambda() {
		double min = zip.getGammaMin();
		double max = zip.getGammaMax();
		this.gamma = min + (double) (Math.random() * ((max - min)));

	}

	/* wybor wspolczynnika uczenia sie z przedzialu */
	private void setStartBeta() {
		double min = zip.getBetaMin();
		double max = zip.getBetaMax();
		this.beta = min + (double) (Math.random() * ((max - min)));

	}

	/* nadanie ograniczenia cA z konfiguracji */
	private void setStartConstraintA() {
		this.constraintA = zip.getConstraintA();

	}

	/* nadanie ograniczenia cB z konfiguracji */
	private void setStartConstraintB() {
		this.constraintR = zip.getConstraintB();
	}

	/* Adaptacja */

	/**
	 * Funkcja zwraca oferte jaka powinien zalicytowac uczestnik
	 * 
	 * @param p
	 *            - cel uczestnika - kupujacy/sprzedajacy
	 * @param lastMOffer
	 *            - ostatnia oferta na rynku
	 * @param limit
	 *            - limit pieniezny uczestnika na jednostke oferty
	 * @return offer - oferta jaka powinien uczestnik zlozyc na rynku
	 */
	public Offer init(purpose p, double lastMOffer, double limit) {
		this.purpose = p;
		this.lastMarketOffer = lastMOffer;
		this.limit = limit;
		Random r = new Random();
		
		/*
		 * na wypadek gdyby limit byl 0 (czesto sie zdaza dla WNP)
		 */
    	if (this.limit == 0) {
    		this.limit = this.lastMarketOffer;
    		if (this.limit == 0) {
    			this.limit = r.nextDouble() * 30 + 85;
    		}
    	}
		
		offer.setAgentPuprose(p);

		changeDecision();
		updateMargin();
		double actualOffer = countOffer();

		log();
		
		offer.setOfferAmt(actualOffer);
		offer.setUseId(this.logUse);
		
		return offer;
	}

	
	/**
	 * Funkcja do sprawdzenia jaka powinnna byc miana profit marginu
	 * 
	 */
	private void changeDecision() {
		// czy seller czy buyer
		/* jeœli jest sprzedajacym musi sprzedac powyzej swojego limitu */
		if (this.purpose.equals(strategy.controller.purpose.SELLER)) {
			this.lastStrategyOffer = this.limit * (1 + this.margin);
			if (this.lastMarketOffer > this.lastStrategyOffer) {
				this.state = strategy.controller.state.GREEDY;
			} else {
				this.state = strategy.controller.state.FEAR;
			}

			/* jezeli jest kupujacym musi kupic ponizej swojego limitu */
		} else if (this.purpose.equals(strategy.controller.purpose.BUYER)) {
			this.lastStrategyOffer = this.limit * (1 - this.margin);
			if (this.lastMarketOffer < this.lastStrategyOffer) {
				this.state = strategy.controller.state.GREEDY;
			} else {
				this.state = strategy.controller.state.FEAR;
			}
		}

		setAiRi();

	}

	/**
	 * wyliczenie wspolczynnikow Ai Ri niezbednych do wyliczenia t (tau)
	 * docelowej ceny (target price)
	 */
	private void setAiRi() {

		/* wspolczynniki zalezne od stanu agenta */
		if (state.equals(strategy.controller.state.GREEDY)) {
			double min = 1.0;
			double max = 1.0 + constraintR;
			this.Ri = min + (double) (Math.random() * ((max - min)));

			min = 0;
			max = constraintA;
			this.Ai = min + (double) (Math.random() * ((max - min)));

		} else if (state.equals(strategy.controller.state.FEAR)) {
			double min = 1.0 - constraintR;
			double max = 1.0;
			this.Ri = min + (double) (Math.random() * ((max - min)));

			min = -constraintA;
			max = 0;
			this.Ai = min + (double) (Math.random() * ((max - min)));

		}

	}

	/**
	 * zmienia aktualny profit margin
	 */
	private void updateMargin() {

		double tau = countDesiredOffer();

		double du = countDesiredMargin(tau);


		double delta = countDiff(du);
		/*
		 * moja bezczelna latka
		 * 
		 * problem pojawia sie gdy delta jest bardzo mala, wtedy ma maly wplyw
		 * na T i dominuje wczesniejsze T co moze doprowadzic do momentu gdzie
		 * oferta zamiast np zmniejszyc to zwiekszy sie o grosze. Zaobserwowane
		 * przy Sprzedajacy boi sie Nasza oferta 13.163334279305165 Rynkowa
		 * oferta 5.0 Limit 10.0 tau 4.854638912549583 Optimal margin
		 * 0.029072217490083374 F1 delta -0.046932837002529086 beforeT1
		 * 0.010416920231350506 beforeT2 1.9194899693585586E-4 stary margin
		 * 0.31633342793051655 nowy margin 0.3165253769274524 oferta
		 * 13.165253769274523 FALSE
		 * 
		 * dlatego warto zastanowic sie czy zawsze nalezy uzywac aktualizacji T
		 * (moze jednak czasem delta bedzie lepsza)
		 */


		countUpdate(delta);

		double oldm = this.margin;
		this.margin = margin + updateT;

	}

	/**
	 * Wylicza oferte do zlozenia
	 * 
	 * @return
	 */
	private double countOffer() {
		double offer = 0.0;

		if (this.purpose.equals(strategy.controller.purpose.SELLER)) {
			offer = this.limit * (1 + this.margin);

			/* jezeli jest kupujacym musi kupic ponizej swojego limitu */
		} else if (this.purpose.equals(strategy.controller.purpose.BUYER)) {
			offer = this.limit * (1 - this.margin);

		}

		//System.out.println("oferta " + offer);
		this.lastStrategyOffer = offer;
		return offer;
	}

	/* Nauka optymalnego u */
	/**
	 * 
	 * Wylicza tau - porzadana oferte na podstawie tego czy uczestnik sie boi i
	 * tego jaka byla ostatnia oferta na rynku
	 * 
	 * @return defiredOffer - porzadana oferta
	 */
	private double countDesiredOffer() {
		return this.Ri * this.lastMarketOffer + this.Ai;
	}

	/**
	 * Metoda wyliczaj¹ca porzadany margin
	 * 
	 * @param desiredOffer
	 *            - najlepsza mo¿liwa oferta - tau
	 * @return defiredMargin - porzadany margin
	 */
	private double countDesiredMargin(double desiredOffer) {
		//return (1 - (defiredOffer / this.lastMarketOffer));
		return (1 - (desiredOffer / this.limit));
	}

	/**
	 * Metoda zwraca roznice miedzy porzadanym marginem, a aktualnym
	 * uwzgledniajac parametr uczenia sie
	 * 
	 * @param alpha
	 *            - wspolczynnik uczenia sie
	 * @param desiredMargin
	 *            - porzadany margin
	 * @param u
	 *            - aktualny margin
	 * @return - roznica
	 */
	private double countDiff(double desiredMargin) {
		double delta = 0.0;
		/* to jest moj maly patch */
		if (purpose.equals(strategy.controller.purpose.BUYER)) {
			if (state.equals(strategy.controller.state.FEAR)) {
				if (desiredMargin - margin > 0) {
					delta = this.beta * (margin - desiredMargin);
				} else {
					delta = this.beta * (desiredMargin - margin);
				}
			} else {
				if (desiredMargin - margin <= 0) {
					delta = this.beta * (margin - desiredMargin);
				} else {
					delta = this.beta * (desiredMargin - margin);
				}
			}
		} else {
			if (state.equals(strategy.controller.state.FEAR)) {
				if (desiredMargin - margin <= 0) {
					delta = this.beta * (desiredMargin - margin);

				} else {
					delta = this.beta * (margin - desiredMargin);

				}
			} else {
				if (desiredMargin - margin > 0) {
					delta = this.beta * (desiredMargin - margin);

				} else {
					delta = this.beta * (margin - desiredMargin);

				}
			}
		}
		

		return delta;
	}

	/**
	 * Metoda sluzy do wyliczenia zmiany marginu u. Jest uwzgledniany parametr
	 * gamma aby zmiana ta mogla byæ gladsza (wyzsze wartosci) lub bardziej
	 * gwaltowna (nizsze).
	 * 
	 * @param diff
	 *            - roznica
	 * @param gamma
	 *            - wspolczynnik [0,1]
	 * @param updateT
	 *            - poprzednia aktualizacja T
	 * @return wartosc zmiany marginesu T
	 */
	private void countUpdate(double diff) {

		this.updateT = this.gamma * this.updateT + (1 - this.gamma) * diff;

	}


	public String getStrategyId() {
		return zip.getStrategyName();
	}

	public purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(purpose purpose) {
		this.purpose = purpose;
	}

	public void setPurpose(String p) {
		if (p.equals("buy"))
			this.purpose = strategy.controller.purpose.BUYER;
		else if (purpose.equals("sell"))
			this.purpose = strategy.controller.purpose.SELLER;
	}

	public state getState() {
		return state;
	}

	public void setState(state state) {
		this.state = state;
	}

	public void test() {
		this.purpose = strategy.controller.purpose.SELLER;
		double offer;
		System.out.println("***Oferta 1***");
		this.limit = 10;
		this.lastMarketOffer = 11;
		this.lastStrategyOffer = 180;

		changeDecision();
		updateMargin();
		offer = countOffer();
		log();

		System.out.println("***Oferta 2***");
		this.limit = 10;
		this.lastMarketOffer = 9;
		this.lastStrategyOffer = offer;

		changeDecision();
		updateMargin();
		offer = countOffer();
		log();

		System.out.println("***Oferta 3***");

		this.limit = 10;
		this.lastMarketOffer = 9.5;
		this.lastStrategyOffer = offer;

		changeDecision();
		updateMargin();
		offer = countOffer();
		log();

		this.purpose = purpose.BUYER;
		System.out.println("***Oferta 1***");
		this.limit = 10;
		this.lastMarketOffer = 10;
		this.lastStrategyOffer = 180;

		changeDecision();
		updateMargin();
		offer = countOffer();
		log();

		System.out.println("***Oferta 2***");
		this.limit = 10;
		this.lastMarketOffer = 10.5;
		this.lastStrategyOffer = offer;

		changeDecision();
		updateMargin();
		offer = countOffer();
		log();

		System.out.println("***Oferta 3***");

		this.limit = 10;
		this.lastMarketOffer = 9.5;
		this.lastStrategyOffer = offer;

		changeDecision();
		updateMargin();
		offer = countOffer();
		log();
	}

	/**
	 * Zapis dzialania
	 */
	private void log() {
		this.logUse = this.logList.log(getStrategyId(), this.state, this.purpose,
				this.margin, this.lastStrategyOffer, this.limit, (new Date()).getTime(), this.lastMarketOffer);
	}
	
	public int getLogUse(){
		return this.logUse;
	}
}
