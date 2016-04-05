package j14JavaFX;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.beans.property.*;
import javafx.beans.binding.*;
public class J10fontLable extends Application {
	public static void main(String[]args){
		Application.launch(args);
	}
	public void start(Stage primaryStage){
		StackPane pane=new StackPane();
		Circle circle=new Circle();
		circle.radiusProperty().bind(pane.heightProperty().divide(4).add(pane.widthProperty().divide(4)));
		circle.setStroke(Color.BISQUE);
		circle.setFill(Color.CHARTREUSE);
		pane.getChildren().add(circle);
		DoubleBinding dp=pane.heightProperty().multiply(pane.widthProperty());
		
		Label label=new Label();
		label.textProperty().bind(dp.asString());
		label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		pane.getChildren().add(label);
		Scene scene=new Scene(pane, 800, 600);
		primaryStage.setTitle("changing");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}	
