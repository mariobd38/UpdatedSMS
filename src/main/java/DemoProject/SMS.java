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
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class SMS extends Application {
  private static Stage window;
  private static Scene welcomeScene;
  private static final int STAGE_WIDTH = 600;
  private static final int STAGE_HEIGHT = 700;
  private static String username;
  private static String password;
  private static String email;
  private static final String url = "jdbc:mysql://localhost:3306/sms"; //sms is the name of the database created beforehand
  private static final String sqlUsername = "root";
  private static final String sqlPassword = "lionelmessi10";

  public static void main(String[] args) {
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

    Button loginButton = new Button("Log In");
    Button signupButton = new Button("Sign up");
    setButtonFont(loginButton);
    setButtonFont(signupButton);

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
    Label validate2 = new Label("");
    setLabelFont(validate2);

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
      System.out.println(localDate.get() + " vs " + formattedDate);
    };
    Label validate3 = new Label("");
    setLabelFont(validate3);

    setLabelFont(dob);
    // when datePicker is pressed
    dp.setOnAction(event);
    // add button and label
    tp.getChildren().add(dp);

    Label schoolLabel = new Label("College:");
    setLabelFont(schoolLabel);
    ComboBox<Item> schoolChoices = new ComboBox<>(FXCollections.observableArrayList(
            new Item("San Diego State", Color.RED, "sdsu"),
            new Item("UC San Diego", Color.NAVY, "ucsd"),
            new Item("Cal Poly Pomona", Color.GREEN, "cpp"),
            new Item("UC Los Angeles", Color.GOLD, "ucla")
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
    Label validate4 = new Label("");
    setLabelFont(validate4);


    Label majorLabel = new Label("Major:");
    setLabelFont(majorLabel);
    ComboBox<Item> majorChoices = new ComboBox<>(FXCollections.observableArrayList(
            new Item("Biology", Color.LIGHTGREEN, "BIO"),
            new Item("Computer Science", Color.ROYALBLUE, "CS"),
            new Item("Business", Color.SANDYBROWN, "BUS"),
            new Item("Physics", Color.CORNFLOWERBLUE, "PHY")
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
    Label validate5 = new Label("");
    setLabelFont(validate5);

    Label passwordLabel = new Label("Choose a password");
    setLabelFont(passwordLabel);
    PasswordField passwordField = new PasswordField();
    passwordField.setMaxWidth(180);
    setFieldFont(passwordField);
    Label validate6 = new Label("");
    setLabelFont(validate6);

    Label confirmPasswordLabel = new Label("Confirm your password");
    setLabelFont(confirmPasswordLabel);
    PasswordField confirmPasswordField = new PasswordField();
    confirmPasswordField.setMaxWidth(180);
    setFieldFont(confirmPasswordField);
    Label validate7 = new Label("");
    setLabelFont(validate7);

    Button submit = new Button("Submit");
    submit.setStyle("-fx-background-color: #e70413;");
    setButtonFont(submit);
    submit.setAlignment(Pos.CENTER);
    Label usernameLabel = new Label("");
    Map<String,Integer> map = new HashMap<>();
    Label[] validates = {validate1,validate2,validate3,validate4,validate5,validate6,validate7};
    Button continueToLogin = new Button("Continue to Log In");
    continueToLogin.setVisible(false);
    submit.setOnAction(e -> {
      if(fnameField.getText().length() > 0 && lnameField.getText().length() > 0 &&
        dp.getValue() != null && schoolChoices.getValue() != null && majorChoices.getValue() != null &&
      passwordField.getText().length() > 0 && passwordField.getText().equals(confirmPasswordField.getText())){
        for(Label validate : validates) {
          validate.setText("✓");
        }
        username = fnameField.getText().substring(0, 1).toLowerCase() + lnameField.getText().toLowerCase();
        password = passwordField.getText();
        if(map.containsKey(username)) {
          map.replace(username,map.get(username),map.get(username)+1);
        } else {
          map.put(username,1);
        }
        username += map.get(username);
        usernameLabel.setText("Your username is " + username);
        usernameLabel.setFont(new Font("Arial",20));
        fnameField.setDisable(true);
        lnameField.setDisable(true);
        dp.setDisable(true);
        schoolChoices.setDisable(true);
        majorChoices.setDisable(true);
        passwordField.setDisable(true);
        confirmPasswordField.setDisable(true);
        submit.setDisable(true);
        email = username + "@" +  schoolChoices.getValue().getAcronym() + ".edu";

        Connection connection;
        try {
          connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
          String newStudent = "INSERT INTO STUDENT(f_name,l_name,username,email,password," +
                  "college,major,dob) VALUES ("+"'" + fnameField.getText() + "','" +
                  lnameField.getText() + "','" + username + "','" + email + "','" + password + "','" +
                  schoolChoices.getValue().getValue() + "','" + majorChoices.getValue().getValue() + "','" + dp.getValue() + "');";
          System.out.println(newStudent);
          Statement statement = connection.createStatement();
          statement.executeUpdate(newStudent);
          System.out.println("successful insert");

          continueToLogin.setStyle("-fx-background-color: #d97416;");
          setButtonFont(continueToLogin);
          continueToLogin.setAlignment(Pos.CENTER);
          continueToLogin.setVisible(true);
          continueToLogin.setOnAction(a -> window.setScene(loginScene()));
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      } else {
        String txt;
        txt = (fnameField.getText().length() == 0) ? "Error: Enter your first name" : "";
        validate1.setText(txt);
        txt = (lnameField.getText().length() == 0) ? "Error: Enter your last name" : "";
        validate2.setText(txt);
        txt = (dp.getValue() == null) ? "Error: Enter your DOB" : "";
        dob.setText(txt);
        txt = (schoolChoices.getValue() == null) ? "Error: Enter your college" : "";
        validate4.setText(txt);
        txt = (majorChoices.getValue() == null) ? "Error: Enter your major" : "";
        validate5.setText(txt);
        txt = (passwordField.getText().length() == 0) ? "Error: Enter your password" : "";
        validate6.setText(txt);
        txt = (confirmPasswordField.getText().length() == 0) ? "Error: Confirm your password" : "";
        validate7.setText(txt);
        String passwordVerifier = verifyPassword(passwordField.getText());
        if(!passwordVerifier.equals(""))  validate6.setText(passwordVerifier);
        if(!passwordField.getText().equals(confirmPasswordField.getText()))
          validate7.setText("Error: Passwords must match");
      }
    });

    HBox hb1 = new HBox(10);
    hb1.getChildren().addAll(label1, fnameField,validate1);
    HBox hb2 = new HBox(10);
    hb2.getChildren().addAll(label2,lnameField,validate2);
    HBox hb3 = new HBox(10);
    hb3.getChildren().addAll(l,dp,dob,validate3);
    HBox hb4 = new HBox(10);
    hb4.getChildren().addAll(schoolLabel, schoolChoices,validate4);
    HBox hb5 = new HBox(10);
    hb5.getChildren().addAll(majorLabel,majorChoices,validate5);
    HBox hb6 = new HBox(10);
    hb6.getChildren().addAll(passwordLabel, passwordField,validate6);
    HBox hb7 = new HBox(10);
    hb7.getChildren().addAll(confirmPasswordLabel, confirmPasswordField,validate7);
    VBox layout0 = new VBox(20);
    layout0.setAlignment(Pos.TOP_CENTER);
    layout0.getChildren().addAll(hb1,hb2,hb3,hb4,hb5,hb6,hb7,submit,usernameLabel,continueToLogin);


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
    PasswordField passwordField = new PasswordField();
    passwordField.setMaxWidth(210);
    setFieldFont(passwordField);

    Label loginFailed = new Label();
    setLabelFont(loginFailed);
    loginFailed.setVisible(false);
    loginFailed.setStyle("-fx-text-inner-color: red;");

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

    submit.setOnAction(e -> {
      Connection connection;
      try {
        connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
        String loginQuery = "SELECT COUNT(*) FROM STUDENT WHERE (username='"+userField.getText()+"' OR email = "
                +"'"+userField.getText()+"') AND password = '"+passwordField.getText()+"';";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(loginQuery);
        rs.next();
        int count = rs.getInt(1);
        if(count > 0) {
          window.setScene(welcomeScene);
        }
        else if(passwordField.getText().length() == 0) {
          loginFailed.setText("Please enter a password");
          loginFailed.setVisible(true);
        }
        else {
          loginFailed.setText("Login failed. Try again");
          loginFailed.setVisible(true);
          userField.clear();
          passwordField.clear();
        }

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    });

    VBox vLayout = new VBox(10);
    vLayout.getChildren().addAll(hb1,hb2,submit,loginFailed);
    vLayout.setAlignment(Pos.CENTER);
    BorderPane borderPane = new BorderPane();
    borderPane.setTop(layout);
    borderPane.setCenter(vLayout);

    borderPane.setStyle("-fx-padding: 10 10 10 10;");
    borderPane.setStyle("-fx-background-color: #8747e6;");

    Scene scene = new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
    return scene;
  }
  public static String verifyPassword(String password) {
    if(password.length() < 7) {
      return "Error: Password length too short";
    }
    boolean containsDigit = false;
    char[] chars = password.toCharArray();
    for(char c : chars){
      if(Character.isDigit(c)){
       containsDigit = true;
      }
    }
    if(!containsDigit) {
      return "Error: Password must contain at least one digit";
    }
    return "";
  }
}
