package DemoProject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class SMS extends Application {
  private static Stage window;
  private static Scene welcomeScene;
  private static final int STAGE_WIDTH = 600;
  private static final int STAGE_HEIGHT = 700;
  private static String username;
  private static String password;
  private static final String url = "jdbc:mysql://localhost:3306/sms";
  private static final String sqlUsername = "root";
  private static final String sqlPassword = "lionelmessi10";

  public static void main(String[] args) {
    System.out.println("Enter your password to login to the sql server: ");
//    Scanner scan = new Scanner(System.in);
//    String mysqlPassword = scan.nextLine();
    try {
       Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
      String createTable = "CREATE TABLE IF NOT EXISTS STUDENT(" +
              "student_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
              "f_name VARCHAR(50) NOT NULL," +
              "l_name VARCHAR(50) NOT NULL," +
              "username VARCHAR(50) NOT NULL," +
              "email VARCHAR(50) NOT NULL," +
              "password VARCHAR(50) NOT NULL," +
              "college VARCHAR(60) NOT NULL," +
              "major VARCHAR(60) DEFAULT 'Undeclared'," +
              "dob DATE NOT NULL" +
      ");";
      Statement statement = connection.createStatement();
      statement.executeUpdate(createTable);
//      ResultSet result = statement.executeQuery(sql);
      launch(args);
//      connection.close();
    } catch(SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void start(Stage stage) {
    window = stage;
    window.setTitle("Student Management System");

    Label welcomeLabel =  new Label("Welcome student!");
    welcomeLabel.setFont(new Font("Arial",25));
    welcomeLabel.setAlignment(Pos.TOP_CENTER);

    Button loginButton = new Button("Login");
    Button signupButton = new Button("Sign up");
    setButtonFont(loginButton);
    setButtonFont(signupButton);

//    Scene loginScene = new Scene(new VBox(),stageWidth,stageHeight);
//    Scene signupScene = new Scene(new VBox(),stageWidth,stageHeight);
    loginButton.setOnAction(e -> {
      window.setScene(loginScene());
    });
    signupButton.setOnAction(e -> {
      window.setScene(signupScene());
    });

    VBox layout = new VBox(20);
    layout.setStyle("-fx-background-color: #3af0ed;");
    layout.getChildren().addAll(welcomeLabel,loginButton,signupButton);
    layout.setAlignment(Pos.CENTER);

    welcomeScene = new Scene(layout,STAGE_WIDTH,STAGE_HEIGHT);
    window.setScene(welcomeScene);
    window.show();
  }
  public static void setButtonFont(Button button) {
    button.setFont(new Font("Arial",16));
  }
  public static void setLabelFont(Label label) {
    label.setFont(new Font("Arial",16));
  }
  public static void setFieldFont(TextField field) {
    field.setFont(new Font("Arial",16));
  }
  public static Text setTextFont(Text text) {
    text.setFont(new Font("Arial",16));
    return text;
  }
  public static Scene signupScene() {
    Button backButton = new Button("\u2190");
    setButtonFont(backButton);

    VBox layout = new VBox(20);
    backButton.setOnAction(e -> window.setScene(welcomeScene));
    layout.getChildren().addAll(backButton);

    Label label1 = new Label("First Name");
    setLabelFont(label1);
    TextField fnameField = new TextField();
    fnameField.setMaxWidth(150);
    setFieldFont(fnameField);
    Label validate1 = new Label("");
    setLabelFont(validate1);

    Label label2 = new Label("Last Name");
    setLabelFont(label2);
    TextField lnameField = new TextField();
    lnameField.setMaxWidth(150);
    setFieldFont(lnameField);

    Label l = new Label("Date of birth: ");
    setLabelFont(l);
    Label dob = new Label("");
    TilePane tp = new TilePane();
    // create a date picker
    DatePicker dp = new DatePicker();
    // action event
    AtomicReference<LocalDate> localDate = new AtomicReference<>();
    EventHandler<ActionEvent> event = e -> {
      // get the date picker value
      localDate.set(dp.getValue());
      String formattedDate = localDate.get().getMonth() + " " + localDate.get().getDayOfMonth() +
              "," + localDate.get().getYear();
      // get the selected date
      dob.setText("Date: " + formattedDate);
    };

    setLabelFont(dob);
    // when datePicker is pressed
    dp.setOnAction(event);
    // add button and label
    tp.getChildren().add(dp);

    Label schoolLabel = new Label("College:");
    setLabelFont(schoolLabel);
    ComboBox<Item> schoolChoices = new ComboBox<>(FXCollections.observableArrayList(
            new Item("San Diego State", Color.RED),
            new Item("UC San Diego", Color.NAVY),
            new Item("Cal Poly Pomona", Color.GREEN),
            new Item("UC Los Angeles", Color.GOLD)
    ));
    schoolChoices.setCellFactory(lv -> new ListCell<>() {

      @Override
      protected void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
          setBackground(Background.EMPTY);
          setText("");
        } else {
          setBackground(new Background(new BackgroundFill(item.getColor(),
                  CornerRadii.EMPTY,
                  Insets.EMPTY)));
          setText(item.getValue());
        }
      }

    });

    schoolChoices.setButtonCell(schoolChoices.getCellFactory().call(null));
    schoolChoices.setStyle("-fx-font: 13px \"Arial\";");


    Label majorLabel = new Label("Major:");
    setLabelFont(majorLabel);
    ComboBox<Item> majorChoices = new ComboBox<>(FXCollections.observableArrayList(
            new Item("Biology", Color.LIGHTGREEN),
            new Item("Computer Science", Color.ROYALBLUE),
            new Item("Business", Color.SANDYBROWN),
            new Item("Physics", Color.CORNFLOWERBLUE)
    ));
    majorChoices.setCellFactory(lv -> new ListCell<>() {

      @Override
      protected void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
          setBackground(Background.EMPTY);
          setText("");
        } else {
          setBackground(new Background(new BackgroundFill(item.getColor(),
                  CornerRadii.EMPTY,
                  Insets.EMPTY)));
          setText(item.getValue());
        }
      }
    });

    majorChoices.setButtonCell(majorChoices.getCellFactory().call(null));
    majorChoices.setStyle("-fx-font: 13px \"Arial\";");

    Label passwordLabel = new Label("Choose a password");
    setLabelFont(passwordLabel);
    PasswordField passwordField = new PasswordField();
    passwordField.setMaxWidth(180);
    setFieldFont(passwordField);

    Label confirmPasswordLabel = new Label("Confirm your password");
    setLabelFont(confirmPasswordLabel);
    PasswordField confirmPasswordField = new PasswordField();
    confirmPasswordField.setMaxWidth(180);
    setFieldFont(confirmPasswordField);

    Button submit = new Button("Submit");
    submit.setStyle("-fx-background-color: #e70413;");
    setButtonFont(submit);
    submit.setAlignment(Pos.CENTER);
    Label usernameLabel = new Label("");
    Map<String,Integer> map = new HashMap<>();
    submit.setOnAction(e -> {
      if(fnameField.getText().length() > 0 ) {
        validate1.setText("✓");
        username = fnameField.getText().substring(0, 1).toLowerCase() + lnameField.getText();

        if(map.containsKey(username)) {
          map.replace(username,map.get(username),map.get(username)+1);
        } else {
          map.put(username,1);
        }
        username += map.get(username);
        usernameLabel.setText("Your username is " + username);
        setLabelFont(usernameLabel);
      } else if (fnameField.getText().length() == 0){
        validate1.setText("Error: Please enter your first name");
      }
//      try {
//        Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);


//      }
    });
