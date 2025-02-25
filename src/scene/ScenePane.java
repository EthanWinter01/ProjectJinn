package scene;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public abstract class ScenePane extends Pane {
	
	protected Scene overall;
	protected Scene nextScene;
	
	public ScenePane(String fileName) {		
    	this.setBackground(createBackground(fileName));
	}
	
	public Background createBackground(String fileName) {
		Image backgroundImage = new Image(ClassLoader.getSystemResource(fileName).toString());
    	BackgroundImage background = new BackgroundImage(
    			backgroundImage,
    			BackgroundRepeat.NO_REPEAT, 
    			BackgroundRepeat.NO_REPEAT,
    			BackgroundPosition.CENTER,
    			new BackgroundSize(100, 100, true, true, true, false)
//    			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false)
		);
    	return new Background(background);
	}
	
	public Scene getOverall() {
		return overall;
	}
	
	public void setNextScene(ScenePane nextScene) {
		this.nextScene = nextScene.getOverall();
	}
	
	public abstract void next();
}
