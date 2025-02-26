import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
<<<<<<< HEAD
import scene.*;
=======
import scene.Scene2;
import scene.Scene3;
import scene.ScenePane;
>>>>>>> 6364c1e (walkthrough Scene2)

public class OneScene extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		GameLogic.setStage(arg0);
		
<<<<<<< HEAD
		ScenePane scene = new Scene2();
		arg0.setScene(scene.getOverall());
=======
		ScenePane scene2 = new Scene2();
		arg0.setScene(scene2.getOverall());
>>>>>>> 6364c1e (walkthrough Scene2)
		arg0.show();
		arg0.setResizable(false);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

