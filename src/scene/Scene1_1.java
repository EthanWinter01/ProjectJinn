package scene;

import component.ImageObject;
import component.NoisyObject;
import javafx.scene.Scene;
import logic.GameLogic;

public class Scene1_1 extends ScenePane {
	
	public Scene1_1() {
		super("scene1/BG_scene1_1.png");
		ImageObject building = new ImageObject("scene1/object/building.png");
		ImageObject bushtree = new ImageObject("scene1/object/bushtree.png");
		NoisyObject bus = new NoisyObject("scene1/object/bus.png", "scene1/sound/car/car-horn1.mp3");
		ImageObject text = new ImageObject("scene1/object/text.png");
		NoisyObject doorofbus = new NoisyObject("scene1/object/doorofbus.png", "scene1/sound/bus_door.mp3");

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
