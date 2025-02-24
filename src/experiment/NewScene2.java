package experiment;



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

public class NewScene2 {

	private static Color color[] = {Color.DARKGRAY, Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.PINK};
	private static String back[] = {"startBackground", "pop", "printer1", "printer2", "toilet1", "toilet2", "toilet3"};
	
    private Scene scene; // Store the scene
    private ImageView imageView; // ทำตัวเหมือนปุ่ม // act like button
    private ImageView trigger;

    public NewScene2(int i) { // Pass Stage and scene2
        Label label1 = new Label();
        label1.setText("This is Scene " + i);
        label1.setTextFill(Color.AQUA);
        
        Image image = new Image(ClassLoader.getSystemResource("start.png").toString()); 
        imageView = new ImageView(image);
        imageView.setFitWidth(300);  
        imageView.setFitHeight(300); 
        imageView.setPreserveRatio(true); 
        imageView.setCursor(Cursor.HAND);
        imageView.setOpacity(0);
                
        
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
    	
    	if (i == 0) {
    		Image img = new Image(ClassLoader.getSystemResource("faculty-label.png").toString()); 
    		ImageView t = new ImageView(img);
    		t.setFitWidth(900);  
            t.setFitHeight(650); 
            t.setPreserveRatio(true); 
            t.setCursor(Cursor.HAND);
            t.setLayoutX(1);
        	t.setLayoutY(1);
        	
        	
        	Image i2 = new Image(ClassLoader.getSystemResource("building.png").toString());
        	ImageView b = new ImageView(i2);
        	b.setFitWidth(900);  
            b.setFitHeight(650); 
            b.setPreserveRatio(true); 
            b.setCursor(Cursor.HAND);
            b.setLayoutX(0);
        	b.setLayoutY(-0.3);
        	
        	Image i3 = new Image(ClassLoader.getSystemResource("tree.png").toString());
        	ImageView tr = new ImageView(i3);
        	tr.setFitWidth(900);  
            tr.setFitHeight(650); 
            tr.setPreserveRatio(true); 
            tr.setCursor(Cursor.HAND);
            tr.setLayoutX(0);
        	tr.setLayoutY(0);
        	
        	t.setOnMouseClicked(e -> {
        		System.out.println("label");
        	});
        	b.setOnMouseClicked(e -> {
        		System.out.println("building");
        	});
        	tr.setOnMouseClicked(e -> {
        		System.out.println("tree");
        	});
        	
        	pane.getChildren().add(t);
        	pane.getChildren().add(b);
        	pane.getChildren().add(tr);
        	
        	
        	
    	}

    	//77.8 and 117.4
    	if (i == 3) {
    		Image img = new Image(ClassLoader.getSystemResource("printer2-Photoroom.png").toString()); 
    		trigger = new ImageView(img);
    		trigger.setFitWidth(900);  
            trigger.setFitHeight(650); 
            trigger.setPreserveRatio(true); 
            trigger.setCursor(Cursor.HAND);
            trigger.setLayoutX(0.3);
        	trigger.setLayoutY(0);
        	pane.getChildren().add(trigger);
    	}

        scene = new Scene(pane, 900, 650);
    }

    public Scene getScene() { // Method to access the scene
        return scene;
    }
    
    public void setNext(Stage stage, Scene next) {
    	imageView.setOnMouseEntered(event -> {
    		imageView.setOpacity(1); // Make the image fully visible
            imageView.setCursor(Cursor.HAND);
    	});
    	imageView.setOnMouseClicked(event -> {
    		stage.setScene(next);
    	});
    	imageView.setOnMouseExited(event -> {
    		imageView.setOpacity(0); // Make the image fully visible
            imageView.setCursor(Cursor.DEFAULT);
    	});
    }
}
