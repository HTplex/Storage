package j14JavaFX;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.*;
public class J09styleRotate extends Application{
	public static void main(String[]args){
		Application.launch(args);
	}
	public void start(Stage primaryStage){
		StackPane pane=new StackPane();
		Button ok=new Button("OK");
		ok.setStyle("-fx-border-color: blue;");
		pane.getChildren().add(ok);
		pane.setStyle("-fx-border-color: red; -fx-background-color: yellow;");
		pane.setRotate(37.5);
		Scene scene=new Scene(pane, 400, 400);
		primaryStage.setTitle("rotate");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
