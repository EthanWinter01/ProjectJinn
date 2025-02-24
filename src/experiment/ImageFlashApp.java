package experiment;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ImageFlashApp extends Application {
	private volatile boolean isFlashing = true;
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Load your images
        Image image1 = new Image(ClassLoader.getSystemResource("startBackground.png").toString()); // Replace with your image paths
        Image image2 = new Image(ClassLoader.getSystemResource("pop.png").toString());

        ImageView imageView = new ImageView(image1); // Start with the first image
        
        Thread flashThread = new Thread(() -> {
            try {
                while (isFlashing) { // Keep flashing while isFlashing is true
                    // Simulate some work or condition that determines when to flash
                    Thread.sleep(100); // Example: Wait 500ms

                    // Update the JavaFX UI on the JavaFX Application Thread
                    Platform.runLater(() -> {
                        // Switch images and toggle visibility (flash effect)
                        if (imageView.getImage() == image1) {
                            imageView.setImage(image2);
                        } else {
                            imageView.setImage(image1);
                        }
//                        imageView.setOpacity(imageView.getOpacity() == 1 ? 0 : 1); // Toggle opacity
                    });
                }
            } catch (InterruptedException e) {
                // Handle interruption (e.g., when stopping the thread)
                Thread.currentThread().interrupt(); // Restore interrupt flag
            }
        });

        Pane pane = new Pane(imageView);
        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Image Flash");
        primaryStage.show();
        
        flashThread.setDaemon(true);
        flashThread.start();


    }
    @Override
    public void stop() { // Override the stop() method
        Platform.exit(); // This will terminate daemon threads
    }
    public static void main(String[] args) {
        launch(args);
    }
}
