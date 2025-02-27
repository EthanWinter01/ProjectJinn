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
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.scene.text.Font; // Import Font

public class Scene4 extends ScenePane {
    private Background next_background;
    private ImageObject text, text2, door;
    private ImageObject[] pass;
    private Timeline countdownTimeline;
    private Label countdownLabel;
    private int remainingSeconds;

    public Scene4() {
        super("scene4/BG_scene4_1.png");
        next_background = this.createBackground("scene4/BG_scene4_2.png");
        text = new ImageObject("scene4/object/text1.png");
        text2 = new ImageObject("scene4/object/text2.png");
        door = new ImageObject("scene4/object/door.png");

        pass = new ImageObject[6];
        for (int i = 0; i < 6; i++) {
            pass[i] = new ImageObject("scene4/object/C" + (i + 1) + ".png");
        }

        this.getChildren().add(door);
        startTextFade(text);

        door.setOnMouseClicked(event -> {
            this.getChildren().remove(door);
            this.setBackground(next_background);
            this.getChildren().addAll(pass);
            startTextFade(text2);
            startCountdown(5); // Start 5-second countdown when door is clicked
        });
    }

    private void startCountdown(int seconds) {
        remainingSeconds = seconds;
        countdownLabel = new Label(String.valueOf(remainingSeconds));

        // Make the font bigger and fit the app size (adjust as needed)
        countdownLabel.setFont(Font.font("Arial", 200));  // Adjust font and size
        countdownLabel.setStyle("-fx-text-fill: white;"); // Optional: Set text color

        StackPane countdownPane = new StackPane(countdownLabel);
        countdownPane.setAlignment(Pos.CENTER);
        countdownPane.setPrefSize(900, 650); // Set to app size
        this.getChildren().add(countdownPane);

        countdownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingSeconds--;
            if (remainingSeconds >= 0) {
                countdownLabel.setText(String.valueOf(remainingSeconds));
            } else {
                countdownTimeline.stop();
                this.getChildren().remove(countdownPane); // Remove countdown display
                handleTimeout();
            }
        }));
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);
        countdownTimeline.play();

        pass[0].setOnMouseClicked(event -> {
            if (countdownTimeline != null && countdownTimeline.getStatus() == Timeline.Status.RUNNING) {
                countdownTimeline.stop();
                this.getChildren().remove(countdownPane); // Remove countdown display
                handleCorrectClick();
            }
        });
    }

    private void handleTimeout() {
        // Handle what happens if the player doesn't click pass[0] in 5 seconds
        System.out.println("Timeout! Player didn't click pass[0] in time.");
        // Add your timeout logic here (e.g., show a message, change scene, etc.)
    }

    private void handleCorrectClick() {
        // Handle what happens if the player clicks pass[0] within 5 seconds
        System.out.println("Correct click! Player clicked pass[0] in time.");
        // Add your correct click logic here (e.g., advance to next scene, etc.)
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