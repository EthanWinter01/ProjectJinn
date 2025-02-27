package scene;

import component.BackgroundMusic;
import component.Blinker;
import component.ImageObject;
import component.NoisyObject;
import javafx.application.Platform;
import javafx.scene.Cursor;
import logic.GameLogic;

public class Scene0 extends ScenePane {

	private ImageObject start;
	
	public Scene0() {
		super("home/BG_home.png");
		   	
    	start = new ImageObject("home/object/start.png", 600, 460);
    	start.setFitWidth(300);
    	start.setFitHeight(300);
    	start.setOpacity(0);


    	NoisyObject label = new NoisyObject("home/object/label.png", "home/sound/haha(baby).mp3");
    	label.setOnMouseClicked(event -> {
    		label.onClick();
    	});
    	
    	NoisyObject tree = new NoisyObject("home/object/tree.png", "home/sound/haha(oldman).mp3");
    	tree.setOnMouseClicked(event -> {
    		tree.onClick();
    	});
    	
    	NoisyObject building = new NoisyObject("home/object/building.png", "home/sound/haha(women ).mp3");
    	building.setOnMouseClicked(event -> {
    		building.onClick();
    	});
    	
    	Blinker blinker = new Blinker();
    	this.getChildren().addAll(start, label, tree, building, blinker.getBlinker());
    	
    	start.setOnMouseEntered(event -> {
	   		start.setOpacity(1); // Make the image fully visible
            start.setCursor(Cursor.HAND);
	    });
	   	start.setOnMouseExited(event -> {
	    	start.setOpacity(0); 
	        start.setCursor(Cursor.DEFAULT);
	    });
    	start.setOnMouseClicked(event -> {
    	    new Thread(() -> {
    	        try {
    	            Thread.sleep(100); // Small delay to ensure smooth transition
    	        } catch (InterruptedException e) {
    	            e.printStackTrace();
    	        }
    	        Platform.runLater(() -> {
    	        	this.setNextScene(new Scene1_1());
    	            if (this.nextScene != null) { // Ensure nextScene is not null
    	            	GameLogic.getStage().setScene(this.nextScene);
    	                tree.onClick();
    	            } else {
    	                System.err.println("Next scene is not set!");
    	            }
    	        });
    	    }).start();
    	});
    	
    	BackgroundMusic.playMusic("home/sound/BGM_home.mp3");
	}

}
