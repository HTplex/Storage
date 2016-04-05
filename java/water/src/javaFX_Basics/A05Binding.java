package javaFX_Basics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



/**
 * Created by htplex on 20/7/2015.
 */
public class A05Binding extends Application{
  public void start(Stage primaryStage){
    Pane pane=new Pane();

    Circle circle=new Circle();
    circle.centerXProperty().bind(pane.widthProperty().divide(2));
    circle.centerYProperty().bind(pane.heightProperty().divide(3));
    circle.radiusProperty().bind(pane.heightProperty().divide(6));
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.YELLOW);

    pane.getChildren().add(circle);
    Scene scene=new Scene(pane,400,400);
    primaryStage.setScene(scene);
    primaryStage.setTitle("nah too tired for this");
    primaryStage.show();
  }
  public static void main(String[]args){
    Application.launch(args);
  }
}
