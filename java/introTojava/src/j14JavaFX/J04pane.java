package j14JavaFX;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
public class J04pane extends Application{
	public void start(Stage primaryStage){
		StackPane pane=new StackPane();
		pane.getChildren().add(new Button("OK"));
		Scene scene=new Scene(pane, 100, 50);
		primaryStage.setTitle("go");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	public static void main(String[] args){
		Application.launch(args);
	}
}
