package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class TeamController extends Controller {
    @FXML
    private Button editPlayerButton,
            saveButton, editButton, cancelButton, deleteButton,newTeamButton,backButton;

    @FXML
    private ImageView teamLogo;

    @FXML
    private TableView<Player> teamPlayers;

    @FXML
    private TextArea notes;

    @FXML
    private TextField teamName,
            stadiumName, stadiumStreet, stadiumZip,stadiumCity, stadiumCountry,
            contactFirstName,contactLastName, contactPhone, contactEmail,
            website, founded, currentLeague,
            presidentFirstName,presidentLastName, headCoachFirstName,headCoachLastName;
}
