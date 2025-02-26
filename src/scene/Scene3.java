package scene;

import java.util.ArrayList;

import component.Blinker;
import component.ImageObject;
import component.NoisyObject;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene3 extends ScenePane {

	private int sceneGuider = 0;
	private ArrayList<Background> backgroundList;
	private ImageObject bg1_sink, oneghost, text, tryMe, urinal;
	private NoisyObject crack, faucet, optionalghost;
	public Scene3() {
		super("scene3/BG_scene3_1.png");
		// TODO Auto-generated constructor stub
		backgroundList = new ArrayList<Background>();
		backgroundList.add(getBackground());
		String[] bg = {"light", "med", "dark","zoom_in", "crack", "ghost"};
		for (String file: bg) {
			backgroundList.add(createBackground("scene3/BG_scene3_2_" + file + ".png"));
		}
		
		bg1_sink = new NoisyObject("scene3/object/bg1_sink.png", "scene3/sound/open_faucet2.mp3", 500);
		crack = new NoisyObject("scene3/object/crack.png", "scene3/sound/glass-breaking.mp3", 500);
		faucet = new NoisyObject("scene3/object/faucet.png", "scene3/sound/open_faucet1.mp3", 500);
		oneghost = new ImageObject("scene3/object/oneghost.png");
		optionalghost = new NoisyObject("scene3/object/optionalghost.png", "scene3/sound/ghostwomenhaha.mp3");
		text = new ImageObject("scene3/object/text.png");
		tryMe = new ImageObject("scene3/object/tryme.png");
		urinal = new ImageObject("scene3/object/urinal.png");

		crack.close();
		faucet.close();
		oneghost.close();
		optionalghost.close();
		tryMe.close();
		urinal.close();
		
		Blinker blinker = new Blinker();
        Rectangle fadeOverlay = new Rectangle(900, 650, Color.BLACK);
		
		FadeTransition sceneFadeIn = new FadeTransition(Duration.seconds(1.5), fadeOverlay);
        sceneFadeIn.setFromValue(1.0); 
        sceneFadeIn.setToValue(0.0);   
        sceneFadeIn.setOnFinished(event -> this.getChildren().remove(fadeOverlay)); // Remove after fade
        sceneFadeIn.play(); 
		
		this.getChildren().addAll(bg1_sink, crack, faucet, oneghost, optionalghost, tryMe, urinal, text, blinker.getBlinker(), fadeOverlay);
		
		startTextFade();
		
		bg1_sink.setOnMouseClicked(event -> {
			bg1_sink.onClick();
			this.getChildren().remove(bg1_sink);
			this.setBackground(backgroundList.get(1));
			urinal.open();
			sceneGuider = 1;
		});
		
		urinal.setOnMouseClicked(event -> {
			this.getChildren().remove(urinal);
			this.setBackground(backgroundList.get(2));
			faucet.open();
			sceneGuider = 2;
		});
		
		faucet.setOnMouseClicked(event -> {
			faucet.onClick();
			this.getChildren().remove(faucet);
			this.setBackground(backgroundList.get(3));
			oneghost.open();
			sceneGuider = 3;
		});
		
		oneghost.setOnMouseClicked(event -> {
			this.getChildren().remove(oneghost);
			this.setBackground(backgroundList.get(4));
			tryMe.open();
			sceneGuider = 4;
		});
		
		tryMe.setOnMouseClicked(event -> {
			if (sceneGuider == 4) {
				this.getChildren().remove(tryMe);
				this.setBackground(backgroundList.get(5));
				crack.open();
				crack.onClick();
				sceneGuider = 5;
			}
		});
		
		crack.setOnMouseClicked(event -> {
			crack.onClick();
			if (sceneGuider == 5) {
//				optionalghost.open();
//				optionalghost.onClick();
				this.setBackground(backgroundList.get(6));
				sceneGuider = 6;
			} else if (sceneGuider == 6) { 
				optionalghost.open();
				optionalghost.onClick();
				optionalghost.onClick();
				optionalghost.onClick();
				FadeTransition ghostFadeOut = new FadeTransition(Duration.seconds(0.001), optionalghost) ;
	            ghostFadeOut.setDelay(Duration.millis(1000));
	            ghostFadeOut.setFromValue(1.0);
	            ghostFadeOut.setToValue(0.0);
	            ghostFadeOut.setOnFinished(subevent -> {
	            	this.getChildren().remove(optionalghost);
	            	this.getChildren().remove(crack);
	            });
	            ghostFadeOut.play();
	            
				new Thread(() -> {
	        		try {
	                    Thread.sleep(5000); // Hold for 3 seconds
	                } catch (InterruptedException e) {
	                    Thread.currentThread().interrupt();
	                }
	        		Platform.runLater(() -> {
	       				this.setNextScene(new Scene4());
	       				if (this.nextScene != null) { // Ensure nextScene is not null
	       					GameLogic.getStage().setScene(this.nextScene);
	       				} else {
	       					System.err.println("Next scene is not set!");
	       				}
	       			});
        		}).start();
			}
		});
	}
	
	public void startTextFade() {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), text);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setDelay(Duration.seconds(3));
        fadeOut.setOnFinished(event -> this.getChildren().remove(text)); // Remove text after fade
        fadeOut.play();
    }
}
