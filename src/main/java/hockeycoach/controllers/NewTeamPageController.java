package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class NewTeamPageController {
    @FXML
    private ImageView teamLogo;

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
    private TableView<Player> teamPlayers;

    @FXML
    private TextArea notes;
}
