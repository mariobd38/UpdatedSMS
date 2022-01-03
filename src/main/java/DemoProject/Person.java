package DemoProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Person {
  protected Stage window;
  protected Scene welcomeScene;
  protected final int STAGE_WIDTH = 700;
  protected final int STAGE_HEIGHT = 750;
  protected String username;
  protected String password;
  protected String email;
  protected String first_name;
  protected String last_name;
  protected String url; //sms is the name of the database created beforehand
  protected String sqlUsername;
  protected String sqlPassword;
  protected String[] sqlDetails;
  protected Label welcomeLabel;
  protected Button loginButton;
  protected  Button signupButton;
  protected Stage stage;
  protected Label identify;
  protected RadioButton studentChoice;
  protected RadioButton profChoice;

  public Person(String[] args, Stage window, Scene welcomeScene, Label welcomeLabel, Button loginButton,
                     Button signupButton, Stage stage, Label identify, RadioButton studentChoice, RadioButton profChoice) {
    this.url = args[0];
    this.sqlUsername = args[1];
    this.sqlPassword = args[2];
    this.sqlDetails = args;
    this.window = window;
    this.welcomeLabel = welcomeLabel;
    this.loginButton = loginButton;
    this.signupButton = signupButton;
    this.welcomeScene = welcomeScene;
    this.stage = stage;
    this.identify = identify;
    this.studentChoice = studentChoice;
    this.profChoice = profChoice;
  }


  public void setButtonFont(Button button, int fontSize) {
    button.setFont(new Font("Arial",fontSize));
  }

  public void setLabelFont(Label label, int fontSize) {
    label.setFont(new Font("Arial",fontSize));
  }

  public void setFieldFont(TextField field) {
    field.setFont(new Font("Arial",16));
  }

  public Stage setWindow() {
    VBox radioChoice = new VBox(20);
    radioChoice.getChildren().addAll(identify,studentChoice,profChoice);
    radioChoice.setAlignment(Pos.CENTER);

    VBox layout = new VBox(20);
    layout.setStyle("-fx-background-color: #3af0ed;");
    layout.getChildren().addAll(welcomeLabel,loginButton,signupButton,radioChoice);
    layout.setAlignment(Pos.CENTER);

    welcomeScene = new Scene(layout,STAGE_WIDTH,STAGE_HEIGHT);
    window.setScene(welcomeScene);
    window.show();
    return window;
  }

  public Scene signupScene() {
    Button backButton = new Button("\u2190");
    setButtonFont(backButton,16);

    VBox layout = new VBox(20);
//    backButton.setOnAction(e -> window.setScene(welcomeScene));
    backButton.setOnAction(e -> setWindow());
    layout.getChildren().addAll(backButton);

    Label fname_label = new Label("First Name");
    setLabelFont(fname_label,16);
    TextField fnameField = new TextField();
    fnameField.setMaxWidth(150);
    setFieldFont(fnameField);
    Label validate1 = new Label("");
    setLabelFont(validate1,16);

    Label lname_label = new Label("Last Name");
    setLabelFont(lname_label,16);
    TextField lnameField = new TextField();
    lnameField.setMaxWidth(150);
    setFieldFont(lnameField);
    Label validate2 = new Label("");
    setLabelFont(validate2,16);

    Label l = new Label("Date of birth: ");
    setLabelFont(l,16);
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
    setLabelFont(validate3,16);

    setLabelFont(dob,16);
    // when datePicker is pressed
    dp.setOnAction(event);
    // add button and label
    tp.getChildren().add(dp);

    Label schoolLabel = new Label("College:");
    setLabelFont(schoolLabel,16);
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
    setLabelFont(validate4,16);

    Label majorLabel = new Label("Major:");
    setLabelFont(majorLabel,16);
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
    setLabelFont(validate5,16);

    Label passwordLabel = new Label("Choose a password");
    setLabelFont(passwordLabel,16);
    PasswordField passwordField = new PasswordField();
    passwordField.setMaxWidth(180);
    setFieldFont(passwordField);
    Label validate6 = new Label("");
    setLabelFont(validate6,16);

    Label confirmPasswordLabel = new Label("Confirm your password");
    setLabelFont(confirmPasswordLabel,16);
    PasswordField confirmPasswordField = new PasswordField();
    confirmPasswordField.setMaxWidth(180);
    setFieldFont(confirmPasswordField);
    Label validate7 = new Label("");
    setLabelFont(validate7,16);

    Button submit = new Button("Submit");
    submit.setStyle("-fx-background-color: #e70413;");
    setButtonFont(submit,16);
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
        first_name = fnameField.getText();
        last_name = lnameField.getText();

        Connection connection;
        try {
          connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
          String newPerson = (studentChoice.isSelected()) ? "INSERT INTO STUDENT(f_name,l_name,role,username,email,password," +
                  "college,college_acronym,major,major_acronym,dob) VALUES ("+"'" + first_name + "','" +
                  last_name + "', 'student','" + username + "','" + email + "','" + password + "','" +
                  schoolChoices.getValue().getValue() + "','" + schoolChoices.getValue().getAcronym() + "','" +
                  majorChoices.getValue().getValue() + "','" + majorChoices.getValue().getAcronym() + "','" +
                  dp.getValue() + "');"
                  : "INSERT INTO PROFESSOR(f_name,l_name,role, username,email,password," +
                  "college,college_acronym,course,course_acronym,dob) VALUES ("+"'" + first_name + "','" +
                  last_name + "', 'professor','" + username + "','" + email + "','" + password + "','" +
                  schoolChoices.getValue().getValue() + "','" + schoolChoices.getValue().getAcronym() + "','" +
                  majorChoices.getValue().getValue() + "','" + majorChoices.getValue().getAcronym() + "','" +
                  dp.getValue() + "');";
//          System.out.println(newStudent);
          Statement statement = connection.createStatement();
          statement.executeUpdate(newPerson);
//          String personSchool = schoolChoices.getValue().getAcronym();
          continueToLogin.setStyle("-fx-background-color: #d97416;");
          setButtonFont(continueToLogin,16);
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
    hb1.getChildren().addAll(fname_label, fnameField,validate1);
    HBox hb2 = new HBox(10);
    hb2.getChildren().addAll(lname_label,lnameField,validate2);
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

    return new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
  }

  public Scene loginScene() {
    Button backButton = new Button("\u2190");
    setButtonFont(backButton,16);

    VBox layout = new VBox(20);
//    backButton.setOnAction(e -> window.setScene(welcomeScene));
    backButton.setOnAction(e -> setWindow());
    layout.getChildren().addAll(backButton);

    Label userLabel = new Label("Username/Email");
    setLabelFont(userLabel,16);
//    userLabel.setAlignment(Pos.CENTER);
    TextField userField = new TextField();
    userField.setMaxWidth(210);
    setFieldFont(userField);

    Label passwordLabel = new Label("Password");
    setLabelFont(passwordLabel,16);
    PasswordField passwordField = new PasswordField();
    passwordField.setMaxWidth(210);
    setFieldFont(passwordField);


    Label loginFailed = new Label();
    setLabelFont(loginFailed,16);
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
    setButtonFont(submit,16);
    submit.setAlignment(Pos.CENTER);

    submit.setOnAction(e -> {
      Connection connection;
      try {
        connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
        String loginQuery = (studentChoice.isSelected()) ? "SELECT COUNT(*) FROM STUDENT WHERE (username='"+userField.getText()+"' OR email = "
                +"'"+userField.getText()+"') AND password = '"+passwordField.getText()+"';" :
                "SELECT COUNT(*) FROM PROFESSOR WHERE (username='"+userField.getText()+"' OR email = "
                        +"'"+userField.getText()+"') AND password = '"+passwordField.getText()+"';";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(loginQuery);
        rs.next();
        int count = rs.getInt(1);
        if(count > 0) {
//          window.setScene(homeScene(userField.getText()));
          if(studentChoice.isSelected())
            window.setScene(new StudentPage(sqlDetails,window, welcomeScene, welcomeLabel,
                  loginButton, signupButton, stage, identify, studentChoice, profChoice).homeScene(userField.getText()));
          else
            window.setScene(new ProfessorPage(sqlDetails,window, welcomeScene, welcomeLabel,
                    loginButton, signupButton, stage, identify, studentChoice, profChoice).homeScene(userField.getText()));
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

      } catch (SQLException | FileNotFoundException throwables) {
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

    return new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
  }

  public String verifyPassword(String password) {
    if(password.length() == 0)
      return "Error: Enter a password";
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
  //method checks if the class being added interferes with other added classes
  public boolean checkClassTimes(ObservableList<ClassEntry> classes, ClassEntry selectedClass) throws ParseException {
    if(classes == null) {
      return true;
    }
    else {
      String startTime1 = selectedClass.getCourseStartTime();
      String endTime1 = selectedClass.getCourseEndTime();
      SimpleDateFormat format = new SimpleDateFormat("HH:mm");
      java.util.Date startTimeDate1 = format.parse(startTime1);
      java.util.Date endTimeDate1 = format.parse(endTime1);

      String[] courseDays = selectedClass.getCourseDays().split(",");
      for(int i = 0; i < classes.size();i++) {
        String startTime2 = classes.get(i).getCourseStartTime();
        String endTime2 = classes.get(i).getCourseEndTime();
        java.util.Date startTimeDate2 = format.parse(startTime2);
        Date endTimeDate2 = format.parse(endTime2);
        if((startTimeDate1.before(endTimeDate2) && endTimeDate1.after(startTimeDate2))) {
          for(String courseDay : courseDays) {
            if(classes.get(i).getCourseDays().contains(courseDay))
              return false;
          }
        }
      }
    }
    return true;
  }

}
