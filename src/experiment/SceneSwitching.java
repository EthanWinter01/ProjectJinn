package experiment;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import scene.NewScene;

public class SceneSwitching extends Application {
	
	@Override
	  public void start(Stage primaryStage) {
		GameLogic.setStage(primaryStage);
		
		primaryStage.show();
	  }	

    public static void main(String[] args) {
        launch(args);
    }
}