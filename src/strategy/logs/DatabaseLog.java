package strategy.logs;

import history.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import strategy.controller.Offer;
import strategy.controller.ZIPController;
import strategy.controller.purpose;
import strategy.xml.Strategy;

public class DatabaseLog {

	private Connection conn;

	public DatabaseLog() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		connect();

		clearDB();
	}

	public void connect() {
		String url = "jdbc:mysql://localhost:3306/GHG";
		Properties props = new Properties();
		props.setProperty("user", "GHG-user");
		props.setProperty("password", "strategy");

		try {
			conn = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void clearDB() {
		Statement st;
		try {
			st = conn.createStatement();

			st.executeUpdate("DELETE FROM agent");

			st.executeUpdate("DELETE FROM strategy");

			st.executeUpdate("DELETE FROM sell");

			st.executeUpdate("DELETE FROM strategy_use");

			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addUsers(String name) {

		Statement st;
		ResultSet rs;
		try {
			st = conn.createStatement();

			rs = st.executeQuery("SELECT * FROM agent WHERE agent_name = '"
					+ name + "'");

			if (!rs.next()) {
				PreparedStatement st1 = conn
						.prepareStatement("INSERT INTO agent (agent_name) VALUES ('"
								+ name + "')");
				st1.execute();
				st1.close();
			}

			rs.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addStrategy(Strategy s, String agent) {
		Statement st;
		ResultSet rs;
		try {
			st = conn.createStatement();

			rs = st.executeQuery("SELECT * FROM strategy WHERE strategy_name = '"
					+ s.getStrategyName() + "'");

			if (!rs.next()) {
				String params = "";
				for (double p : s.getParams()) {
					params = params + "'" + ",'" + String.valueOf(p);

				}
				PreparedStatement st1 = conn
						.prepareStatement("INSERT INTO strategy (strategy_name, strategy_type, beta_min, beta_max, margin_min, margin_max, gamma_min, gamma_max, constraint_A, constraint_B, owner) VALUES (\'"
								+ s.getStrategyName()
								+ "\',\'"
								+ s.getType()
								+ params
								+  "\',\'"
								+ agent
								+ "\')");
				st1.execute();
				st1.close();
			}

			rs.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int logUse(ConsoleStrategyLog log, String agentName, String strategy) {

		Statement st;
		ResultSet rs;
		int strategyUseId = 0;
		int agentId = 0;
		int strategyId = 0;
		try {

			st = conn.createStatement();
			rs = st.executeQuery("SELECT agent_id FROM agent WHERE agent_name = '"
					+ agentName + "'");
			rs.next();
			agentId = rs.getInt(1);

			rs = st.executeQuery("SELECT strategy_id FROM strategy WHERE strategy_name = '"
					+ strategy + "'");
			rs.next();
			strategyId = rs.getInt(1);

			PreparedStatement st1 = conn
					.prepareStatement(
							"INSERT INTO strategy_use (offer, limit_offer, agent_id, strategy_id, purpose, state, margin, last_trans) VALUES (\'"
									+ log.getOffer()
									+ "\',\'"
									+ log.getLimit()
									+ "\',\'"
									+ agentId
									+ "\',\'"
									+ strategyId
									+ "\',\'"
									+ log.getPurpose().toString().toUpperCase()
									+ "\',\'"
									+ log.getState().toString().toUpperCase()
									+ "\',\'" 
									+ log.getMargin() 
									+ "\',\'" 
									+ log.getLast()
									+ "\')",
							Statement.RETURN_GENERATED_KEYS);
			st1.execute();
			ResultSet rs1 = st1.getGeneratedKeys();
			if (rs1 != null && rs1.next()) {
				
				strategyUseId = rs1.getInt(1);
				
			}
			st.close();
			st1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return strategyUseId;
	}

	public void logSell(Transaction trans, String strategy,
			boolean hasStrategy, Double kosztZakupuSprzedazy, Offer offer, boolean hadStrategy, String sender,  String conversationId, Double double1, String sell_agent) {

		Statement st;
		ResultSet rs;
		

		try {
		
			st = conn.createStatement();

			long l = (new Date()).getTime();
			purpose p;
			
			if (double1 == -1.0) {
				p = purpose.BUYER;
			} else {
				p = purpose.SELLER;
			}

			st = conn.createStatement();
			rs = st.executeQuery("SELECT agent_id FROM agent WHERE agent_name = '"
					+ sell_agent + "'");
			rs.next();
			int agentId = rs.getInt(1);

			
			rs = st.executeQuery("SELECT agent_id FROM agent WHERE agent_name = '"
					+ sender + "'");
			rs.next();
			int contrahent = rs.getInt(1);
			
			PreparedStatement st1;
			
			Double costSell = truncate(trans.getKosztZakupu());
			
			Double costSellBuy = truncate(kosztZakupuSprzedazy);

			Double costEmission = truncate(trans.getKosztRedukcji() );

			if (hasStrategy) {
				

				rs = st.executeQuery("SELECT strategy_id FROM strategy WHERE strategy_name = '"
						+ strategy + "'");
				int strategyId = 0;

				if (rs.next())
					strategyId = rs.getInt(1);

				rs = st.executeQuery("SELECT max(strategy_use_id) FROM strategy_use WHERE conversation_id = '"
						+ conversationId + "'");
				
				int strategyUse = 0;
				
				if (rs.next())
					strategyUse = rs.getInt(1);

				st1 = conn
						.prepareStatement("INSERT INTO sell (offer, volume, shadow_price, agent_id, strategy_use_id, purpose, cost_sell, cost_emission, contrahent, conversation_id, test) VALUES (\'"
								+ trans.getTransactionPrice()
								+ "\',\'"
								+ trans.getVolume()
								+ "\',\'"
								+ trans.getShadowPrice()
								+ "\',\'"
								+ agentId
								+ "\',\'"
								+ strategyUse
								+ "\',\'"
								+ p.toString()
								+ "\',\'"
								+ costSell
								+ "\',\'"
								+ costEmission
								+ "\',\'"
								+ contrahent
								+ "\',\'"
								+ conversationId
								+ "\',\'"
								+ double1
								+ "\')");
			} else {
				st1 = conn
						.prepareStatement("INSERT INTO sell (offer, volume, shadow_price, agent_id, purpose, cost_sell, cost_emission, contrahent, conversation_id, test) VALUES (\'"
								+ trans.getTransactionPrice()
								+ "\',\'"
								+ trans.getVolume()
								+ "\',\'"
								+ trans.getShadowPrice()
								+ "\',\'"
								+ agentId
								+ "\',\'"
								+ p.toString()
								+ "\',\'"
								+ costSell
								+ "\',\'"
								+ costEmission
								+ "\',\'"
								+ contrahent
								+ "\',\'"
								+ conversationId
								+ "\',\'"
								+ double1
								+ "\')");
			}
			st1.execute();
			st1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda zwraca ranking strategii wzgledem sprzedazy (bez kosztu)
	 */
	public Map<String, Double> getRank(Timestamp changeDate, String agent, List<ZIPController> ZIPControllerList) {
		Map<String, Double> rank = new HashMap<String, Double>();
		PreparedStatement st = null;
		ResultSet rs = null;

		//String sql = "SELECT strategy_name, sum FROM strategy_worth_agg_vw WHERE agent_name = ?";
		String sql = "SELECT strategy_name, cost FROM strategy_worth_with_cost_vw WHERE initiator = ?";
		
		if (!ZIPControllerList.isEmpty()){
			sql = sql + " AND strategy_name IN (";
			for (ZIPController zipc : ZIPControllerList){
				sql = sql + "\'"+ zipc.getStrategyId() +"\',";
			}
			sql = sql + "\'\')";
		}
		
		try {

			st = conn.prepareStatement(sql);
			st.setString(1, agent);
			rs = st.executeQuery();

			while (rs.next()) {
				rank.put(rs.getString(1), rs.getDouble(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}

		return rank;
	}

	/**
	 * Zapisuje w bazie startowe wylosowane zmienne do strategii ZIP
	 * 
	 * @param beta
	 * @param gamma
	 * @param margin
	 * @param strategyName
	 */
	public void logStrategyStarters(double beta, double gamma, double margin,
			String strategyName) {

		try {

			PreparedStatement st = conn
					.prepareStatement("UPDATE strategy SET beta = '" + beta
							+ "', gamma = '" + gamma + "', margin = '" + margin
							+ "' WHERE strategy_name = '" + strategyName + "'");
			st.execute();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	private Double truncate(Double value){

        if(value.isInfinite() || value.isNaN()){
        	if (value < 0) {
        		value = Double.MIN_VALUE+1;
        		
        	} else {
        		value = Double.MAX_VALUE-1;
        		
        	}
        	
        }
        return value;
	}

	public void logConversationId(String conversationId, int useId) {
		try {

			PreparedStatement st = conn
					.prepareStatement("UPDATE strategy_use SET conversation_id = '" + conversationId
							+ "' WHERE strategy_use_id = '" + useId + "'");
			st.execute();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
