package component;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.GameLogic;

public class ImageObject extends ImageView {
	
	public ImageObject(String fileName) {
		super(new Image(ClassLoader.getSystemResource(fileName).toString()));
		this.setFitWidth(900);  
        this.setFitHeight(650); 
        this.setPreserveRatio(true); 
        this.setCursor(Cursor.HAND);
        setPosition(0, 0);
//        this.fitWidthProperty().bind(GameLogic.getStage().widthProperty());//
        this.setOnMouseClicked(event -> {
        	System.out.println("click " + fileName);
        });
	}
	
	public ImageObject(String fileName, double x, double y) {
		this(fileName);
		setPosition(x, y);
	}
	
	public void setPosition(double x, double y) {
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
}
