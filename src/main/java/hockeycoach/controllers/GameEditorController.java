package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import jfxtras.scene.control.LocalTimeTextField;

public class GameEditorController extends Controller {
    @FXML
    private AnchorPane lineupAnchorPane, ppAnchorPane, bpAnchorPane,nAnchorPane;

    @FXML
    private Button refreshPlayerList,
            saveButton, backButton;

    @FXML
    DatePicker gameDate;

    @FXML
    LocalTimeTextField gameTime;

    @FXML
    private GridPane lineupGrid, ppLineupGrid, bpLineupGrid,nLineupGrid;

    @FXML
    private ImageView boardImage, ppBoardImage, bpBoardImage,nBoardImage;

    @FXML
    private Label lbfl1, lbfl2, lbfl3, lbfl4, lbfr1, lbfr2, lbfr3, lbfr4,
            lbc1, lbc2, lbc3, lbc4, lbgk1, lbgk2, lbgk3, lbgk4,
            lbdl1, lbdl2, lbdl3, lbdl4, lbdr1, lbdr2, lbdr3, lbdr4,
            lgGK1, lgGK2, lgGK3, lgGK4,
            lgRD1, lgRD2, lgRD3, lgRD4, lgLD1, lgLD2, lgLD3, lgLD4,
            lgRF1, lgRF2, lgRF3, lgRF4, lgC1, lgC2, lgC3, lgC4, lgLF1, lgLF2, lgLF3, lgLF4,
            ngGK1, ngGK2, ngGK3, ngGK4,
            ngRD1, ngRD2, ngRD3, ngRD4, ngLD1, ngLD2, ngLD3, ngLD4,
            ngRF1, ngRF2, ngRF3, ngRF4, ngC1, ngC2, ngC3, ngC4, ngLF1, ngLF2, ngLF3, ngLF4;

    @FXML
    private Tab lineupTab, powerplayTab, boxplayTab,nuclearTab;

    @FXML
    private TableView<Player> teamPlayers, availablePlayers;

    @FXML
    private TextField gameStadium, gameTeam, gameOpponent,
            captain, assistant1, assistant2,
            penalty1,penalty2,emptyNet1,emptyNet2,
            gk1, dl1, dl2, dl3, dl4, dr1, dr2, dr3, dr4,
            c1, c2, c3, c4, fl1, fl2, fl3, fl4, fr1, fr2, fr3, fr4,
            sgk1, sgk2, sgk3, sd1, sd2, sd3, sf1, sf2, sf3,
            ppdl1, ppdl2, ppdlfiller, ppdr1, ppdr2, ppdrfiller,
            ppc1, ppc2, ppcfiller, ppfl1, ppfl2, ppflfiller, ppfr1, ppfr2, ppfrfiller,
            bpdl1, bpdl2, bpdlfiller, bpdr1, bpdr2, bpdrfiller,
            bpc1, bpc2, bpcfiller, bpfl1, bpfl2, bpflfiller, bpfr1, bpfr2, bpfrfiller,
            bpsd1, bpsd2, bpsf1, bpsf2,
            ndl1, ndl2, ndr1, ndr2, nc1, nc2, nfl1, nfl2, nfr1, nfr2;
}