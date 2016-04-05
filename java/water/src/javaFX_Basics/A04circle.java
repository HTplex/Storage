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
public class A04circle extends Application{
  public void start(Stage primaryStage){
    Circle circle=new Circle();
    circle.setCenterX(100);
    circle.setCenterY(200);
    circle.setRadius(50);
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.YELLOW);

    Pane pane=new Pane();
    pane.getChildren().add(circle);
    Scene scene=new Scene(pane, 800, 600);
    primaryStage.setTitle("still circle");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String[]args){
    Application.launch(args);
  }
}
