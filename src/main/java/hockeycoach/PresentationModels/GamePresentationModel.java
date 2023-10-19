package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBGameLoader;
import hockeycoach.DB.DBLoader.DBLineLoader;
import hockeycoach.mainClasses.*;
import hockeycoach.mainClasses.Lines.*;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.SingletonTeam;
import hockeycoach.supportClasses.TextFieldAction;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static hockeycoach.AppStarter.*;

public class GamePresentationModel extends PresentationModel {
    ButtonControls buttonControls = new ButtonControls();
    TextFieldAction textFieldAction = new TextFieldAction();
    Stack<TextFieldAction> textFieldActions = new Stack<>();

    Button saveButton, cancelButton, refreshPlayerList, backButton, newGameButton;
    TextField gameTeam, gameOpponent, gameDate, gameTime, gameStadium,
            captain, assistant1, assistant2,
            gk1,
            dl1, dl2, dl3, dl4, dr1, dr2, dr3, dr4,
            c1, c2, c3, c4,
            fl1, fl2, fl3, fl4, fr1, fr2, fr3, fr4,
            sgk1, sgk2, sgk3,
            sd1, sd2, sd3, sf1, sf2, sf3,
            ppdl1, ppdl2, ppdlfiller, ppdr1, ppdr2, ppdrfiller,
            ppc1, ppc2, ppcfiller,
            ppfl1, ppfl2, ppflfiller, ppfr1, ppfr2, ppfrfiller,
            bpdl1, bpdl2, bpdlfiller, bpdr1, bpdr2, bpdrfiller,
            bpfl1, bpfl2, bpflfiller, bpfr1, bpfr2, bpfrfiller,
            bpsd1, bpsd2, bpsf1, bpsf2,
            ndl1, ndl2, ndr1, ndr2, nc1, nc2, nfl1, nfl2, nfr1, nfr2;

    TableView<Game> allGames;
    TableView<Player> teamPlayers;
    TabPane lineupTabPane;
    AnchorPane  lineupAnchorPane, ppAnchorPane, bpAnchorPane, nAnchorPane;
    ImageView  boardImage, ppBoardImage, bpBoardImage, nBoardImage;
    GridPane  lineupGrid, ppLineupGrid, bpLineupGrid, nLineupGrid;

    DBGameLoader dbGameLoader = new DBGameLoader();
    DBLineLoader dbLineLoader = new DBLineLoader();
    List<Game> allGameList;
    Team selectedTeam;

    @Override
    public void initializeControls(Pane root) {
        importFields(root);

        selectedTeam = SingletonTeam.getInstance().getSelectedTeam();

        allGameList = dbGameLoader.getGames("SELECT * FROM game WHERE team =" + selectedTeam.getTeamID());
        allGames.getItems().clear();
        allGames.getItems().addAll(allGameList);

        gameTeam.setText(selectedTeam.getName());

        TextField[] textFields = {gameTeam, gameOpponent, gameDate, gameTime, gameStadium,
                captain, assistant1, assistant2,
                gk1,
                dl1, dl2, dl3, dl4, dr1, dr2, dr3, dr4,
                c1, c2, c3, c4,
                fl1, fl2, fl3, fl4, fr1, fr2, fr3, fr4,
                sgk1, sgk2, sgk3,
                sd1, sd2, sd3, sf1, sf2, sf3,
                ppdl1, ppdl2, ppdlfiller, ppdr1, ppdr2, ppdrfiller,
                ppc1, ppc2, ppcfiller,
                ppfl1, ppfl2, ppflfiller, ppfr1, ppfr2, ppfrfiller,
                bpdl1, bpdl2, bpdlfiller, bpdr1, bpdr2, bpdrfiller,
                bpfl1, bpfl2, bpflfiller, bpfr1, bpfr2, bpfrfiller,
                bpsd1, bpsd2, bpsf1, bpsf2,
                ndl1, ndl2, ndr1, ndr2, nc1, nc2, nfl1, nfl2, nfr1, nfr2};

        Arrays.stream(textFields).forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        getDBEntries(root);
        setupButtons(root);
        setupEventListeners(root);
    }

