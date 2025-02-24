package scene;

import java.util.ArrayList;

import component.ImageObject;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import logic.GameLogic;

public class PopScene extends ScenePane {

	private ImageObject start;
	private ArrayList<Background> backgroundList;
	private boolean light = false;
	
	public PopScene() {
		super("scene1/BG_scene1_1.png");
		backgroundList = new ArrayList<Background>();
		backgroundList.add(getBackground());
		backgroundList.add(this.createBackground("scene1/BG_scene1_2_light.png"));
		backgroundList.add(this.createBackground("scene1/BG_scene1_2_dark.png"));
		
		
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
    		this.setBackground(backgroundList.get(light ? 2 : 1));
    		light = !light;
//    		GameLogic.getStage().setScene(this.nextScene);
    	});
	}

}
