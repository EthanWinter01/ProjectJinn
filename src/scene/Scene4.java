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
    private NoisyObject choice1, doorSound;
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
       choice1 = new NoisyObject("scene4/object/C1.png", "scene2/sound/blood.mp3");
       doorSound = new NoisyObject("", "scene4/sound/squeky-door-open-113212.mp3");
       
       this.getChildren().add(door);
       startTextFade(text);
       
       door.setOnMouseClicked(event -> {
           this.getChildren().remove(door);
           this.setBackground(next_background);
           this.getChildren().add(choice1);
           startTextFade(text2);
           startCountdown(5);
       });
       
       choice1.setOnMouseClicked(event -> handleCorrectClick());
    }

    private void startCountdown(int seconds) {
        remainingSeconds = seconds;
        countdownLabel = new Label(String.valueOf(remainingSeconds));
        countdownLabel.setFont(Font.font("Arial", 200));
        countdownLabel.setStyle("-fx-text-fill: white;");

        countdownPane = new StackPane(countdownLabel);
        countdownPane.setAlignment(Pos.CENTER);
        countdownPane.setPrefSize(900, 650);
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
        countdownTimeline.play();
    }

    private void handleTimeout() {
        Rectangle blackScreen = new Rectangle(900, 650, Color.BLACK);
        this.getChildren().add(blackScreen);
        
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
        countdownTimeline.stop();
        this.getChildren().remove(countdownPane);
        this.setBackground(next_background);
        
        doorSound.onClick();
        door.setOnMouseClicked(event -> goToNextScene());
        this.getChildren().add(door);
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

    public void startTextFade(ImageObject t) {
        this.getChildren().add(t);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), t);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), t);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setDelay(Duration.seconds(2));
        fadeOut.setOnFinished(event -> this.getChildren().remove(t));

        fadeIn.setOnFinished(event -> fadeOut.play());
        fadeIn.play();
    }
}