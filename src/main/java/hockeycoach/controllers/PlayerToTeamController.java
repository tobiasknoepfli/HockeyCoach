package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PlayerToTeamController extends Controller {
    @FXML
    private Button addButton,removeButton,
            saveButton;
    @FXML
    private TableView teamPlayers,allPlayers;

    @FXML
    private TextField team;
}
