package logic;

import component.Blinker;
import javafx.stage.Stage;

public class GameLogic {
	
//	private static GameLogic gameInstance = null;
	private static Stage gameStage;
	private static Blinker blinker;
	
	public static Stage getStage() {
		return gameStage;
	}
	
	public static void setStage(Stage stage) {
		gameStage = stage;
	}
	
	public static Blinker getBlinker() {
		if (blinker == null) {
			blinker = new Blinker();
		}
		return blinker;
	}
}
