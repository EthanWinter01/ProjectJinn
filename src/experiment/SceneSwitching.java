package experiment;

import javafx.application.Application;
import javafx.stage.Stage;
import scene.NewScene;

public class SceneSwitching extends Application {

	
//  @Override
//  public void start(Stage primaryStage) {
//      // Create Scene2 *before* Scene1 because Scene1 needs it.
//	  CreateScene scene0 = new CreateScene(0); // Pass the scene
//      CreateScene scene1 = new CreateScene(1); // scene1 will be set later
//      CreateScene scene2 = new CreateScene(2); // Pass the scene
//      CreateScene scene3 = new CreateScene(3); // Pass the scene
//      CreateScene scene4 = new CreateScene(4); // Pass the scene
//      CreateScene scene5 = new CreateScene(5); // Pass the scene
//
//      // Now that scene1 is created, set scene1 in scene2
//      scene0.setNext(primaryStage, scene1.getScene(), "1");
//      scene1.setNext(primaryStage, scene2.getScene(), "2");
//      scene2.setNext(primaryStage, scene3.getScene(), "3");
//      scene3.setNext(primaryStage, scene4.getScene(), "4");
//      scene4.setNext(primaryStage, scene5.getScene(), "5");
//      scene5.setNext(primaryStage, scene0.getScene(), "0");
//      
//      primaryStage.setScene(scene0.getScene()); // Start with Scene1
//      primaryStage.setTitle("Scene Switching Example");
//      primaryStage.show();
//  }	
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

	      // Now that scene1 is created, set scene1 in scene2
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