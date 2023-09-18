package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class GameEditorController {
    @FXML
    private TextField gameDate;

    @FXML
    private TextField gameTime;

    @FXML
    private TextField gameStadium;

    @FXML
    private TextField gameTeam;

    @FXML
    private TextField gameOpponent;

    @FXML
    private TableView<Player> teamPlayers;

    @FXML
    private TableView<Player> availablePlayers;

    @FXML
    private ImageView boardImage;
}
