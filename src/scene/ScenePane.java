package scene;

import component.BackgroundMusic;
import component.ImageObject;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class ScenePane extends Pane {
	
	protected Scene overall;
	protected Scene nextScene;
	protected BackgroundMusic backgroundMusic;
	
	public ScenePane(String fileName) {		
    	this.setBackground(createBackground(fileName));
    	this.backgroundMusic = null;
    	overall = new Scene(this, 900, 650);
	}
	
	public Background createBackground(String fileName) {
		
		Image backgroundImage = new Image(ClassLoader.getSystemResource(fileName).toString());
    	BackgroundImage background = new BackgroundImage(
    			backgroundImage,
    			BackgroundRepeat.NO_REPEAT, 
    			BackgroundRepeat.NO_REPEAT,
    			BackgroundPosition.CENTER,
    			new BackgroundSize(100, 100, true, true, true, false)
		);
    	return new Background(background);
	}
	
	private void startFadeEffect(ImageView object) {
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), object);
	    fadeOut.setFromValue(1.0);
	    fadeOut.setToValue(0.0);
	    fadeOut.setDelay(Duration.seconds(5)); // Wait 5 seconds before fading
	    fadeOut.setOnFinished(e -> object.setVisible(false)); // Hide text after fading
	    fadeOut.play();
	}
	
	public Scene getOverall() {
		return overall;
	}
	
	public void setNextScene(ScenePane nextScene) {
		this.nextScene = nextScene.getOverall();
	}
	
}
