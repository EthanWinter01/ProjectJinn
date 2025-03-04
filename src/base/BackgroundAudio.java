package base;

import java.net.URL;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class BackgroundAudio {
	// Field
	private static MediaPlayer player;

	// Constructor
    public static void playAudio(String soundFilePath) {
        stopAudio(); 

        URL soundUrl = ClassLoader.getSystemResource(soundFilePath);
        if (soundUrl != null) {
            Media sound = new Media(soundUrl.toString());
            player = new MediaPlayer(sound);
            player.setVolume(0.4);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            new Thread(() -> {
	        	Platform.runLater(() -> {
            		player.seek(Duration.ZERO);
            		player.play();
	        	});
	        }).start();
        } else {
            System.err.println("Background music file not found: " + soundFilePath);
        }
    }
    
    // Method
    public static void stopAudio() {
        if (player != null) {
            player.stop();
            player.dispose();
            player = null;
        }
    }

    public static void pauseAudio() {
        if (player != null) {
            player.pause();
        }
    }

    public static void resumeAudio() {
        if (player != null) {
            player.play();
        }
    }
    
    public static void setAudio(double volume) { 
        if (player != null) {
            player.setVolume(volume);
        }
    }
}
