package strategy.logs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import strategy.controller.purpose;
import strategy.controller.state;

/**
 * Paczka do logow dla kazdego z uzytkownikow
 */

public class PackLog {
	/** id i lista strategii */ 
	private HashMap<String, ArrayList<ConsoleStrategyLog>> logs = new HashMap<String, ArrayList<ConsoleStrategyLog>>();
	private DatabaseLog db;
	private String agentName;
	
	public PackLog(DatabaseLog db, String agent) {
		this.db = db; 
		this.agentName = agent;
	}

	public ArrayList<ConsoleStrategyLog> initializePackLog(String idStrategy){ 
		ArrayList<ConsoleStrategyLog> list = new ArrayList<ConsoleStrategyLog>();
		logs.put(idStrategy.toString(), list);
		return list;
	}

	public int log(String idStrategy, state state, purpose purpose, double margin, double offer, double limit, long l, double lastTransaction) {
		ArrayList<ConsoleStrategyLog> list = logs.get(idStrategy);
				
		ConsoleStrategyLog log = new ConsoleStrategyLog(margin,offer, state, purpose, limit, l, lastTransaction);
		list.add(log);
		return db.logUse(log, agentName, idStrategy);
	}
	
	/**
	 * Wypisanie zawartosci logow
	 */
	public void print(){
		Iterator i = logs.entrySet().iterator();
		while (i.hasNext()){
			Map.Entry<String, ArrayList<ConsoleStrategyLog>> pairs = (Map.Entry<String, ArrayList<ConsoleStrategyLog>>)i.next();
			System.out.println("\t@Strategia "+pairs.getKey());
			ArrayList<ConsoleStrategyLog> list = (ArrayList<ConsoleStrategyLog>)pairs.getValue();
			for(ConsoleStrategyLog l: list){
				l.print();
			}
		}
	}
	
	/**
	 * Wylicza sumaryczna wartosc zlozonych ofert w ramach kazdej ze strategii
	 * 
	 * @return ranking strategii z sumaryczna wartoscia zlozonych ofert
	 */
	public HashMap<String, Double> getRank() {
		
		
		
		Iterator i = logs.entrySet().iterator();
		HashMap<String, Double> rank = new HashMap<String, Double>();
		while (i.hasNext()){
			/*
			Map.Entry<String, ArrayList<ConsoleStrategyLog>> pairs = (Map.Entry<String, ArrayList<ConsoleStrategyLog>>)i.next();
			ArrayList<ConsoleStrategyLog> list = (ArrayList<ConsoleStrategyLog>)pairs.getValue();
			Double suma = 0.0;
			for(ConsoleStrategyLog l: list){
				suma = suma + l.getOffer();
			}
			rank.put(pairs.getKey(), suma);
			*/
		}
		return rank;
	}

	/**
	 * Metoda zapisujaca w bazie wylosowane pierwsze elementy
	 * 
	 * @param beta
	 * @param lambda
	 * @param margin
	 */
	public void logStrategyStarters(double beta, double lambda, double margin, String strategyName) {
		db.logStrategyStarters(beta, lambda, margin, strategyName);
		
	}
}
