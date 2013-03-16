package strategy.logs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Kontroler zajmujacy sie wszystkimi paczkami logow dla kazdego z uzytkownikow.
 * Klasa odpowiadajaca za tworzenie statystyk, wykresow itp
 */

public class StrategyLogsController {

	/** uczestnik - Paczka logow (dla paczki strategii) */
	private HashMap<String, PackLog> allLogs = new HashMap<String, PackLog>();

	private DatabaseLog db;
	
	
	public StrategyLogsController(DatabaseLog db) {
		this.db = db; 
	}

	public PackLog initalizeLogForUser(String agent) {
		PackLog logs = new PackLog(db, agent);
		
		allLogs.put(agent, logs);
		return logs;
	}
	
	public void print(){
		Iterator i = allLogs.entrySet().iterator();
		
		while (i.hasNext()){
			Map.Entry pairs = (Map.Entry)i.next();
			System.out.println("Uczestnik " + pairs.getKey());
			PackLog pl = (PackLog)pairs.getValue();
			pl.print();
		}
	}
}
