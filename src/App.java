import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		HBox root = new HBox();
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(root, 400, 200);
		
		root.setOnMouseClicked((e) -> {
			double x = e.getX();
			double y = e.getY();
			System.out.println("x = " + x + ", y = " + y);
		});
		
		stage.setScene(scene);
		stage.setTitle("SaaYoong");
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
