package j14JavaFX;
import javafx.application.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.stage.*;
public class J06circleplus extends Application {
	public static void main(String[]args){
		Application.launch(args);
	}
	public void start(Stage primaryStage){
		Pane pane=new Pane();
		Circle circle=new Circle();
		circle.radiusProperty().bind(pane.widthProperty().divide(3));;
		circle.centerXProperty().bind(pane.widthProperty().divide(2));
		circle.centerYProperty().bind(pane.heightProperty().divide(2));
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHITE);
		pane.getChildren().add(circle);
		Scene scene=new Scene(pane, 640, 480);
		primaryStage.setTitle("show");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
