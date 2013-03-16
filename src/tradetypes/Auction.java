/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradetypes;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import m3objects.OfferComplexType;
import strategy.controller.StrategyController;
import agents.GHGTrader;
import agents.GHGTrader.TradeTypeEnum;
import behaviours.ghgagent.ContenerBehaviour;
import behaviours.ghgagent.newReceiverBehaviour;

/**
 *
 * @author power
 */
public class Auction extends Trade{
    private HashMap<AID, OfferComplexType> map;
    private final TradeTypeEnum tradeType;
    
    public Auction(String method){
        if (method.compareTo(GHGTrader.TradeTypeEnum.AUCTION.name()) == 0) this.tradeType = GHGTrader.TradeTypeEnum.AUCTION;
        else if (method.compareTo(GHGTrader.TradeTypeEnum.AUCTION_BUY.name()) == 0) this.tradeType = GHGTrader.TradeTypeEnum.AUCTION_BUY;
        else if (method.compareTo(GHGTrader.TradeTypeEnum.AUCTION_SELL.name()) == 0) this.tradeType = GHGTrader.TradeTypeEnum.AUCTION_SELL;
        else this.tradeType = GHGTrader.TradeTypeEnum.AUCTION;
    }

    @Override
    public void initiateTrade(ACLMessage message, String convId, GHGTrader agent, ParallelBehaviour parentBeh) {
        super.assignContener(agent, convId);
        
        AID[] aids = agent.findAgents("GHG Trader");
        if (aids.length > 1){
            for (AID aid : aids) if (!aid.equals(agent.getAID())) message.addReceiver(aid);

            this.map = new HashMap();

            ParallelBehaviour parBeh = new ParallelBehaviour(agent, ParallelBehaviour.WHEN_ALL);
            seqBeh.addSubBehaviour(parBeh);

            ((ParallelBehaviour)parentBeh).addSubBehaviour(seqBeh);

            MessageTemplate receiverTemplate = MessageTemplate.MatchConversationId(convId);

            for (AID aid : aids) if (!aid.equals(agent.getAID())){
                parBeh.addSubBehaviour(new newReceiverBehaviour(agent, 100, MessageTemplate.and(receiverTemplate, MessageTemplate.MatchSender(aid))){
                    @Override
                    public void handle(ACLMessage message){
                        try {
                            if (message.getContentObject() instanceof OfferComplexType){
                                OfferComplexType offer = (OfferComplexType) message.getContentObject();
                                map.put(message.getSender(), offer);
                            }
                        } catch (UnreadableException ex) {
                            Logger.getLogger(Auction.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }

            seqBeh.addSubBehaviour(new OneShotBehaviour(){
                @Override
                public void action() {
                    ContenerBehaviour seqBeh = (ContenerBehaviour)parent;
                    GHGTrader ghgAgent = (GHGTrader)myAgent;

                    Iterator iter = map.keySet().iterator();
                    double maxPrice = 1000000000;
                    double kosztAktualny = ghgAgent.getKosztRedukcjiEmisji();
                    double myPrice = ghgAgent.shadowPrice(0, -1);

                    AID winner = null;

                    while (iter.hasNext()){
                        AID aid = (AID)iter.next();

                        OfferComplexType offer = (OfferComplexType) map.get(aid);

                        double offerPrice  = offer.getOfferedPrice().doubleValue();
                        double offerVolume = offer.getVolumeRange().get(0).getMaxValue();
                        double offerShare  = offer.getElementaryOffer().getOfferedCommodity().getShareFactor();

                        // rzeczywisty wol dla operatora
                        double myOfferVolume = offerVolume;
                        double myOfferPrice = offerPrice;
                        double myOfferShareFactor = -offerShare;
                        
                        double kosztPo     = ghgAgent.obliczKosztRedukcji(myOfferVolume, -myOfferShareFactor);
                        double kosztZakupu = -myOfferShareFactor * myOfferPrice * myOfferVolume;

                        //ghgAgent.log("oferta " + aid.getLocalName() + ", cena = " + offerPrice + ", vol = " + offerVolume + ", share = " + offerShare, "test" );
                        //ghgAgent.log("koszty redukcji po transakcji = " + kosztPo + ", koszty zakupu/sprzedazy = " + kosztZakupu, "test");
                        //ghgAgent.log("koszty po łączne = " + (kosztPo + kosztZakupu) + " <> " + kosztAktualny, "test");

                        if (myOfferVolume > 0 && myOfferPrice < maxPrice && myOfferPrice < 100000 && myOfferPrice < myPrice)// && koszt_po + koszt_zakupu <= koszt_aktualny)
                        {
                            maxPrice = myOfferPrice;
                            winner = aid;
                        }
                    }

                    if (winner != null){
                        ACLMessage succ = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                        succ.addReceiver(winner);
                        succ.setConversationId(seqBeh.convId);
                        ghgAgent.send(succ);

                        OfferComplexType offer = map.get(winner);

                        seqBeh.setPrice(offer.getOfferedPrice().doubleValue());
                        seqBeh.setVolume(offer.getVolumeRange().get(0).getMaxValue());
                        seqBeh.setShareFactor(-offer.getElementaryOffer().getOfferedCommodity().getShareFactor());

                        ((GHGTrader)myAgent).countValues(seqBeh, winner);
                    }

                    ACLMessage fail = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                    fail.setConversationId(seqBeh.convId);
                    iter = map.keySet().iterator();
                    while(iter.hasNext()){
                        AID aid = (AID)iter.next();
                        if (!aid.equals(winner)) fail.addReceiver(aid);
                    }
                    ghgAgent.send(fail);
                }
            });
        }
    }

    @Override
    public void participateInTrade(AID sender, String convId, GHGTrader agent, ParallelBehaviour parentBeh) {
        Random r = new Random();
        Double volume = (r.nextInt(29) + 1)/10.0;
        Double shareFactor = 1.0;
        // dec == true - agent chce kupić uprawnienia, wolumen ma ujemną wartość
        // w przypadku tender - agent chce sprzedać uprawnienia - wolumen nie może być ujemny - zerujemy go
        boolean dec = (agent.F < agent.F0);
        if (r.nextInt(10) <= 3) dec = !dec;
        if (dec) {volume = 0.0; shareFactor *= -1;}

        Double price = Math.max(agent.shadowPrice(0.0, -shareFactor), r.nextDouble() * 30 + 85);
        
        price = modifyPrice(price, agent, shareFactor);
        volume = modifyVolume(volume, agent);
        
        agent.sendPropose(sender, convId, volume, price, shareFactor);
        
        seqBeh = new ContenerBehaviour();
        seqBeh.setAgent(agent);
        seqBeh.setConvId(convId);
        seqBeh.setShareFactor(shareFactor);
        seqBeh.setVolume(volume);
        seqBeh.setPrice(price);
        ((ParallelBehaviour)parentBeh).addSubBehaviour(seqBeh);
        
        MessageTemplate receiverTemplate = MessageTemplate.MatchConversationId(seqBeh.convId);

        // wait for specified time for answer
        seqBeh.addSubBehaviour(new newReceiverBehaviour(agent, 1000, receiverTemplate){
            @Override
            public void handle(ACLMessage message){
                if (message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                    // finalizuj
                    ((GHGTrader)myAgent).countValues((ContenerBehaviour)parent, message.getSender());
                }
            }
        });
    }    
}
