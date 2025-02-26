package scene;

import component.ImageObject;
import component.NoisyObject;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.util.Duration;
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
	
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), text);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.setDelay(Duration.seconds(5));
		fadeOut.play();
		    
		fadeOut.setOnFinished(eventII -> {
			this.getChildren().remove(text); // Removes text after fading
		});
		
		bus.setOnMouseClicked(event -> {
    		bus.onClick();
    	});
		
		doorofbus.setOnMouseClicked(event -> {
			doorofbus.onClick();
			GameLogic.getStage().setScene(this.nextScene);
		});
		
		
		
		this.overall = new Scene(this, 900, 650);
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub

	}

}
