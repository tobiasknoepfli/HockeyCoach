package hockeycoach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class NewTeamPageController extends Controller {
    @FXML
    private TextField teamName;

    @FXML
    private TextField stadiumName, stadiumStreet, stadiumZipCity, stadiumCountry;

    @FXML
    private TextField contactName, contactPhone, contactEmail;

    @FXML
    private TextField website, founded, currentLeague;

    @FXML
    private TextField presidentName, headCoachName, captainName;

    @FXML
    private TextArea notes;

    @FXML
    private Label controlLabel, controlZip, controlContact, controlFounded, controlHeadCoach, controlCaptain, controlPresident;

    @FXML
    private ImageView teamLogo;

    @FXML
    private Button saveButton, cancelButton, closeWindowButton;

}

