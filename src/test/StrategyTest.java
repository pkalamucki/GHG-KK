package test;

import strategy.controller.StrategyController;
import strategy.controller.PackController;
import strategy.controller.purpose;

public class StrategyTest{

	public static void main(String[] args) {
		
		/** to jezeli debugowanie xmla jest jakos potrzebne
		Result result = JUnitCore.runClasses(Strategy.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		*/
		
		/** to dla systemu i musi byc przekazane kazdemu agentowi*/
		StrategyController controller = new StrategyController();

		/** to dla agenta aby podal swoja nazwe i wyciagnal paczke*/
		PackController pC = controller.givePackControllerToAgent("EU");
		double offer = 10;
		double limit = 10;
		for(int i = 0; i<10;i++){
			offer = pC.start(purpose.SELLER,offer,limit);
		}
		offer = 10;
		for(int i = 0; i<10;i++){
			offer = pC.start(purpose.BUYER,offer,limit);
		}
		
		PackController pU = controller.givePackControllerToAgent("USA");
		offer = 10;
		for(int i = 0; i<10;i++){
			offer = pU.start(purpose.SELLER,offer,limit);
		}
		offer = 10;
		for(int i = 0; i<10;i++){
			offer = pU.start(purpose.BUYER,offer,limit);
		}
		
		PackController pE = controller.givePackControllerToAgent("USA");
		offer = 10;
		for(int i = 0; i<10;i++){
			offer = pE.start(purpose.SELLER,offer,limit);
		}
		offer = 10;
		for(int i = 0; i<10;i++){
			offer = pE.start(purpose.BUYER,offer,limit);
		}
		/** to wysietlenie logowania*/
		controller.printLogs();
	}
}
