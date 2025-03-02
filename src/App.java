

import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import scene.*;

public class App extends Application {
	@Override
	  public void start(Stage primaryStage) {
		  GameLogic.setStage(primaryStage);
		  
		  Scene0 scene0 = new Scene0();
		 
	      primaryStage.setScene(scene0.getOverall()); 
	      primaryStage.setTitle("Scene");
	      primaryStage.show();
	      primaryStage.setResizable(false);
	  }	

    public static void main(String[] args) {
        launch(args);
    }
}