package component;

import javafx.animation.FadeTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class FadeInEffect {

    private static final double FADE_IN_DURATION = 1.5; // Duration of fade-in effect (in seconds)
    
    private final Rectangle fadeOverlay;

    public FadeInEffect(double width, double height, Color color) {
        fadeOverlay = new Rectangle(width, height, color);
        fadeOverlay.setMouseTransparent(true);
        fadeOverlay.setOpacity(1.0);  // Start with full opacity (visible)
    }

    // Play the fade-in effect
    public void playFadeInEffect() {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(FADE_IN_DURATION), fadeOverlay);
        fadeIn.setFromValue(1.0);  // Start from full opacity
        fadeIn.setToValue(0.0);    // Fade to transparent
        fadeIn.setOnFinished(event -> removeFadeOverlay()); // Remove overlay after fade
        fadeIn.play();
    }

    // Get the fade overlay (Rectangle) to add to the scene
    public Rectangle getFadeOverlay() {
        return fadeOverlay;
    }

    // Remove the fade overlay from the scene (to clean up after fade)
    private void removeFadeOverlay() {
        fadeOverlay.setOpacity(0.0);
    }
}
