package scenes;

import java.net.URL;
import base.*;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene1_2 extends BaseScene {
    private static final String[] BG_PATH = {
        "scene1/BG_scene1_2_light.png", "scene1/BG_scene1_2_dark.png"
    };

    private AudibleObject hands, ghost;
    private BaseObject busbell, chair, window;
    private boolean chairClicked = false, windowClicked = false, ghostAppeared = false, blinked = false;

    public Scene1_2() {
        super(BG_PATH[0]);
        initializeObjects();
        setupEventHandlers();
    }

    @Override
    protected void initializeObjects() {
        hands = new AudibleObject("scene1/object/hands.png", "scene1/sound/hand/paste.mp3");
        ghost = new AudibleObject("scene1/object/ghost.png", "scene1/sound/hshnew.mp3");
        busbell = new BaseObject("scene1/object/busbell.png");
        chair = new BaseObject("scene1/object/chair.png");
        window = new BaseObject("scene1/object/mirror.png");

        getChildren().addAll(window, hands, chair, ghost, busbell, new Blinker(), new FadeEffect());
        hands.close();
        ghost.close();
    }

    @Override
    protected void setupEventHandlers() {
        busbell.onClick(() -> handleBusbellClick());
        chair.onClick(() -> handleChairClick());
        window.onClick(() -> handleWindowClick());
    }

    private void handleChairClick() {
        if (!chairClicked) {
            chairClicked = true;
            sceneBlink(10);
        }
        checkGhostAppearance();
    }

    private void handleWindowClick() {
        if (chairClicked && !windowClicked && blinked) {
            windowClicked = true;
            hands.setOpacity(0.7);
            hands.playAudio();
        }
    }

    private void handleBusbellClick() {
        if (chairClicked && windowClicked && ghostAppeared) {
            GameLogic.transition(() -> {
            	Platform.runLater(() -> {
            		toNextScene(new Scene2());
            	});
            });
        }
    }

    private void checkGhostAppearance() {
        if (chairClicked && windowClicked && blinked && !ghostAppeared) {
            ghost.setOpacity(1.0);
            ghost.getMediaPlayer().setStopTime(Duration.seconds(0.5));
            ghost.playAudio();

            FadeTransition ghostFadeOut = new FadeTransition(Duration.seconds(0.1), ghost);
            ghostFadeOut.setDelay(Duration.millis(500));
            ghostFadeOut.setFromValue(1.0);
            ghostFadeOut.setToValue(0.0);
            ghostFadeOut.setOnFinished(event -> {
                getChildren().removeAll(hands, ghost);
                setBackground(createBackground(BG_PATH[0]));
                getHeartBeatPlayer().stop();
            });
            ghostFadeOut.play();

            ghostAppeared = true;
            busbell.setOpacity(1);
            window.setOpacity(1);
            chair.setOpacity(1);
        }
    }

    private void sceneBlink(int durationSeconds) {
        URL soundUrl = ClassLoader.getSystemResource("scene1/sound/light_pop.mp3");
        MediaPlayer popSound = (soundUrl != null) ? new MediaPlayer(new Media(soundUrl.toString())) : null;

        new Thread(() -> {
            try {
                getHeartBeatPlayer().setVolume(0.3);
                getHeartBeatPlayer().play();
                if (popSound != null) {
                    popSound.seek(Duration.ZERO);
                    popSound.play();
                }

                long endTime = System.currentTimeMillis() + durationSeconds * 1000L;
                while (System.currentTimeMillis() < endTime) {
                    toggleVisibility(true);
                    Thread.sleep(100);
                    toggleVisibility(false);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                blinked = true;
                toggleVisibility(false);
            }
        }).start();
    }

    private void toggleVisibility(boolean isVisible) {
        double value = isVisible ? 1.0 : 0.0;
        Platform.runLater(() -> {
            setBackground(createBackground(isVisible ? BG_PATH[0] : BG_PATH[1]));
            busbell.setOpacity(value);
            window.setOpacity(value);
            chair.setOpacity(value);
        });
    }
}