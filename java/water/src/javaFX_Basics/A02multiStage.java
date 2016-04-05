package javaFX_Basics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by htplex on 19/7/2015.
 */
public class A02multiStage extends Application {
  public static void main(String[] args) {
    Application.launch(args);

  }

  public void start(Stage primaryStage) {
    Scene scene = new Scene(new Button("1st"), 90, 160);
    primaryStage.setScene(scene);
    primaryStage.setTitle("this is the first one");
    primaryStage.show();
    Stage seconderyStage = new Stage();
    seconderyStage.setTitle("this is the second one");
    seconderyStage.setScene(new Scene(new Button("2nd"), 160, 90));
    seconderyStage.show();
    seconderyStage.setResizable(false);

  }
}
