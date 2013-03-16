package test;

import history.Transaction;
import history.TransactionList;
import jade.Boot3;

import java.util.ArrayList;
import java.util.List;

import strategy.controller.StrategyController;

public class Start {
	

	public static List<Transaction> listaList;
	public static Transaction lastTrans = new Transaction();
	public static StrategyController controller = new StrategyController();
	
	public static void main(String args[]) {
		
		int all = 6;
		String[] param = new String[all];

		param[0] = "-gui";
		
		
		for (int i = 1; i < 6; i++) {
			param[i] = i-1 +":agents.GHGTrader";
		}
		
		listaList = new ArrayList<Transaction>();

		new Boot3(param);
		
	}
}
