package scenes;

import base.BaseScene;
import base.BlackScreen;
import base.FadeEffect;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.GameLogic;

public class SceneStart extends BaseScene {

	private static final String BG_PATH = "home/M(900x650).png";
	private Rectangle black;
	
	public SceneStart() {
		super(BG_PATH);	
		black = new BlackScreen();
		black.setOpacity(0);
		this.getChildren().addAll(new FadeEffect(1), black);
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), black);
        fadeIn.setFromValue(0.0); 
        fadeIn.setToValue(1.0);  
        fadeIn.setDelay(Duration.seconds(7));
        fadeIn.setOnFinished(event -> {
        	PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(delayEvent -> {
                GameLogic.getStage().setScene((new Scene0()).getOverall());
            });
            delay.play();
        });
        fadeIn.play();
	}

	@Override
	protected void initializeObjects() {
		
	}

	@Override
	protected void setupEventHandlers() {
		
	}
	
}
