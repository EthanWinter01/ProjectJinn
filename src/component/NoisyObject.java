package component;

import java.net.URL;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class NoisyObject extends ImageObject{
	private MediaPlayer noise;
	private boolean isPlaying = false;
	private final static int PAUSE_DEFAULT = 2000;

	public NoisyObject(String fileName, String voiceName) {
		this(fileName, voiceName, PAUSE_DEFAULT);
	}
	
	public NoisyObject(String fileName, String voiceName, int pause) {
		super(fileName);
		this.setMediaPlayer(voiceName, pause);
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
	
	public MediaPlayer getMediaPlayer() {
		return noise;
	}
	
	public void setMediaPlayer(String voiceName) {
		this.setMediaPlayer(voiceName, PAUSE_DEFAULT);
	}
	
	public void setMediaPlayer(String voiceName, int pause) {
		URL soundUrl = ClassLoader.getSystemResource(voiceName);
	    if (soundUrl != null) {
	        noise = new MediaPlayer(new Media(soundUrl.toString())); 
	        noise.setOnEndOfMedia(() -> {
	        	new Thread(() -> {
	        		try {
	        			Thread.sleep(pause);
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
	
}
