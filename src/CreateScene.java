
import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage; // Import Stage

public class CreateScene {

	private static Color color[] = {Color.DARKGRAY, Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.PINK};
	
    private Scene scene; // Store the scene
    private Button button1;

    public CreateScene(int i) { // Pass Stage and scene2
        Label label1 = new Label("This is Scene " + i);
        
        Random random = new Random();
        button1 = new Button();
        button1.setLayoutX(random.nextDouble(800));
        button1.setLayoutY(random.nextDouble(500));

        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(color[i], CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().add(button1);
        pane.getChildren().add(label1);
        scene = new Scene(pane, 900, 650);
    }

    public Scene getScene() { // Method to access the scene
        return scene;
    }
    
    public void setNext(Stage stage, Scene next, String ii) {
    	button1.setText("GO to scene " + ii);
    	button1.setOnAction((e) -> {
    		stage.setScene(next);
    	});
    }
}