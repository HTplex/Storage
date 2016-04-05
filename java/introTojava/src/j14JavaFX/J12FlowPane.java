package j14JavaFX;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
public class J12FlowPane extends Application{
	public static void main(String[]args){
		Application.launch(args);
	}
	public void start(Stage primaryStage){
		FlowPane pane=new FlowPane();
		pane.setPadding(new Insets(11, 100, 43, 290));//around boundry
		pane.setHgap(5);
		pane.setVgap(5);
		pane.getChildren().addAll(new Label("your name"), 
				new TextField(),
				new Label("MI")
				);
		TextField tf=new TextField();
		tf.setPrefColumnCount(1);
		pane.getChildren().addAll(tf, new Label("Last name"),
		new TextField());
		Scene scene=new Scene(pane, 500, 600);
		primaryStage.setTitle("i hate this class");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
