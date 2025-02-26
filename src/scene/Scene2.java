package scene;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.Background;

public class Scene2 extends ScenePane {

	private ArrayList<Background> backgroundList;
	
	public Scene2() {
		super("scene2/BG_scene2_1.png");
		String[] bg = {"light", "dark", "green", "red_notsmile", "red_smile"};
		backgroundList = new ArrayList<Background>();
		backgroundList.add(getBackground());
		for (int i=0; i<5; i++) {
			backgroundList.add(createBackground("scene2/BG_scene2_2_" + bg[i] + ".png"));
		}
		
		this.overall = new Scene(this, 900, 650);
	}

}
