package scene;

import java.util.ArrayList;

import component.ImageObject;
import javafx.scene.Scene;
import javafx.scene.layout.Background;

public class Scene2 extends ScenePane {

	private ArrayList<Background> backgroundList;
	private ImageObject text, hi, cheater, paper, printer, ready, doubleA, smile_1, smile_2, smile_3, smile_4;
	public Scene2() {
		super("scene2/BG_scene2_1.png");
		String[] bg = {"light", "dark", "green", "red_notsmile", "red_smile"};
		backgroundList = new ArrayList<Background>();
		backgroundList.add(getBackground());
		for (int i=0; i<5; i++) {
			backgroundList.add(createBackground("scene2/BG_scene2_2_" + bg[i] + ".png"));
		}
		
		text = new ImageObject("scene2/object/text.png");
		printer = new ImageObject("scene2/object/printer.png");
		hi = new ImageObject("scene2/object/hi.png");
		cheater = new ImageObject("scene2/object/cheater.png");
		paper = new ImageObject("scene2/object/paper.png");
		ready = new ImageObject("scene2/object/ready.png");
		doubleA = new ImageObject("scene2/object/doubleA.png");
		smile_1 = new ImageObject("scene2/object/smile_1.png");
		smile_2 = new ImageObject("scene2/object/smile_2.png");
		smile_3 = new ImageObject("scene2/object/smile_3.png");
		smile_4 = new ImageObject("scene2/object/smile_4.png");
		
		this.getChildren().addAll(printer);
		
		printer.setOnMouseClicked(event -> {
			this.getChildren().remove(printer);
			this.setBackground(backgroundList.get(1));
			this.getChildren().add(text);
		});
		
		this.overall = new Scene(this, 900, 650);
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub

	}

}
