package DemoProject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;

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
      String studentTable = "CREATE TABLE IF NOT EXISTS STUDENT(" +
              "student_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
              "f_name VARCHAR(50) NOT NULL," +
              "l_name VARCHAR(50) NOT NULL," +
              "role VARCHAR(10) NOT NULL," +
              "username VARCHAR(50) NOT NULL," +
              "email VARCHAR(50) NOT NULL," +
              "password VARCHAR(50) NOT NULL," +
              "college VARCHAR(60) NOT NULL," +
              "college_acronym VARCHAR(8) NOT NULL," +
              "major VARCHAR(60) DEFAULT 'Undeclared'," +
              "major_acronym VARCHAR(5) DEFAULT 'N/A'," +
              "dob DATE NOT NULL" +
              ");";
      String professorTable = "CREATE TABLE IF NOT EXISTS PROFESSOR(" +
              "professor_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
              "f_name VARCHAR(50) NOT NULL," +
              "l_name VARCHAR(50) NOT NULL," +
              "role VARCHAR(10) NOT NULL," +
              "username VARCHAR(50) NOT NULL," +
              "email VARCHAR(50) NOT NULL," +
              "password VARCHAR(50) NOT NULL," +
              "college VARCHAR(60) NOT NULL," +
              "college_acronym VARCHAR(8) NOT NULL," +
              "course VARCHAR(60) DEFAULT 'None'," +
              "course_acronym VARCHAR(5) DEFAULT 'N/A'," +
              "dob DATE NOT NULL" +
              ");";
      Statement statement = connection.createStatement();
      statement.executeUpdate(studentTable);
      statement.executeUpdate(professorTable);
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

    Label identify =  new Label("I am a: ");
    identify.setFont(new Font("Arial",16));

    ToggleGroup group = new ToggleGroup();
    RadioButton rb1 = new RadioButton("Student");
    rb1.setToggleGroup(group);
    rb1.setFont(new Font("Arial",16));
    rb1.setSelected(true);
    RadioButton rb2 = new RadioButton("Professor");
    rb2.setFont(new Font("Arial",16));
    rb2.setToggleGroup(group);
    AtomicReference<RadioButton> selectedButton = new AtomicReference<>();
    selectedButton.set(rb1);
    rb1.setOnAction(e -> {
      welcomeLabel.setText("Welcome student!");
      rb1.setSelected(true);
      rb2.setSelected(false);
      selectedButton.set(rb1);
    });

    rb2.setOnAction(e -> {
      welcomeLabel.setText("Welcome professor!");
      rb2.setSelected(true);
      rb1.setSelected(false);
      selectedButton.set(rb2);
    });

    StudentPage studentPage = new StudentPage(sqlDetails, window,
            welcomeScene, welcomeLabel, loginButton, signupButton, stage,identify,rb1,rb2);
    ProfessorPage professorPage = new ProfessorPage(sqlDetails, window,
            welcomeScene, welcomeLabel, loginButton, signupButton, stage,identify,rb1,rb2);

    Person p = new Person(sqlDetails, window,
            welcomeScene, welcomeLabel, loginButton, signupButton, stage,identify,rb1,rb2);
    p.setButtonFont(loginButton,16);
    p.setButtonFont(signupButton,16);

    loginButton.setOnAction(e2 -> {
      if(selectedButton.get().getText().equals("student"))
        window.setScene(studentPage.loginScene());
      else
        window.setScene(professorPage.loginScene());
    });
    signupButton.setOnAction(e3 -> {
      if(selectedButton.get().getText().equals("student"))
        window.setScene(studentPage.signupScene());
      else
        window.setScene(professorPage.signupScene());
    });

    VBox layout1 = new VBox(20);
//    layout1.setStyle("-fx-background-color: #3af0ed;");
    layout1.getChildren().addAll(identify,rb1,rb2);
    layout1.setAlignment(Pos.CENTER);

    VBox layout2 = new VBox(20);
    layout2.setStyle("-fx-background-color: #3af0ed;");
    layout2.getChildren().addAll(welcomeLabel,loginButton,signupButton,layout1);
    layout2.setAlignment(Pos.CENTER);

    welcomeScene = new Scene(layout2,STAGE_WIDTH,STAGE_HEIGHT);
    window.setScene(welcomeScene);
    window.show();
  }
}
