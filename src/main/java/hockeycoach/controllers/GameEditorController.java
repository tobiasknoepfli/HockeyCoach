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

    @FXML
    private TextField gk1;

    @FXML
    private TextField gk2;

    @FXML
    private TextField dl1;

    @FXML
    private TextField dl2;

    @FXML
    private TextField dl3;

    @FXML
    private TextField dl4;

    @FXML
    private TextField dr1;

    @FXML
    private TextField dr2;

    @FXML
    private TextField dr3;

    @FXML
    private TextField dr4;

    @FXML
    private TextField c1;

    @FXML
    private TextField c2;

    @FXML
    private TextField c3;

    @FXML
    private TextField c4;

    @FXML
    private TextField fl1;

    @FXML
    private TextField fl2;

    @FXML
    private TextField fl3;

    @FXML
    private TextField fl4;

    @FXML
    private TextField fr1;

    @FXML
    private TextField fr2;

    @FXML
    private TextField fr3;

    @FXML
    private TextField fr4;

    @FXML
    TextField sgk1;

    @FXML
    TextField sgk2;

    @FXML
    TextField sd1;

    @FXML
    TextField sd2;

    @FXML
    TextField sd3;

    @FXML
    TextField sf1;

    @FXML
    TextField sf2;

    @FXML
    TextField sf3;
}