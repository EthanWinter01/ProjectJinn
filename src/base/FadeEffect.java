package base;

import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class FadeEffect extends BlackScreen {
	
	private static final double FADE_IN_DURATION = 1.5;
	
	public FadeEffect(double delay) {
		fadeStart(delay);
	}
	
	public FadeEffect() {
		fadeStart(3);
	}
	
	public void fadeStart(double delay) {
		 FadeTransition fadeIn = new FadeTransition(Duration.seconds(FADE_IN_DURATION), this);
        fadeIn.setFromValue(1.0); 
        fadeIn.setToValue(0.0);  
        fadeIn.setDelay(Duration.seconds(delay));
        fadeIn.setOnFinished(event -> this.setOpacity(0));
        fadeIn.play();
	}
}
