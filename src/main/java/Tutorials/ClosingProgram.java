package Tutorials;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ClosingProgram extends Application {
  private Stage window;
  private Button button;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    window = stage;
    window.setTitle("Title");
    window.setOnCloseRequest(e -> {
      e.consume();
      closeProgram();
    });
    button = new Button("Close program");
    button.setOnAction(e -> closeProgram());
    VBox layout = new VBox();
    layout.getChildren().add(button);
    layout.setAlignment(Pos.CENTER);
    Scene scene = new Scene(layout,300,250);
    stage.setScene(scene);
    stage.show();
  }
  public void closeProgram() {
    boolean result = ConfirmBox.display("Title","Are you sure you want to exit?");
    if(result)
      window.close();
  }
}
