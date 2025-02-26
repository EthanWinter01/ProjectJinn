package scene;

import component.Blinker;
import component.ImageObject;
import component.NoisyObject;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene1_1 extends ScenePane {
	
	public Scene1_1() {
		super("scene1/BG_scene1_1.png");
		NoisyObject building = new NoisyObject("scene1/object/building.png", "scene1/sound/crow.mp3");
		NoisyObject bushtree = new NoisyObject("scene1/object/bushtree.png", "scene1/sound/treemove.mp3");
		NoisyObject bus = new NoisyObject("scene1/object/bus.png", "scene1/sound/car/car-horn1.mp3");
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
		
		bus.setOnMouseClicked(event -> {
    		bus.onClick();
    	});
		
		building.setOnMouseClicked(event -> {
    		building.onClick();
    	});
		System.out.println("Hello1");
		doorofbus.setOnMouseClicked(event -> {
			new Thread(() -> {
				try {
					Thread.sleep(100); // Small delay to ensure smooth transition
	    	    } catch (InterruptedException e) {
	    	        e.printStackTrace();
	    	    }
				System.out.println("Hello2");
	    	    Platform.runLater(() -> {
	    	    	System.out.println(this.getScene());
	    	    	this.setNextScene(new Scene1_2());
	    	    	System.out.println("Hello4");
	    	    	if (this.nextScene != null) { // Ensure nextScene is not null
	    	    		GameLogic.getStage().setScene(this.nextScene);
	    	    	} else {
	    	    		System.err.println("Next scene is not set!");
	    	    	}
	    	    	doorofbus.onClick();
	    	    });
			}).start();
		});
		
		this.overall = new Scene(this, 900, 650);
	}
	
	private void startTextFade(ImageObject text) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), text);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setDelay(Duration.seconds(5));
        fadeOut.setOnFinished(event -> this.getChildren().remove(text)); // Remove text after fade
        fadeOut.play();
    }
	
}
