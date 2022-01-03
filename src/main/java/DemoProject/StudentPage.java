package DemoProject;

import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.sql.*;

public class StudentPage extends Person{
  private String student_name;
  public StudentPage(String[] args, Stage window, Scene welcomeScene, Label welcomeLabel, Button loginButton,
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

    Button signOut = new Button("Sign Out");
    setButtonFont(signOut,22);

    final Button[] buttons = new Button[]{update_info,myClasses,myGrades,signOut};

    String schoolAcronym = "";
    Connection connection;
    try {
      connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
      String getAcronym = "SELECT college_acronym FROM STUDENT WHERE username='"+user+"';";
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


    HBox hLayout = new HBox(320);
    hLayout.setAlignment(Pos.TOP_CENTER);
    hLayout.getChildren().addAll(imageView,signOut);

    VBox vLayout = new VBox(20);
    vLayout.setAlignment(Pos.BASELINE_LEFT);
    vLayout.getChildren().addAll(update_info,myClasses,myGrades);

    BorderPane borderPane = new BorderPane();
//    borderPane.setCenter(hLayout,vLayout);
    borderPane.setTop(hLayout);
    borderPane.setLeft(vLayout);
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
    update_info.setOnAction(e ->
            window.setScene(updateInfoScene(finalBorder_pane_color2, finalText_fill_color1,user)));
//    myClasses.setOnAction(e ->
//            window.setScene(myClassesScene(finalBorder_pane_color2, finalText_fill_color1,user)));
    myGrades.setOnAction(e ->
            window.setScene(myGradesScene(finalBorder_pane_color2, finalText_fill_color1,user)));
    signOut.setOnAction(e ->
            setWindow());

    return new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
  }
  public Scene updateInfoScene(String border_pane_color, String text_fill_color,String user) {

    Label oldPasswordLabel = new Label("Password: ");
    PasswordField oldPasswordField = new PasswordField();
    oldPasswordField.setMaxWidth(150);
    setFieldFont(oldPasswordField);
    Label validate1 = new Label("");

    Label newPasswordLabel = new Label("New password:");
    PasswordField newpasswordField = new PasswordField();
    newpasswordField.setMaxWidth(150);
    setFieldFont(newpasswordField);
    Label validate2 = new Label("");

    Label confirmPasswordLabel = new Label("Confirm new password: ");
    PasswordField confirmPasswordField = new PasswordField();
    confirmPasswordField.setMaxWidth(150);
    setFieldFont(confirmPasswordField);
    Label validate3 = new Label("");

    Button update = new Button("Update");
    update.setStyle("-fx-background-color: " + text_fill_color + "; -fx-text-fill: "+border_pane_color+";");
    setButtonFont(update,16);
    update.setAlignment(Pos.CENTER);

    Button returnButton  = new Button("Return");
    returnButton.setStyle("-fx-background-color: " + text_fill_color + "; -fx-text-fill: "+border_pane_color+";");
    setButtonFont(returnButton,16);
    returnButton.setAlignment(Pos.CENTER);
//    returnButton.setDisable(true);
//    returnButton.setVisible(false);

    update.setOnAction(e -> {
      Connection connection = null;
      String oldPassword = "";
      try {
        connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
        String getOldPassword = "SELECT password FROM STUDENT WHERE username= '" + user + "';";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(getOldPassword);
        rs.next();
        oldPassword = rs.getString("password");
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }

      try {
        if(oldPasswordField.getText().equals(oldPassword) && newpasswordField.getText().length() > 0 &&
                newpasswordField.getText().equals(confirmPasswordField.getText())) {
          connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
          String updatePass = "UPDATE STUDENT SET password='" + newpasswordField.getText() + "' where " +
                  "username = '" + user + "';";
          System.out.println(updatePass);

          Statement statement = connection.createStatement();
          statement.executeUpdate(updatePass);
          returnButton.setVisible(true);
          returnButton.setDisable(false);
          for(Label validate : new Label[]{validate1,validate2,validate3}) {
            validate.setText("✓");
          }
        }
        else {
          String txt;
          txt = (!oldPasswordField.getText().equals(oldPassword)) ? "Error: Wrong password" : "";
          validate1.setText(txt);
          txt = (newpasswordField.getText().length() == 0) ? "Error: Enter your new password" : "";
          validate2.setText(txt);
          txt = (confirmPasswordField.getText().length() == 0) ? "Error: Confirm your new password" : "";
          validate3.setText(txt);
          String passwordVerifier = verifyPassword(newpasswordField.getText());
          if(!passwordVerifier.equals(""))  validate2.setText(passwordVerifier);
          if(!newpasswordField.getText().equals(confirmPasswordField.getText()) && newpasswordField.getText().length() > 0
            && confirmPasswordField.getText().length() > 0)
            validate3.setText("Error: Passwords must match");
        }

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    });

    returnButton.setOnAction(e2 -> {
      try {
        window.setScene(homeScene(user));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    });

    Label[] labels = new Label[]{oldPasswordLabel,validate1,newPasswordLabel,validate2,
            confirmPasswordLabel,validate3};
    for(Label l : labels) {
      setLabelFont(l,16);
      l.setStyle("-fx-text-fill: "+text_fill_color+";");
    }


    HBox hb1 = new HBox(10);
    hb1.getChildren().addAll(oldPasswordLabel,oldPasswordField,validate1);
    HBox hb2 = new HBox(10);
    hb2.getChildren().addAll(newPasswordLabel,newpasswordField,validate2);
    HBox hb3 = new HBox(10);
    hb3.getChildren().addAll(confirmPasswordLabel, confirmPasswordField,validate3);
    HBox hb4 = new HBox(30);
    hb4.getChildren().addAll(update, returnButton);
    hb4.setAlignment(Pos.CENTER);

    VBox vLayout = new VBox(50);
    vLayout.getChildren().addAll(hb1,hb2,hb3,hb4);
    vLayout.setAlignment(Pos.TOP_CENTER);
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(vLayout);

//    borderPane.setStyle("-fx-padding: 10 10 10 10;");
    borderPane.setStyle("-fx-padding: 25 10 10 10; -fx-background-color: "+border_pane_color +
            ";");


    return new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
  }
//  public Scene myClassesScene(String border_pane_color, String text_fill_color,String user) {
//
//    final ObservableList<ClassEntry> data =
//            FXCollections.observableArrayList(
//                    new ClassEntry("Jacob", "Smith"),
//                    new ClassEntry("Isabella", "Johnson"),
//                    new ClassEntry("Ethan", "Williams"),
//                    new ClassEntry("Emma", "Jones"),
//                    new ClassEntry("Michael", "Brown")
//            );
//
//    TableView table = new TableView();
//    TableColumn firstNameCol = new TableColumn("Class ID");
//    firstNameCol.setMinWidth(100);
//    firstNameCol.setCellValueFactory(
//            new PropertyValueFactory<ClassEntry, String>("firstName"));
//
//
//    TableColumn lastNameCol = new TableColumn("Last Name");
//    lastNameCol.setMinWidth(100);
//    lastNameCol.setCellValueFactory(
//            new PropertyValueFactory<ClassEntry, String>("lastName"));
//
//    table.setItems(data);
//    table.getColumns().addAll(firstNameCol, lastNameCol);
//
//    table.setMaxHeight(250);
//    table.setMaxWidth(200);
//
//    VBox vLayout = new VBox();
//    vLayout.setSpacing(5);
//    vLayout.setPadding(new Insets(10, 0, 0, 10));
//    vLayout.getChildren().add(table);
//    vLayout.setAlignment(Pos.TOP_CENTER);
//
//    BorderPane borderPane = new BorderPane();
//    borderPane.setCenter(vLayout);
//
//    borderPane.setStyle("-fx-padding: 25 10 10 10; -fx-background-color: "+border_pane_color +
//            ";");
//
//    return new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
//  }
  public Scene myGradesScene(String border_pane_color, String text_fill_color,String user) {
    Label testLabel = new Label("My Grades Scene");
    setLabelFont(testLabel,16);
    testLabel.setStyle("-fx-text-fill: "+text_fill_color+";");

    HBox hb1 = new HBox(10);
    hb1.getChildren().addAll(testLabel);
    VBox vLayout = new VBox(50);
    vLayout.getChildren().addAll(hb1);
    vLayout.setAlignment(Pos.TOP_CENTER);
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(vLayout);

//    borderPane.setStyle("-fx-padding: 10 10 10 10;");
    borderPane.setStyle("-fx-padding: 25 10 10 10; -fx-background-color: "+border_pane_color +
            ";");

    return new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
  }
}
