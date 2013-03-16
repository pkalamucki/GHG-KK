package behaviours.ghgagent;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GCAgent extends TickerBehaviour
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Set seen = new HashSet(),
       old  = new HashSet();
   
   public GCAgent( Agent a, long dt) { super(a,dt); }
   
   protected void onTick() 
   {
      ACLMessage msg = myAgent.receive();
      while (msg != null) {
         if (! old.contains(msg) && msg.getPerformative() != ACLMessage.CFP)
            seen.add( msg);
         else {
           // System.out.print("+++ Flushing message: ");
           // dumpMessage(msg);
         }
         msg = myAgent.receive();
      }
      for( Iterator it = seen.iterator(); it.hasNext(); ){
    	   myAgent.putBack( (ACLMessage) it.next() );
      }
      
         
      old.clear();
      Set tmp = old;
      old = seen;
      seen = tmp; 
   }
   
   void dumpMessage( ACLMessage msg )
	{
		System.out.println( 
					myAgent.getLocalName() + " gets "
					+ ACLMessage.getPerformative(msg.getPerformative() )
					+ " from "
					+  msg.getSender().getLocalName()
					+ ", cid=" + msg.getConversationId());
	}
}