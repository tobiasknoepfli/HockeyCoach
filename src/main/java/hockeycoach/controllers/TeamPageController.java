package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class TeamPageController extends Controller {
    @FXML
    private Button editPlayerButton,
            saveButton, editButton, cancelButton, deleteButton;

    @FXML
    private ImageView teamLogo;

    @FXML
    private TableView<Player> teamPlayers;

    @FXML
    private TextArea notes;

    @FXML
    private TextField teamName,
            stadiumName, stadiumStreet, stadiumZipCity, stadiumCountry,
            contactName, contactPhone, contactEmail,
            website, founded, currentLeague,
            presidentName, headCoachName, captainName;
}
