package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PlayerController extends Controller {
    @FXML
    private Button saveButton, editButton, cancelButton, deleteButton, newPlayerButton;

    @FXML
    private ImageView playerPhoto;

    @FXML
    private TableView<Player> teamPlayers;

    @FXML
    private TableView<Team> playerTeams;

    @FXML
    private TextArea strengths, weaknesses, notes;

    @FXML
    private TextField playerName, team, street, zipCity, country, phone, email,
            jersey, positions;
}
