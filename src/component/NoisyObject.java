package component;

import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class NoisyObject extends ImageObject{
	private MediaPlayer noise;
	private boolean isPlaying = false;

	public NoisyObject(String fileName, String voiceName) {
		super(fileName);
		URL soundUrl = ClassLoader.getSystemResource(voiceName);
	    if (soundUrl != null) {
	        noise = new MediaPlayer(new Media(soundUrl.toString())); 
	        noise.setOnEndOfMedia(() -> {
	        	new Thread(() -> {
	        		try {
	        			Thread.sleep(2000);
	        		} catch (InterruptedException e){
	        			e.printStackTrace();
	        		}
	        		isPlaying = false;
	        	}).start();
	        });
	    } else {
	        System.err.println("Sound file not found: " + voiceName); 
	    }
	}
	
	@Override
	public void onClick() {
		if (!isPlaying) {
			isPlaying = true;
			new Thread(() -> {
				Platform.runLater(() -> {
					noise.seek(Duration.ZERO);
					noise.play();
				});
			}).start();
		}
	}
	
}
