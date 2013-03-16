package strategy.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pack", propOrder = { "agent", "strategy" })
public class Pack {

	@XmlElement(name = "Agent", required = true)
	protected String agent;
	@XmlElement(name = "Strategy", required = true)
	protected List<Strategy> strategy;

	public String getAgent() {
		return agent;
	}

	public void setAgent(String value) {
		this.agent = value;
	}

	public List<Strategy> getStrategy() {
		if (strategy == null) {
			strategy = new ArrayList<Strategy>();
		}
		return this.strategy;
	}

}
