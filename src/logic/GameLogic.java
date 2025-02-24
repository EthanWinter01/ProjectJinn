package logic;

import javafx.stage.Stage;

public class GameLogic {
	
//	private static GameLogic gameInstance = null;
	private static Stage gameStage;
	
	
	public static Stage getStage() {
		return gameStage;
	}
	
	public static void setStage(Stage stage) {
		gameStage = stage;
	}
}
