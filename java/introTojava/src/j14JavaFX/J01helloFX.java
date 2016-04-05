package j14JavaFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class J01helloFX extends Application {
	public static void main(String[] args){
		Application.launch(args);
	}
	public void start(Stage primaryStage){
		Button OK=new Button("OK");
		Scene scene=new Scene(OK,240,320);
		primaryStage.setTitle("HelloFX");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
