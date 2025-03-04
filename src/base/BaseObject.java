package base;

import interfaces.Clickable;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BaseObject extends ImageView implements Clickable {
	// Fields
	private String resPath;
	protected Blinker blinker;
	
	public final static int SCENE_WIDTH = 900;
	public final static int SCENE_HEIGHT = 650;
	
	// Constructors
	public BaseObject(String resPath) {
		this(resPath, 0, 0);
	}
	
	public BaseObject(String resPath, double x, double y) { 
		super(new Image(ClassLoader.getSystemResource(resPath).toString()));
		this.setFitWidth(SCENE_WIDTH);
		this.setFitHeight(SCENE_HEIGHT);
		this.setPreserveRatio(true);
		this.setCursor(Cursor.HAND);
		setPosition(x, y);
	}
	
	// Methods
	public void setPosition(double x, double y) {
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
	
	public void onClick(Runnable eventHandler) {
		this.setOnMouseClicked(event -> {
			if (this instanceof AudibleObject) {
				((AudibleObject) this).playAudio();
			}
			if (eventHandler != null)
				eventHandler.run();
		});
		// use for debugging
		// System.out.println("You are clicking on object from"+getResPath(false));
	}
	
	@Override
	public void open() {
		this.setOpacity(1.0);
		this.setMouseTransparent(false);
		this.setCursor(Cursor.HAND);
	}

	@Override
	public void viewOnly() {
		this.setOpacity(1.0);
		this.setMouseTransparent(true);
		this.setCursor(Cursor.DEFAULT);
	}

	@Override
	public void clickOnly() {
		this.setOpacity(0.0);
		this.setMouseTransparent(false);
		this.setCursor(Cursor.HAND);
	}

	@Override
	public void close() {
		this.setOpacity(0.0);
		this.setMouseTransparent(true);
		this.setCursor(Cursor.DEFAULT);
	}

	// Getters & Setters
	public String getResPath() {
		return getResPath(false);
	}
	
	public String getResPath(boolean local) {
		if (local)
			return ClassLoader.getSystemResource(this.resPath).toString();
		return resPath;
	}

	public void setResPath(String resPath) {
		this.resPath = resPath;
	}
}