    @Override
    public void getDBEntries(Pane root) {

    }

    @Override
    public void setupButtons(Pane root) {
        newGameButton.setOnAction(event -> {
            buttonControls.openGameEditorHide(root, GAME);
        });

        backButton.setOnAction(event -> {
            textFieldAction.undoLastAction(textFieldActions);
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
        allGames.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            gameOpponent.setText(newValue.getOpponent());
            gameDate.setText(String.valueOf(newValue.getGameDate()));
            gameTime.setText(String.valueOf(newValue.getGameTime()));
            gameStadium.setText(newValue.getStadium());
            captain.setText(newValue.getCaptain().getLastName() + " " + newValue.getCaptain().getFirstName());
            assistant1.setText(newValue.getAssistant1().getLastName() + " " + newValue.getAssistant1().getFirstName());
            assistant2.setText(newValue.getAssistant2().getLastName() + " " + newValue.getAssistant2().getFirstName());

            List<Line> lines = dbLineLoader.getLines("SELECT * FROM line WHERE gameID = " + newValue.getGameID());

            Line firstLine = lines.stream()
                    .filter(line -> line.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstLine != null) {
                gk1.setText(getPlayerName(firstLine.getGoalkeeper()));
                dl1.setText(getPlayerName(firstLine.getDefenderLeft()));
                dr1.setText(getPlayerName(firstLine.getDefenderRight()));
                c1.setText(getPlayerName(firstLine.getCenter()));
                fl1.setText(getPlayerName(firstLine.getForwardLeft()));
                fr1.setText(getPlayerName(firstLine.getForwardRight()));
            }

            Line secondLine = lines.stream()
                    .filter(line -> line.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondLine != null) {
                dr2.setText(getPlayerName(secondLine.getDefenderRight()));
                dl2.setText(getPlayerName(secondLine.getDefenderLeft()));
                c2.setText(getPlayerName(secondLine.getCenter()));
                fr2.setText(getPlayerName(secondLine.getForwardRight()));
                fl2.setText(getPlayerName(secondLine.getForwardLeft()));
            }

            Line thirdLine = lines.stream()
                    .filter(line -> line.getLineNr() == 3)
                    .findAny().orElse(null);
            if (thirdLine != null) {
                dr3.setText(getPlayerName(thirdLine.getDefenderRight()));
                dl3.setText(getPlayerName(thirdLine.getDefenderLeft()));
                c3.setText(getPlayerName(thirdLine.getCenter()));
                fr3.setText(getPlayerName(thirdLine.getForwardRight()));
                fl3.setText(getPlayerName(thirdLine.getForwardLeft()));
            }

            Line fourthLine = lines.stream()
                    .filter(line -> line.getLineNr() == 4)
                    .findAny().orElse(null);
            if (fourthLine != null) {
                dr4.setText(getPlayerName(fourthLine.getDefenderRight()));
                dl4.setText(getPlayerName(fourthLine.getDefenderLeft()));
                c4.setText(getPlayerName(fourthLine.getCenter()));
                fr4.setText(getPlayerName(fourthLine.getForwardRight()));
                fl4.setText(getPlayerName(fourthLine.getForwardLeft()));
            }

            List<PowerplayLine> ppLine = dbLineLoader.getPPLines("SELECT * FROM powerplayLine WHERE gameID = " + newValue.getGameID());

            PowerplayLine firstPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstPPLine != null) {
                ppdl1.setText(getPlayerName(firstPPLine.getDefenderLeft()));
                ppdr1.setText(getPlayerName(firstPPLine.getDefenderRight()));
                ppc1.setText(getPlayerName(firstPPLine.getCenter()));
                ppfl1.setText(getPlayerName(firstPPLine.getForwardLeft()));
                ppfr1.setText(getPlayerName(firstPPLine.getForwardRight()));
            }

            PowerplayLine secondPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondPPLine != null) {
                ppdl2.setText(getPlayerName(secondPPLine.getDefenderLeft()));
                ppdr2.setText(getPlayerName(secondPPLine.getDefenderRight()));
                ppc2.setText(getPlayerName(secondPPLine.getCenter()));
                ppfl2.setText(getPlayerName(secondPPLine.getForwardLeft()));
                ppfr2.setText(getPlayerName(secondPPLine.getForwardRight()));
            }

            PowerplayLine fillerPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 3)
                    .findAny().orElse(null);
            if (fillerPPLine != null) {
                ppdlfiller.setText(getPlayerName(fillerPPLine.getDefenderLeft()));
                ppdrfiller.setText(getPlayerName(fillerPPLine.getDefenderRight()));
                ppcfiller.setText(getPlayerName(fillerPPLine.getCenter()));
                ppfrfiller.setText(getPlayerName(fillerPPLine.getForwardLeft()));
                ppfrfiller.setText(getPlayerName(fillerPPLine.getForwardRight()));
            }

