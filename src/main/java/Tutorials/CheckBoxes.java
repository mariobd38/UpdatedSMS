package Tutorials;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CheckBoxes extends Application {
  private Stage window;
  private Button button;
  private Scene scene;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception{
    window = stage;
    window.setTitle("Sports");

    CheckBox box1 = new CheckBox("Soccer");
    CheckBox box2 = new CheckBox("Basketball");
    box2.setSelected(true);
    CheckBox box3 = new CheckBox("Football");

    button = new Button("Click me");
    button.setOnAction(e -> handleOptions(box1, box2,box3));

    VBox layout = new VBox(10);
    layout.setPadding(new Insets(20));
    layout.getChildren().addAll(box1, box2, box3, button);

    scene = new Scene(layout,300,250);
    window.setScene(scene);
    window.show();
  }

  private void handleOptions(CheckBox box1, CheckBox box2,CheckBox box3) {
    String message = "User's order: \n";
    CheckBox[] checkBoxes = new CheckBox[]{box1,box2,box3};
    for(CheckBox box : checkBoxes) {
      if(box.isSelected()) {
        message += box.getText() + "\n";
      }
    }
    System.out.println(message);
  }
}
