import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import scene.*;

public class App extends Application {
	@Override
	  public void start(Stage primaryStage) {
		  GameLogic.setStage(primaryStage);
		  
		  Scene0 scene0 = new Scene0();
		  Scene1_1 scene1_1 = new Scene1_1();
		  Scene1_2 scene1_2 = new Scene1_2();
		  
		  scene0.setNextScene(scene1_1);
		  scene1_1.setNextScene(scene1_2);
		  scene1_2.setNextScene(scene1_1);
		  
	      primaryStage.setScene(scene0.getOverall()); 
	      primaryStage.setTitle("Scene");
	      primaryStage.show();
	      primaryStage.setResizable(false);
	  }	

    public static void main(String[] args) {
        launch(args);
    }
}