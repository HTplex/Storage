package j14JavaFX;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class J02MultipleStage extends Application {
//	public static void main(String[] args){
//		Application.launch(args);
//	}
	public void start(Stage primaryStage){
		Scene scene=new Scene(new Button("OK"), 180, 240);
		primaryStage.setTitle("main");
		primaryStage.setScene(scene);
		primaryStage.show();
		Stage second=new Stage();
		second.setTitle("secondery");
		second.setScene(new Scene(new Button("2nd"), 120, 180));
		second.setResizable(false);
		second.show();
	}
}
