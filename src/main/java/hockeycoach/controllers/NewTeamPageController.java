package hockeycoach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class NewTeamPageController extends Controller {
    @FXML
    private Button saveButton, cancelButton, closeWindowButton;

    @FXML
    private ImageView teamLogo;

    @FXML
    private Label controlLabel, controlZip, controlContact, controlFounded,
            controlHeadCoach, controlCaptain, controlPresident;

    @FXML
    private TextArea notes;

    @FXML
    private TextField teamName,
            stadiumName, stadiumStreet, stadiumZipCity, stadiumCountry,
            contactName, contactPhone, contactEmail,
            website, founded, currentLeague,
            presidentName, headCoachName, captainName;
}

