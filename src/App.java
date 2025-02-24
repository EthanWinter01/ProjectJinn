import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import scene.PopScene;
import scene.StartScene;

public class App extends Application {
	@Override
	  public void start(Stage primaryStage) {
		  GameLogic.setStage(primaryStage);
		  
		  PopScene scene1 = new PopScene();
		  StartScene scene0 = new StartScene();
		  
		  scene0.setNextScene(scene1);
		  scene1.setNextScene(scene1);
		  
	      primaryStage.setScene(scene0.getOverall()); 
	      primaryStage.setTitle("Scene");
	      primaryStage.show();
	      primaryStage.setResizable(false);
	  }	

    public static void main(String[] args) {
        launch(args);
    }
}