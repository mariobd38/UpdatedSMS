package Tutorials;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class ConfirmBox {
  static boolean sendMessage;
  public static boolean display(String title, String message) {
    Stage window = new Stage();
    window.setTitle(title);
    //block user interaction with other windows until this one is taken care of
    window.initModality(Modality.APPLICATION_MODAL);
    window.setMinWidth(250);

    Label label = new Label(message);

    Button yesButton = new Button("yes");
    Button noButton = new Button("no");
    yesButton.setOnAction(e -> {
      sendMessage = true;
      window.close();
    });

    noButton.setOnAction(e -> {
      sendMessage = false;
      window.close();
    });

    VBox layout = new VBox(10);
    layout.getChildren().addAll(label,yesButton,noButton);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout, 200,200);
    window.setScene(scene);
    window.showAndWait();
    return sendMessage;
  }
}

public class WindowCommunication extends Application {
  Stage window;
  Button button;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    window = stage;
    window.setTitle("Window Communication");

    button = new Button("Click me");
    Label label = new Label("");
    label.setFont(new Font("Arial",16));
    AtomicInteger isClicked = new AtomicInteger(0);

    button.setOnAction(e -> {
      boolean response = ConfirmBox.display("New window", "Do you want to share naked pics?");
      isClicked.set(1);
      String labelMessage = (response) ? "Mario wants to share naked pics" :
              "Mario does not want to share naked pics";
      label.setText(labelMessage);
    });
    //layout 1 - children are laid out in vertical column
    VBox layout1 = new VBox(20);
    layout1.getChildren().addAll(button,label);
    layout1.setAlignment(Pos.CENTER);
    Scene scene = new Scene(layout1,400,400);

    window.setScene(scene);
    window.show();
  }
}
