package experiment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ButtonAndImage extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Load the image
        Image image = new Image("startBackground.png"); // Replace with your image path
        ImageView imageView = new ImageView(image);
        
        // Set image size
        imageView.setFitWidth(100);
        imageView.setFitHeight(50);
        
        // Create a button and set the image
        Button button = new Button();
        button.setGraphic(imageView);

        // Set button action
        button.setOnAction(e -> System.out.println("Button clicked!"));

        // Layout
        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 600, 400);

        // Set stage
        primaryStage.setTitle("Image Button Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
