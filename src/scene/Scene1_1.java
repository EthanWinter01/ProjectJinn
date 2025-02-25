package scene;

import component.ImageObject;
import javafx.scene.Scene;
import logic.GameLogic;

public class Scene1_1 extends ScenePane {

	private ImageObject building, bushtree, bus, text, doorofbus;
	
	public Scene1_1() {
		super("scene1/BG_scene1_1.png");
		building = new ImageObject("scene1/object/building.png");
		bushtree = new ImageObject("scene1/object/bushtree.png");
		bus = new ImageObject("scene1/object/bus.png");
		text = new ImageObject("scene1/object/text.png");
		doorofbus = new ImageObject("scene1/object/doorofbus.png");
		
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
