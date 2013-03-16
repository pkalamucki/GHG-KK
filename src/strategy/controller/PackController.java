package strategy.controller;

import history.Transaction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import strategy.logs.DatabaseLog;
import strategy.logs.PackLog;
import strategy.xml.Pack;
import strategy.xml.Strategy;
import strategy.xml.ZIP;

/**
 * Klasa zajmujaca sie obsluga paczki strategii
 * 
 * @author Katarzyna Kucharczyk
 * 
 */

public class PackController {

	String agent;
	private Pack pack;
	private List<ZIPController> ZIPControllerList;

	private ZIPController activeStrategy;

	private PackLog logs;

	/* czas w którym zmieniono strategie */
	Date date = new Date();
	private Timestamp changeDate;

	/* odlicza ilosc uzyc strategii */
	private int counter;
	/* ilosc uzycia kazdej strategii przed jej sprawdzeniem */
	private int amountOfUse = 50;
	DatabaseLog db;

	public PackController(Pack p, PackLog l, DatabaseLog db) {
		this.setPack(p);
		this.logs = l;
		this.db = db;

		date = new Date();
		changeDate = new Timestamp(date.getTime());

		agent = p.getAgent();
		ZIPControllerList = new ArrayList<ZIPController>();

		/* inicjalizacja logow dla kazdej strategii */
		for (Strategy s : p.getStrategy()) {


			/* na razie interesuja nas tylko ZIPy */
			if (s.getClass().equals(ZIP.class)) {
				this.logs.initializePackLog(s.getStrategyName());
				ZIPControllerList.add(new ZIPController((ZIP) s, this.logs));

			}
		}

		/* pierwsza strategia aktywn¹ strategia */
		if (ZIPControllerList.isEmpty() == false) {

			activeStrategy = ZIPControllerList.get(0);
		}

		counter = 0;

	}

	/**
	 * Dla agentow nie posiadajacych strategii
	 * 
	 * @param l
	 * @param db
	 */
	public PackController(PackLog l, DatabaseLog db) {
		this.logs = l;
		this.db = db;

	}

	/**
	 * Zwraca oferte jaka powinien zagrac uczestnik + jesli taka jest tura
	 * zmienia strategie
	 * 
	 * @param pur
	 *            - jaki cel ma uczestnik (sprzedaz/kupno)
	 * @param lastOffer
	 *            - ostatnia oferta na rynku
	 * @param limit
	 *            - limit
	 * @return
	 */
	public Offer start(purpose pur, double lastOffer, double limit) {
		this.counter++;

		Offer offer = activeStrategy.init(pur, lastOffer, limit);

		checkStrategy();

		return offer;
	}

	/**
	 * Zmiana strategii na kolejna lub jesli zmienia sie tura w grze to wyrzuca
	 * ta ktora miala najnizsze osiagniecia
	 */
	private void checkStrategy() {
		//System.out.println("Ilosc "+ZIPControllerList.size());
		if (ZIPControllerList.size() > 1) {

			if (counter / amountOfUse >= ZIPControllerList.size()) {
				System.out.println(agent + ": Kolejna tura!");
				rankAndChange();
				counter = 0;
			} else if (counter % amountOfUse == 0) {
				System.out.println(agent + ":Zmiana strategii");
				activeStrategy = ZIPControllerList.get(counter / amountOfUse);
				//logs.print();
			} 
		}

	}

	/**
	 * Sprawdza ranking i wyrzuca najgorsza strategie z puli
	 */
	private void rankAndChange() {

		Map<String, Double> rank = db.getRank(changeDate, agent,
				ZIPControllerList);

	 printRank(rank);
	 if (rank.isEmpty()){
		 System.out.println("Nie ma najgorszej");
	 } else {
			String s = findWorstStrategy(rank);

			for (ZIPController zc : ZIPControllerList) {
				if (zc.getStrategyId().equals(s)) {

					ZIPControllerList.remove(zc);
					break;
				}
			}		 
	 }


		activeStrategy = ZIPControllerList.get(0);

		
		date = new Date();
		changeDate = new Timestamp(date.getTime());
	}

	/**
	 * Wypisuje ranking
	 * 
	 * @param rank
	 */
	public void printRank(Map<String, Double> rank) {
		Iterator i = rank.entrySet().iterator();
		System.out.println(agent + ":Ranking strategii:");
		while (i.hasNext()) {
			Map.Entry pairs = (Map.Entry) i.next();
			System.out.println("\tStrategia " + pairs.getKey() + " ugrala "
					+ pairs.getValue());
		}
	}

	/**
	 * Metoda znajduje strategie ktora zwrocila oferty o najmniejszej sumie
	 * 
	 * @param rank
	 *            - ranking ofert (nazwa strategii, sumaryczna wartosc ofert)
	 * @return
	 */
	public String findWorstStrategy(Map<String, Double> rank) {
		Map.Entry<String, Double> minEntry = null;
		for (Map.Entry<String, Double> entry : rank.entrySet()) {

			if (minEntry == null
					|| entry.getValue().compareTo(minEntry.getValue()) >= 0) {
				minEntry = entry;
				//System.out.println(agent + ":Strat " + minEntry.getValue()
				//		+ " klucz: " + minEntry.getKey());
			}
		}

		return minEntry.getKey();
	}

	public void setAgentName(String agent) {
		this.agent = agent;
	}

	public String getAgentName() {
		return agent;
	}

	public Pack getPack() {
		return pack;
	}

	public void setPack(Pack pack) {
		this.pack = pack;
	}

	public void logDatabaseSell(Transaction trans, boolean hasStrategy,
			Double kosztZakupuSprzedazy, Offer offer, boolean hadStrategy, String sender, String conversationId, Double double1, String agent_name) {
		
		if (hasStrategy) {
			db.logSell(trans, activeStrategy.getStrategyId(), hasStrategy, kosztZakupuSprzedazy, offer, hadStrategy, sender,  conversationId, double1, agent_name);
		} else {
			db.logSell(trans, null, hasStrategy, kosztZakupuSprzedazy, null, hadStrategy, sender, conversationId, double1, agent_name);
		}
	}

	public void setConversationId(String conversationId, int useId) {
		db.logConversationId(conversationId, useId);		
	}

}
