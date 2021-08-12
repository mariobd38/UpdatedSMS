package Tutorials;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class AlertBox {
  public static void display(String title, String message) {
    Stage window = new Stage();
    window.setTitle(title);
    //block user interaction with other windows until this one is taken care of
    window.initModality(Modality.APPLICATION_MODAL);
    window.setMinWidth(250);

    Label label = new Label(message);
    Button closeButton = new Button("Close window");
    closeButton.setOnAction(e -> window.close());

    VBox layout = new VBox(10);
    layout.getChildren().addAll(label,closeButton);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout, 200,200);
    window.setScene(scene);
    window.show();
  }
}

public class AlertBoxes extends Application {
  Stage window;
  Button button;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    window = stage;
    window.setTitle("Alert boxes");

    button = new Button("Click me");
    button.setOnAction(e -> AlertBox.display("New window","Hello World"));

    //layout 1 - children are laid out in vertical column
    VBox layout1 = new VBox(20);
    layout1.getChildren().add(button);
    layout1.setAlignment(Pos.CENTER);
    Scene scene = new Scene(layout1,400,400);

    window.setScene(scene);
    window.show();
  }
}
