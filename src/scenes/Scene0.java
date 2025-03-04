package scenes;

import base.AudibleObject;
import base.BaseObject;
import base.BaseScene;
import base.BackgroundAudio;
import base.Blinker;
import javafx.application.Platform;
import logic.GameLogic;

public class Scene0 extends BaseScene {
	// Fields
	private static final String BG_PATH = "home/BG_home.png";
    private static final String BG_AUDIO_PATH = "background_S0toS4.mp3";

    private static final String[] OBJECTS = {
        "home/object/label.png",
        "home/object/tree.png",
        "home/object/building.png"
    };
    
    private static final String[] SOUND_FILES = {
        "home/sound/haha(baby).mp3",
        "home/sound/haha(oldman).mp3",
        "home/sound/haha(women).mp3"
    };
    
    private BaseObject start;
    private AudibleObject[] noisyBackground;
    
    // Constructor
    public Scene0() {
    	super(BG_PATH);
    	initializeObjects();
    	setupEventHandlers();
    	BackgroundAudio.playAudio(BG_AUDIO_PATH);
    	
    	this.getChildren().addAll(start, new Blinker());
    }

	@Override
	public void initializeObjects() {
		start = new BaseObject("home/object/start.png", 600, 460);
    	start.setFitWidth(300);
    	start.setFitHeight(300);
    	start.setOpacity(0);
    	
    	noisyBackground = new AudibleObject[3];
        for (int i = 0; i < OBJECTS.length; i++) {
        	noisyBackground[i] = new AudibleObject(OBJECTS[i], SOUND_FILES[i]);
        	noisyBackground[i].onClick(null);
            this.getChildren().add(noisyBackground[i]);
        }
	}
	
	@Override
	public void setupEventHandlers() {
		start.onClick(() -> {
			GameLogic.transition(() -> {
				noisyBackground[1].playAudio(); // This may not activate if this sound is playing
				Platform.runLater(() -> {
					this.toNextScene(new Scene1_1());
				});
			});
		});
		start.setOnMouseEntered(event -> start.open());
	    start.setOnMouseExited(event -> start.clickOnly());
	}
}
