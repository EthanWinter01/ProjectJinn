import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import scene.Scene3;
import scene.ScenePane;

public class OneScene extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		GameLogic.setStage(arg0);
		
		ScenePane scene3 = new Scene3();
		arg0.setScene(scene3.getOverall());
		arg0.show();
		arg0.setResizable(false);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
