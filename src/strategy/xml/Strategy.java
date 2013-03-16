package strategy.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Strategy", propOrder = { "strategyName" })
@XmlSeeAlso({
    ZIP.class,
    ZIP60.class
})
public abstract class Strategy {
	@XmlElement(name = "strategyName", required = true)
	private String strategyName;
	public abstract double getPrice();

	public void print() {
		
	}

	public String getStrategyName() {
		
		return strategyName;
	}
	
	public String getType(){
		return "strategia";
	}

	public double[] getParams() {
		return null;
	}
}
