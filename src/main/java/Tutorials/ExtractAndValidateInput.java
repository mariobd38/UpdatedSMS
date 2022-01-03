package Tutorials;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExtractAndValidateInput extends Application {
  private Stage window;
  private Button button;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception{
    stage.setTitle("Title");
    window = stage;

    TextField nameInput = new TextField();
    button = new Button("Click me");
    button.setOnAction(e -> {
      isInt(nameInput,nameInput.getText());
    });

    VBox layout = new VBox(10);
    layout.setPadding(new Insets(20));
    layout.getChildren().addAll(nameInput,button);

    Scene scene = new Scene(layout,300,250);
    window.setScene(scene);
    window.show();
  }

  private boolean isInt(TextField nameIput, String text) {
    try {
      int age = Integer.parseInt(text);
      System.out.println("user is " + age + " years old");
    } catch (NumberFormatException nfe) {
      System.out.println("Error: " + text + " is not a number");
      return false;
    }
    return true;
  }
}