//    submit.setOnAction(e -> {
//      try {
//        Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
////        String newStudent = "CREATE TABLE IF NOT EXISTS STUDENT(" +
////                "student_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
////                "f_name VARCHAR(50) NOT NULL," +
////                "l_name VARCHAR(50) NOT NULL," +
////                "username VARCHAR(50) NOT NULL," +
////                "email VARCHAR(50) NOT NULL," +
////                "password VARCHAR(50) NOT NULL," +
////                "college VARCHAR(60) NOT NULL," +
////                "major VARCHAR(60) DEFAULT 'Undeclared'," +
////                "dob DATE NOT NULL" +
////                ");";
//        String newStudent = "INSERT INTO STUDENT(f_name,l_name,username,email,password," +
//                "college,major,dob) VALUES (" + fnameField.getText() + "," +
//                lnameField.getText() + "," + ";";
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(newStudent);
//      } catch(SQLException se) {
//        se.printStackTrace();
//      }
//    });

    HBox hb1 = new HBox(10);
    hb1.getChildren().addAll(label1, fnameField,validate1);
    HBox hb2 = new HBox(10);
    hb2.getChildren().addAll(label2,lnameField);
    HBox hb3 = new HBox(10);
    hb3.getChildren().addAll(l,dp,dob);
    HBox hb4 = new HBox(10);
    hb4.getChildren().addAll(schoolLabel, schoolChoices,majorLabel,majorChoices);
