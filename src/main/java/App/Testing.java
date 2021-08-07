package App;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Testing extends Application {
  private final TableView<Team> table = new TableView<>();
  private final String[] plTeams = {
          "Arsenal","Aston Villa","Brentford","Brighton","Burnley","Chelsea","Crystal Palace","Everton","Leeds United","Leicester City",
          "Liverpool","Man City","Man United","Newcastle","Norwich City","Southampton","Tottenham","Watford","West Ham","Wolves"
  };
  private TableCell<Object,Object> clubColorTest = new TableCell<>();

  final ObservableList<Team> data =
          FXCollections.observableArrayList(
                  new Team(plTeams[0],0,0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[1], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[2], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[3], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[4], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[5], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[6], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[7], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[8], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[9], 0, 0,0,0,0,0,0,0,"green"),
                  new Team(plTeams[10], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[11], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[12], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[13], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[14], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[15], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[16], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[17], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[18], 0, 0,0,0,0,0,0,0,"yellow"),
                  new Team(plTeams[19], 0, 0,0,0,0,0,0,0,"yellow")
          );

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(new Group());
    stage.setTitle("Premier League Table");
    stage.setWidth(450);
    stage.setHeight(500);

    final Label label = new Label("Premier League 2021-22");
    label.setFont(new Font("Arial", 20));

    table.setEditable(true);

    TableColumn clubCol = new TableColumn("Club");
    clubCol.setMinWidth(100);
    clubCol.setCellValueFactory(
            new PropertyValueFactory<>("club"));
    clubCol.setStyle("-fx-background-color: gold;");
    
    TableColumn mpCol = new TableColumn("MP");
    mpCol.setMinWidth(8);
    mpCol.setCellValueFactory(
            new PropertyValueFactory<>("mp"));

    TableColumn winsCol = new TableColumn("W");
    winsCol.setMinWidth(10);
    winsCol.setCellValueFactory(
            new PropertyValueFactory<>("wins"));

    TableColumn drawsCol = new TableColumn("D");
    drawsCol.setMinWidth(10);
    drawsCol.setCellValueFactory(
            new PropertyValueFactory<>("draws"));

    TableColumn lossesCol = new TableColumn("L");
    lossesCol.setMinWidth(10);
    lossesCol.setCellValueFactory(
            new PropertyValueFactory<>("losses"));

    TableColumn gfCol = new TableColumn("GF");
    gfCol.setMinWidth(10);
    gfCol.setCellValueFactory(
            new PropertyValueFactory<>("gf"));

    TableColumn gaCol = new TableColumn("GA");
    gaCol.setMinWidth(10);
    gaCol.setCellValueFactory(
            new PropertyValueFactory<>("ga"));

    TableColumn gdCol = new TableColumn("GD");
    gdCol.setMinWidth(10);
    gdCol.setCellValueFactory(
            new PropertyValueFactory<>("gd"));

    TableColumn pointsCol = new TableColumn("PTS");
    pointsCol.setMinWidth(10);
    pointsCol.setCellValueFactory(
            new PropertyValueFactory<>("points"));

    table.setItems(data);
    table.getColumns().addAll(clubCol, mpCol, winsCol,drawsCol,lossesCol,
            gfCol,gaCol,gdCol,pointsCol);

    final VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.setPadding(new Insets(8, 0, 0, 8));
    vbox.getChildren().addAll(label, table);

    ((Group) scene.getRoot()).getChildren().addAll(vbox);

    stage.setScene(scene);
    stage.show();
  }

  public static class Team {

    private final SimpleStringProperty club;
    private final SimpleIntegerProperty mp;
    private final SimpleIntegerProperty wins;
    private final SimpleIntegerProperty draws;
    private final SimpleIntegerProperty losses;
    private final SimpleIntegerProperty gf;
    private final SimpleIntegerProperty ga;
    private final SimpleIntegerProperty gd;
    private final SimpleIntegerProperty points;
    private TableRow clubColor = new TableRow();


    private Team(String club, int mp, int wins, int draws, int losses, int gf,
                 int ga, int gd, int points,String color) {
      this.club = new SimpleStringProperty(club);
      this.mp = new SimpleIntegerProperty(mp);
      this.wins = new SimpleIntegerProperty(wins);
      this.draws = new SimpleIntegerProperty(draws);
      this.losses = new SimpleIntegerProperty(losses);
      this.gf = new SimpleIntegerProperty(gf);
      this.ga = new SimpleIntegerProperty(ga);
      this.gd = new SimpleIntegerProperty(gd);
      this.points = new SimpleIntegerProperty(points);
      this.clubColor.setStyle("-fx-background-color: " + color + ";");
    }

    public String getClub() {
      return club.get();
    }

    public void setClub(String clubName) {
      club.set(clubName);
    }

    public int getMp() {
      return mp.get();
    }

    public void setMp(int mpNum) {
      mp.set(mpNum);
    }

    public int getWins() {
      return wins.get();
    }

    public void setWins(int winNum) {
      wins.set(winNum);
    }
    public int getDraws() {
      return draws.get();
    }

    public SimpleIntegerProperty drawsProperty() {
      return draws;
    }

    public void setDraws(int draws) {
      this.draws.set(draws);
    }

    public int getLosses() {
      return losses.get();
    }

    public SimpleIntegerProperty lossesProperty() {
      return losses;
    }

    public void setLosses(int losses) {
      this.losses.set(losses);
    }

    public int getGf() {
      return gf.get();
    }

    public SimpleIntegerProperty gfProperty() {
      return gf;
    }

    public void setGf(int gf) {
      this.gf.set(gf);
    }

    public int getGa() {
      return ga.get();
    }

    public SimpleIntegerProperty gaProperty() {
      return ga;
    }

    public void setGa(int ga) {
      this.ga.set(ga);
    }

    public int getGd() {
      return gd.get();
    }

    public SimpleIntegerProperty gdProperty() {
      return gd;
    }

    public void setGd(int gd) {
      this.gd.set(gd);
    }

    public int getPoints() {
      return points.get();
    }

    public SimpleIntegerProperty pointsProperty() {
      return points;
    }

    public void setPoints(int points) {
      this.points.set(points);
    }
  }
}
