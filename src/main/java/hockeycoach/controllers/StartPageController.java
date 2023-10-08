package hockeycoach.controllers;

import hockeycoach.mainClasses.Game;
import hockeycoach.supportClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class StartPageController extends  Controller{
    @FXML
    private TableView<Team> teamsTable;

    @FXML
    private TableView<Game> gamesTable;

    @FXML
    private TableView<Training> trainingsTable;

    @FXML
    private Button closeWindowButton;

    @FXML
    public void setSelectedTeam() {
        Team selectedTeam = teamsTable.getSelectionModel().getSelectedItem();
        SingletonTeam.getInstance().setSelectedTeam(selectedTeam);
        SingletonTeam.getInstance().setIndex(teamsTable.getSelectionModel().getSelectedIndex());
    }

}