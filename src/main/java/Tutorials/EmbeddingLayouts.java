package Tutorials;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Stack;

public class EmbeddingLayouts extends Application {
  Stage window;
  public static void main(String[] args) {
    launch(args);
  }
  @Override
  public void start(Stage stage) throws Exception{
    window = stage;
    window.setTitle("Title");
    HBox topMenu = new HBox(20);
    Button buttonA = new Button("File");
    Button buttonB = new Button("Edit");
    Button buttonC = new Button("View");
    topMenu.getChildren().addAll(buttonA,buttonB,buttonC);

    VBox leftMenu = new VBox(30);
    Button buttonD = new Button("D");
    Button buttonE = new Button("E");
    Button buttonF = new Button("F");
    leftMenu.getChildren().addAll(buttonD,buttonE,buttonF);

    BorderPane borderPane = new BorderPane();
    borderPane.setTop(topMenu);
    borderPane.setLeft(leftMenu);

    Scene scene = new Scene(borderPane,300,250);
    window.setScene(scene);
    window.show();
  }
}
