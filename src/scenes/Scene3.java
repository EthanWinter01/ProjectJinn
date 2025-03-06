package scenes;

import java.util.ArrayList;

import base.AudibleObject;
import base.BaseObject;
import base.BaseScene;
import base.Blinker;
import base.FadeEffect;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.util.Duration;
import logic.GameLogic;

public class Scene3 extends BaseScene {
	// Fields
	private static final String BG_PATH = "scene3/BG_scene3_1.png";
	
	private int sceneGuider = 0;
	private ArrayList<Background> backgroundList;
	private BaseObject bg1_sink, text, urinal;
	private AudibleObject crack, oneghost, faucet, optionalghost, tryMe;
	private FadeEffect fadeOverlay;
	
	// Constructor
	public Scene3() {
		super(BG_PATH);
		initializeBackgrounds();
		initializeObjects();
		setupEventHandlers();
		
		fadeOverlay = new FadeEffect();
		this.getChildren().addAll(bg1_sink, crack, faucet, oneghost, optionalghost, tryMe, urinal, text, new Blinker(), fadeOverlay);
		startTextFade(text);
	}

	private void initializeBackgrounds() {
		backgroundList = new ArrayList<Background>();
		backgroundList.add(getBackground());
		String[] bg = {"light", "med", "dark","zoom_in", "crack", "ghost"};
		for (String file: bg) {
			backgroundList.add(createBackground("scene3/BG_scene3_2_" + file + ".png"));
		}
    }
	
	@Override
	protected void initializeObjects() {
		bg1_sink = createAudibleObject("bg1_sink", "open_faucet2", 500);
        crack = createAudibleObject("crack", "glass-breaking", 500);
        faucet = createAudibleObject("faucet", "open_faucet2", 500);
        oneghost = createAudibleObject("oneghost", "turn_on_light", 500);
        optionalghost = createAudibleObject("optionalghost", "jtoilet");
        tryMe = new AudibleObject("scene3/object/tryme.png", "scene2/sound/blood.mp3");
        urinal = createAudibleObject("urinal", "toilet");
        text = createBaseObject("text");
        
		hideObjects();
	}
	
	private AudibleObject createAudibleObject(String name, String sound, int pause) {
        return new AudibleObject("scene3/object/"+ name+".png", "scene3/sound/"+sound+".mp3", pause);
    }

    private AudibleObject createAudibleObject(String name, String sound) {
        return new AudibleObject("scene3/object/" + name + ".png", "scene3/sound/" + sound + ".mp3");
    }

    private BaseObject createBaseObject(String name) {
        return new BaseObject("scene3/object/" + name + ".png");
    }

    private void hideObjects() {
        crack.close();
        faucet.close();
        oneghost.close();
        optionalghost.close();
        tryMe.close();
        urinal.close();
    }
    
	@Override
	protected void setupEventHandlers() {
		bg1_sink.onClick(() -> updateScene(bg1_sink, 1, backgroundList.get(1), urinal));
		urinal.onClick(() -> handleUrinalClick());
		faucet.onClick(() -> handleFaucetClick());
		oneghost.onClick(() -> updateScene(oneghost, 4, backgroundList.get(4), tryMe));
		tryMe.onClick(() -> handleTryMeClick());
		crack.onClick(() -> handleCrackClick());
	}
	
	// Utility Methods
    private void updateScene(BaseObject obj, int nextState, Background bg, BaseObject nextObj) {
    	obj.close();
        if (bg != null)
        	setBackground(bg);
        if (nextObj != null) 
        	nextObj.open();
        sceneGuider = nextState;
    }

    private void handleUrinalClick() {
        updateScene(urinal, 2, backgroundList.get(2), faucet);
        oneghost.playAudio();
    }

    private void handleFaucetClick() {
        updateScene(faucet, 3, backgroundList.get(3), oneghost);
        oneghost.playAudio();
        getHeartBeatPlayer().setVolume(0.4);
        getHeartBeatPlayer().play();
    }
    
    private void handleTryMeClick() {
        if (sceneGuider == 4) {
            updateScene(tryMe, 5, backgroundList.get(5), crack);
            crack.playAudio();
        }
    }

    private void handleCrackClick() {
        if (sceneGuider == 5) {
            crack.getMediaPlayer().setVolume(0.6);
            crack.playAudio();
            this.setBackground(backgroundList.get(6));
            sceneGuider = 6;
        } else if (sceneGuider == 6) {
            handleFinalSceneTransition();
        }
    }

    // Scene Transition to Scene4
    private void handleFinalSceneTransition() {
        optionalghost.open();
        optionalghost.playAudio();

        FadeTransition ghostFadeOut = new FadeTransition(Duration.seconds(0.001), optionalghost);
        ghostFadeOut.setDelay(Duration.millis(3000));
        ghostFadeOut.setFromValue(1.0);
        ghostFadeOut.setToValue(0.0);
        ghostFadeOut.setOnFinished(event -> transitionToScene4());
        ghostFadeOut.play();
    }

    // Transition with Black Screen Effect
    private void transitionToScene4() {
        fadeOverlay.setOpacity(1.0);
        fadeOverlay.setMouseTransparent(false);
        optionalghost.close();
        crack.close();

        GameLogic.transition(() -> {
        	(new AudibleObject("idiotdot.png","scene3/sound/runaway_toilet.mp3")).playAudio();
        	Platform.runLater(() -> {
        		fadeOverlay.setOpacity(0.0);
        	});
        }, 2000);
        
        GameLogic.transition(() -> {
        	Platform.runLater(() -> {
        		toNextScene(new Scene4());
        	});
        }, 5000);
    }
}
