package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class NewTeamPageController {
    @FXML
    private TextField teamName;

    @FXML
    private TextField stadiumName;

    @FXML
    private TextField stadiumStreet;

    @FXML
    private TextField stadiumZipCity;

    @FXML
    private TextField stadiumCountry;

    @FXML
    private TextField contactName;

    @FXML
    private TextField contactPhone;

    @FXML
    private TextField contactEmail;

    @FXML
    private TextField website;

    @FXML
    private TextField founded;

    @FXML
    private TextField presidentName;

    @FXML
    private TextField currentLeague;

    @FXML
    private TextField headCoachName;

    @FXML
    private TextField captainName;

    @FXML
    private TextArea notes;

    @FXML
    private Label controlLabel;

    @FXML
    private ImageView teamLogo;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label controlZip;

    @FXML
    private Label controlContact;

    @FXML
    private Label controlFounded;

    @FXML
    private Label controlHeadCoach;

    @FXML
    private Label controlCaptain;

    @FXML
    private Label controlPresident;
}
