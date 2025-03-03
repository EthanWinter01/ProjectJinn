package component;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Blinker {

    private static final double FADE_IN_OPACITY = 1.0;
    private static final double FADE_OUT_OPACITY = 0.1;
    private static final long FADE_IN_DURATION = 15000;  
    private static final long FADE_OUT_DURATION = 20;    
    
    public final Rectangle blackScene;  

    public Blinker() {
        blackScene = new Rectangle(900, 650);
        blackScene.setFill(Color.BLACK);
        blackScene.setMouseTransparent(true); 
        blackScene.setOpacity(FADE_OUT_OPACITY);  
        
        startBlinking();
    }

    private void startBlinking() {
        
        Thread blinkThread = new Thread(() -> {
            try {
                while (true) {
                    
                    Platform.runLater(() -> blackScene.setOpacity(FADE_OUT_OPACITY));
                    Thread.sleep(FADE_IN_DURATION); 

                    Platform.runLater(() -> blackScene.setOpacity(FADE_IN_OPACITY));
                    Thread.sleep(FADE_OUT_DURATION); 
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
