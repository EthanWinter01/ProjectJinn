package component;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;import javafx.util.Duration;

import java.net.URL;

public class BackgroundMusic {

    private static MediaPlayer mediaPlayer;

    public static void playMusic(String soundFilePath) {
        stopMusic(); 

        URL soundUrl = ClassLoader.getSystemResource(soundFilePath);
        if (soundUrl != null) {
            Media sound = new Media(soundUrl.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.3);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            new Thread(() -> {
	        	Platform.runLater(() -> {
            		mediaPlayer.seek(Duration.ZERO);
            		mediaPlayer.play();
	        	});
	        }).start();
        } else {
            System.err.println("Background music file not found: " + soundFilePath);
        }
    }

    public static void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
    }

    public static void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public static void resumeMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public static void setVolume(double volume) { 
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }
}