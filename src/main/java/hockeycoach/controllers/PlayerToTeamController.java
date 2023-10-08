package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PlayerToTeamController extends Controller {
    @FXML
    private TableView teamPlayers;

    @FXML
    private TableView allPlayers;

    @FXML
    private TextField team;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button saveButton;
}
