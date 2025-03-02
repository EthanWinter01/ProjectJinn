//package scene;
//
//import component.ImageObject;
//import javafx.animation.FadeTransition;
//import javafx.scene.layout.Background;
//import javafx.util.Duration;
//
///*
//Scene 4
//หน้า BG_scene4_1
//       มี text1 ขึ้นให้อ่านแล้วหาย 
//		กด door แล้วพาเข้า BG_scene4_2
//หน้า BG_scene4_2
//      มี text2 ขึ้นให้อ่านแล้วหาย
//		มีแป้นขึ้น ซึ่งมีตัวอักษร L,A,T,E,M,P,0(ศูนย์), O (โอ), C,H,-,2,5,1,S,D,R + จับเวลา  ให้ผู้เล่นกด
//		ถ้าผู้เล่นกดตัวอักษรถูก (เฉลยคือ CTRL-SP) ภายในเวลาจะตัดเข้าซีน5 ถ้าหมดเวลาก่อนตอบถูกให้มีเสียงกรี้ดแสบหูก่อนเข้าซีน5
//
// */
package scene;

import component.ImageObject;
import component.NoisyObject;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene4 extends ScenePane {
    private Background next_background;
    private ImageObject text, text2, door;
    private NoisyObject choice[], doorSound;
    private Timeline countdownTimeline;
    private Label countdownLabel;
    private int remainingSeconds;
    private StackPane countdownPane;

    public Scene4() {
       super("scene4/BG_scene4_1.png");
       next_background = this.createBackground("scene4/BG_scene4_2.png");
       text = new ImageObject("scene4/object/text1.png");
       text2 = new ImageObject("scene4/object/text2.png");
       door = new ImageObject("scene4/object/door.png");
       choice = new NoisyObject[6];
       for (int i=0; i<6; i++) {
    	   choice[i] = new NoisyObject("scene4/object/C"+(i+1)+".png", "scene2/sound/blood.mp3");
    	   choice[i].close();
       }
       doorSound = new NoisyObject("", "scene4/sound/squeaky-door-open-113212.mp3");
       
       this.getChildren().addAll(door,doorSound, choice[0], choice[1], choice[2], choice[3], choice[4], choice[5]);
       door.close();
       doorSound.close();
       
       door.setOnMouseClicked(event -> {
    	   door.close();
           this.setBackground(next_background);
           startTextFade(text2, 1);
       });
       
       choice[0].setOnMouseClicked(event -> {});
       choice[1].setOnMouseClicked(event -> {});
       choice[2].setOnMouseClicked(event -> {});
       choice[3].setOnMouseClicked(event -> {});
       choice[4].setOnMouseClicked(event -> {});
       choice[5].setOnMouseClicked(event -> handleCorrectClick());
       
       Rectangle fadeOverlay = new Rectangle(900, 650, Color.BLACK);
//       fadeOverlay.setMouseTransparent(true);
		this.getChildren().addAll(fadeOverlay, GameLogic.getBlinker().blackScene);
		FadeTransition sceneFadeIn = new FadeTransition(Duration.seconds(3), fadeOverlay);
       sceneFadeIn.setFromValue(1.0); 
       sceneFadeIn.setToValue(0.0);   
       sceneFadeIn.setDelay(Duration.seconds(2));
       sceneFadeIn.setOnFinished(event -> {
    	   this.getChildren().remove(fadeOverlay); // Remove after fade
    	   startTextFade(text, 0);
       });
       sceneFadeIn.play(); 
    }

    private void startCountdown(int seconds) {
        remainingSeconds = seconds;
        countdownLabel = new Label(String.valueOf(remainingSeconds));
        countdownLabel.setFont(Font.font("Arial", 200));
        countdownLabel.setStyle("-fx-text-fill: white;");

        countdownPane = new StackPane(countdownLabel);
        countdownPane.setAlignment(Pos.CENTER);
        countdownPane.setPrefSize(900, 650);
        countdownPane.setMouseTransparent(true);
        this.getChildren().add(countdownPane);

        countdownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingSeconds--;
            if (remainingSeconds >= 0) {
                countdownLabel.setText(String.valueOf(remainingSeconds));
            } else {
                countdownTimeline.stop();
                this.getChildren().remove(countdownPane);
                handleTimeout();
            }
        }));
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);
        for (int i=0; i<6; i++)
        	choice[i].open();
        countdownTimeline.play();
    }

    private void handleTimeout() {
        Rectangle blackScreen = new Rectangle(900, 650, Color.BLACK);
        this.getChildren().add(blackScreen);
        for (int i=0; i<6; i++)
     	   choice[i].close();
        
        if (getHeartBeat() != null) {
            getHeartBeat().stop();
        }
        
        NoisyObject scream = new NoisyObject("", "scene1/sound/Jumpscar.mp3");
        scream.onClick();
        
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Platform.runLater(() -> {
                this.setNextScene(new Scene5());
                GameLogic.getStage().setScene(this.nextScene);
            });
        }).start();
    }

    private void handleCorrectClick() {
    	for (int i=0; i<6; i++)
       	   choice[i].close();
        countdownTimeline.stop();
        this.getChildren().remove(countdownPane);
        this.setBackground(next_background);
        
        doorSound.onClick();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Platform.runLater(() -> {
                this.setNextScene(new Scene5());
                GameLogic.getStage().setScene(this.nextScene);
            });
        }).start();
    }

    private void goToNextScene() {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Platform.runLater(() -> {
                this.setNextScene(new Scene5());
                GameLogic.getStage().setScene(this.nextScene);
            });
        }).start();
    }

    public void startTextFade(ImageObject t, int flag) {
        this.getChildren().add(t);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), t);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), t);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setDelay(Duration.seconds(2));
        fadeOut.setOnFinished(event -> {
        	this.getChildren().remove(t);
        	if (flag == 0) {
        		door.open();
        	} else if (flag == 1) {
        		startCountdown(5);
        	}
        });
        fadeOut.play();
//        fadeIn.setOnFinished(event -> fadeOut.play());
//        fadeIn.play();
    }
}
