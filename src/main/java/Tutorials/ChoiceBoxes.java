package Tutorials;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChoiceBoxes extends Application {
  private Stage window;
  private Scene scene;
  private Button button;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception{
    window = stage;
    stage.setTitle("Title");
    button = new Button("Click me");

    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    choiceBox.getItems().addAll("Apple","Banana", "Mango","Orange");
    //set default value
    choiceBox.setValue("Apple");

    button.setOnAction(e -> getChoice(choiceBox));

    VBox layout = new VBox();
    layout.setPadding(new Insets(20));
    layout.getChildren().addAll(choiceBox, button);

    scene = new Scene(layout,300,250);
    window.setScene(scene);
    window.show();
  }

  private void getChoice(ChoiceBox<String> choiceBox) {
    String food = choiceBox.getValue();
    System.out.println(food);
  }
}
