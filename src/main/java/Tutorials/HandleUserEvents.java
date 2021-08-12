package Tutorials;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class HandleUserEvents extends Application implements EventHandler<ActionEvent>{
  private Button button;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception{
    stage.setTitle("Title");
    button = new Button("Click me");
    button.setOnAction(this);

    StackPane layout = new StackPane();
    layout.getChildren().add(button);

    Scene scene = new Scene(layout,300,250);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void handle(ActionEvent actionEvent) {
    if(actionEvent.getSource() == button) {
      button.setText("Clicked");
    }
  }
}
