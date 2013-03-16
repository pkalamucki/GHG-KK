/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradetypes;

import jade.core.AID;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import m3objects.OfferComplexType;
import strategy.controller.Offer;
import umontreal.iro.lecuyer.randvar.ExponentialGen;
import umontreal.iro.lecuyer.rng.GenF2w32;
import agents.GHGTrader;
import behaviours.ghgagent.ContenerBehaviour;
import behaviours.ghgagent.NotifyTheTrade;
import behaviours.ghgagent.newReceiverBehaviour;

/**
 *
 * @author power
 */
public class Negotiate extends Trade{

    @Override
    public void initiateTrade(ACLMessage message, String convId, GHGTrader agent, ParallelBehaviour parentBeh) {
        super.assignContener(agent, convId);
        
        Random r = new Random();
        AID[] aids = agent.findAgents("GHG Trader");
        
        AID aid = null;
        do {aid = aids[r.nextInt(aids.length)];} while (aid.equals(agent.getAID()));
        message.addReceiver(aid);

        parentBeh.addSubBehaviour(seqBeh);

        MessageTemplate receiverTemplate = MessageTemplate.MatchConversationId(convId);

        // wait for specified time for answer
        seqBeh.addSubBehaviour(new newReceiverBehaviour(agent, 1000, receiverTemplate){
            @Override
            public void handle(ACLMessage message){
                try {
                    //((GHGTrader)myAgent).log("Initiate otrzymano odpowiedz od " + message.getSender().getLocalName());
                    // sprawdź jaka jest performatywa
                    if (message.getPerformative() == ACLMessage.PROPOSE){
                        OfferComplexType off = (OfferComplexType) message.getContentObject();
                        ((GHGTrader)myAgent).answerForNegotiation(off, message.getSender(), message.getConversationId(), (ContenerBehaviour)parent);

                    } else if (message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                        // finalizuj
                        // wypisz logi
              
                        ((GHGTrader)myAgent).countValues((ContenerBehaviour)parent, message.getSender(), true, message.getConversationId());
                    } else if (message.getPerformative() == ACLMessage.REJECT_PROPOSAL){
                        // finalizuj
                    }

                } catch (UnreadableException ex) {
                    Logger.getLogger(Negotiate.class.getName()).log(Level.SEVERE, null, ex);
                }                    
            }
        });
    }

    @Override
    public void participateInTrade(AID sender, String convId, GHGTrader agent, ParallelBehaviour parentBeh) {
        
        Random r = new Random();
        Double volume = (r.nextInt(29) + 1)/10.0;
        Double shareFactor = 1.0;
        // dec == true - agent chce kupić uprawnienia, wolumen ma ujemną wartość
        boolean dec = (agent.F < agent.F0);
        if (r.nextInt(10) <= 3) dec = !dec;
        if (dec) shareFactor = -1.0;

        Offer offer;
        double price;
        
        if(agent.hasStrategy()){
        	
        	double shadow = agent.shadowPrice(0.0, -shareFactor);
        	
        	offer = agent.getOffer( shadow, -shareFactor);
        	
        	price = offer.getOfferAmt();
        	
        } else {
        
        	price = Math.max(agent.shadowPrice(0.0, -shareFactor), r.nextDouble() * 30 + 85);
        	ExponentialGen ed = new ExponentialGen(new GenF2w32(), 2);
        	
                   	
        	
        }
        
        price = modifyPrice(price, agent, shareFactor);
        volume = modifyVolume(volume, agent);
        
		Double o = new Double(price);

        if(o.isInfinite() || o.isNaN()){
        	if (o < 0) {
        		price = Double.MIN_VALUE+1;
        		
        	} else {
        		price = Double.MAX_VALUE-1;
      
        	}
        	
        }
        
        seqBeh = new ContenerBehaviour();
        seqBeh.setAgent(agent);
        seqBeh.setConvId(convId);
        seqBeh.setShareFactor(shareFactor);
        seqBeh.setVolume(volume);
        seqBeh.setPrice(price);
        
    
        ((ParallelBehaviour)parentBeh).addSubBehaviour(seqBeh);

        agent.sendPropose(sender, convId, volume, price, shareFactor);

        MessageTemplate receiverTemplate = MessageTemplate.MatchConversationId(seqBeh.convId);

        // wait for specified time for answer
        seqBeh.addSubBehaviour(new newReceiverBehaviour(agent, 1000, receiverTemplate){
            @Override
            public void handle(ACLMessage message){
                try {
                    //((GHGTrader)myAgent).log("Participate otrzymano odpowiedź od " + message.getSender().getLocalName());
                    // sprawdź jaka jest performatywa
                    if (message.getPerformative() == ACLMessage.PROPOSE){
                        OfferComplexType off = (OfferComplexType) message.getContentObject();
                        ((GHGTrader)myAgent).answerForNegotiation(off, message.getSender(), message.getConversationId(), (ContenerBehaviour)parent);

                    } else if (message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                        // finalizuj
                        ((GHGTrader)myAgent).countValues((ContenerBehaviour)parent, message.getSender(), true, message.getConversationId());

                        
                    } else if (message.getPerformative() == ACLMessage.REJECT_PROPOSAL){
                        // finalizuj
                    }

                } catch (UnreadableException ex) {
                    Logger.getLogger(NotifyTheTrade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    
}
