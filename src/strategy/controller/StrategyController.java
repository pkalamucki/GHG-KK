package strategy.controller;

import java.io.File;
import java.io.FileReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import strategy.logs.DatabaseLog;
import strategy.logs.PackLog;
import strategy.logs.StrategyLogsController;
import strategy.xml.Pack;
import strategy.xml.Strategy;
import strategy.xml.StrategyRoot;

/**
 * Kontroler calego modulu strategii STworzony raz zwraca kontroler paczki
 * strategii dla danego uzytkownika, tak ze uzytkownik odpytuje tylko kontroler
 * paczki o kolejna oferte jaka ma zlozyc
 * 
 * @author Katarzyna Kucharczyk
 * 
 */

public class StrategyController {

	static final private String urlFile = "utils/strategie.xml";
	StrategyRoot model;
	private StrategyLogsController logController;
	private DatabaseLog db;
	
	public StrategyController() {
		/** Dla bazy danych */
		db = new DatabaseLog();
		
		/** Dla logow */
		logController = new StrategyLogsController(db);
		
		init();
	}

	/**
	 * Funkcja do zczytania xml
	 */
	private void init() {
		try {
			File xmlStrategy = new File(urlFile);

			/* XML parser */
			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File("utils/schema.xsd"));

			JAXBContext jc = JAXBContext.newInstance(StrategyRoot.class);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setSchema(schema);
			model = (StrategyRoot) unmarshaller.unmarshal(new FileReader(
					xmlStrategy));

		} catch (Exception e) {
			e.printStackTrace();
		}
		updateDB();

	}

	/**
	 * updatuje baze gotowymi elementami 
	 */
	private void updateDB() {
		for(Pack p:model.getPack()){
			db.addUsers(p.getAgent());
			
			for(Strategy s:p.getStrategy()){
				db.addStrategy(s, p.getAgent());
			}
		}
		
		
	}
	
	
	/**
	 * Zwraca kontroler paczki strategii danemu uzytkownikowi
	 * 
	 * @param agent
	 *            - uzytkownik ktory chce swoja paczke strategii
	 * @return
	 */
	public PackController givePackControllerToAgent(String agent, boolean hasStrategy) {
		PackLog packlog = logController.initalizeLogForUser(agent);
		
		// dodanie agenta w razie gdyby nie zosta³ wczytany z konfiguracji
		db.addUsers(agent);
		if(hasStrategy){
			return new PackController(model.getPackByAgent(agent), packlog, db);
		} else {
			return new PackController(packlog, db);
		}
	}

	/**
	 * Wypisuje wszystkie logi
	 */
	public void printLogs() {
		logController.print();
	}

	/**
	 * Sprawdza czy dany agent posiada jak¹kolwiek strategie
	 * 
	 * @param agent
	 *            - uzytkownik dla ktorego jes sprawdzana strategia
	 * @return
	 * 
	 */
	public boolean hasStrategy(String agent) {
		return model.hasAgentStrategy(agent);
		
	}

}
