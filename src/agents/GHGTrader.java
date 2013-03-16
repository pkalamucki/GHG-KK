/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import history.Transaction;
import history.TransactionList;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.JADEAgentManagement.ShutdownPlatform;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import m3objects.OfferComplexType;
import m3objects.OfferComplexType.VolumeRange;
import m3objects.OfferedCommodity;
import strategy.controller.Offer;
import strategy.controller.PackController;
import strategy.controller.purpose;
import test.Start;
import behaviours.ghgagent.ContenerBehaviour;
import behaviours.ghgagent.GCAgent;
import behaviours.ghgagent.NotifyTheTrade;
import behaviours.ghgagent.WaitForNotification;

/**
 * 
 * @author power
 */
public class GHGTrader extends Agent {
	private ParallelBehaviour parBeh;
	public TransactionList tList;

	public double F; // emisja biezaca
	public double F0; // emisja startowa
	public double K; // limit Kioto
	public double a; // wspoczynnik kosztu redukcji emisjii
	private double ug;
	private double ud;
	private double alpha;
	private int no;
	private double u;
	private double Freal;
	private double F0real;
	public String name;

	private double kosztZakupuPozwolen;
	private double kosztRedukcjiEmisji;
	private double poczKosztRedukcji;

	public boolean terminate;
	public boolean deregistered;

	static public enum TradeTypeEnum {
		NEGOTIATION, AUCTION_SELL, AUCTION_BUY, AUCTION
	};

	static public enum LogLevelEnum {
		PLOT, SUMMARY
	};

	transient protected GHGTraderGui myGui;

	public TradeTypeEnum tradeType = TradeTypeEnum.NEGOTIATION;
	public LogLevelEnum logLevel = LogLevelEnum.PLOT;

	/* dla strategii */
	/* ostatnia transakcja */
	private Transaction lastTranaction;
	/* kontroler paczki strategii */
	protected PackController packController;
	protected boolean hasStrategy;
	/* Oferta pochodzaca ze strategii */
	private Offer offer;

	@Override
	protected void setup() {
		this.DFregister();
		this.tList = new TransactionList();

		this.terminate = false;
		this.deregistered = false;

		// ustawienia
		no = Integer.parseInt(getAID().getLocalName());
		this.K = settings.Settings.emissionLimits[no];
		this.F0 = settings.Settings.baseEmissions[no];
		this.a = settings.Settings.costParams[no];
		this.alpha = 0.5;

		this.u = settings.Settings.uncert[no];
		this.ud = settings.Settings.uncertd[no];
		this.ug = settings.Settings.uncertu[no];
		this.F = this.K;
		this.name = settings.Settings.names[no];
		this.hasStrategy = false;

		/* ustawienie paczki strategii */
		if (Start.controller.hasStrategy(this.name)) {
			this.hasStrategy = true;
		}

		packController = Start.controller.givePackControllerToAgent(this.name,
				this.hasStrategy);
		this.offer = new Offer();
		this.offer.setAgentName(name);

		this.Freal = this.F;
		this.F0real = this.F0;
		this.F /= (1 + this.u());

		this.kosztRedukcjiEmisji = this.obliczKosztRedukcji(0, 0);
		this.kosztZakupuPozwolen = 0.0;
		this.poczKosztRedukcji = this.kosztRedukcjiEmisji;

		this.logujTransakcje("Beginning ... ", 0.0, 0.0, "begining");

		parBeh = new ParallelBehaviour(this, ParallelBehaviour.WHEN_ALL);
		parBeh.addSubBehaviour(new WaitForNotification());
		parBeh.addSubBehaviour(new NotifyTheTrade(this, 75));
		
		parBeh.addSubBehaviour(new GCAgent(this, 15000));
		 
		
		this.addBehaviour(parBeh);

		// Instanciate the gui
		// myGui = new GHGTraderGui(this);
		// myGui.pack();
		// myGui.setVisible(true);

	}

