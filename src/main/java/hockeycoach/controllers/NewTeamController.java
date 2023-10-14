package hockeycoach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class NewTeamController extends Controller {
    @FXML
    private Button saveButton, cancelButton, closeWindowButton, backButton;

    @FXML
    private ImageView teamLogo;

    @FXML
    private TextArea notes;

    @FXML
    private TextField teamName,
            stadiumName, stadiumStreet, stadiumZip,stadiumCity, stadiumCountry,
            contactFirstName, contactLastName, contactPhone, contactEmail,
            website, founded, currentLeague,
            presidentFirstName, presidentLastName, headCoachFirstName, headCoachLastName;

    @FXML
    private Label controlLabel;
}

