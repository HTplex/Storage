package javaFX_Basics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * Created by htplex on 19/7/2015.
 */
public class A01HelloJFX extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }

  public void start(Stage primaryStage) {
    Button btOK = new Button("OK");
    Scene scene = new Scene(btOK, 90, 160);
    primaryStage.setTitle("helloJFX again!");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
