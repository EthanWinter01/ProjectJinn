package scene;

import java.util.ArrayList;

import component.Blinker;
import component.ImageObject;
import component.NoisyObject;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene2 extends ScenePane {

	
    private int sceneGuider = 0;
    private ArrayList<Background> backgroundList;
    private ImageObject screenReady, smile1, smile2, smile4, text;
    private NoisyObject hi, cheater, scanner, printerBody, paper, smile3;
    
    public Scene2() {
        super("scene2/BG_scene2_1.png");
        String[] bg = {"light", "dark", "green","red_notsmile", "red_smile"};
        backgroundList = new ArrayList<Background>();
        backgroundList.add(getBackground());
        for (int i = 0; i < 5; i++) {
            backgroundList.add(createBackground("scene2/BG_scene2_2_" + bg[i] + ".png"));
        }
        
        scanner = new NoisyObject("scene2/object/scanner.png", "scene2/sound/printer2.mp3", 10);
        cheater = new NoisyObject("scene2/object/cheater.png", "scene2/sound/blood.mp3", 10);
        hi = new NoisyObject("scene2/object/hi.png", "scene2/sound/blood.mp3", 10);
        paper = new NoisyObject("scene2/object/paper.png", "scene2/sound/paper.mp3", 10);
        printerBody = new NoisyObject("scene2/object/printer_body.png", "scene2/sound/printer1.mp3", 10);
        screenReady = new ImageObject("scene2/object/screen_ready.png");
        smile1 = new ImageObject("scene2/object/smile_1.png");
        smile2 = new ImageObject("scene2/object/smile_2.png");
        smile3 = new NoisyObject("scene2/object/smile_3.png", "scene2/sound/scar.mp3", 10);
        smile4 = new ImageObject("scene2/object/smile_4.png");
        text = new ImageObject("scene2/object/text.png");
        startTextFade(text);
        
        cheater.close();
        hi.close();
        paper.close();
        printerBody.close();
        screenReady.close();
        smile1.close();
        smile2.close();
        smile3.close();
        smile4.close();
        
        Blinker blinker = new Blinker();
        Rectangle fadeOverlay = new Rectangle(900, 650, Color.BLACK);
        this.getChildren().addAll(scanner, printerBody, screenReady, hi, cheater, paper, smile1, smile2, smile3, smile4, text, blinker.getBlinker(), fadeOverlay);
        
        FadeTransition sceneFadeIn = new FadeTransition(Duration.seconds(1.5), fadeOverlay);
        sceneFadeIn.setFromValue(1.0);
        sceneFadeIn.setToValue(0.0);
        sceneFadeIn.setOnFinished(event -> this.getChildren().remove(fadeOverlay)); // Remove after fade
        sceneFadeIn.play();
        
        scanner.setOnMouseClicked(event -> {
        	scanner.close();
        	setBackground(backgroundList.get(1));
        	printerBody.open();
        	sceneGuider = 1;
        }); // Scene2_2_light
        
        printerBody.setOnMouseClicked(event -> {
        	if (sceneGuider == 1) {
        		screenReady.open();
        		printerBody.close();
        		printerBody.onClick();
        		sceneGuider = 2;
        	}
        	if (sceneGuider == 3) {
        		hi.open();
        		printerBody.close();
        		setBackground(backgroundList.get(4));
        		printerBody.onClick();
        		sceneGuider = 4;
        	}
        });
        
        screenReady.setOnMouseClicked(event -> {
        	if (sceneGuider == 2) {
        		printerBody.setMouseTransparent(false);
	        	screenReady.close();
	            setBackground(backgroundList.get(2)); // BG_scene2_2_red_not_smile
	            sceneGuider = 3;
        	}
        });
        
        hi.setOnMouseClicked(event -> {
        	hi.onClick();
        	if (sceneGuider == 4) {
        		printerBody.close();
        		hi.close();
        		smile1.open();
	        	setBackground(backgroundList.get(4)); // BG_scene2_2_red_smile
	        	if (getHeartBeat() != null) {
                    getHeartBeat().setVolume(0.4);
                    getHeartBeat().play();
                }
	        	sceneGuider = 5;
        	}
        });
        
        cheater.setOnMouseClicked(event -> {
        	cheater.onClick();
        	if (sceneGuider == 6) {	
        		cheater.close();
        		paper.open();
        		setBackground(backgroundList.get(3)); // BG_scene2_2_green
        		sceneGuider = 7;
        	}
        });
        
        paper.setOnMouseClicked(event -> {
        	paper.onClick();
            if (sceneGuider == 7) {
            	paper.close();
            	smile1.open();
            	setBackground(backgroundList.get(2)); 
            	sceneGuider = 8;
            } else if (sceneGuider == 9) {
            	paper.close();
            	smile2.open();
            	setBackground(backgroundList.get(2)); 
            	sceneGuider = 10;
            } else if (sceneGuider == 11) {
            	paper.close();
            	smile3.open();
            	setBackground(backgroundList.get(2));
            	sceneGuider = 12;
            }
        });
        
        smile1.setOnMouseClicked(event -> {
            if (sceneGuider == 5) {
                smile1.close();
                cheater.open();
                setBackground(backgroundList.get(5));
                sceneGuider = 6;
            } else if (sceneGuider == 8) {
                smile1.close();
                paper.open();
                setBackground(backgroundList.get(3));
                sceneGuider = 9;
            }
        });
        
        smile2.setOnMouseClicked(event -> { 
        	if (sceneGuider == 10) {
        		smile2.close();
        		paper.open();
        		setBackground(backgroundList.get(3)); 
        		sceneGuider = 11;
        		if (getHeartBeat() != null) {
                    getHeartBeat().setVolume(0.8);
                }
                
        	}
        }); // BG_scene2_2_green
        
        smile3.setOnMouseClicked(event -> {
            if (sceneGuider == 12) {
                smile4.setOpacity(1);
                smile3.onClick();
                smile3.close();
                
                new Thread(() -> {
                    try {
                        Thread.sleep(3000); // Hold for 3 seconds
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    Platform.runLater(() -> {
                        this.setNextScene(new Scene3());
                        if (this.nextScene != null) { // Ensure nextScene is not null
                            GameLogic.getStage().setScene(this.nextScene);
                        } else {
                            System.err.println("Next scene is not set!");
                        }
                        
                        if (getHeartBeat() != null) {
                            getHeartBeat().stop();
                        }
                    });
                }).start();
            }
        });
	}
}
