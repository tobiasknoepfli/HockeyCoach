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
    private TextField sgk1, sgk2,sgk3;

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
    private Button saveButton;

    @FXML
    private AnchorPane lineupAnchorPane, ppAnchorPane, bpAnchorPane;

    @FXML
    private GridPane lineupGrid, ppLineupGrid, bpLineupGrid;

    @FXML
    Label lbfl1, lbfl2, lbfl3, lbfl4, lbfr1, lbfr2, lbfr3, lbfr4;

    @FXML
    Label lbc1,lbc2,lbc3,lbc4,lbgk1,lbgk2,lbgk3,lbgk4;

    @FXML
    Label lbdl1,lbdl2,lbdl3,lbdl4,lbdr1,lbdr2,lbdr3,lbdr4;

    @FXML
    private Label lgGK1, lgGK2, lgGK3, lgGK4;

    @FXML
    private Label lgRD1, lgRD2, lgRD3, lgRD4, lgLD1, lgLD2, lgLD3, lgLD4;

    @FXML
    private Label lgRF1, lgRF2, lgRF3, lgRF4, lgC1, lgC2, lgC3, lgC4, lgLF1, lgLF2, lgLF3, lgLF4;

    @FXML
    private Label ngGK1, ngGK2, ngGK3, ngGK4;

    @FXML
    private Label ngRD1, ngRD2, ngRD3, ngRD4, ngLD1, ngLD2, ngLD3, ngLD4;

    @FXML
    private Label ngRF1, ngRF2, ngRF3, ngRF4, ngC1, ngC2, ngC3, ngC4, ngLF1, ngLF2, ngLF3, ngLF4;
}