package scene;

import java.net.URL;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public abstract class ScenePane extends Pane {
	
	protected Scene overall;
	protected Scene nextScene;
	protected BackgroundMusic backgroundMusic;
	protected MediaPlayer heartBeatPlayer;
	
	public ScenePane(String fileName) {		
    	this.setBackground(createBackground(fileName));
    	this.backgroundMusic = null;
    	overall = new Scene(this, 900, 650);
    	initialHeartBeat();
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
	
	public Scene getOverall() {
		return overall;
	}
	
	public void setNextScene(ScenePane nextScene) {
		this.nextScene = nextScene.getOverall();
	}
	
	protected void startTextFade(ImageObject text) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), text);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setDelay(Duration.seconds(5));
        fadeOut.setOnFinished(event -> this.getChildren().remove(text)); // Remove text after fade
        fadeOut.play();
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
	
	public MediaPlayer getHeartBeat() {
		return heartBeatPlayer;
	}
}
