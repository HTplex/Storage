package j14JavaFX;
import  javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.beans.property.*;
import javafx.beans.binding.*;
public class J07exercise extends Application {
	public static void main(String[]args){
		Application.launch(args);
	}
	public void start(Stage primaryStage){
		Pane pane=new Pane();
		Circle circle=new Circle();
		circle.radiusProperty().bind(pane.heightProperty().divide(4));
		circle.centerXProperty().bind(pane.widthProperty().divide(3));
		circle.centerYProperty().bind(pane.heightProperty().divide(3));
		//circle.setFill(Color.AQUA);
		//circle.fillProperty().bind(pane.);
		circle.setFill(Color.AQUA);
		pane.getChildren().add(circle);
		
		Scene scene=new Scene(pane, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("nope");
		primaryStage.show();
	}
}
