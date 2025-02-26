package scene;

import java.util.ArrayList;

import component.Blinker;
import component.ImageObject;
import component.NoisyObject;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene3 extends ScenePane {

	private ArrayList<Background> backgroundList;
	private int i = 0;
	private ImageObject bg1_sink, crack, faucet, oneghost, optionalghost, text, tryMe, urinal;
	public Scene3() {
		super("scene3/BG_scene3_1.png");
		// TODO Auto-generated constructor stub
		backgroundList = new ArrayList<Background>();
		backgroundList.add(getBackground());
		String[] bg = {"light", "med", "dark","zoom_in", "crack", "ghost"};
		for (String file: bg) {
			backgroundList.add(createBackground("scene3/BG_scene3_2_" + file + ".png"));
		}
//		back
//		this.overall = new Scene(this, 900, 650);
		bg1_sink = new ImageObject("scene3/object/bg1_sink.png");
		crack = new ImageObject("scene3/object/crack.png");
		faucet = new ImageObject("scene3/object/faucet.png");
		oneghost = new ImageObject("scene3/object/oneghost.png");
		optionalghost = new ImageObject("scene3/object/optionalghost.png");
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
			this.getChildren().remove(bg1_sink);
			this.setBackground(backgroundList.get(1));
		});
		
//		this.setOnMouseClicked(event -> {
//			this.setBackground(backgroundList.get((++i)%7));
//		});
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
