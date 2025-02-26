import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import scene.*;

public class OneScene extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		GameLogic.setStage(arg0);
		
		ScenePane scene = new Scene6();
		arg0.setScene(scene.getOverall());
		arg0.show();
		arg0.setResizable(false);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

