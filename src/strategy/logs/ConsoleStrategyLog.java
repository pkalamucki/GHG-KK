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

public class ConsoleStrategyLog {


	protected double offer;
	protected double margin;
	protected state state;
	protected purpose purpose;
	protected Strategy strategy;
	protected Date time;
	protected double limit;
	protected double lastTransaction;
	
	
	public ConsoleStrategyLog(double margin, double offer, state state,
			purpose purpose, double limit, long l, double lastTransaction) {
		this.margin = margin;
		this.offer = offer;
		this.purpose = purpose;
		this.state = state;
		this.limit = limit;
		time = new Date(l);
		this.lastTransaction = lastTransaction;
	}


	public double getOffer() {
		Double o = new Double(offer);

        if(o.isInfinite() || o.isNaN()){
        	if (o < 0) {
        		o = Double.MIN_VALUE+1;
        		
        	} else {
        		o = Double.MAX_VALUE-1;
        		
        	}

        }

		return o;
	}

	public void setOffer(double bid) {
		Double o = new Double(bid);
        if(o.isNaN()){
        	o = Double.MAX_VALUE-1;

        }

		this.offer = o;
	}

	public double getMargin() {
		Double o = new Double(margin);

        if(o.isInfinite() || o.isNaN()){
        	if (o < 0) {
        		o = Double.MIN_VALUE+1;

        	} else {
        		o = Double.MAX_VALUE-1;

        	}

        }
		return o;
	}

	public void setMargin(double margin) {
		Double o = new Double(margin);
        if(o.isNaN()){
        	o = Double.MAX_VALUE-1;
        }
		this.margin = o;
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


	public double getLast() {
		
		return this.lastTransaction;
	}
}
