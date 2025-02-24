package scene;

import component.ImageObject;
import javafx.scene.Scene;
import logic.GameLogic;

public class PopScene extends ScenePane {

	ImageObject start;
	
	public PopScene() {
		super("pop.png");
		
		start = new ImageObject("pop.png", 300, 300);
		start.setFitHeight(300);
		start.setFitWidth(300);
    	this.getChildren().add(start);
    	//
    	this.overall = new Scene(this, 900, 650);
    	next();
	}

	@Override
	public void next() {
    	start.setOnMouseClicked(event -> {
    		GameLogic.getStage().setScene(this.nextScene);
    	});
	}

}
