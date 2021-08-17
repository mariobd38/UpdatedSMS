package DemoProject;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class WelcomePage  {
  private final Stage window;
  private Scene welcomeScene;
  private final int STAGE_WIDTH = 700;
  private final int STAGE_HEIGHT = 750;
  private String username;
  private String password;
  private String email;
  private final String url; //sms is the name of the database created beforehand
  private final String sqlUsername;
  private final String sqlPassword;
  private final Label welcomeLabel;
  private final Button loginButton;
  private final Button signupButton;
  private String studentSchool;

  public WelcomePage(String[] args, Stage window, Scene welcomeScene, Label welcomeLabel, Button loginButton,
                     Button signupButton) {
    this.url = args[0];
    this.sqlUsername = args[1];
    this.sqlPassword = args[2];
    this.window = window;
    this.welcomeLabel = welcomeLabel;
    this.loginButton = loginButton;
    this.signupButton = signupButton;
    this.welcomeScene = welcomeScene;
  }

  public Stage setWindow() {
    VBox layout = new VBox(20);
    layout.setStyle("-fx-background-color: #3af0ed;");
    layout.getChildren().addAll(welcomeLabel,loginButton,signupButton);
    layout.setAlignment(Pos.CENTER);

    welcomeScene = new Scene(layout,STAGE_WIDTH,STAGE_HEIGHT);
    window.setScene(welcomeScene);
    window.show();
    return window;
  }
  public void setButtonFont(Button button, int fontSize) {
    button.setFont(new Font("Arial",fontSize));
  }

  public void setLabelFont(Label label, int fontSize) {
    label.setFont(new Font("Arial",fontSize));
  }

  public void setLinkFont(Hyperlink link) {
    link.setFont(new Font("Arial",16));
  }

  public void setFieldFont(TextField field) {
    field.setFont(new Font("Arial",16));
  }

  public Scene signupScene() {
    Button backButton = new Button("\u2190");
    setButtonFont(backButton,16);

    VBox layout = new VBox(20);
//    backButton.setOnAction(e -> window.setScene(welcomeScene));
    backButton.setOnAction(e -> setWindow());
    layout.getChildren().addAll(backButton);

    Label label1 = new Label("First Name");
    setLabelFont(label1,16);
    TextField fnameField = new TextField();
    fnameField.setMaxWidth(150);
    setFieldFont(fnameField);
    Label validate1 = new Label("");
    setLabelFont(validate1,16);

    Label label2 = new Label("Last Name");
    setLabelFont(label2,16);
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

        Connection connection;
        try {
          connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
          String newStudent = "INSERT INTO STUDENT(f_name,l_name,username,email,password," +
                  "college,college_acronym,major,dob) VALUES ("+"'" + fnameField.getText() + "','" +
                  lnameField.getText() + "','" + username + "','" + email + "','" + password + "','" +
                  schoolChoices.getValue().getValue() + "','" + schoolChoices.getValue().getAcronym() + "','" +
                  majorChoices.getValue().getValue() + "','" + dp.getValue() + "');";
//          System.out.println(newStudent);
          Statement statement = connection.createStatement();
          statement.executeUpdate(newStudent);

          studentSchool = schoolChoices.getValue().getAcronym();
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
        String loginQuery = "SELECT COUNT(*) FROM STUDENT WHERE (username='"+userField.getText()+"' OR email = "
                +"'"+userField.getText()+"') AND password = '"+passwordField.getText()+"';";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(loginQuery);
        rs.next();
        int count = rs.getInt(1);
        if(count > 0) {
          window.setScene(homeScene(userField.getText()));
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

    Scene scene = new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
    return scene;
  }
  public String verifyPassword(String password) {
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
    update_info.setOnAction(e1 -> {
      window.setScene(updateInfoScene(finalBorder_pane_color2, finalText_fill_color1,user));
    });


    Scene scene = new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
    return scene;
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


    Scene scene = new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
    return scene;
  }
}
