package hockeycoach.controllers;

import hockeycoach.mainClasses.Game;
import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GameController {

    @FXML
    private HBox controlBox;

    @FXML
    private Button saveButton, cancelButton;

    @FXML
    private TableView<Game> allGames;

    @FXML
    private TextField gameTeam, gameOpponent, gameDate, gameTime;

    @FXML
    private TextField gameStadium, captain, assistant1, assistant2;

    @FXML
    private Button refreshPlayerList;

    @FXML
    private TableView<Player> teamPlayers;

    @FXML
    private TableColumn playerLastName, playerFirstName, playerPositions, stick;

    @FXML
    private TabPane lineupTabPane;

    @FXML
    private Tab lineupTab;

    @FXML
    private AnchorPane lineupAnchorPane;

    @FXML
    private ImageView boardImage;

    @FXML
    private GridPane lineupGrid;

    @FXML
    private TextField gk1, dl1, dl2, dl3, dl4, dr1, dr2, dr3, dr4;

    @FXML
    private TextField c1, c2, c3, c4;

    @FXML
    private TextField fl1, fl2, fl3, fl4, fr1, fr2, fr3, fr4, sgk1, sgk2, sgk3;


    @FXML
    private TextField sd1, sd2, sd3, sf1, sf2, sf3;


    @FXML
    private Tab powerplayTab;


    @FXML
    private AnchorPane ppAnchorPane;


    @FXML
    private ImageView ppBoardImage;


    @FXML
    private GridPane ppLineupGrid;


    @FXML
    private TextField ppdl1;


    @FXML
    private TextField ppdl2;


    @FXML
    private TextField ppdlfiller;


    @FXML
    private TextField ppdr1;


    @FXML
    private TextField ppdr2;


    @FXML
    private TextField ppdrfiller;


    @FXML
    private TextField ppc1;


    @FXML
    private TextField ppc2;


    @FXML
    private TextField ppcfiller;


    @FXML
    private TextField ppfl1;


    @FXML
    private TextField ppfl2;


    @FXML
    private TextField ppflfiller;


    @FXML
    private TextField ppfr1;


    @FXML
    private TextField ppfr2;


    @FXML
    private TextField ppfrfiller;


    @FXML
    private Tab boxplayTab;


    @FXML
    private AnchorPane bpAnchorPane;


    @FXML
    private ImageView bpBoardImage;


    @FXML
    private GridPane bpLineupGrid;


    @FXML
    private TextField bpdl1;


    @FXML
    private TextField bpdl2;


    @FXML
    private TextField bpdlfiller;


    @FXML
    private TextField bpdr1;


    @FXML
    private TextField bpdr2;


    @FXML
    private TextField bpdrfiller;


    @FXML
    private TextField bpfl1;


    @FXML
    private TextField bpfl2;


    @FXML
    private TextField bpflfiller;


    @FXML
    private TextField bpfr1;


    @FXML
    private TextField bpfr2;


    @FXML
    private TextField bpfrfiller;


    @FXML
    private TextField bpsd1;


    @FXML
    private TextField bpsd2;


    @FXML
    private TextField bpsf1;


    @FXML
    private TextField bpsf2;


}
