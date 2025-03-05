import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import scenes.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		GameLogic.setStage(primaryStage);
		primaryStage.setScene((new SceneStart()).getOverall()); 
	    primaryStage.setTitle("Scene");
	    primaryStage.show();
	    primaryStage.setResizable(false);
	}	

  public static void main(String[] args) {
      launch(args);
  }
}
