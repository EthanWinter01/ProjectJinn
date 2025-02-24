package scene;

import component.ImageObject;
import javafx.scene.Scene;
import logic.GameLogic;

public class Scene1_1 extends ScenePane {

	private ImageObject building, bushtree, bus, text, doorofbus;
	
	public Scene1_1() {
		super("scene1/BG_scene1_1.png");
		building = new ImageObject("scene1/building.png");
		bushtree = new ImageObject("scene1/bushtree.png");
		bus = new ImageObject("scene1/bus.png");
		text = new ImageObject("scene1/text.png");
		doorofbus = new ImageObject("scene1/doorofbus.png");
		
		this.getChildren().addAll(building, bushtree, bus, doorofbus, text);
		
		doorofbus.setOnMouseClicked(event -> {
			GameLogic.getStage().setScene(this.nextScene);
		});
		
		this.overall = new Scene(this, 900, 650);
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub

	}

}
