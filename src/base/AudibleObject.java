package base;

import java.net.URL;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class AudibleObject extends BaseObject {	
	// Field
	private String audioPath;
	private MediaPlayer player;
	private boolean isPlaying = false;
	private final static int DEFAULT_PAUSE = 2000; // milliseconds

	// Constructor
	public AudibleObject(String resPath, String audioPath) {
		this(resPath, audioPath, DEFAULT_PAUSE);
	}

	public AudibleObject(String resPath, String audioPath, int pause) {
		super(resPath);
		this.setMediaPlayer(audioPath, pause);
	}
	
	// Method
	public void playAudio() {
		if (!isPlaying) {
			isPlaying = true;
			new Thread(() -> {
				Platform.runLater(() -> {
					player.seek(Duration.ZERO);
					player.play();
				});
			}).start();
		}
	}
	
	// Getter & Setter
	public String getResPath() {
		return getResPath(false);
	}

	public void setResPath(String audioPath) {
		this.audioPath = audioPath;
	}
	
	public String getAudioPath() {
		return getAudioPath(false);
	}

	public String getAudioPath(boolean local) {
		if (local)
			return ClassLoader.getSystemResource(this.audioPath).toString();
		return audioPath;
	}


	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}
	
	public MediaPlayer getMediaPlayer() {
		return player;
	}
	
	public void setMediaPlayer(String audioPath) {
		this.setMediaPlayer(audioPath, DEFAULT_PAUSE);
	}
	
	public void setMediaPlayer(String audioPath, int pause) {
		URL audioUrl = ClassLoader.getSystemResource(audioPath);
	    if (audioUrl != null) {
	    	this.audioPath = audioPath;
	        player = new MediaPlayer(new Media(audioUrl.toString())); 
	        player.setOnEndOfMedia(() -> {
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
	        System.err.println("Sound file not found: " + audioPath); 
	    }
	}
}