            List<BoxplayLine> bpLine = dbLineLoader.getBPLines("SELECT * FROM boxplayLine WHERE gameID = " + newValue.getGameID());

            BoxplayLine firstBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstBPLine != null) {
                bpdl1.setText(getPlayerName(firstBPLine.getDefenderLeft()));
                bpdr1.setText(getPlayerName(firstBPLine.getDefenderRight()));
                bpfl1.setText(getPlayerName(firstBPLine.getForwardLeft()));
                bpfr1.setText(getPlayerName(firstBPLine.getForwardRight()));
            }

            BoxplayLine secondBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondBPLine != null) {
                bpdl2.setText(getPlayerName(secondBPLine.getDefenderLeft()));
                bpdr2.setText(getPlayerName(secondBPLine.getDefenderRight()));
                bpfl2.setText(getPlayerName(secondBPLine.getForwardLeft()));
                bpfr2.setText(getPlayerName(secondBPLine.getForwardRight()));
            }

            BoxplayLine thirdBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 3)
                    .findAny().orElse(null);
            if (thirdBPLine != null) {
                bpdlfiller.setText(getPlayerName(thirdBPLine.getDefenderLeft()));
                bpdrfiller.setText(getPlayerName(thirdBPLine.getDefenderRight()));
                bpflfiller.setText(getPlayerName(thirdBPLine.getForwardLeft()));
                bpfrfiller.setText(getPlayerName(thirdBPLine.getForwardRight()));
            }

            SubstituteLine substituteLine = dbLineLoader.getSubLine("SELECT * FROM substituteLine WHERE gameID =" + newValue.getGameID());

            if (substituteLine != null) {
                sgk1.setText(getPlayerName(substituteLine.getGoalkeeper1()));
                sgk2.setText(getPlayerName(substituteLine.getGoalkeeper2()));
                sgk3.setText(getPlayerName(substituteLine.getGoalkeeper3()));
                sd1.setText(getPlayerName(substituteLine.getDefender1()));
                sd2.setText(getPlayerName(substituteLine.getDefender2()));
                sd3.setText(getPlayerName(substituteLine.getDefender3()));
                sf1.setText(getPlayerName(substituteLine.getForward1()));
                sf2.setText(getPlayerName(substituteLine.getForward2()));
                sf3.setText(getPlayerName(substituteLine.getForward3()));
                bpsd1.setText(getPlayerName(substituteLine.getBoxplayDefender1()));
                bpsd2.setText(getPlayerName(substituteLine.getBoxplayDefender2()));
                bpsf1.setText(getPlayerName(substituteLine.getBoxplayForward1()));
                bpsf2.setText(getPlayerName(substituteLine.getBoxplayForward2()));
            }

            List <NuclearLine> nuclearLines = dbLineLoader.getNuclearLine("SELECT * FROM nuclearLine WHERE gameID =" + newValue.getGameID());

            NuclearLine firstNLine = nuclearLines.stream()
                    .filter(nuclearLine -> nuclearLine.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstNLine != null) {
                ndl1.setText(getPlayerName(firstNLine.getDefenderLeft()));
                ndr1.setText(getPlayerName(firstNLine.getDefenderRight()));
                nc1.setText(getPlayerName(firstNLine.getCenter()));
                nfl1.setText(getPlayerName(firstNLine.getForwardLeft()));
                nfr1.setText(getPlayerName(firstNLine.getForwardRight()));
            }

            NuclearLine secondNLine = nuclearLines.stream()
                    .filter(nuclearLine -> nuclearLine.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondNLine != null) {
                ndl2.setText(getPlayerName(secondNLine.getDefenderLeft()));
                ndr2.setText(getPlayerName(secondNLine.getDefenderRight()));
                nc2.setText(getPlayerName(secondNLine.getCenter()));
                nfl2.setText(getPlayerName(secondNLine.getForwardLeft()));
                nfr2.setText(getPlayerName(secondNLine.getForwardRight()));
            }
        });


    }

    public String getPlayerName(Player player) {
        if (player != null) {
            if (player.getPlayerID() > 0) {
                return player.getLastName() + " " + player.getFirstName();
            }
        }
        return "";
    }

    @Override
    public void importFields(Pane root) {
        saveButton = (Button) root.lookup("#saveButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        backButton = (Button) root.lookup("#backButton");
        newGameButton = (Button) root.lookup("#newGameButton");
        refreshPlayerList = (Button) root.lookup("#refreshPlayerList");

        allGames = (TableView) root.lookup("#allGames");
        teamPlayers = (TableView) root.lookup("#teamPlayers");

        lineupTabPane = (TabPane) root.lookup("#lineupTabPane");

        lineupAnchorPane = (AnchorPane) root.lookup("#lineupAnchorPane");
        ppAnchorPane = (AnchorPane) root.lookup("#ppAnchorPane");
        bpAnchorPane = (AnchorPane) root.lookup("#bpAnchorPane");
        nAnchorPane = (AnchorPane ) root.lookup("#nAnchorPane");

        boardImage = (ImageView) root.lookup("#boardImage");
        ppBoardImage = (ImageView) root.lookup("#ppBoardImage");
        bpBoardImage = (ImageView) root.lookup("#bpBoardImage");
        nBoardImage = (ImageView ) root.lookup("#nBoardImage");

        lineupGrid = (GridPane) root.lookup("#lineupGrid");
        ppLineupGrid = (GridPane) root.lookup("#ppLineupGrid");
        bpLineupGrid = (GridPane) root.lookup("#bpLineupGrid");
        nLineupGrid = (GridPane ) root.lookup("#nLineupGrid");

        gameTeam = (TextField) root.lookup("#gameTeam");
        gameOpponent = (TextField) root.lookup("#gameOpponent");
        gameDate = (TextField) root.lookup("#gameDate");
        gameTime = (TextField) root.lookup("#gameTime");
        gameStadium = (TextField) root.lookup("#gameStadium");
        captain = (TextField) root.lookup("#captain");
        assistant1 = (TextField) root.lookup("#assistant1");
        assistant2 = (TextField) root.lookup("#assistant2");

        gk1 = (TextField) root.lookup("#gk1");
        dl1 = (TextField) root.lookup("#dl1");
        dl2 = (TextField) root.lookup("#dl2");
        dl3 = (TextField) root.lookup("#dl3");
        dl4 = (TextField) root.lookup("#dl4");
        dr1 = (TextField) root.lookup("#dr1");
        dr2 = (TextField) root.lookup("#dr2");
        dr3 = (TextField) root.lookup("#dr3");
        dr4 = (TextField) root.lookup("#dr4");
        c1 = (TextField) root.lookup("#c1");
        c2 = (TextField) root.lookup("#c2");
        c3 = (TextField) root.lookup("#c3");
        c4 = (TextField) root.lookup("#c4");
        fl1 = (TextField) root.lookup("#fl1");
        fl2 = (TextField) root.lookup("#fl2");
        fl3 = (TextField) root.lookup("#fl3");
        fl4 = (TextField) root.lookup("#fl4");
        fr1 = (TextField) root.lookup("#fr1");
        fr2 = (TextField) root.lookup("#fr2");
        fr3 = (TextField) root.lookup("#fr3");
        fr4 = (TextField) root.lookup("#fr4");
        sgk1 = (TextField) root.lookup("#sgk1");
        sgk2 = (TextField) root.lookup("#sgk2");
        sgk3 = (TextField) root.lookup("#sgk3");
        sd1 = (TextField) root.lookup("#sd1");
        sd2 = (TextField) root.lookup("#sd2");
        sd3 = (TextField) root.lookup("#sd3");
        sf1 = (TextField) root.lookup("#sf1");
        sf2 = (TextField) root.lookup("#sf2");
        sf3 = (TextField) root.lookup("#sf3");

        ppdl1 = (TextField) root.lookup("#ppdl1");
        ppdl2 = (TextField) root.lookup("#ppdl2");
        ppdlfiller = (TextField) root.lookup("#ppdlfiller");
        ppdr1 = (TextField) root.lookup("#ppdr1");
        ppdr2 = (TextField) root.lookup("#ppdr2");
        ppdrfiller = (TextField) root.lookup("#ppdrfiller");
        ppc1 = (TextField) root.lookup("#ppc1");
        ppc2 = (TextField) root.lookup("#ppc2");
        ppcfiller = (TextField) root.lookup("#ppcfiller");
        ppfl1 = (TextField) root.lookup("#ppfl1");
        ppfl2 = (TextField) root.lookup("#ppfl2");
        ppflfiller = (TextField) root.lookup("#ppflfiller");
        ppfr1 = (TextField) root.lookup("#ppfr1");
        ppfr2 = (TextField) root.lookup("#ppfr2");
        ppfrfiller = (TextField) root.lookup("#ppfrfiller");

        bpdl1 = (TextField) root.lookup("#bpdl1");
        bpdl2 = (TextField) root.lookup("#bpdl2");
        bpdlfiller = (TextField) root.lookup("#bpdlfiller");
        bpdr1 = (TextField) root.lookup("#bpdr1");
        bpdr2 = (TextField) root.lookup("#bpdr2");
        bpdrfiller = (TextField) root.lookup("#bpdrfiller");
        bpfl1 = (TextField) root.lookup("#bpfl1");
        bpfl2 = (TextField) root.lookup("#bpfl2");
        bpflfiller = (TextField) root.lookup("#bpflfiller");
        bpfr1 = (TextField) root.lookup("#bpfr1");
        bpfr2 = (TextField) root.lookup("#bpfr2");
        bpfrfiller = (TextField) root.lookup("#bpfrfiller");
        bpsd1 = (TextField) root.lookup("#bpsd1");
        bpsd2 = (TextField) root.lookup("#bpsd2");
        bpsf1 = (TextField) root.lookup("#bpsf1");
        bpsf2 = (TextField) root.lookup("#bpsf2");

        ndl1 = (TextField ) root.lookup("#ndl1");
        ndl2 = (TextField ) root.lookup("#ndl2");
        ndr1 = (TextField ) root.lookup("#ndr1");
        ndr2 = (TextField ) root.lookup("#ndr2");
        nc1 = (TextField ) root.lookup("#nc1");
        nc2 = (TextField ) root.lookup("#nc2");
        nfl1 = (TextField ) root.lookup("#nfl1");
        nfl2 = (TextField ) root.lookup("#nfl2");
        nfr1 = (TextField ) root.lookup("#nfr1");
        nfr2 = (TextField ) root.lookup("#nfr2");

    }
}
