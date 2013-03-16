/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package history;

import jade.core.AID;
import agents.GHGTrader;
import agents.GHGTrader.TradeTypeEnum;

/**
 * 
 * @author power
 */
public class Transaction {
	private double F;
	private double F0;
	private double K;
	private double transactionPrice;
	private double shadowPrice;
	private double volume;
	private double shareFactor;
	private double kosztRedukcji;
	private double kosztZakupu;
	private GHGTrader.TradeTypeEnum tradeType;
	private AID partner;
	private String thisAgent;

	public Transaction(double F, double F0, double K, double price,
			double shadowPrice, double volume, double shareFactor,
			double kosztZakupu, double kosztRedukcji, TradeTypeEnum tradeType,
			AID partner, String thisAgent) {
		this.F = F;
		this.F0 = F0;
		this.K = K;
		this.transactionPrice = price;
		this.shadowPrice = shadowPrice;
		this.volume = volume;
		this.shareFactor = shareFactor;
		this.kosztZakupu = kosztZakupu;
		this.kosztRedukcji = kosztRedukcji;
		this.tradeType = tradeType;
		this.partner = partner;
		this.setThisAgent(thisAgent);
	}

	public Transaction() {

	}

	public double getF() {
		return F;
	}

	public void setF(double F) {
		this.F = F;
	}

	public double getF0() {
		return F0;
	}

	public void setF0(double F0) {
		this.F0 = F0;
	}

	public double getK() {
		return K;
	}

	public void setK(double K) {
		this.K = K;
	}

	public double getKosztRedukcji() {
		return kosztRedukcji;
	}

	public void setKosztRedukcji(double kosztRedukcji) {
		this.kosztRedukcji = kosztRedukcji;
	}

	public double getKosztZakupu() {
		return kosztZakupu;
	}

	public void setKosztZakupu(double kosztZakupu) {
		this.kosztZakupu = kosztZakupu;
	}

	public AID getPartner() {
		return partner;
	}

	public void setPartner(AID partner) {
		this.partner = partner;
	}

	public double getShadowPrice() {
		return shadowPrice;
	}

	public void setShadowPrice(double shadowPrice) {
		this.shadowPrice = shadowPrice;
	}

	public double getTransactionPrice() {
		return transactionPrice;
	}

	public void setTransactionPrice(double transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	public double getShareFactor() {
		return shareFactor;
	}

	public void setShareFactor(double shareFactor) {
		this.shareFactor = shareFactor;
	}

	public TradeTypeEnum getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeTypeEnum tradeType) {
		this.tradeType = tradeType;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getThisAgent() {
		return thisAgent;
	}

	public void setThisAgent(String thisAgent) {
		this.thisAgent = thisAgent;
	}

}
