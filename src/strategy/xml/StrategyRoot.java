package strategy.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StrategyRoot", propOrder = { "pack" })
@XmlRootElement(name = "StrategyRoot")
public class StrategyRoot {

	@XmlElement(name = "Pack", required = true)
	protected List<Pack> pack;

	public StrategyRoot() {
	}

	public List<Pack> getPack() {
		if (pack == null) {
			pack = new ArrayList<Pack>();
		}
		return this.pack;
	}

	public Pack getPackByAgent(String agent) {
		for (Pack p : pack) {
			System.out.println(p.getAgent());

		
			if (p.getAgent().equals(agent)) {
				return p;

			}
		}
		return null;
	}

	public boolean hasAgentStrategy(String agent) {
		for (Pack p : pack) {
			if (p.getAgent().equals(agent)) {
				return true;
			}
		}
		return false;
	}

}
