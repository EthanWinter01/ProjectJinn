package scene;

import component.ImageObject;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import logic.GameLogic;

public class Scene0 extends ScenePane {

	private ImageObject start;
	
	public Scene0() {
		super("home/BG_home.png");
		   	
    	start = new ImageObject("home/object/start.png", 600, 460);
    	start.setFitWidth(300);
    	start.setFitHeight(300);
//    	start.setPosition(600, 460);
    	start.setOpacity(0);

    	ImageObject label = new ImageObject("home/object/label.png");
    	ImageObject tree = new ImageObject("home/object/tree.png");
    	ImageObject building = new ImageObject("home/object/building.png");
    	
    	
    	
    	this.getChildren().addAll(start, label, tree, building);
    	this.overall = new Scene(this, 900, 650);
    	start.setOnMouseEntered(event -> {
    		start.setOpacity(1); // Make the image fully visible
            start.setCursor(Cursor.HAND);
    	});
    	start.setOnMouseClicked(event -> {
    		GameLogic.getStage().setScene(this.nextScene);
    	});
    	start.setOnMouseExited(event -> {
    		start.setOpacity(0); 
            start.setCursor(Cursor.DEFAULT);
    	});
	}
	
	@Override
	public void next() {
		
	}

}
