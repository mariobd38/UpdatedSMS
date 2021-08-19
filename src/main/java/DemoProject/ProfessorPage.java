package DemoProject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class ProfessorPage extends Person{

  public ProfessorPage(String[] args, Stage window, Scene welcomeScene, Label welcomeLabel, Button loginButton,
                     Button signupButton, Stage stage, Label identify, RadioButton studentChoice, RadioButton profChoice) {
    super(args,window, welcomeScene, welcomeLabel, loginButton, signupButton, stage, identify, studentChoice, profChoice);
  }

  public Scene homeScene(String user) throws FileNotFoundException {
    Button update_info = new Button("Update Info");
    setButtonFont(update_info,22);

    Button myClasses = new Button("My Classes");
    setButtonFont(myClasses,22);

    Button myGrades = new Button("My Grades");
    setButtonFont(myGrades,22);

    final Button[] buttons = new Button[]{update_info,myClasses,myGrades};

    String schoolAcronym = "";
    Connection connection;
    try {
      connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
      String getAcronym = "SELECT college_acronym FROM PROFESSOR WHERE username='"+user+"';";
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(getAcronym);
      rs.next();
      schoolAcronym = rs.getString("college_acronym");


    } catch (SQLException ex) {
      ex.printStackTrace();
    }


    //Creating an image
    Image image = new Image(new FileInputStream("src/main/resources/"+schoolAcronym+"Logo.png"));

    //Setting the image view
    ImageView imageView = new ImageView(image);

    //setting the fit height and width of the image view
    imageView.setFitHeight(200);
    imageView.setFitWidth(250);

    //Setting the preserve ratio of the image view
    imageView.setPreserveRatio(true);


    VBox layout0 = new VBox(20);
    layout0.setAlignment(Pos.TOP_LEFT);
    layout0.getChildren().addAll(imageView,update_info,myClasses,myGrades);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(layout0);
    borderPane.setStyle("-fx-padding: 10 10 10 10;");

    final String HOVER_BUTTON_STYLE = " -fx-text-fill: #15e5e8;";
    String border_pane_color = "";
    String text_fill_color = "";

    switch (schoolAcronym) {
      case "sdsu":
        border_pane_color = "#000000";
        text_fill_color = "#ff0000";
        break;
      case "ucla":
        border_pane_color = "#0073cf";
        text_fill_color = "#d4af37";
        break;
      case "cpp":
        border_pane_color = "#008000";
        text_fill_color = "#d4af37";
        break;
      case "ucsd":
        border_pane_color = "#000080";
        text_fill_color = "#d4af37";
        break;
      default:
        border_pane_color = "#ffffff";
        text_fill_color = "#ff0000";
        break;

    }
    borderPane.setStyle("-fx-background-color: "+border_pane_color + ";");

    for(Button button : buttons) {
      button.setStyle("-fx-background-color: "+border_pane_color+"; -fx-text-fill: "+text_fill_color+";");
      String finalBorder_pane_color = border_pane_color;
      button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: "+ finalBorder_pane_color +"; " +HOVER_BUTTON_STYLE));
      String finalText_fill_color = text_fill_color;
      String finalBorder_pane_color1 = border_pane_color;
      button.setOnMouseExited(e -> button.setStyle("-fx-background-color: "+ finalBorder_pane_color1
              +"; -fx-text-fill: "+ finalText_fill_color +";"));
    }

    String finalBorder_pane_color2 = border_pane_color;
    String finalText_fill_color1 = text_fill_color;


//    update_info.setOnAction(e ->
//            window.setScene(updateInfoScene(finalBorder_pane_color2, finalText_fill_color1,user)));
//    myClasses.setOnAction(e ->
//            window.setScene(myClassesScene(finalBorder_pane_color2, finalText_fill_color1,user)));
//    myGrades.setOnAction(e ->
//            window.setScene(myGradesScene(finalBorder_pane_color2, finalText_fill_color1,user)));

    return new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
  }
}
