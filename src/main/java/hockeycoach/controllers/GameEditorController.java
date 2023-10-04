package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class GameEditorController {
    @FXML
    private TextField gameDate, gameTime;

    @FXML
    private TextField gameStadium;

    @FXML
    private TextField gameTeam;

    @FXML
    private TextField gameOpponent;

    @FXML
    private TextField captain, assistant1, assistant2;

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
    private TextField ppdl1, ppdl2, ppdlfiller, ppdr1, ppdr2, ppdrfiller;

    @FXML
    private TextField ppc1, ppc2, ppcfiller, ppfl1, ppfl2, ppflfiller, ppfr1, ppfr2, ppfrfiller;

    @FXML
    private TextField bpdl1, bpdl2, bpdlfiller, bpdr1, bpdr2, bpdrfiller;

    @FXML
    private TextField bpc1, bpc2, bpcfiller, bpfl1, bpfl2, bpflfiller, bpfr1, bpfr2, bpfrfiller;

    @FXML
    private TextField bpsd1, bpsd2, bpsf1, bpsf2;

    @FXML
    private Button refreshPlayerList;

    @FXML
    private Tab lineupTab, powerplayTab, boxplayTab;

    @FXML
    private Label lineOutput;

    @FXML
    private Button saveButton;

    @FXML
    private AnchorPane lineupAnchorPane, ppAnchorPane, bpAnchorPane;

    @FXML
    private GridPane lineupGrid, ppLineupGrid, bpLineupGrid;
}