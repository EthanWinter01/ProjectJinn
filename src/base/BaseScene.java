package base;

import java.net.URL;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import logic.GameLogic;

abstract public class BaseScene extends Pane {
	// Field
	protected Scene overall;
	protected Scene nextScene;
	protected BackgroundAudio backgroundMusic;
	protected MediaPlayer heartBeatPlayer;
	
	// Constructor
	public BaseScene(String fileName) {		
    	this.setBackground(createBackground(fileName));
    	this.backgroundMusic = null;
    	overall = new Scene(this, 900, 650);
    	initialHeartBeat();
	}
	
	// Method
	protected abstract void initializeObjects();
	protected abstract void setupEventHandlers();
	
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
	
	protected void startTextFade(BaseObject text) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), text);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setDelay(Duration.seconds(5));
        fadeOut.setOnFinished(event -> this.getChildren().remove(text)); // Remove text after fade
        fadeOut.play();
    }

	protected void toNextScene(BaseScene scene) {
		this.setNextScene(scene);
        if (this.nextScene != null) {
            GameLogic.getStage().setScene(this.nextScene);
        } else {
            System.err.println("Next scene is not set!");
        }
	}
	
	private void initialHeartBeat() {
		URL heartBeatUrl = ClassLoader.getSystemResource("scene3/sound/heart-beat-137135.mp3");
        if (heartBeatUrl != null) {
            heartBeatPlayer = new MediaPlayer(new Media(heartBeatUrl.toString()));
            heartBeatPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop sound
        } else {
            System.err.println("Sound file not found: scene3/sound/heart-beat-137135.mp3");
        }
	}

	// Getters & Setters
	public Scene getOverall() {
		return overall;
	}

	public void setOverall(Scene overall) {
		this.overall = overall;
	}

	public Scene getNextScene() {
		return nextScene;
	}

	public void setNextScene(BaseScene nextScene) {
		this.nextScene = nextScene.getOverall();
	}

	public BackgroundAudio getBackgroundMusic() {
		return backgroundMusic;
	}

	public void setBackgroundMusic(BackgroundAudio backgroundMusic) {
		this.backgroundMusic = backgroundMusic;
	}

	public MediaPlayer getHeartBeatPlayer() {
		return heartBeatPlayer;
	}

	public void setHeartBeatPlayer(MediaPlayer heartBeatPlayer) {
		this.heartBeatPlayer = heartBeatPlayer;
	}	
}
