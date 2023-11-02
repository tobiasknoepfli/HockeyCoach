package hockeycoach.controllers;

import hockeycoach.mainClasses.Game;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class StartController extends  Controller{
    @FXML
    private Button  newTeamButton, closeWindowButton, newPlayerButton, newDrillButton;

    @FXML
    private HBox controlBoxLeft, controlBoxRight;

    @FXML
    private TableColumn teamsColumn, gamesColumn1, gamesColumn2, gamesColumn3, trainingsColumn1, trainingsColumn2;

    @FXML
    private TableView<Game> gamesTable;

    @FXML
    private TableView<Team> teamsTable;

    @FXML
    private TableView<Training> trainingsTable;

}