package scene;

import component.Blinker;
import component.ImageObject;
import component.NoisyObject;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene1_1 extends ScenePane {
	
	public Scene1_1() {
		super("scene1/BG_scene1_1.png");
		NoisyObject building = new NoisyObject("scene1/object/building.png", "scene1/sound/crow.mp3", 800);
		NoisyObject bushtree = new NoisyObject("scene1/object/bushtree.png", "scene1/sound/treemove.mp3", 800);
		NoisyObject bus = new NoisyObject("scene1/object/bus.png", "scene1/sound/car/car-horn1.mp3", 500);
		ImageObject text = new ImageObject("scene1/object/text.png");
		NoisyObject doorofbus = new NoisyObject("scene1/object/doorofbus.png", "scene1/sound/bus_door.mp3");

		Blinker blinker = new Blinker();
		Rectangle fadeOverlay = new Rectangle(900, 650, Color.BLACK);
	    this.getChildren().addAll(building, bushtree, bus, doorofbus, text, blinker.getBlinker(), fadeOverlay);
	    
		FadeTransition sceneFadeIn = new FadeTransition(Duration.seconds(1.5), fadeOverlay);
        sceneFadeIn.setFromValue(1.0); 
        sceneFadeIn.setToValue(0.0);   
        sceneFadeIn.setOnFinished(event -> this.getChildren().remove(fadeOverlay)); // Remove after fade
        sceneFadeIn.play(); 
        startTextFade(text);
		
        bushtree.setOnMouseClicked(	event -> { bushtree.onClick(); 	});
        bus.setOnMouseClicked(		event -> { bus.onClick(); 		});
		building.setOnMouseClicked(	event -> { building.onClick(); 	});
		
		doorofbus.setOnMouseClicked(event -> {
			new Thread(() -> {
				try {
					Thread.sleep(100); // Small delay to ensure smooth transition
	    	    } catch (InterruptedException e) {
	    	        e.printStackTrace();
	    	    }
	    	    Platform.runLater(() -> {
	    	    	this.setNextScene(new Scene1_2());
	    	    	if (this.nextScene != null) { // Ensure nextScene is not null
	    	    		GameLogic.getStage().setScene(this.nextScene);
	    	    	} else {
	    	    		System.err.println("Next scene is not set!");
	    	    	}
	    	    	doorofbus.onClick();
	    	    });
			}).start();
		});
		
	}
}
