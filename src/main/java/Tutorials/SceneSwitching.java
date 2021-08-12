package Tutorials;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneSwitching  extends Application {
  private Stage window;
  private Scene scene1, scene2;
  public static void main(String[] args) {
    launch(args);
  }
  @Override
  public void start(Stage stage) throws Exception {
    window = stage;

    Label label1 = new Label("Welcome to the first scene");
    Button button1 = new Button("Click to go to scene 2");
    button1.setOnAction(e -> window.setScene(scene2));

    //layout 1 - children are laid out in vertical column
    VBox layout1 = new VBox(20);
    layout1.getChildren().addAll(label1,button1);
    scene1 = new Scene(layout1,200,200);
    //Button 2
    Label label2 = new Label("Welcome to the second scene");
    Button button2 = new Button("Click to go to back to scene 1");
    button2.setOnAction(e -> window.setScene(scene1));
    //Layout 2
    VBox layout2 = new VBox(20);
    layout2.getChildren().addAll(label2,button2);
    scene2 = new Scene(layout2, 200, 200);

    window.setScene(scene1);
    window.setTitle("Scene switching");
    window.show();
  }
}
