package component;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageObject extends ImageView {
	
	public ImageObject(String file) {
		super(new Image(ClassLoader.getSystemResource(file).toString()));
		this.setFitWidth(900);  
        this.setFitHeight(650); 
        this.setPreserveRatio(true); 
        this.setCursor(Cursor.HAND);
        setPosition(0, 0);
        
        this.setOnMouseClicked(event -> {
        	System.out.println("click " + file);
        });
	}
	
	public ImageObject(String file, double x, double y) {
		this(file);
		setPosition(x, y);
	}
	
	public void setPosition(double x, double y) {
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
}
