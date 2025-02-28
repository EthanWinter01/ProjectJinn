package component;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameObject extends ImageView {
	public GameObject(String fileName) {
		super(new Image(ClassLoader.getSystemResource(fileName).toString()));
		this.setFitWidth(900);  
        this.setFitHeight(650); 
        this.setPreserveRatio(true);
	}
}
