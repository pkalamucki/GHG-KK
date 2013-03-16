/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradetypes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import strategy.controller.StrategyController;
import umontreal.iro.lecuyer.randvar.ExponentialGen;
import umontreal.iro.lecuyer.rng.GenF2w32;
import agents.GHGTrader;
import behaviours.ghgagent.ContenerBehaviour;

/**
 *
 * @author power
 */
public abstract class Trade {
    protected ContenerBehaviour seqBeh;
    protected void assignContener(Agent agent, String convId){
        seqBeh = new ContenerBehaviour();
        seqBeh.setAgent(agent);
        seqBeh.setConvId(convId);
        seqBeh.setInitiator(true);
    }
    abstract public void initiateTrade(ACLMessage message, String convId, GHGTrader agent, ParallelBehaviour parentBeh);
    abstract public void participateInTrade(AID aid, String convId, GHGTrader agent, ParallelBehaviour parentBeh);
    
    final protected double modifyPrice(Double price, GHGTrader agent, Double shareFactor){
        Double beta = agent.tList.getPriceDifferenceMovingAverage(10)/100;
        
        ExponentialGen ed = new ExponentialGen(new GenF2w32(), 2);
        //LognormalGen ed = new LognormalGen(new GenF2w32(), 0, 1.5);
        
        double X = ed.nextDouble()*beta;

        if (shareFactor == 1) price *= X + 1;
        else                  price *= 1 - X;
        
        return price;
    }
    final protected double modifyVolume(Double volume, GHGTrader agent){
       Double pMean = agent.tList.getPriceDifferenceMovingAverage(25);
       Double vMean = agent.tList.getVolumeMovingAverage(10);
       
       if (Math.abs(vMean) < 0.5 && pMean < 2.5){
           volume *= pMean/25;
       }
       
       return volume;
    }
}
