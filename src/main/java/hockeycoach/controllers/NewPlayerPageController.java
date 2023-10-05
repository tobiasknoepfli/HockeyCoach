package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class NewPlayerPageController {
    @FXML
    private TableView<Player> allPlayers;

    @FXML
    private ImageView playerPhoto;

    @FXML
    private TextField playerFirstName;

    @FXML
    private TextField playerLastName;

    @FXML
    private TextField street;

    @FXML
    private TextField zip;

    @FXML
    private TextField city;

    @FXML
    private TextField country;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField positions;

    @FXML
    private TextField aLicence;

    @FXML
    private TextField bLicence;

    @FXML
    private TextField stick;

    @FXML
    private TextArea strengths;

    @FXML
    private TextArea weaknesses;

    @FXML
    private TextArea notes;

    @FXML
    private Button saveButton;

}
