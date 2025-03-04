import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import scenes.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		GameLogic.setStage(primaryStage);
		
		Scene2 scene = new Scene2();
		
		primaryStage.setScene(scene.getOverall()); 
	    primaryStage.setTitle("Scene");
	    primaryStage.show();
	    primaryStage.setResizable(false);
	}	

  public static void main(String[] args) {
      launch(args);
  }
}
