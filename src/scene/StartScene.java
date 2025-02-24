package scene;

import component.ImageObject;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import logic.GameLogic;

public class StartScene extends ScenePane {

	private ImageObject start;
	
	public StartScene() {
		super("startBackground.png");
		   	
    	start = new ImageObject("start.png", 600, 460);
    	start.setFitWidth(300);
    	start.setFitHeight(300);
//    	start.setPosition(600, 460);
    	start.setOpacity(0);

    	ImageObject label = new ImageObject("faculty-label.png", 1, 1);
    	ImageObject tree = new ImageObject("tree.png");
    	ImageObject building = new ImageObject("building.png", 0, -0.3);
    	
    	
    	
    	this.getChildren().addAll(start, label, tree, building);
    	this.overall = new Scene(this, 900, 650);
    	next();
	}
	
	@Override
	public void next() {
		// TODO Auto-generated method stub
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

}
