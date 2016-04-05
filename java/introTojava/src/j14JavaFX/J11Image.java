package j14JavaFX;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.stage.*;
//import javafx.sce
public class J11Image extends Application{
	public static void main(String[] args){
		Application.launch(args);
	}
	public void start(Stage primaryStage){
		Pane pane=new HBox(10);
		pane.setPadding(new Insets(5,5,5,5));
		Image image=new Image("/src/test.png");////mac path
		pane.getChildren().add(new ImageView(image));// and try webimage
		ImageView iv2=new ImageView(image);
		iv2.setFitHeight(100);
		iv2.setFitWidth(100);
		pane.getChildren().add(iv2);
		ImageView iv3=new ImageView(image);
		iv3.setRotate(45);
		pane.getChildren().add(iv3);
		Scene scene=new Scene(pane, 800, 800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("go");
		primaryStage.show();
	}
}
