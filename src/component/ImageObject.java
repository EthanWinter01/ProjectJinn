package component;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageObject extends ImageView {
	
	public ImageObject(Image img) {
		super(img);
		this.setFitWidth(900);  
        this.setFitHeight(650); 
        this.setPreserveRatio(true); 
        this.setCursor(Cursor.HAND);
	}
	
	public ImageObject(Image img, double x, double y) {
		this(img);
		setPosition(x, y);
	}
	
	public void setPosition(double x, double y) {
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
}
