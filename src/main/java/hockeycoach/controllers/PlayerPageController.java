package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PlayerPageController {
    @FXML
    private TableView<Player> teamPlayers;

    @FXML
    private ImageView playerPhoto;

    @FXML
    private TextField playerName;

    @FXML
    private TextField team;

    @FXML
    private TextField street;

    @FXML
    private TextField zipCity;

    @FXML
    private TextField country;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField jersey;

    @FXML
    private TextField positions;

    @FXML
    private TextArea strengths;

    @FXML
    private TextArea weaknesses;

    @FXML
    private TextArea notes;
}
