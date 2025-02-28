package scene;

import java.net.URL;

import component.Blinker;
import component.ImageObject;
import component.NoisyObject;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene1_2 extends ScenePane {

    private Background[] backgrounds;
    private ImageObject busbell, chair, mirror;
    private NoisyObject hands, ghost;
    private boolean chairClicked = false;
    private boolean mirrorClicked = false;
    private boolean ghostAppeared = false;
    private boolean blinked = false;

    public Scene1_2() {
        super("scene1/BG_scene1_2_light.png");
        backgrounds = new Background[2];
        backgrounds[0] = getBackground();
        backgrounds[1] = createBackground("scene1/BG_scene1_2_dark.png");
    
        busbell = new ImageObject("scene1/object/busbell.png");
        chair = new ImageObject("scene1/object/chair.png");
        ghost = new NoisyObject("scene1/object/ghost.png", "scene1/sound/Jumpscare2.mp3");
        hands = new NoisyObject("scene1/object/hands.png", "scene1/sound/hand/paste.mp3");
        mirror = new ImageObject("scene1/object/mirror.png");
        
        Blinker blinker = new Blinker();
        Rectangle fadeOverlay = new Rectangle(900, 650, Color.BLACK);
        this.getChildren().addAll(mirror, hands, chair, ghost, busbell, blinker.getBlinker(), fadeOverlay);
    
        FadeTransition sceneFadeIn = new FadeTransition(Duration.seconds(1.5), fadeOverlay);
        sceneFadeIn.setFromValue(1.0); 
        sceneFadeIn.setToValue(0.0);   
        sceneFadeIn.setOnFinished(event -> this.getChildren().remove(fadeOverlay)); 
        sceneFadeIn.play();
        
        hands.setOpacity(0.0);
        hands.setMouseTransparent(true);
        ghost.setOpacity(0.0);
        ghost.setMouseTransparent(true);
        chair.setOnMouseClicked(event -> handleChairClick());
        mirror.setOnMouseClicked(event -> handleMirrorClick());
        busbell.setOnMouseClicked(event -> handleBusbellClick());
        
//      this.overall = new Scene(this, 900, 650);
    }

    private void handleChairClick() {
        if (!chairClicked) {
            chairClicked = true;
            sceneBlink(10);
        }
        
        if (chairClicked && mirrorClicked && blinked && !ghostAppeared) {
            ghost.setOpacity(1.0);
            ghost.getMediaPlayer().setStopTime(Duration.seconds(0.5));
            ghost.onClick();
            FadeTransition ghostFadeOut = new FadeTransition(Duration.seconds(0.1),  ghost) ;
            ghostFadeOut.setDelay(Duration.millis(500));
            ghostFadeOut.setFromValue(1.0);
            ghostFadeOut.setToValue(0.0);
            ghostFadeOut.setOnFinished(event -> {
            	this.getChildren().remove(hands);
            	this.getChildren().remove(ghost);
            	setBackground(backgrounds[0]);
            	getHeartBeat().stop();
            });
            ghostFadeOut.play();
            ghostAppeared = true;
            busbell.setOpacity(1);
            mirror.setOpacity(1);
            chair.setOpacity(1);
            
        }
    }

    private void handleMirrorClick() {
        if (chairClicked && !mirrorClicked && blinked) {
        	mirrorClicked = true;
        	hands.setOpacity(0.7);
        	hands.onClick();
        }
    }
    
    private void handleBusbellClick() {
    	if (chairClicked && mirrorClicked && ghostAppeared) {
    		new Thread(() -> {
    			Platform.runLater(() -> {
    				this.setNextScene(new Scene2());
    				if (this.nextScene != null) { // Ensure nextScene is not null
    					GameLogic.getStage().setScene(this.nextScene);
    				} else {
    					System.err.println("Next scene is not set!");
    				}
		   	   		busbell.onClick();
    			});
    		}).start();
    	}
    }

    private void sceneBlink(int durationSeconds) {
    	URL soundUrl = ClassLoader.getSystemResource("scene1/sound/light_pop.mp3");
        MediaPlayer popSound = null;
        
        if (soundUrl != null) {
            popSound = new MediaPlayer(new Media(soundUrl.toString()));
        } else {
            System.err.println("Sound file not found: scene1/sound/light_pop.mp3");
        }
        MediaPlayer finalPopSound = popSound;
    	
        Thread blinkThread = new Thread(() -> {
            long endTime = System.currentTimeMillis() + durationSeconds * 1000L;

            try {
            	getHeartBeat().setVolume(0.3);
            	getHeartBeat().play();
            	if (finalPopSound != null) {
                    finalPopSound.seek(Duration.ZERO);
                    finalPopSound.play();
                }
                while (System.currentTimeMillis() < endTime) {
                	
                	Thread.sleep(100);
                    Platform.runLater(() -> {
                    	busbell.setOpacity(1);
                        mirror.setOpacity(1);
                        chair.setOpacity(1);
                        setBackground(backgrounds[0]);
                    });

                    Thread.sleep(100);
                    Platform.runLater(() -> {
                        busbell.setOpacity(0);
                        mirror.setOpacity(0);
                        chair.setOpacity(0);
                        setBackground(backgrounds[1]);
                    });
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
            	blinked = true;
                Platform.runLater(() -> {
                    busbell.setOpacity(0);
                    mirror.setOpacity(0);
                    chair.setOpacity(0);
                    setBackground(backgrounds[1]);
                });
            }
        });

        blinkThread.setDaemon(true);
        blinkThread.start();
    }
}
