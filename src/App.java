//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.HBox;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//
//public class App extends Application {
//
//	@Override
//	public void start(Stage stage) throws Exception {
//		// TODO Auto-generated method stub
//		HBox root = new HBox();
//		root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
//		Scene scene = new Scene(root, 900, 650);
//		
//		root.setOnMouseClicked((e) -> {
//			double x = e.getX();
//			double y = e.getY();
//			System.out.println("x = " + x + ", y = " + y);
//		});
//		
//		stage.setScene(scene);
//		stage.setTitle("SaaYoong");
//		stage.show();
//		
//	}


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Load the image
        Image backgroundImage = new Image("900X650 BG.png"); // Change the path

        // Create a BackgroundImage
        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(100, 100, true, true, true, false)
        );

        // Create a Pane and set the background
        Pane root = new Pane();
        root.setBackground(new Background(background));

        // Create Scene and Stage
        Scene scene = new Scene(root, 900, 650);
        primaryStage.setTitle("JavaFX Background Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//	
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//}