/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours.ghgagent;

import agents.GHGTrader;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.JADEAgentManagement.ShutdownPlatform;
import jade.lang.acl.ACLMessage;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import m3objects.OfferComplexType;
import tradetypes.Auction;
import tradetypes.Negotiate;

/**
 *
 * @author power
 */
public class NotifyTheTrade extends TickerBehaviour{
    
    public Map<AID, OfferComplexType> map;
    
    public NotifyTheTrade(Agent a, long tick){
        super(a,tick);
    }

    @Override
    protected void onTick() {
        
        String cId = ((GHGTrader)myAgent).generateCID();
        Random r = new Random();
        GHGTrader ghgAgent = (GHGTrader)myAgent;
        
        if (ghgAgent.findAgents("GHG Trader").length == 1){
            ghgAgent.summary();
            ghgAgent.shutdown();
            ghgAgent.doDelete();
        }
        
        if (ghgAgent.terminate || ghgAgent.tList.getPriceDifferenceMovingAverage(10) < settings.Settings.priceAccuracy){
            // tutaj powinien być koniec...
            if (parent.getChildren().size() == 2){
                try {
                    if (!ghgAgent.deregistered){
                        DFService.deregister(ghgAgent);
                        ghgAgent.deregistered = true;
                        ghgAgent.summary();
                        ghgAgent.doDelete();
                    }
                } catch (FIPAException ex) {
                    Logger.getLogger(NotifyTheTrade.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ghgAgent.terminate = true;
            }
        }else{
        
            ACLMessage mess = new ACLMessage(ACLMessage.CFP);
            mess.setConversationId(cId);
            mess.setContent(((GHGTrader)myAgent).tradeType.name());

            switch (((GHGTrader)myAgent).tradeType){
                case NEGOTIATION : 
                    Negotiate tradeNeg = new Negotiate() ; 
                    tradeNeg.initiateTrade(mess, cId, ghgAgent, (ParallelBehaviour)parent); 
                    break;
                case AUCTION : 
                case AUCTION_SELL : 
                case AUCTION_BUY : 
                    Auction tradeAuct = new Auction(((GHGTrader)myAgent).tradeType.name()) ; 
                    tradeAuct.initiateTrade(mess, cId, ghgAgent, (ParallelBehaviour)parent); 
                    break;
                default : break;
            }
            myAgent.send(mess);
        }
    }
}
