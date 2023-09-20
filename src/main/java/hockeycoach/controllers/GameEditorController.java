package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private ImageView ppBoardImage;

    @FXML
    private ImageView bpBoardImage;

    @FXML
    private TextField gk1;

    @FXML
    private TextField dl1, dl2, dl3, dl4;

    @FXML
    private TextField dr1, dr2, dr3, dr4;

    @FXML
    private TextField c1, c2, c3, c4;

    @FXML
    private TextField fl1, fl2, fl3, fl4;

    @FXML
    private TextField fr1, fr2, fr3, fr4;

    @FXML
    private TextField sgk1, sgk2;

    @FXML
    private TextField sd1, sd2, sd3;

    @FXML
    private TextField sf1, sf2, sf3;

    @FXML
    private Label lbgk1;

    @FXML
    private Label lbdr1, lbdr2, lbdr3, lbdr4;

    @FXML
    private Label lbdl1, lbdl2, lbdl3, lbdl4;

    @FXML
    private Label lbc1, lbc2, lbc3, lbc4;

    @FXML
    private Label lbfl1, lbfl2, lbfl3, lbfl4;

    @FXML
    private Label lbfr1, lbfr2, lbfr3, lbfr4;

    @FXML
    private Label lbgks1, lbgks2, lbsd1, lbsd2, lbsd3, lbsf1, lbsf2, lbsf3;
}