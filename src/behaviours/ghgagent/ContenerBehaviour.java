/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours.ghgagent;

import jade.core.behaviours.SequentialBehaviour;

/**
 *
 * @author power
 */
public class ContenerBehaviour extends SequentialBehaviour{
    public Double volume;
    public Double price;
    public Double shareFactor;
    public String convId;
    public boolean initiator;

    public boolean isInitiator() {
        return initiator;
    }

    public void setInitiator(boolean initiator) {
        this.initiator = initiator;
    }
    
    public String getConvId() {
        return convId;
    }

    public void setConvId(String convId) {
        this.convId = convId;
    }

    public Double getShareFactor() {
        return shareFactor;
    }

    public void setShareFactor(Double shareFactor) {
        this.shareFactor = shareFactor;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    
}
