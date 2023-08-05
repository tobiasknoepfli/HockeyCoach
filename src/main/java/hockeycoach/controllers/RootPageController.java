package hockeycoach.controllers;

import hockeycoach.mainClasses.Game;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class RootPageController {
    @FXML
    private TableView<Team> teamsTable;

    @FXML
    private TableView<Game> gamesTable;

    @FXML
    private TableView<Training> trainingsTable;
}