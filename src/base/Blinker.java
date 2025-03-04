package base;

import javafx.application.Platform;

public class Blinker extends BlackScreen {
	
	private static final double FADE_IN_OPACITY = 1.0;
    private static final double FADE_OUT_OPACITY = 0.1;
    private static final long FADE_IN_DURATION = 15000;  
//    private static final long FADE_IN_DURATION = 150;  
    private static final long FADE_OUT_DURATION = 20;  
   
	public Blinker() {
		startBlinking();
	}
	
	private void startBlinking() {
        
        Thread blinkThread = new Thread(() -> {
            try {
                while (true) {
                    Platform.runLater(() -> this.setOpacity(FADE_OUT_OPACITY));
                    Thread.sleep(FADE_IN_DURATION); 

                    Platform.runLater(() -> this.setOpacity(FADE_IN_OPACITY));
                    Thread.sleep(FADE_OUT_DURATION); 
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        blinkThread.setDaemon(true); 
        blinkThread.start();
    }
}
