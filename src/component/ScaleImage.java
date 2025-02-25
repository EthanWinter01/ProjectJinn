package component;

import javafx.scene.image.ImageView;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane; // Or StackPane, or your container

public class ScaleImage extends ImageView {

    private double originalImageWidth;
    private double originalImageHeight;
    private Pane container; // Store a reference to the container

    public ScaleImage(String fileName, Pane parent) { // Pass the parent Pane
        super(new Image(ClassLoader.getSystemResource(fileName).toString()));

        // Get original image dimensions:
        Image image = this.getImage();
        originalImageWidth = image.getWidth();
        originalImageHeight = image.getHeight();

        this.setPreserveRatio(true);
        this.setCursor(Cursor.HAND);

        // Bind scaling to the parent container, not the stage:
        this.fitWidthProperty().bind(parent.widthProperty()); // Use the parent Pane
        // OR
        // this.fitHeightProperty().bind(parent.heightProperty()); // If you want to fit to height

        container = parent; // Store reference

        setOnMouseClicked(event -> {
            System.out.println("click " + fileName);
        });
    }

    public ScaleImage(String fileName, Pane parent, double x, double y) {
        this(fileName, parent);
        setPosition(x, y);
    }

    public void setPosition(double x, double y) {
        // Calculate the scaled coordinates:
        double scaleX = this.getFitWidth() / originalImageWidth;
        double scaleY = this.getFitHeight() / originalImageHeight;

        this.setLayoutX(x * scaleX);
        this.setLayoutY(y * scaleY);
    }
}