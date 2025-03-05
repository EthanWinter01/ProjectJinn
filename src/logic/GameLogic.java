package logic;
import javafx.application.Platform;
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
	
	public static void transition(Runnable sceneTransitionLogic) {
		transition(sceneTransitionLogic, 100);
    }
	
	public static void transition(Runnable sceneTransitionLogic, long delayMillis) {
        new Thread(() -> {
            try {
                Thread.sleep(delayMillis); // Optional delay before transition
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(sceneTransitionLogic);
        }).start();
    }
}
