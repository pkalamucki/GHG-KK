package strategy.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZIP", propOrder = { "betaMin", "betaMax", "marginStartMin",
		"marginStartMax", "gammaMin", "gammaMax", "constraintA",
		"constraintB" })
public class ZIP extends Strategy {

	@XmlElement(name = "BetaMin", required = true)
	protected double betaMin;
	@XmlElement(name = "BetaMax", required = true)
	protected double betaMax;
	@XmlElement(name = "MarginStartMin", required = true)
	protected double marginStartMin;
	@XmlElement(name = "MarginStartMax", required = true)
	protected double marginStartMax;
	@XmlElement(name = "GammaMin", required = true)
	protected double gammaMin;
	@XmlElement(name = "GammaMax", required = true)
	protected double gammaMax;
	@XmlElement(name = "ConstraintA", required = true)
	protected double constraintA;
	@XmlElement(name = "ConstraintB", required = true)
	protected double constraintB;

	public double getBetaMin() {
		return betaMin;
	}

	public void setBetaMin(double betaMin) {
		this.betaMin = betaMin;
	}

	public double getBetaMax() {
		return betaMax;
	}

	public void setBetaMax(double betaMax) {
		this.betaMax = betaMax;
	}

	public double getMarginStartMin() {
		return marginStartMin;
	}

	public void setMarginStartMin(double marginStartMin) {
		this.marginStartMin = marginStartMin;
	}

	public double getMarginStartMax() {
		return marginStartMax;
	}

	public void setMarginStartMax(double marginStartMax) {
		this.marginStartMax = marginStartMax;
	}

	public double getGammaMin() {
		return gammaMin;
	}

	public void setGammaMin(double gammaMin) {
		this.gammaMin = gammaMin;
	}

	public double getGammaMax() {
		return gammaMax;
	}

	public void setGammaMax(double gammaMax) {
		this.gammaMax = gammaMax;
	}

	public double getConstraintA() {
		return constraintA;
	}

	public void setConstraintA(double constraintA) {
		this.constraintA = constraintA;
	}

	public double getConstraintB() {
		return constraintB;
	}

	public void setConstraintB(double constraintB) {
		this.constraintB = constraintB;
	}


	public double getBitPrice() {
		return 0.0;
	}

	public void runLearning() {

	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void print() {
		//System.out.println(getBetaMin());
		//System.out.println(getGammaMax());
	}
	
	@Override
	public String getType(){
		return "ZIP";
	
	}
	
	@Override
	public double[] getParams() {
		double[] params = {betaMin, betaMax, marginStartMin, marginStartMax, gammaMin, gammaMax, constraintA, constraintB};
		return params;
	}
}
