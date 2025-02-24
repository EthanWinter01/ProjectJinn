package scene;

import component.ImageObject;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import logic.GameLogic;

public class Scene1_2 extends ScenePane {

	private Background[] backgrounds;
	private ImageObject busbell, chair, ghost, hands, mirror;
	
	public Scene1_2() {
		super("scene1/BG_scene1_2_light.png");
		backgrounds = new Background[2];
		backgrounds[0] = getBackground();
		backgrounds[1] = createBackground("scene1/BG_scene1_2_dark.png");
		
		busbell = new ImageObject("scene1/busbell.png");
		chair = new ImageObject("scene1/chair.png");
		ghost = new ImageObject("scene1/ghost.png");
		hands = new ImageObject("scene1/hands.png");
		mirror = new ImageObject("scene1/mirror.png");
		
		this.getChildren().addAll(mirror, chair, busbell);
		mirror.setOnMouseClicked(event -> {
			this.getChildren().add(hands);
		});
		chair.setOnMouseClicked(event -> {
			this.getChildren().add(ghost);
		});
		busbell.setOnMouseClicked(event -> {
			GameLogic.getStage().setScene(this.nextScene);
		});
		
		this.overall = new Scene(this, 900, 650);
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub

	}

}
