package experiment;

import component.ScaleImage;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import logic.GameLogic;
import scene.ScenePane;

public class ScaleScene extends ScenePane {

	private ScaleImage start;
	
	public ScaleScene() {
		super("home/home.png");
		   	
//    	start = new ScaleImage("home/start.png", this, 600, 460);
//    	start.setFitWidth(300);
//    	start.setFitHeight(300);
//    	start.setPosition(600, 460);
//    	start.setOpacity(0);

    	ScaleImage label = new ScaleImage("home/label.png", this);
    	ScaleImage tree = new ScaleImage("home/tree.png", this);
    	ScaleImage building = new ScaleImage("home/building.png", this);
    	
    	
    	
    	this.getChildren().addAll(label, tree, building);
    	this.overall = new Scene(this, 900, 650);
//    	start.setOnMouseEntered(event -> {
//    		start.setOpacity(1); // Make the image fully visible
//            start.setCursor(Cursor.HAND);
//    	});
//    	start.setOnMouseClicked(event -> {
//    		GameLogic.getStage().setScene(this.nextScene);
//    	});
//    	start.setOnMouseExited(event -> {
//    		start.setOpacity(0); 
//            start.setCursor(Cursor.DEFAULT);
//    	});
	}
	
	@Override
	public void next() {
		
	}

}
