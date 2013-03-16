package strategy.logs;

import java.util.Date;

import strategy.controller.purpose;
import strategy.controller.state;
import strategy.xml.Strategy;

/**
 * Klasa do generowania pojedynczego loga z obrotu strategii
 * 
 * @author Kasia
 * 
 */

public class StrategyLog {


	protected double offer;
	protected double margin;
	protected state state;
	protected purpose purpose;
	protected Strategy strategy;
	protected Date time;
	protected double limit;
	
	
	public StrategyLog(double margin, double offer, state state,
			purpose purpose, double limit, long l) {
		this.margin = margin;
		this.offer = offer;
		this.purpose = purpose;
		this.state = state;
		this.limit = limit;
		time = new Date(l);
	}


	public double getOffer() {
		return offer;
	}

	public void setOffer(double bid) {
		this.offer = bid;
	}

	public double getMargin() {
		return margin;
	}

	public void setMargin(double margin) {
		this.margin = margin;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public state getState() {
		return state;
	}


	public void setState(state state) {
		this.state = state;
	}


	public purpose getPurpose() {
		return purpose;
	}


	public void setPurpose(purpose purpose) {
		this.purpose = purpose;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}

	public String getStrategyId(){
		return strategy.getStrategyName();
	}
		
	public void print(){
		System.out.println("\t\t->Cel " + this.purpose + " stan " + this.state + " oferta "+this.offer + " margin " + this.margin + " czas " + this.time);
	}


	public double getLimit() {
		
		return limit;
	}
}
