/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours.ghgagent;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import tradetypes.Auction;
import tradetypes.Negotiate;
import agents.GHGTrader;

/**
 *
 * @author power
 */
public class WaitForNotification extends CyclicBehaviour{

    @Override
    public void action() {
        MessageTemplate mtINF = MessageTemplate.MatchPerformative(ACLMessage.CFP);
        ACLMessage msgINF = myAgent.receive(mtINF);
        
        if (msgINF != null){
            String content = msgINF.getContent();
            if (((GHGTrader)myAgent).terminate){
                //((GHGTrader)myAgent).removeBehaviour(this);
            }else{
                if (content.compareTo(GHGTrader.TradeTypeEnum.NEGOTIATION.name()) == 0) {
                    Negotiate tradeNeg = new Negotiate();
                    tradeNeg.participateInTrade(msgINF.getSender(), msgINF.getConversationId(), (GHGTrader)myAgent, (ParallelBehaviour)parent);

                } else if (content.compareTo(GHGTrader.TradeTypeEnum.AUCTION_BUY.name()) == 0 || 
                        content.compareTo(GHGTrader.TradeTypeEnum.AUCTION_SELL.name()) == 0 || 
                        content.compareTo(GHGTrader.TradeTypeEnum.AUCTION.name()) == 0) {
                    Auction tradeAuct = new Auction(content);
                    tradeAuct.participateInTrade(msgINF.getSender(), msgINF.getConversationId(), (GHGTrader)myAgent, (ParallelBehaviour)parent);
                }
            }
        } else block();
    }

}
