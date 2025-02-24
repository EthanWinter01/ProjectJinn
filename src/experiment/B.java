package experiment;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class B extends Application {
	@Override
	  public void start(Stage primaryStage) {
	      // Create Scene2 *before* Scene1 because Scene1 needs it.
		  NewScene2 scene0 = new NewScene2(0); // Pass the scene
	      NewScene2 scene1 = new NewScene2(1); // scene1 will be set later
	      NewScene2 scene2 = new NewScene2(2); // Pass the scene
	      NewScene2 scene3 = new NewScene2(3); // Pass the scene
	      NewScene2 scene4 = new NewScene2(4); // Pass the scene
	      NewScene2 scene5 = new NewScene2(5); // Pass the scene
	      NewScene2 scene6 = new NewScene2(6); // Pass the scene

	      scene0.setNext(primaryStage, scene1.getScene());
	      scene1.setNext(primaryStage, scene2.getScene());
	      scene2.setNext(primaryStage, scene3.getScene());
	      scene3.setNext(primaryStage, scene4.getScene());
	      scene4.setNext(primaryStage, scene5.getScene());
	      scene5.setNext(primaryStage, scene6.getScene());
	      scene6.setNext(primaryStage, scene0.getScene());
	      
	      primaryStage.setScene(scene0.getScene()); 
	      primaryStage.setTitle("Scene Switching Example");
	      primaryStage.show();
	  }	

    public static void main(String[] args) {
        launch(args);
    }
}