package component;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Blinker {
	public Rectangle blackScene;
	// blinker / fade 
	
	public Blinker() {
	    blackScene = new Rectangle(900, 650);
	    blackScene.setFill(Color.BLACK);
	    blackScene.setMouseTransparent(true);
	    
	    Thread blinkThread = new Thread(() -> {
	        try {
	            while (true) {
	                Platform.runLater(() -> blackScene.setOpacity(0.1));
	                Thread.sleep(150);
	                Platform.runLater(() -> blackScene.setOpacity(1.0));
	                Thread.sleep(20);
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    });
	    blinkThread.setDaemon(true);
	    blinkThread.start();
	}

	public Rectangle getBlinker() {
		return blackScene;
	}
	
}
