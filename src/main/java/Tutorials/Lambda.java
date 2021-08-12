package Tutorials;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Lambda extends Application {
  private Button button;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception{
    stage.setTitle("Title");
    button = new Button("Click me");
    //anonymous inner classes
//    button.setOnAction(new EventHandler<ActionEvent>() {
//      @Override
//      public void handle(ActionEvent actionEvent) {
//        button.setText("Clicked");
//      }
//    });
    //lambda expression
    button.setOnAction(actionEvent -> button.setText("Clicked"));

    StackPane layout = new StackPane();
    layout.getChildren().add(button);

    Scene scene = new Scene(layout,300,250);
    stage.setScene(scene);
    stage.show();
  }
}
