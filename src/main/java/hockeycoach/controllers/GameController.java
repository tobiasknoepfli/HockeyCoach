package hockeycoach.controllers;

import hockeycoach.mainClasses.Game;
import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GameController extends Controller {
    @FXML
    private AnchorPane lineupAnchorPane, ppAnchorPane, bpAnchorPane;

    @FXML
    private Button saveButton, cancelButton,
            refreshPlayerList;

    @FXML
    private GridPane lineupGrid, ppLineupGrid, bpLineupGrid;

    @FXML
    private HBox controlBox;

    @FXML
    private ImageView boardImage, ppBoardImage, bpBoardImage;

    @FXML
    private Tab lineupTab, powerplayTab, boxplayTab;

    @FXML
    private TableColumn playerLastName, playerFirstName, playerPositions, stick;

    @FXML
    private TableView<Game> allGames;

    @FXML
    private TableView<Player> teamPlayers;

    @FXML
    private TabPane lineupTabPane;

    @FXML
    private TextField gameTeam, gameOpponent, gameDate, gameTime,
            gameStadium, captain, assistant1, assistant2,
            gk1, dl1, dl2, dl3, dl4, dr1, dr2, dr3, dr4,
            c1, c2, c3, c4,
            fl1, fl2, fl3, fl4, fr1, fr2, fr3, fr4,
            sgk1, sgk2, sgk3,
            sd1, sd2, sd3, sf1, sf2, sf3,
            ppdl1, ppdl2, ppdlfiller, ppdr1, ppdr2, ppdrfiller,
            ppc1, ppc2, ppcfiller, ppfl1, ppfl2, ppflfiller, ppfr1, ppfr2, ppfrfiller,
            bpdl1, bpdl2, bpdlfiller, bpdr1, bpdr2, bpdrfiller,
            bpfl1, bpfl2, bpflfiller, bpfr1, bpfr2, bpfrfiller,
            bpsd1, bpsd2, bpsf1, bpsf2;
}
