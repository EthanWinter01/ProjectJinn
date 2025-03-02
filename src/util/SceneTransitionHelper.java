package util;

import javafx.application.Platform;

public class SceneTransitionHelper {

    // Utility method to handle scene transition with optional delay
    public static void transitionToScene(Runnable sceneTransitionLogic, long delayMillis) {
        new Thread(() -> {
            try {
                Thread.sleep(delayMillis); // Optional delay before transitioning
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(sceneTransitionLogic);
        }).start();
    }
}