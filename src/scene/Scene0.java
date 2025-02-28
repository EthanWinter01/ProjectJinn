package scene;

import component.BackgroundMusic;
import component.Blinker;
import component.ImageObject;
import component.NoisyObject;
import javafx.application.Platform;
import javafx.scene.Cursor;
import logic.GameLogic;
import util.SceneTransitionHelper;

public class Scene0 extends ScenePane {

    private static final String BACKGROUND_PATH = "home/BG_home.png";
    private static final String MUSIC_FILE = "background_S0toS4.mp3";

    private static final String[] OBJECTS = {
        "home/object/label.png",
        "home/object/tree.png",
        "home/object/building.png"
    };
    
    private static final String[] SOUND_FILES = {
        "home/sound/haha(baby).mp3",
        "home/sound/haha(oldman).mp3",
        "home/sound/haha(women).mp3"
    };

    private ImageObject start;

    public Scene0() {
        super(BACKGROUND_PATH);
        
        start = createStartObject();
        
        Blinker blinker = new Blinker();
        
        this.getChildren().addAll(start, blinker.getBlinker());
        
        for (int i = 0; i < OBJECTS.length; i++) {
            NoisyObject noisyObject = new NoisyObject(OBJECTS[i], SOUND_FILES[i]);
            noisyObject.setOnMouseClicked(event -> noisyObject.onClick());
            this.getChildren().add(noisyObject);
        }

        BackgroundMusic.playMusic(MUSIC_FILE);
    }

    private ImageObject createStartObject() {
        ImageObject startObj = new ImageObject("home/object/start.png", 600, 460);
        startObj.setFitWidth(300);
        startObj.setFitHeight(300);
        startObj.setOpacity(0);

        startObj.setOnMouseEntered(event -> {
            startObj.setOpacity(1); 
            startObj.setCursor(Cursor.HAND);
        });

        startObj.setOnMouseExited(event -> {
            startObj.setOpacity(0);
            startObj.setCursor(Cursor.DEFAULT);
        });

        startObj.setOnMouseClicked(event -> {
            SceneTransitionHelper.transitionToScene(() -> {
                Platform.runLater(() -> {
                    this.setNextScene(new Scene1_1());
                    if (this.nextScene != null) {
                        GameLogic.getStage().setScene(this.nextScene);
                    } else {
                        System.err.println("Next scene is not set!");
                    }
                });
            }, 100);
        });

        return startObj;
    }
}
