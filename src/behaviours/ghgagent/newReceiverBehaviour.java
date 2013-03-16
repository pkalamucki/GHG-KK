/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package behaviours.ghgagent;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;

public class newReceiverBehaviour extends SimpleBehaviour
{

    protected MessageTemplate template;
    protected long    timeOut,
	                wakeupTime;
    protected boolean finished;

    protected ACLMessage msg;

    public ACLMessage getMessage() { return msg; }


    public newReceiverBehaviour(Agent a, int millis, MessageTemplate mt) {
        super(a);
        timeOut = millis;
        template = mt;
    }

    @Override
	public void onStart() {
		wakeupTime = (timeOut<0 ? Long.MAX_VALUE
		              :System.currentTimeMillis() + timeOut);
	}

	public boolean done () {
		return finished;
	}

	public void action()
	{
        this.prepateTemplate();

		if(template == null) msg = myAgent.receive();
		else                 msg = myAgent.receive(template);

		if( msg != null) {
			finished = true;
			handle( msg );
			return;
		}
        long dt = wakeupTime - System.currentTimeMillis();

        if ( dt > 0 )
            block(dt);
        else {
			finished = true;
            if (msg != null) handle( msg );
        }
	}

	public void handle( ACLMessage m) { /* can be redefined in sub_class */ }

	public void reset() {
		msg = null;
		finished = false;
		super.reset();
  	}

	public void reset(int dt) {
		timeOut= dt;
		reset();
  	}

    public void prepateTemplate() {  /* can be redefined in sub_class */  }

}