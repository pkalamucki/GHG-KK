package strategy.controller;

public class Offer {

	int useId;
	String agentName;

	double offerAmt;
	double margin;
	purpose agentPuprose;
	state agentState;

	public int getUseId() {
		return useId;
	}

	public void setUseId(int useId) {
		this.useId = useId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentId) {
		this.agentName = agentId;
	}

	public double getOfferAmt() {
		return offerAmt;
	}

	public void setOfferAmt(double offerAmt) {
        if(offerAmt == Double.NaN){
        	offerAmt = Double.MAX_VALUE;
        }
		this.offerAmt = offerAmt;
	}

	public double getMargin() {
		return margin;
	}

	public void setMargin(double margin) {
		this.margin = margin;
	}

	public purpose getAgentPuprose() {
		return agentPuprose;
	}

	public void setAgentPuprose(purpose agentPuprose) {
		this.agentPuprose = agentPuprose;
	}

	public state getAgentState() {
		return agentState;
	}

	public void setAgentState(state agentState) {
		this.agentState = agentState;
	}
}
