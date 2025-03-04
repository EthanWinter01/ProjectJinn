package base;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BlackScreen extends Rectangle {
	public BlackScreen() {
		super(900, 650, Color.BLACK);
		this.setMouseTransparent(true);
	}
}
