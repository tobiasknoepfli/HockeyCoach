package hockeycoach.controllers;

import hockeycoach.UI.PresentationModel;
import hockeycoach.mainClasses.Game;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class StartPageController {
    @FXML
    private TableView<Team> teamsTable;

    @FXML
    private TableView<Game> gamesTable;

    @FXML
    private TableView<Training> trainingsTable;
}