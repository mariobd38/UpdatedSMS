package DemoProject;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ProfessorPage extends Person{
  private String profUsername;
  private String prof_name;
  public ProfessorPage(String[] args, Stage window, Scene welcomeScene, Label welcomeLabel, Button loginButton,
                     Button signupButton, Stage stage, Label identify, RadioButton studentChoice, RadioButton profChoice) {
    super(args,window, welcomeScene, welcomeLabel, loginButton, signupButton, stage, identify, studentChoice, profChoice);
  }

  public Scene homeScene(String user) throws FileNotFoundException {
    profUsername = user;
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
    String border_pane_color;
    String text_fill_color;

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
    String finalSchoolAcronym = schoolAcronym;
    myClasses.setOnAction(e ->
            window.setScene(myClassesScene(finalBorder_pane_color2, finalText_fill_color1,user, finalSchoolAcronym)));
//    myGrades.setOnAction(e ->
//            window.setScene(myGradesScene(finalBorder_pane_color2, finalText_fill_color1,user)));
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
      Connection connection;
      String oldPassword = "";
      try {
        connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
        String getOldPassword = "SELECT password FROM PROFESSOR WHERE username= '" + user + "';";
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
          String updatePass = "UPDATE PROFESSOR SET password='" + newpasswordField.getText() + "' where " +
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

  public Scene myClassesScene(String border_pane_color, String text_fill_color,String user, String schoolAcronym) {
    String courseTeaching = "";
    Connection connection;
    try {
      connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
      String getAcronym = "SELECT course_acronym FROM PROFESSOR WHERE username='"+user+"';";
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(getAcronym);
      rs.next();
      courseTeaching = rs.getString("course_acronym");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    String[] weekdays = new DateFormatSymbols().getWeekdays();
    ObservableList<ClassEntry> data = new AllCourses(courseTeaching,weekdays).getAllCourses();

    //CALL TO SQL
    insertClassDataToDatabase(schoolAcronym,courseTeaching,data);

    TableView<ClassEntry> table1 = new TableView<>();
    TableColumn<ClassEntry,String> allClassesCol = new TableColumn<>("Available Courses");
//    allClassesCol.setMinWidth(75);

    TableColumn courseCol = new TableColumn("Course");
    courseCol.setMinWidth(75);
    courseCol.setCellValueFactory(
            new PropertyValueFactory<ClassEntry, String>("course"));

    TableColumn courseIDCol1 = new TableColumn("Course ID");
    courseIDCol1.setMinWidth(75);
    courseIDCol1.setCellValueFactory(
            new PropertyValueFactory<ClassEntry, Integer>("courseID"));


    TableColumn courseTitleCol = new TableColumn("Course Title");
    courseTitleCol.setMaxWidth(245);

    courseTitleCol.setCellValueFactory(
            new PropertyValueFactory<ClassEntry, String>("courseTitle"));

    TableColumn courseTimeCol = new TableColumn("Course Time");
    courseTimeCol.setMinWidth(100);
    courseTimeCol.setCellValueFactory(
            new PropertyValueFactory<ClassEntry, String>("courseTime"));

    TableColumn courseDaysCol = new TableColumn("Course Days");
    courseDaysCol.setMaxWidth(205);
    courseDaysCol.setCellValueFactory(
            new PropertyValueFactory<ClassEntry, String>("courseDays"));

    Button addClass = new Button("Add Class");
    addClass.setStyle("-fx-background-color: " + text_fill_color + "; -fx-text-fill: "+border_pane_color+";");
    setButtonFont(addClass,16);
    addClass.setAlignment(Pos.CENTER);
    addClass.setDisable(true);

    Button returnButton = new Button("Return");
    returnButton.setStyle("-fx-background-color: " + text_fill_color + "; -fx-text-fill: "+border_pane_color+";");
    setButtonFont(returnButton,16);
    returnButton.setAlignment(Pos.CENTER);

    allClassesCol.getColumns().addAll(courseCol,courseIDCol1,courseTitleCol,courseTimeCol,courseDaysCol);
    table1.setItems(data);
    table1.setStyle("-fx-font: 14px \"Arial\";");
    table1.getColumns().add(allClassesCol);
    table1.setPlaceholder(new Label("No available classes left"));
    table1.getSortOrder().add(courseCol);

    AtomicReference<ClassEntry> rowSelected = new AtomicReference<>();

    returnButton.setOnAction(e -> {
      try {
        window.setScene(homeScene(user));
      } catch (FileNotFoundException fileNotFoundException) {
        fileNotFoundException.printStackTrace();
      }
    });

    table1.setMaxHeight(270);
    table1.setMaxWidth(575);

    TableView<ClassEntry> table2 = new TableView<>();
    TableColumn<ClassEntry,String> myCoursesCol = new TableColumn<>("My Courses");

    TableColumn courseCol2 = new TableColumn("Course");
    courseCol2.setMinWidth(75);
    courseCol2.setCellValueFactory(
            new PropertyValueFactory<ClassEntry, String>("course"));

    TableColumn courseIDCol2 = new TableColumn("Course ID");
    courseIDCol2.setMinWidth(75);
    courseIDCol2.setCellValueFactory(
            new PropertyValueFactory<ClassEntry, Integer>("courseID"));


    TableColumn courseTitleCol2 = new TableColumn("Course Title");
    courseTitleCol2.setMinWidth(190);
    courseTitleCol2.setCellValueFactory(
            new PropertyValueFactory<ClassEntry, String>("courseTitle"));

    TableColumn courseTimeCol2 = new TableColumn("Course Time");
    courseTimeCol2.setMinWidth(100);
    courseTimeCol2.setCellValueFactory(
            new PropertyValueFactory<ClassEntry, String>("courseTime"));

    TableColumn courseDaysCol2 = new TableColumn("Course Days");
    courseDaysCol2.setMinWidth(200);
    courseDaysCol2.setCellValueFactory(
            new PropertyValueFactory<ClassEntry, String>("courseDays"));

    myCoursesCol.getColumns().addAll(courseCol2,courseIDCol2,courseTitleCol2,courseTimeCol2,courseDaysCol2);
    table2.setStyle("-fx-font: 14px \"Arial\";");
    myCoursesCol.setMinWidth(575);
    table2.getColumns().add(myCoursesCol);
    table2.setMaxHeight(270);
    table2.setMaxWidth(575);
    table2.setPlaceholder(new Label("You have no classes"));
    table2.getSortOrder().add(courseCol2);


    Label errorLabel = new Label("");
    setLabelFont(errorLabel,16);
    errorLabel.setStyle("-fx-text-fill: "+text_fill_color+";");

    Button dropClass = new Button("Drop Class");
    dropClass.setStyle("-fx-background-color: " + text_fill_color + "; -fx-text-fill: "+border_pane_color+";");
    setButtonFont(dropClass,16);
    dropClass.setAlignment(Pos.CENTER);
    dropClass.setDisable(true);

    Button submitInfo = new Button("Submit");
    submitInfo.setStyle("-fx-background-color: " + text_fill_color + "; -fx-text-fill: "+border_pane_color+";");
    setButtonFont(submitInfo,16);
    submitInfo.setAlignment(Pos.CENTER);
    submitInfo.setDisable(true);

    table1.setOnMouseClicked((MouseEvent event) -> {
      rowSelected.set(getTableRow(table1));
      addClass.setDisable(false);
      dropClass.setDisable(true);
    });

    table2.setOnMouseClicked((MouseEvent event) -> {
      rowSelected.set(getTableRow(table2));
      dropClass.setDisable(false);
      addClass.setDisable(true);
    });
    AtomicInteger classesNum = new AtomicInteger();
    addClass.setOnAction(e -> {
      try {
        boolean classTimesOk = checkClassTimes(table2.getItems(),rowSelected.get());
        if(classTimesOk) {
          errorLabel.setText("");
          classesNum.getAndIncrement();
          table2.getItems().add(rowSelected.get());
          table1.getItems().remove(rowSelected.get());
        } else {
          errorLabel.setText("Cannot add class, there is a time conflict");
        }
      } catch (ParseException parseException) {
        parseException.printStackTrace();
      }
      addClass.setDisable(true);
      table2.sort();

      submitInfo.setDisable(classesNum.get() <= 0);
    });

    dropClass.setOnAction(e -> {
      classesNum.getAndDecrement();
      table2.getItems().remove(rowSelected.get());
      table1.getItems().add(rowSelected.get());
      dropClass.setDisable(true);
      table1.sort();
      submitInfo.setDisable(classesNum.get() <= 0);
    });

    String finalCourseTeaching = courseTeaching;
    submitInfo.setOnAction(e -> {
//      for(int i = 0; i < classesNum.get();i++) {
//        System.out.println(table2.getItems().get(i));
//      }
        insertProfessorIntoClassDB(schoolAcronym, finalCourseTeaching,classesNum.get(),table2);

    });

    HBox buttons1 = new HBox();
    buttons1.setSpacing(20);
    buttons1.setPadding(new Insets(10, 0, 0, 10));
    buttons1.getChildren().addAll(addClass,returnButton);
    buttons1.setAlignment(Pos.CENTER);

    HBox buttons2 = new HBox();
    buttons2.setSpacing(20);
    buttons2.setPadding(new Insets(10, 0, 0, 10));
    buttons2.getChildren().addAll(dropClass,submitInfo);
    buttons2.setAlignment(Pos.CENTER);


    VBox vLayout = new VBox();
    vLayout.setSpacing(5);
    vLayout.setPadding(new Insets(10, 0, 0, 10));
    vLayout.getChildren().addAll(table1,buttons1,errorLabel,table2,buttons2);
    vLayout.setAlignment(Pos.TOP_CENTER);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(vLayout);

    borderPane.setStyle("-fx-padding: 25 10 10 10; -fx-background-color: "+border_pane_color + ";");

    return new Scene(borderPane,STAGE_WIDTH,STAGE_HEIGHT);
  }
  public ClassEntry getTableRow(TableView<ClassEntry> table) {
    // check the table's selected item and get selected item
    return (table.getSelectionModel().getSelectedItem() != null) ?
            table.getSelectionModel().getSelectedItem() : null;
  }
  public void insertClassDataToDatabase(String schoolAcronym, String majorAcronym,ObservableList<ClassEntry> data) {

    Connection connection;
    try {
      connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
      Statement statement = connection.createStatement();


      String tableName = "ALL_" + schoolAcronym.toUpperCase() + "_" + majorAcronym + "_CLASSES";


      String showTables = "SHOW TABLES LIKE '" + tableName + "'";
      ResultSet rs = statement.executeQuery(showTables);
      if(!rs.next()) { //no table yet
        String classesTable = "CREATE TABLE " + tableName + "(" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "course_id INT NOT NULL," +
                "course_acronym CHAR(5) NOT NULL," +
                "professor VARCHAR(30) DEFAULT ''," +
                "class_name VARCHAR(50) NOT NULL," +
                "class_level INT NOT NULL," +
                "class_start_time TIME NOT NULL," +
                "class_end_time TIME NOT NULL," +
                "class_days VARCHAR(80) NOT NULL," +
//              "professor_f_name VARCHAR(50) DEFAULT 'None'," +
//              "professor_l_name VARCHAR(50) DEFAULT 'None'," +
                "description VARCHAR(200) DEFAULT 'No description yet for this class'," +
                "college VARCHAR(60) NOT NULL" +
//              "college_acronym VARCHAR(8) NOT NULL" +
                ");";

        statement.executeUpdate(classesTable);
        String profNameQuery = "SELECT f_name,l_name from PROFESSOR where username='" + profUsername + "';";
        rs = statement.executeQuery(profNameQuery);
        rs.next();
//      String prof_f_name = rs.getString("f_name");
//      String prof_l_name = rs.getString("l_name");
        String profCollegeQuery = "SELECT college from PROFESSOR where username='" + profUsername + "';";
        rs = statement.executeQuery(profCollegeQuery);
        rs.next();
        String prof_college = rs.getString("college");

        for (ClassEntry datum : data) {
          String theCourse = datum.getCourse();

          String insertIntoTable = "INSERT INTO " + tableName + "(course_id,course_acronym, class_name, class_level, class_start_time,class_end_time, class_days, " +
                  "description, college) VALUES ('" + datum.getCourseID() + "', '" +
                  majorAcronym + "','" + datum.getCourseTitle() + "','" + theCourse.substring(theCourse.length() - 3) + "','" +
                  Time.valueOf(datum.getCourseStartTime() + ":00") + "','" + Time.valueOf(datum.getCourseEndTime() + ":00") + "','"
                  + datum.getCourseDays() + "','" + datum.getCourseDescription() + "','"
                  + prof_college + "');";
          statement.executeUpdate(insertIntoTable);
        }



//        Time sqlTime = Time.valueOf(time);

//        System.out.println(sqlTime);
//        Date d = Date.valueOf(data.get(i).getCourseStartTime());
//        System.out.println(d);

//        System.out.println("ITERATION: " + i);
//        System.out.println(majorAcronym);
//        System.out.println(data.get(i).getCourseTitle());
//        System.out.println(data.get(i).getCourse().substring(theCourse.length()-3,theCourse.length()));
//        System.out.println(data.get(i).getCourseTime());
//        System.out.println(data.get(i).getCourseDays());
//        System.out.println(prof_f_name);
//        System.out.println(prof_l_name);
//        System.out.println(data.get(i).getCourseDescription());
//        System.out.println(prof_college);
//        System.out.println(schoolAcronym);
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
  public void insertProfessorIntoClassDB(String schoolAcronym, String majorAcronym, int numOfClasses,TableView<ClassEntry> table) {
    Connection connection;
    try {
      connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
      Statement statement = connection.createStatement();
      String tableName = "ALL_" + schoolAcronym.toUpperCase() + "_" + majorAcronym + "_CLASSES";
      String getFullName = "SELECT f_name,l_name FROM PROFESSOR WHERE username='"+profUsername+"';";
      ResultSet rs = statement.executeQuery(getFullName);
      rs.next();
      String first_name = rs.getString("f_name");
      String last_name = rs.getString("l_name");
      prof_name = first_name + " " + last_name;
      for(int i = 0; i < numOfClasses;i++) {

        String insertIntoTable = "UPDATE " + tableName +
                " SET PROFESSOR = '" + prof_name + "' WHERE course_id = "
                + table.getItems().get(i).getCourseID()  + ";";
        statement.executeUpdate(insertIntoTable);
      }

    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
  }
}
