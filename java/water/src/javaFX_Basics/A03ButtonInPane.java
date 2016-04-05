package javaFX_Basics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by htplex on 20/7/2015.
 */
public class A03ButtonInPane extends Application{
  public void start(Stage primaryStage){
    StackPane pane=new StackPane();
    pane.getChildren().add(new Button("OK"));
    Scene scene=new Scene(pane, 200, 200);
    primaryStage.setScene(scene);
    primaryStage.setTitle("pane");
    primaryStage.show();
  }
  public static void main(String[]args){
    Application.launch(args);
  }
}