//    hb4.getChildren().addAll(majorLabel, majorChoices);
    HBox hb5 = new HBox(10);
    hb5.getChildren().addAll(passwordLabel, passwordField);
    HBox hb6 = new HBox(10);
    hb6.getChildren().addAll(confirmPasswordLabel, confirmPasswordField);
    VBox layout0 = new VBox(20);
    layout0.setAlignment(Pos.TOP_CENTER);
    layout0.getChildren().addAll(hb1,hb2,hb3,hb4,hb5,hb6,submit,usernameLabel);


    BorderPane borderPane = new BorderPane();
    borderPane.setTop(layout);
    borderPane.setCenter(layout0);
    borderPane.setStyle("-fx-padding: 10 10 10 10;");
    borderPane.setStyle("-fx-background-color: #d135d4;");

    Scene scene = new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
    return scene;
  }
  public static Scene loginScene() {
    Button backButton = new Button("\u2190");
    setButtonFont(backButton);

    VBox layout = new VBox(20);
    backButton.setOnAction(e -> window.setScene(welcomeScene));
    layout.getChildren().addAll(backButton);

    Label userLabel = new Label("Username/Email");
    setLabelFont(userLabel);
//    userLabel.setAlignment(Pos.CENTER);
    TextField userField = new TextField();
    userField.setMaxWidth(210);
    setFieldFont(userField);

    Label passwordLabel = new Label("Password");
    setLabelFont(passwordLabel);
//    passwordLabel.setContentDisplay(ContentDisplay.CENTER);
    PasswordField passwordField = new PasswordField();
    passwordField.setMaxWidth(210);
    setFieldFont(passwordField);

    HBox hb1 = new HBox(10);
    hb1.getChildren().addAll(userLabel, userField);
    hb1.setAlignment(Pos.CENTER);
    HBox hb2 = new HBox(10);
    hb2.getChildren().addAll(passwordLabel,passwordField);
    hb2.setAlignment(Pos.CENTER);
    Button submit = new Button("Submit");
    submit.setStyle("-fx-background-color: #e70413;");
    setButtonFont(submit);
    submit.setAlignment(Pos.CENTER);

    VBox vLayout = new VBox(10);
    vLayout.getChildren().addAll(hb1,hb2,submit);
    vLayout.setAlignment(Pos.CENTER);
    BorderPane borderPane = new BorderPane();
    borderPane.setTop(layout);
    borderPane.setCenter(vLayout);

    borderPane.setStyle("-fx-padding: 10 10 10 10;");
    borderPane.setStyle("-fx-background-color: #8747e6;");

    Scene scene = new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
    return scene;
  }
}
