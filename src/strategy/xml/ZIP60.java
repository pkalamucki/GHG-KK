package strategy.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZIP60", propOrder = { "parameter" })
public class ZIP60 extends Strategy {

	// private int counterT; // kwant czasu

	@XmlElement(name = "Parameter", required = true)
	protected List<String> parameter;

	public List<String> getParameter() {
		if (parameter == null) {
			parameter = new ArrayList<String>();
		}
		return this.parameter;
	}

	/*
	 * in time t
	 * 
	 * the trader i
	 * 
	 * limit price l (lambda - matrix) quote price p = l * (1 + u)
	 * 
	 * 
	 * u is profit margin for seller u is >= 0.0 for buyer u is [-1;0]
	 * 
	 * 
	 * update rule 1. after using margin once: u(t+1) =
	 */

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
		System.out.println(getParameter());
	}
	
	@Override
	public String getType(){
		return "ZIP60";
	
	}
}
