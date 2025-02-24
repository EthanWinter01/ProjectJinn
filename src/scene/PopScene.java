package scene;

import java.util.ArrayList;

import component.ImageObject;
import javafx.scene.Scene;
import javafx.scene.layout.Background;

public class PopScene extends ScenePane {

	private ImageObject building, bushtree, bus, text, busbell, chair, doorofbus, ghost, hands, mirror;
	private ArrayList<Background> backgroundList;
	private int phase = 0;
	
	public PopScene() {
		super("scene1/BG_scene1_1.png");
		backgroundList = new ArrayList<Background>();
		backgroundList.add(getBackground());
		backgroundList.add(this.createBackground("scene1/BG_scene1_2_light.png"));
		backgroundList.add(this.createBackground("scene1/BG_scene1_2_dark.png"));
		
		building = new ImageObject("scene1/building.png");
		bushtree = new ImageObject("scene1/bushtree.png");
		bus = new ImageObject("scene1/bus.png");
		text = new ImageObject("scene1/text.png");
		busbell = new ImageObject("scene1/busbell.png");
		chair = new ImageObject("scene1/chair.png");
		doorofbus = new ImageObject("scene1/doorofbus.png");
		ghost = new ImageObject("scene1/ghost.png");
		hands = new ImageObject("scene1/hands.png");
		mirror = new ImageObject("scene1/mirror.png");
		
		this.getChildren().addAll(building, bushtree, bus);
    	//
    	this.overall = new Scene(this, 900, 650);
    	next();
	}

	@Override
	public void next() {
		ghost.setOnMouseClicked(event -> {
			this.setBackground(backgroundList.get(phase%2 + 1));
			phase++;
		});
		
		bus.setOnMouseClicked(event -> {
			if (phase == 1) {
				this.getChildren().clear();
				this.setBackground(backgroundList.get(1));	
				this.getChildren().addAll(mirror, chair, doorofbus, busbell, hands, ghost);
			} else {
				this.getChildren().add(text);
			}
			phase++;
		});
		
		
	}

}
