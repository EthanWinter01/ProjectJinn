package scenes;

import base.AudibleObject;
import base.BaseObject;
import base.BaseScene;
import base.BlackScreen;
import base.Blinker;
import base.FadeEffect;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene4 extends BaseScene {
	// Fields
	private static final String BG_PATH = "scene4/BG_scene4_1.png";
	
	private Background next_background;
	private BaseObject text, text2;
	private AudibleObject[] choice;
	private AudibleObject door;
	private Timeline countdownTimeline;
    private Label countdownLabel;
    private int remainingSeconds;
    private StackPane countdownPane;
	
	// Constructor
	public Scene4() {
		super(BG_PATH);
		next_background = this.createBackground("scene4/BG_scene4_2.png");
        initializeObjects();
        setupEventHandlers();
        this.getChildren().addAll(new Blinker(), new FadeEffect());
        this.getHeartBeatPlayer().setVolume(0.5);
        this.getHeartBeatPlayer().play();
	}

	@Override
	protected void initializeObjects() {
		text = new BaseObject("scene4/object/text1.png");
        text2 = new BaseObject("scene4/object/text2.png");
        door = new AudibleObject("scene4/object/door.png", "scene4/sound/opendoor.mp3");

        choice = new AudibleObject[6];
        for (int i = 0; i < 6; i++) {
            choice[i] = new AudibleObject("scene4/object/C"+(i+1)+".png", "scene4/sound/incorrect.mp3");
            choice[i].close();
        }
        choice[5].setMediaPlayer("scene4/sound/correct.mp3");
        this.getChildren().addAll(door, choice[0], choice[1], choice[2], choice[3], choice[4], choice[5]);
        startTextFade(text, 0);
	}

	@Override
	protected void setupEventHandlers() {
		door.setOnMouseClicked(event -> {
            door.close();
            this.setBackground(next_background);
            startTextFade(text2, 1);
        });
		
        for (int i = 0; i < 6; i++)
            choice[i].onClick(null);
        choice[5].onClick(() -> handleCorrectClick());
	}
	
    // Start Count Down Timer
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
        this.getHeartBeatPlayer().setVolume(1);

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
        for (AudibleObject obj : choice) 
        	obj.open();
        countdownTimeline.play();
    }
	
	// Handle Timeout (When Player Fails)
    private void handleTimeout() {
    	this.getHeartBeatPlayer().stop();
        this.getChildren().add(new BlackScreen());

        for (AudibleObject obj : choice) 
        	obj.close();

        if (getHeartBeatPlayer() != null) {
            getHeartBeatPlayer().stop();
        }
        
        new AudibleObject("", "scene1/sound/Jumpscar.mp3").playAudio();
        transitionToNextScene();
    }

    // Handle Correct Answer (CTRL-SP)
    private void handleCorrectClick() {
    	this.getHeartBeatPlayer().stop();
        for (AudibleObject obj : choice) 
        	obj.close();
        countdownTimeline.stop();
        this.getChildren().remove(countdownPane);
        this.setBackground(next_background);
        transitionToNextScene();
    }

    // Transition to Next Scene (Scene5)
    private void transitionToNextScene() {
    	GameLogic.transition(() -> {
    		Platform.runLater(() -> {
    			toNextScene(new Scene5());
    		});
    	}, 3000);
    }

    // Text Fade In & Out Effect
	public void startTextFade(BaseObject t, int flag) {
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
    }
}
