package scene;

import component.ImageObject;
import javafx.application.Platform;
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
		
		busbell = new ImageObject("scene1/object/busbell.png");
		chair = new ImageObject("scene1/object/chair.png");
		ghost = new ImageObject("scene1/object/ghost.png");
		hands = new ImageObject("scene1/object/hands.png");
		mirror = new ImageObject("scene1/object/mirror.png");
		
		this.getChildren().addAll(mirror, chair, busbell);
		mirror.setOnMouseClicked(event -> {
			this.getChildren().add(hands);
		});
		chair.setOnMouseClicked(event -> {
			this.getChildren().add(ghost);
			sceneBlink(10);
		});
		busbell.setOnMouseClicked(event -> {
			GameLogic.getStage().setScene(this.nextScene);
		});
		
		this.overall = new Scene(this, 900, 650);
	}
	
	private void sceneBlink(int durationSeconds) {
		
		Thread blinkThread = new Thread(() -> {
	        long endTime = System.currentTimeMillis() + durationSeconds * 1000L;

	        try {
	            while (System.currentTimeMillis() < endTime) {
	                Thread.sleep(100);

	                Platform.runLater(() -> {
	                	busbell.setOpacity(1);
	                	mirror.setOpacity(1);
	                    setBackground(backgrounds[0]);
	                });

	                Thread.sleep(100);

	                Platform.runLater(() -> {
	                	busbell.setOpacity(0);
	                	mirror.setOpacity(0);
	                    setBackground(backgrounds[1]);
	                });

	            }
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        } finally {
	            Platform.runLater(() -> {
	                setBackground(backgrounds[0]); 
	            });
	        }
	    });

	    blinkThread.setDaemon(true); // Allow application to exit even if thread is running
	    blinkThread.start();
		
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub

	}

}
