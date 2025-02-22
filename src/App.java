
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {
	@Override
	  public void start(Stage primaryStage) {
	      // Create Scene2 *before* Scene1 because Scene1 needs it.
		  NewScene scene0 = new NewScene(0); // Pass the scene
	      NewScene scene1 = new NewScene(1); // scene1 will be set later
	      NewScene scene2 = new NewScene(2); // Pass the scene
	      NewScene scene3 = new NewScene(3); // Pass the scene
	      NewScene scene4 = new NewScene(4); // Pass the scene
	      NewScene scene5 = new NewScene(5); // Pass the scene
	      NewScene scene6 = new NewScene(6); // Pass the scene

	      scene0.setNext(primaryStage, scene1.getScene());
	      scene1.setNext(primaryStage, scene2.getScene());
	      scene2.setNext(primaryStage, scene3.getScene());
	      scene3.setNext(primaryStage, scene4.getScene());
	      scene4.setNext(primaryStage, scene5.getScene());
	      scene5.setNext(primaryStage, scene6.getScene());
	      scene6.setNext(primaryStage, scene0.getScene());
	      
	      primaryStage.setScene(scene0.getScene()); // Start with Scene1
	      primaryStage.setTitle("Scene Switching Example");
	      primaryStage.show();
	  }	

    public static void main(String[] args) {
        launch(args);
    }
}