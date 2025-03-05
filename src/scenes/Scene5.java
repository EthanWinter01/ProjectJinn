package scenes;

import java.util.ArrayList;

import base.AudibleObject;
import base.BaseObject;
import base.BaseScene;
import base.Blinker;
import base.FadeEffect;
import component.BackgroundMusic;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene5 extends BaseScene {
	// Fields
	private int sceneGuider = 0;
	private static final String BG_PATH = "scene5/BG_scene5_1.png";
	private ArrayList<Background> backgroundList;
	private ArrayList<BaseObject> ties, ps;
	private BaseObject blackscreen, keyboard, monitor, mouse, timetohavefun, welcomestupid, comsmile1, comsmile2, comsmile3, projector1, projector2;
		
	// Constructor
	public Scene5() {
		super(BG_PATH);
		initializeBackgrounds();
		initializeObjects();
		setupEventHandlers();
		
		this.getChildren().addAll(ties);
		this.getChildren().addAll(ps);
		this.getChildren().addAll(blackscreen, keyboard, monitor, mouse, timetohavefun, welcomestupid, 
								  comsmile1, comsmile2, comsmile3, projector1, projector2, new Blinker(), new FadeEffect());
	}

	private void initializeBackgrounds() {
		backgroundList = new ArrayList<Background>();
		backgroundList.add(getBackground());
		String[] bg = {"1", "2", "black", "red"};
		for (String file: bg) {
			backgroundList.add(createBackground("scene5/BG_scene5_" + file + ".png"));
		}
		
	}

	@Override
	protected void initializeObjects() {
		ties = new ArrayList<BaseObject>();
		ps = new ArrayList<BaseObject>();
		for (int i=0; i<7; i++) {
			int k = i + 1;
			ties.add(createBaseObject("tie" + k));
			ps.add(createBaseObject("p" + k));
		}
		comsmile1 = createBaseObject("comsmile_1");
		comsmile2 = createBaseObject("comsmile_2");
		comsmile3 = createBaseObject("comsmile_3");
		projector1 = createBaseObject("projector1");
		projector2 = createBaseObject("projector2");
		blackscreen = createBaseObject("blackscreen");
		keyboard = createBaseObject("keyboard");
		monitor = createBaseObject("monitor");
		mouse = createBaseObject("mouse");
		timetohavefun = createBaseObject("timetohavefun");
		welcomestupid = createBaseObject("welcomestupid");
		
		hideObjects();
	}

	@Override
	protected void setupEventHandlers() {
		handleThisPane(0);
		projector1.onClick(() -> handleProjectorClick(1));
		monitor.onClick(() -> handleMonitorClick(2));
		blackscreen.onClick(() -> handleBlackscreenClick(3));
		welcomestupid.onClick(() -> handleWelcomestupidClick(4));
		timetohavefun.onClick(() -> handleTimeToFunClick(5));
		setTiesOnClick(6, 11);
	}
	
	// Utility Method
	private void hideObjects() {
		for (int i=0; i<7; i++) {
			ties.get(i).close();
			ps.get(i).close();
		}
		comsmile1.close();
		comsmile2.close();
		comsmile3.close();
		projector1.close();
		projector2.close();
		blackscreen.close();
		keyboard.close();
		monitor.close();
		mouse.close();
		timetohavefun.close();
		welcomestupid.close();
		
	}
	
	private void handleThisPane(int expect) {
		this.setOnMouseClicked(event -> {
			if (sceneGuider == expect) {
				projector1.clickOnly();
				sceneGuider++;
			}
		});
	}

	private void handleProjectorClick(int expect) {
		if (sceneGuider == expect) {
			projector1.close();
			projector2.open();
			monitor.open();
			sceneGuider++;
		}		
	}
	
	private void handleMonitorClick(int expect) {
		if (sceneGuider == expect) {
			monitor.close();
			projector2.close();
			this.setBackground(backgroundList.get(2));
			runGlitchEffect();
			sceneGuider++;
		}
	}
	
	private void handleBlackscreenClick(int expect) {
		if (sceneGuider == expect) {
			blackscreen.close();
	        welcomestupid.open();
			sceneGuider++;
		}
	}
	
	private void handleWelcomestupidClick(int expect) {
		if (sceneGuider == expect) {
	        
	        Timeline sequence = new Timeline(
	            new KeyFrame(Duration.seconds(1), e -> {
	                setBackground(backgroundList.get(3)); // Blink black for 0.5 sec
	            }),
	            new KeyFrame(Duration.seconds(1.5), e -> {
	                setBackground(backgroundList.get(4)); //"scene5_red"
	                BackgroundMusic.stopMusic();
	                BackgroundMusic.playMusic("scene5/sound/pasatV2.mp3");
	                sceneGuider++;
	            })
	        );
	        
	        welcomestupid.close();
	        timetohavefun.open();
	        sequence.play();
	    }
	}
	
	private void handleTimeToFunClick(int expect) {
		if (sceneGuider == expect) {
			timetohavefun.viewOnly();
			for (int i=0; i<7; i++) {
				ties.get(i).open();
			}
			sceneGuider++;
		}
	}

	private void setTiesOnClick(int begin, int end) {
		for (int k=0; k<7; k++) {
			final int i = k;
			ties.get(i).onClick(() -> handleTiesClick(i, begin, end));
		}
	}
	
	private void handleTiesClick(int i, int begin, int end) {
		if (sceneGuider >= begin && sceneGuider <= end) {
			ties.get(i).close();
			ps.get(i).viewOnly();
			sceneGuider++;
		} else if (sceneGuider == end+1) {
			goToNextScene();
		}
	}
	
    private BaseObject createBaseObject(String name) {
        return new BaseObject("scene5/object/" + name + ".png");
    }
    
	private void runGlitchEffect() {
	    comsmile1.open();
	    comsmile2.open();
	    comsmile3.open();

	    Timeline glitchEffect = new Timeline(
	        new KeyFrame(Duration.millis(100), e -> {
	            comsmile1.setVisible(true);
	            comsmile2.setVisible(false);
	            comsmile3.setVisible(false);
	        }),
	        new KeyFrame(Duration.millis(200), e -> {
	            comsmile1.setVisible(false);
	            comsmile2.setVisible(true);
	            comsmile3.setVisible(false);
	        }),
	        new KeyFrame(Duration.millis(300), e -> {
	            comsmile1.setVisible(false);
	            comsmile2.setVisible(false);
	            comsmile3.setVisible(true);
	        })
	    );

	    glitchEffect.setCycleCount(10);
	    glitchEffect.setOnFinished(e -> {
	        comsmile1.close();
	        comsmile2.close();
	        comsmile3.close();
	        this.setBackground(backgroundList.get(3));//"scene5_black"
	        blackscreen.open();
	    });

	    glitchEffect.play();
	}
	
	private void goToNextScene() {
        GameLogic.transition(() -> {
        	new AudibleObject("","scene6/sound/heartdie.mp3").playAudio();
        }, 50);
        
        GameLogic.transition(() -> {
        	Platform.runLater(() -> {
        		toNextScene(new Scene6());
        	});
        }, 50);
	}
	
}