	private void DFregister() {
		DFAgentDescription agentDesc = new DFAgentDescription();
		agentDesc.setName(this.getAID());

		ServiceDescription serviceDesc = new ServiceDescription();
		serviceDesc.setType("AGENT_TYPE");
		serviceDesc.setName("GHG trader");
		agentDesc.addServices(serviceDesc);

		try {
			DFService.register(this, agentDesc);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}

	/**
	 * Method searches for the agent of the specified name registered on the DF
	 * 
	 * @param name
	 *            search for specified name on the DF
	 * @return array of AIDs of the found agents, null when no agent found
	 */
	public AID[] findAgents(String name) {
		AID[] aids = null;
		int agentsNo = 0;

		DFAgentDescription agentDesc = new DFAgentDescription();

		ServiceDescription agentsDesc = new ServiceDescription();
		agentsDesc.setType("AGENT_TYPE");
		agentsDesc.setName(name);
		agentDesc.addServices(agentsDesc);

		try {
			DFAgentDescription[] result = DFService.search(this, agentDesc);
			if (result != null) {
				agentsNo = result.length;
				if (agentsNo != 0) {
					aids = new AID[agentsNo];
					for (int i = 0; i < agentsNo; ++i) {
						aids[i] = result[i].getName();
					}
				}
			}
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
		return aids;
	}

	/**
	 * generates unique conversation id
	 * 
	 * @return generated unique conversation id
	 */
	public String generateCID() {
		return this.getLocalName() + hashCode() + System.currentTimeMillis()
				% 10000 + "_";
	}

	public double u() {
		double du = this.F * this.ug;
		double dl = this.F * this.ud;
		double rv = (1.0 - this.alpha * (1 + dl / du)) * du / this.F;
		return rv;
	}

	public double u(int no) {
		double du = this.F * settings.Settings.uncertu[no];
		double dl = this.F * settings.Settings.uncertd[no];

		double rv = (1.0 - 2 * this.alpha) * settings.Settings.uncert[no];
		return rv;
	}

	/**
	 * @param volume
	 * @param shareFactor
	 * @return obliczamy ile teoretycznie b�dziemy musieli zap�aci� po
	 *         redukcji/zwi�kszeniu emisji o warto�� volume dla
	 *         shareFactor = 1 - sprzedajemy pozwolenia -> musimy zwi�kszy�
	 *         emisj� dla shareFactor =-1 - kupujemy pozwolenia -> zmniejszamy
	 *         emisj�
	 */
	public double obliczKosztRedukcji(double deltaVol, double shareFactor) {

		// założenie - liczymy koszty na bazie emisji efektywnych
		// musimy więc najpierw powrócić do emisjii skorygowanych
		// jeśli shareFactor = 1 - sprzedawca
		// jeśli shareFactor = -1 - nabywca
		double delta = this.F0 - this.F + shareFactor * deltaVol;
		delta /= 1 - shareFactor * this.u();

		double rv = this.a * Math.pow(delta, 2);
		if (this.F > this.F0)
			rv = 0.0;
		return rv;

		// double kosztRedukcjiEmisji = this.costParameter *
		// Math.pow(this.emission + shareFactor * volume - this.emissionLimit,
		// 2);
		// if (this.emission + shareFactor * volume < this.emissionLimit)
		// kosztRedukcjiEmisji = 0.0;
		// return kosztRedukcjiEmisji;

	}

	public void ustawBiezacaEmisje(double vol) {
		this.F = vol;
	}

	/**
	 * Obliczamy koszty redukcji emisji/teoretyczne koszty redukcji emisjii przy
	 * zmianie o deltaVol
	 * 
	 * @param deltaVol
	 *            - zmiana wolumenu
	 * @param shareFactor
	 *            - +1 gdy emisja rosnie, -1 gdy emisja maleje
	 * @return
	 */
	public double emissionReductionPrice(double deltaVol, double shareFactor) {
		// założenie - liczymy koszty na bazie emisji efektywnych
		// musimy więc najpierw powrócić do emisjii skorygowanych
		// jeśli shareFactor = 1 - sprzedawca
		// jeśli shareFactor = -1 - nabywca
		double delta = this.F0 - this.F + shareFactor * deltaVol;
		delta /= 1 - shareFactor * this.u();

		double rv = this.a * Math.pow(delta, 2);
		if (this.F > this.F0)
			rv = 0.0;
		rv = Math.abs(rv * 1000) / 1000;

		// nie wracamy do wartości efektywnych - liczymy prawdziwe koszty
		return rv;
	}

	public double shadowPrice(double deltaVol, double shareFactor) {
		// założenie - liczymy cenę na bazie emisji efektywnych,
		// musimy więc najpierw powrócić do emisjii skorygowanych
		// jeśli shareFactor = 1 - sprzedawca
		// jeśli shareFactor = -1 - nabywca
		double delta = this.F0 - this.F;
		delta /= 1 - shareFactor * this.u();

		double rv = 2 * this.a * (delta + shareFactor * deltaVol);
		if (this.F > this.F0)
			rv = 0.0;
		rv = Math.abs(rv * 1000) / 1000;

		// obliczamy tu cene efektywna, wiec musimy przeliczyć
		return rv * (1 - shareFactor * u());

		// PS: tak naprawde to mozna by było to zostawić, ale niech będzie
		// pro forma
	}

	public boolean sprawdzOplacalnoscWystawionejOferty(double price,
			double volume, double shareFactor) {
		Double kosztRedukcji = this.obliczKosztRedukcji(volume, shareFactor);
		Double kosztZakupuSprzedazy = -shareFactor * price * volume;
		return kosztRedukcji + kosztZakupuSprzedazy - this.kosztRedukcjiEmisji < 0;
	}

	public boolean sprawdzOplacalnosc(double price, double volume,
			double shareFactor) {
		double myPrice = this.shadowPrice(0, -shareFactor);
		return shareFactor * myPrice > shareFactor * price;
	}

	public void sendPropose(AID sender, String conversationId, Double volume,
			Double price, Double shareFactor) {

		VolumeRange volRange = new VolumeRange();
		volRange.setMaxValue(volume);

		OfferComplexType.ElementaryOffer elOff = new OfferComplexType.ElementaryOffer();
		OfferedCommodity ofComm = new OfferedCommodity();
		ofComm.setShareFactor(shareFactor);
		elOff.setOfferedCommodity(ofComm);

		OfferComplexType offer = new OfferComplexType();

		offer.setOfferedPrice(BigDecimal.valueOf(price));
		offer.getVolumeRange().add(volRange);
		offer.setElementaryOffer(elOff);

		ACLMessage mess = new ACLMessage(ACLMessage.PROPOSE);
		mess.setConversationId(conversationId);
		mess.addReceiver(sender);
		packController.setConversationId(mess.getConversationId(),
				this.offer.getUseId());
		try {
			mess.setContentObject(offer);
		} catch (IOException ex) {
			Logger.getLogger(GHGTrader.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		send(mess);
		// this.log("wysłano propozycję negocjacyjną do agenta " + sender);
	}

	public void answerForNegotiation(OfferComplexType offer, AID sender,
			String convId, ContenerBehaviour seqBeh) {
		// to są otrzymane dane
		Double offVolume = offer.getVolumeRange().get(0).getMaxValue();
		Double offPrice = offer.getOfferedPrice().doubleValue();
		Double offShareFactor = offer.getElementaryOffer()
				.getOfferedCommodity().getShareFactor();

		Double myShareFactor = -offShareFactor;
		Double myVolume = offVolume;

		seqBeh.setPrice(offPrice);
		seqBeh.setVolume(myVolume);
		seqBeh.setShareFactor(myShareFactor);

		// najpierw sprawdź, czy oferta jest korzystna
		if (this.sprawdzOplacalnosc(offPrice, offVolume, offShareFactor)) {
			Double myPrice = offPrice;

			// zakończ
			ACLMessage mess = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
			mess.setConversationId(convId);
			mess.addReceiver(sender);
			send(mess);

			// wypisz logi
			this.countValues(seqBeh, sender, false, mess.getConversationId());
		} else {
			// sprawdź czy oferta jest w ogóle opłacalna... jeśli nie,
			// odrzuć ją zupełnie
			// jeśli tak, kontynuuj
			ACLMessage mess = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
			mess.setConversationId(convId);
			mess.addReceiver(sender);
			send(mess);

			// wypisz logi
		}
	}

	/**
	 * przeliczenie wartosci po zakonczeniu transakcji oraz dodanie tranzakcji
	 * do logow transakcji
	 */
	public void countValues(ContenerBehaviour seqBeh, AID sender) {
		double shadowPrice = this.shadowPrice(0, -seqBeh.getShareFactor());

		seqBeh.setVolume(seqBeh.getVolume() * seqBeh.getShareFactor());

		if (seqBeh.isInitiator())
			seqBeh.setVolume(seqBeh.getVolume() * (1 - this.u()));

		this.kosztRedukcjiEmisji = this.obliczKosztRedukcji(seqBeh.getVolume(),
				1);
		this.kosztZakupuPozwolen += -seqBeh.getVolume() * seqBeh.getPrice();
		this.ustawBiezacaEmisje(this.F - seqBeh.getVolume() / (1 - this.u()));


		if (seqBeh.isInitiator())
			this.Freal = this.Freal - seqBeh.getVolume() / 1 + this.u();
		else
			this.Freal = this.Freal - seqBeh.getVolume()
					/ (1 + this.u(Integer.parseInt(sender.getLocalName())));

		Transaction trans = new Transaction(F, F0, K, seqBeh.getPrice(),
				shadowPrice, seqBeh.getVolume(), seqBeh.getShareFactor(),
				kosztRedukcjiEmisji, kosztZakupuPozwolen, tradeType, sender,
				name);

		this.tList.add(trans);
		this.setLastTrans(trans);

		//packController.logDatabaseSell(trans, this.hasStrategy, kosztZakupuSprzedazy, this.offer);

		this.logujTransakcje(
				((seqBeh.getShareFactor() == -1) ? "kupno emisji od "
						: "sprzedaz emisji dla ")
						+ settings.Settings.names[Integer.parseInt(sender
								.getLocalName())], seqBeh.getVolume(), seqBeh
						.getPrice(), seqBeh.convId);
		offer.setOfferAmt(-1);
	}

	public void countValues(ContenerBehaviour seqBeh, AID sender,
			boolean hadStrategy, String conversationId) {
		
		double shadowPrice = this.shadowPrice(0, -seqBeh.getShareFactor());

		seqBeh.setVolume(seqBeh.getVolume() * seqBeh.getShareFactor());

		if (seqBeh.isInitiator())
			seqBeh.setVolume(seqBeh.getVolume() * (1 - this.u()));

		this.kosztRedukcjiEmisji = this.obliczKosztRedukcji(seqBeh.getVolume(),
				1);
		this.kosztZakupuPozwolen += -seqBeh.getVolume() * seqBeh.getPrice();
		this.ustawBiezacaEmisje(this.F - seqBeh.getVolume() / (1 - this.u()));

		Double kosztZakupuSprzedazy = -seqBeh.getVolume() * seqBeh.getPrice();

		if (seqBeh.isInitiator())
			this.Freal = this.Freal - seqBeh.getVolume() / 1 + this.u();
		else
			this.Freal = this.Freal - seqBeh.getVolume()
					/ (1 + this.u(Integer.parseInt(sender.getLocalName())));

		Transaction trans = new Transaction(F, F0, K, seqBeh.getPrice(),
				shadowPrice, seqBeh.getVolume(), seqBeh.getShareFactor(),
				kosztRedukcjiEmisji, kosztZakupuPozwolen, tradeType, sender,
				name);

		this.tList.add(trans);
		this.setLastTrans(trans);

		Start.listaList.add(trans);

		String agent_sender = settings.Settings.names[Integer.parseInt(sender
				.getLocalName())];

		packController.logDatabaseSell(trans, this.hasStrategy,
				kosztZakupuSprzedazy, this.offer, hadStrategy,
				agent_sender, conversationId, seqBeh.getShareFactor(), settings.Settings.names[Integer.parseInt(this.getLocalName())]);

		
		this.logujTransakcje(
				((seqBeh.getShareFactor() == -1) ? "kupno emisji od "
						: "sprzedaz emisji dla ")
						+ settings.Settings.names[Integer.parseInt(sender
								.getLocalName())], seqBeh.getVolume(), seqBeh
						.getPrice(), seqBeh.convId);
		offer.setOfferAmt(-1);
	}

	private synchronized void setLastTrans(Transaction trans) {
		lastTranaction = trans;
	}

	private synchronized Transaction getLastTrans() {

		if (!Start.listaList.isEmpty()) {
			return Start.listaList.get(Start.listaList.size() - 1);
		} else {
			return null;
		}
	}

	public double getKosztRedukcjiEmisji() {
		return kosztRedukcjiEmisji;
	}

	public void setKosztRedukcjiEmisji(double kosztRedukcjiEmisji) {
		this.kosztRedukcjiEmisji = kosztRedukcjiEmisji;
	}

	public double getKosztZakupuPozwolen() {
		return kosztZakupuPozwolen;
	}

	public void setKosztZakupuPozwolen(double kosztZakupuPozwolen) {
		this.kosztZakupuPozwolen = kosztZakupuPozwolen;
	}

	public double getPoczKosztRedukcji() {
		return poczKosztRedukcji;
	}

	public void setPoczKosztRedukcji(double poczKosztRedukcji) {
		this.poczKosztRedukcji = poczKosztRedukcji;
	}

	public void log(String msg, String cId) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd-hh.mm.ss.SSS");
		String timeSuffix = formatter.format(new Date());

		
		 System.out.println(timeSuffix + " LOG , " + this.name + ", [" + cId +
		 "] ," + msg);
	}

	public void log(String log) {
		System.out.println(log);
	}

	public void summary() {
		if (this.logLevel == LogLevelEnum.SUMMARY)
			this.log(this.tList.summary(this), "summary");
	}

	void logujTransakcje(String agentTypeText, Double volume, Double price,
			String cId) {
		if (this.logLevel == LogLevelEnum.PLOT) {
			String loguj = agentTypeText + ", ";

			loguj += Math.round((this.F) * 1000000) / 1000000.0 + ", ";
			loguj += Math.round((this.F0) * 1000000) / 1000000.0 + ", ";
			loguj += (this.K) + ", ";
			loguj += Math.round((volume) * 100000000) / 100000000.0 + ", ";
			loguj += Math.round((this.kosztRedukcjiEmisji) * 1000000)
					/ 1000000.0 + ", ";
			loguj += Math.round((this.kosztZakupuPozwolen) * 1000000)
					/ 1000000.0 + ", ";
			loguj += Math.round((price) * 1000000) / 1000000.0 + ", ";
			loguj += Math.round(this.shadowPrice(0, 1) * 1000000) / 1000000.0
					+ ", ";
			loguj += Math.round((this.Freal) * 10000000) / 10000000.0 + ", ";

			loguj += Math.round((this.u()) * 10000000) / 10000000.0 + ", ";

			this.log(loguj, cId);
		}
	}

	public void shutdown() {
		Codec codec = new SLCodec();
		Ontology jmo = JADEManagementOntology.getInstance();
		this.getContentManager().registerLanguage(codec);
		this.getContentManager().registerOntology(jmo);
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.addReceiver(this.getAMS());
		msg.setLanguage(codec.getName());
		msg.setOntology(jmo.getName());
		try {
			this.getContentManager().fillContent(msg,
					new Action(this.getAID(), new ShutdownPlatform()));
			this.send(msg);
		} catch (Exception e) {
		}
	}

	public PackController getStrategyController() {
		return packController;
	}

	public Offer getOffer(double limit, double shareFactor) {
		double last;
		Random r = new Random();
		if (getLastTrans() == null) {
			last = 0;
		} else {
			last = getLastTrans().getShadowPrice();
		}

		if (shareFactor == -1.0) {
			// kupno pozwolen
			this.offer = packController.start(purpose.BUYER, last, limit);

		} else if (shareFactor == 1.0) {
			// sprzedaz pozwolen

			this.offer = packController.start(purpose.SELLER, last, limit);

		}

		offer.setAgentName(settings.Settings.names[Integer.parseInt(this
				.getLocalName())]);

		return this.offer;
	}

	public boolean hasStrategy() {
		return this.hasStrategy;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
}