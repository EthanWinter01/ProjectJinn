package scenes;

import base.BackgroundAudio;
import base.BaseObject;
import base.BaseScene;
import component.BackgroundMusic;
import base.AudibleObject;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene5 extends BaseScene {
	private static final String BG_PATH = "scene5/BG_scene5_1.png";
	private static final String BG_AUDIO_PATH[] = {
			"scene5/sound/horrorspeed.mp3",
			"scene5/sound/pasatV2.mp3",
			"scene6/sound/heartdie.mp3"
	};
		
    private int sceneGuider = 0;
    private BaseObject[] ties, comsmile, projector;
    private AudibleObject[] ps;
    private BaseObject blackscreen, keyboard, monitor, mouse, timetohavefun, welcomestupid;
    private Background[] backgrounds;

    public Scene5() {
        super(BG_PATH);
        BackgroundAudio.stopAudio();
        BackgroundAudio.playAudio(BG_AUDIO_PATH[0]);

        initializeBackgrounds();
        initializeObjects();
        setupEventHandlers();
        setupSceneTransition();
    }

    private void initializeBackgrounds() {
        String[] bg = {"1", "2", "black", "red"};
        backgrounds = new Background[bg.length];
        for (int i = 0; i < bg.length; i++) {
            backgrounds[i] = createBackground("scene5/BG_scene5_" + bg[i] + ".png");
        }
    }

    @Override
	protected void initializeObjects() {
        ties = new BaseObject[7];
        ps = new AudibleObject[7];
        comsmile = new BaseObject[3];
        projector = new BaseObject[2];

        for (int i = 0; i < 7; i++) {
            ties[i] = new BaseObject("scene5/object/tie"+(i+1)+".png");
            ps[i] = new AudibleObject("scene5/object/p"+(i+1)+".png", "scene5/sound/ghost"+(i+1)+".mp3");
            ties[i].close();
            ps[i].close();
        }

        for (int i = 0; i < 3; i++) {
            comsmile[i] = new BaseObject("scene5/object/comsmile_" + (i + 1) + ".png");
            comsmile[i].close();
        }

        projector[0] = new BaseObject("scene5/object/projector1.png");
        projector[1] = new BaseObject("scene5/object/projector2.png");

        blackscreen = new BaseObject("scene5/object/blackscreen.png");
        keyboard = new BaseObject("scene5/object/keyboard.png");
        monitor = new BaseObject("scene5/object/monitor.png");
        mouse = new BaseObject("scene5/object/mouse.png");
        timetohavefun = new BaseObject("scene5/object/timetohavefun.png");
        welcomestupid = new BaseObject("scene5/object/welcomestupid.png");

        closeObjects(blackscreen, keyboard, monitor, mouse, timetohavefun, welcomestupid, projector[0], projector[1]);
        this.getChildren().addAll(ties);
        this.getChildren().addAll(ps);
        this.getChildren().addAll(comsmile);
        this.getChildren().addAll(projector);
        this.getChildren().addAll(blackscreen, keyboard, monitor, mouse, timetohavefun, welcomestupid);
    }

    private void closeObjects(BaseObject... objects) {
        for (BaseObject obj : objects) 
        	obj.close();
    }
    
    @Override
	protected void setupEventHandlers() {
        this.setOnMouseClicked(event -> {
            if (sceneGuider == 0) {
                projector[0].open();
                projector[0].setOpacity(0);
                sceneGuider = 1;
            }
        });
        projector[0].setOnMouseClicked(event -> transitionProjector());
        monitor.setOnMouseClicked(event -> transitionMonitor());
        blackscreen.setOnMouseClicked(event -> transitionBlackscreen());
        welcomestupid.setOnMouseClicked(event -> transitionWelcomestupid());
        timetohavefun.setOnMouseClicked(event -> transitionTimeToHaveFun());
        setupTiesClickEvents();
    }

    private void transitionProjector() {
        if (sceneGuider == 1) {
            projector[0].close();
            projector[1].open();
            monitor.open();
            sceneGuider = 2;
        }
    }

    private void transitionMonitor() {
        if (sceneGuider == 2) {
            projector[1].close();
            monitor.close();
            setBackground(backgrounds[1]);
            runGlitchEffect();
            sceneGuider = 3;
        }
    }

    private void transitionBlackscreen() {
        if (sceneGuider == 4) {
            blackscreen.close();
            welcomestupid.open();
            sceneGuider = 5;
        }
    }

    private void transitionWelcomestupid() {
        if (sceneGuider == 5) {
            welcomestupid.close();
            timetohavefun.open();
            Timeline sequence = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> setBackground(backgrounds[2])),
                new KeyFrame(Duration.seconds(1.5), e -> {
                    setBackground(backgrounds[3]);
                    BackgroundAudio.stopAudio();
                    BackgroundAudio.playAudio(BG_AUDIO_PATH[1]);
                    sceneGuider = 6;
                })
            );
            sequence.play();
        }
    }

    private void transitionTimeToHaveFun() {
        if (sceneGuider == 6) {
            timetohavefun.close();
            for (BaseObject tie : ties) tie.open();
            sceneGuider = 7;
        }
    }

    private void setupTiesClickEvents() {
        for (int i = 0; i < 7; i++) {
            final int index = i;
            ties[i].setOnMouseClicked(event -> {
                ties[index].close();
                if (sceneGuider >= 7 && sceneGuider < 13) {
                    ps[index].open();
                    ps[index].playAudio();
                    sceneGuider++;
                } else if (sceneGuider == 13) {
                    goToNextScene();
                }
            });
        }
    }
    
    private void runGlitchEffect() {
        comsmile[0].open();
        comsmile[1].open();
        comsmile[2].open();

        Timeline glitchEffect = new Timeline(
            new KeyFrame(Duration.millis(100), e -> {
                comsmile[0].setVisible(true);
                comsmile[1].setVisible(false);
                comsmile[2].setVisible(false);
            }),
            new KeyFrame(Duration.millis(200), e -> {
                comsmile[0].setVisible(false);
                comsmile[1].setVisible(true);
                comsmile[2].setVisible(false);
            }),
            new KeyFrame(Duration.millis(300), e -> {
                comsmile[0].setVisible(false);
                comsmile[1].setVisible(false);
                comsmile[2].setVisible(true);
            })
        );

        glitchEffect.setCycleCount(10);
        glitchEffect.setOnFinished(e -> {
            comsmile[0].close();
            comsmile[1].close();
            comsmile[2].close();
            setBackground(backgrounds[2]);
            blackscreen.open();
            sceneGuider = 4;
        });

        glitchEffect.play();
    }

    private void goToNextScene() {
	    new Thread(() -> {
	        try {
	            Thread.sleep(3000); // Pause for 3 seconds before blackout
	            Platform.runLater(() -> {
	                BackgroundMusic.stopMusic();
	                BackgroundMusic.playMusic(BG_AUDIO_PATH[2]);
	                Rectangle blackOut = new Rectangle(900, 650, Color.BLACK);
	                this.getChildren().add(blackOut);
	            });

	            Thread.sleep(5000); // Wait for 5 seconds on blackout
	            
	            Platform.runLater(() -> {
	                toNextScene(new Scene6());
	            });

	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }).start();
	}
    
    private void setupSceneTransition() {
        Rectangle fadeOverlay = new Rectangle(900, 650, Color.BLACK);
        this.getChildren().addAll(fadeOverlay, GameLogic.getBlinker().blackScene);
        FadeTransition sceneFadeIn = new FadeTransition(Duration.seconds(3), fadeOverlay);
        sceneFadeIn.setFromValue(1.0);
        sceneFadeIn.setToValue(0.0);
        sceneFadeIn.setOnFinished(event -> this.getChildren().remove(fadeOverlay));
        sceneFadeIn.play();
    }
}
