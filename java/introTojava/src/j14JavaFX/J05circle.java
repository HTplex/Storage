package j14JavaFX;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
public class J05circle extends Application{
	public static void main(String[] args) {
		Application.launch(args);
	}
	public void start(Stage primaryStage){
		Circle circle=new Circle();
		circle.setCenterX(100.0);
		circle.setCenterY(100.0);
		circle.setRadius(50);
		circle.setFill(Color.RED);
		StackPane pane= new StackPane();
		pane.getChildren().add(circle);
		Scene scene=new Scene(pane, 320, 480);
		primaryStage.setTitle("hehe");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
