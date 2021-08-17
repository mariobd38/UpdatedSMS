package DemoProject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.sql.*;

public class SMS extends Application {
  private static Stage window;
  private static Scene welcomeScene;
  private static final int STAGE_WIDTH = 700;
  private static final int STAGE_HEIGHT = 750;
  private static String[] sqlDetails = null;

  public static void main(String[] args) {
    sqlDetails = new String[]{args[0],args[1],args[2]};
    try {
      Connection connection = DriverManager.getConnection(sqlDetails[0],sqlDetails[1], sqlDetails[2]);
      String createTable = "CREATE TABLE IF NOT EXISTS STUDENT(" +
              "student_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
              "f_name VARCHAR(50) NOT NULL," +
              "l_name VARCHAR(50) NOT NULL," +
              "username VARCHAR(50) NOT NULL," +
              "email VARCHAR(50) NOT NULL," +
              "password VARCHAR(50) NOT NULL," +
              "college VARCHAR(60) NOT NULL," +
              "college_acronym VARCHAR(8) NOT NULL," +
              "major VARCHAR(60) DEFAULT 'Undeclared'," +
              "dob DATE NOT NULL" +
              ");";
      Statement statement = connection.createStatement();
      statement.executeUpdate(createTable);
      launch(args);
//      connection.close();
    } catch(SQLException e) {
      e.printStackTrace();
    }

  }

  public void start(Stage stage) {
    window = stage;
    window.setTitle("Student Management System");

    Label welcomeLabel =  new Label("Welcome student!");
    welcomeLabel.setFont(new Font("Arial",25));
    welcomeLabel.setAlignment(Pos.TOP_CENTER);

    Button loginButton = new Button("Log In");
    Button signupButton = new Button("Sign up");

    WelcomePage welcomePage = new WelcomePage(sqlDetails, window,
            welcomeScene, welcomeLabel, loginButton, signupButton);
    welcomePage.setButtonFont(loginButton,16);
    welcomePage.setButtonFont(signupButton,16);

    loginButton.setOnAction(e -> window.setScene(welcomePage.loginScene()));
    signupButton.setOnAction(e -> window.setScene(welcomePage.signupScene()));

    VBox layout = new VBox(20);
    layout.setStyle("-fx-background-color: #3af0ed;");
    layout.getChildren().addAll(welcomeLabel,loginButton,signupButton);
    layout.setAlignment(Pos.CENTER);

    welcomeScene = new Scene(layout,STAGE_WIDTH,STAGE_HEIGHT);
    window.setScene(welcomeScene);
    window.show();

  }
}
