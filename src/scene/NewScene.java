package scene;


import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage; // Import Stage

public class NewScene {

	private static Color color[] = {Color.DARKGRAY, Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.PINK};
	private static String back[] = {"startBackground", "pop", "printer1", "printer2", "toilet1", "toilet2", "toilet3"};
	
    private Scene scene; // Store the scene
    private ImageView imageView; // ทำตัวเหมือนปุ่ม // act like button

    public NewScene(int i) { // Pass Stage and scene2
        Label label1 = new Label("This is Scene " + i);
        
        Image image = new Image(ClassLoader.getSystemResource("start.png").toString()); 
        imageView = new ImageView(image);
        imageView.setFitWidth(300);  
        imageView.setFitHeight(300); 
        imageView.setPreserveRatio(true); 
        imageView.setCursor(Cursor.HAND);
                
        
        Pane pane = new Pane();
//    	Image backgroundImage = new Image(ClassLoader.getSystemResource(back[i]+".png").toString());
    	Image backgroundImage = new Image(ClassLoader.getSystemResource(back[i] + ".png").toString());
    	BackgroundImage background = new BackgroundImage(
    			backgroundImage,
    			BackgroundRepeat.NO_REPEAT, 
    			BackgroundRepeat.NO_REPEAT,
    			BackgroundPosition.CENTER,
    			new BackgroundSize(100, 100, true, true, true, false)
		);
    	pane.setBackground(new Background(background));
    	imageView.setLayoutX(600);
    	imageView.setLayoutY(460);

        pane.getChildren().add(imageView);
        pane.getChildren().add(label1);
        scene = new Scene(pane, 900, 650);
    }

    public Scene getScene() { // Method to access the scene
        return scene;
    }
    
    public void setNext(Stage stage, Scene next) {
    	imageView.setOnMouseClicked(event -> {
    		stage.setScene(next);
    	});
    }
}