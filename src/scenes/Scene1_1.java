package scenes;

import base.AudibleObject;
import base.BaseObject;
import base.BaseScene;
import base.Blinker;
import base.FadeEffect;
import javafx.application.Platform;
import logic.GameLogic;

public class Scene1_1 extends BaseScene {
	// Field
	private static final String BG_PATH = "scene1/BG_scene1_1.png";
	
	private AudibleObject building, bushtree, bus, doorofbus;
	private BaseObject text;
	
	// Constructor
	public Scene1_1() {
		super(BG_PATH);
		initializeObjects();
		setupEventHandlers();
		this.getChildren().addAll(building, bushtree, bus, doorofbus, text, new Blinker(), new FadeEffect());
		this.startTextFade(text);
	}
	
	@Override
	public void initializeObjects() { 
		building = new AudibleObject("scene1/object/building.png", "scene1/sound/crow.mp3", 800);
		bushtree = new AudibleObject("scene1/object/bushtree.png", "scene1/sound/treemove.mp3", 800);
		bus = new AudibleObject("scene1/object/bus.png", "scene1/sound/car/car-horn1.mp3", 500);
		doorofbus = new AudibleObject("scene1/object/doorofbus.png", "scene1/sound/bus_door.mp3", 500);
		text = new BaseObject("scene1/object/text.png");
	}
	
	@Override
	public void setupEventHandlers() {
		building.onClick(null);
		bushtree.onClick(null);
		bus.onClick(null);
		doorofbus.onClick(() -> {
    		GameLogic.transition(() -> {
    			Platform.runLater(() -> {
    				this.toNextScene(new Scene1_2());
    			});
    		});
    	});
	}
}
