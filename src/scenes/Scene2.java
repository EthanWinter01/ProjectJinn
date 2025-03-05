package scenes;

import java.util.ArrayList;

import base.AudibleObject;
import base.BaseObject;
import base.BaseScene;
import base.Blinker;
import base.FadeEffect;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import logic.GameLogic;

public class Scene2 extends BaseScene {
	// Fields
	private static final String BG_PATH = "scene2/BG_scene2_1.png";
		
	private int sceneGuider = 0;
	private ArrayList<Background> backgroundList;
	private BaseObject screenReady, smile1, smile2, smile4, text;
	private AudibleObject hi, cheater, scanner, printerBody, paper, smile3;
	
	// Constructor
	public Scene2() {
		super(BG_PATH);
		initializeBackgrounds();
		initializeObjects();
		setupEventHandlers();
		
		this.getChildren().addAll(scanner, printerBody, screenReady, hi, cheater, paper, smile1, smile2, smile3, smile4, text, new Blinker(), new FadeEffect());
	}

	private void initializeBackgrounds() {
        String[] bg = {"light", "dark", "green", "red_notsmile", "red_smile"};
        backgroundList = new ArrayList<>();
        backgroundList.add(getBackground());
        for (String bgName : bg) {
            backgroundList.add(createBackground("scene2/BG_scene2_2_" + bgName + ".png"));
        }
    }

	@Override
	protected void initializeObjects() {
        scanner = createAudibleObject("scanner", "printer2");
        cheater = createAudibleObject("cheater", "blood");
        hi = createAudibleObject("hi", "blood");
        paper = createAudibleObject("paper", "paper");
        printerBody = createAudibleObject("printer_body", "printer1");
        smile3 = createAudibleObject("smile_3", "scar");
        screenReady = createBaseObject("screen_ready");
        smile1 = createBaseObject("smile_1");
        smile2 = createBaseObject("smile_2");
        smile4 = createBaseObject("smile_4");
        text = createBaseObject("text");
        startTextFade(text);
        
        cheater.close();
        hi.close();
        paper.close();
        printerBody.close();
        smile3.close();
        screenReady.close();
        smile1.close();
        smile2.close();
        smile4.close();
    }
	
	private AudibleObject createAudibleObject(String name, String sound) {
        return new AudibleObject("scene2/object/" + name + ".png", "scene2/sound/" + sound + ".mp3", 10);
	}
    private BaseObject createBaseObject(String name) {
        return new BaseObject("scene2/object/" + name + ".png");
    }

    @Override
    protected void setupEventHandlers() {
        scanner.onClick(() -> updateScene(scanner, 1, backgroundList.get(1), printerBody));
        printerBody.onClick(() -> handlePrinterBodyClick());
        screenReady.onClick(() -> {
        	updateScene(screenReady, 3, backgroundList.get(2), printerBody);
        	printerBody.clickOnly();
        });
        hi.onClick(() -> handleHiClick());
        cheater.onClick(() -> updateScene(cheater, 7, backgroundList.get(3), paper));
        paper.onClick(() -> handlePaperClick());
        smile1.onClick(() -> handleSmile1Click());
        smile2.onClick(() -> updateScene(smile2, 11, backgroundList.get(3), paper));
        smile3.onClick(() -> handleSmile3Click());
    }
    
    private void updateScene(BaseObject obj, int nextState, Background bg, BaseObject nextObj) {
        obj.close();
        if (bg != null)
        	setBackground(bg);
        if (nextObj != null) 
        	nextObj.open();
        sceneGuider = nextState;
    }
	
    private void handlePrinterBodyClick() {
        if (sceneGuider == 1) {
            updateScene(printerBody, 2, null, screenReady);
        } else if (sceneGuider == 3) {
            updateScene(printerBody, 4, backgroundList.get(4), hi);
        }
    }

    private void handleHiClick() {
        if (sceneGuider == 4) {
            updateScene(hi, 5, backgroundList.get(4), smile1);
            if (this.getHeartBeatPlayer() != null) {
                this.getHeartBeatPlayer().setVolume(0.4);
                this.getHeartBeatPlayer().play();
            }
        }
    }

    private void handlePaperClick() {
    	if (sceneGuider == 7 || sceneGuider == 9 || sceneGuider == 11) {
    	    BaseObject nextSmile;
    	    switch (sceneGuider) {
    	        case 7:
    	            nextSmile = smile1;
    	            break;
    	        case 9:
    	            nextSmile = smile2;
    	            break;
    	        default: // case 11
    	        	this.getHeartBeatPlayer().setVolume(1.0);
    	            nextSmile = smile3;
    	            break;
    	    }
    	    updateScene(paper, sceneGuider + 1, backgroundList.get(2), nextSmile);
    	}
    }

    private void handleSmile1Click() {
        if (sceneGuider == 5) {
            updateScene(smile1, 6, backgroundList.get(5), cheater);
        } else if (sceneGuider == 8) {
            updateScene(smile1, 9, backgroundList.get(3), paper);
        }
    }

    private void handleSmile3Click() {
        if (sceneGuider == 12) {
            smile4.setOpacity(1);
            smile3.onClick(null);
            smile3.close();
            GameLogic.transition(() -> {
            	Platform.runLater(() -> {
            		this.getHeartBeatPlayer().stop();
            		toNextScene(new Scene3());
            	});
            }, 3000);
        }
    }
}