package scene;

import component.Blinker;
import component.ImageObject;
import component.NoisyObject;
import component.FadeInEffect; // Import FadeInEffect
import javafx.application.Platform;
import logic.GameLogic;
import util.SceneTransitionHelper;

public class Scene1_1 extends ScenePane {

    public Scene1_1() {
        super("scene1/BG_scene1_1.png");

        NoisyObject building = createNoisyObject("scene1/object/building.png", "scene1/sound/crow.mp3", 800);
        NoisyObject bushtree = createNoisyObject("scene1/object/bushtree.png", "scene1/sound/treemove.mp3", 800);
        NoisyObject bus = createNoisyObject("scene1/object/bus.png", "scene1/sound/car/car-horn1.mp3", 500);
        ImageObject text = new ImageObject("scene1/object/text.png");
        NoisyObject doorofbus = createNoisyObject("scene1/object/doorofbus.png", "scene1/sound/bus_door.mp3", 500);
        
        Blinker blinker = new Blinker();
        this.getChildren().addAll(building, bushtree, bus, doorofbus, text, blinker.getBlinker());

        FadeInEffect fadeInEffect = new FadeInEffect(900, 650, javafx.scene.paint.Color.BLACK);
        this.getChildren().add(fadeInEffect.getFadeOverlay());
        fadeInEffect.playFadeInEffect();

        startTextFade(text);

        setupClickEvents(building, bushtree, bus, doorofbus);
    }

    // Method to create a NoisyObject with click functionality
    private NoisyObject createNoisyObject(String imagePath, String soundPath, int delay) {
        NoisyObject noisyObject = new NoisyObject(imagePath, soundPath, delay);
        noisyObject.setOnMouseClicked(event -> noisyObject.onClick());
        return noisyObject;
    }

    // Set up mouse click events for the objects
    private void setupClickEvents(NoisyObject building, NoisyObject bushtree, NoisyObject bus, NoisyObject doorofbus) {
        // Set specific on-click behaviors for the objects
        doorofbus.setOnMouseClicked(event -> handleBusDoorClick(doorofbus));
    }

    // Handle click event for the bus door to transition to next scene
    private void handleBusDoorClick(NoisyObject doorofbus) {
        SceneTransitionHelper.transitionToScene(() -> { 
            Platform.runLater(() -> {
                this.setNextScene(new Scene1_2());
                if (this.nextScene != null) {
                    GameLogic.getStage().setScene(this.nextScene);
                } else {
                    System.err.println("Next scene is not set!");
                }
                doorofbus.onClick();  // Trigger the door's click event
            });
        
        }, 100);
    }
}
