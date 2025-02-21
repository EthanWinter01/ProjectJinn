


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

