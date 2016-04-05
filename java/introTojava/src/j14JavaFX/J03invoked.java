package j14JavaFX;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
public class J03invoked extends Application{
	public J03invoked(){
		System.out.println("this class is invoked");
	}
	public static void main(String[] args){
		System.out.println("this main class is invoked");
		Application.launch(args);
	}
	public void start(Stage primaryStage){
		System.out.println("this method is invoked");
		primaryStage.setScene(new Scene(new Button("hehe"), 100, 100));
		primaryStage.show();
	}
}
