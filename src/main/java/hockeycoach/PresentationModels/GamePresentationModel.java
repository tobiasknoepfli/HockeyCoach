package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBGameLoader;
import hockeycoach.DB.DBLoader.DBLineLoader;
import hockeycoach.mainClasses.*;
import hockeycoach.mainClasses.Lines.*;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.CustomTableColumns;
import hockeycoach.supportClasses.TextFieldAction;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import jfxtras.scene.control.LocalTimeTextField;

import java.time.LocalDate;
import java.util.*;

import static hockeycoach.AppStarter.*;
import static hockeycoach.supportClasses.checkups.NullCheck.isNotNullElse;

public class GamePresentationModel extends PresentationModel {
    ButtonControls buttonControls = new ButtonControls();
    TextFieldAction textFieldAction = new TextFieldAction();
    Stack<TextFieldAction> textFieldActions = new Stack<>();
    CustomTableColumns customTableColumns = new CustomTableColumns();

    DatePicker gameDate;
    LocalTimeTextField gameTime;

    Button saveButton, cancelButton, refreshPlayerList, backButton, newGameButton;
    TextField gameTeam, gameOpponent, gameStadium,
            captain, assistant1, assistant2,
            penalty1, penalty2, emptyNet1, emptyNet2,
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
            ndl1, ndl2, ndr1, ndr2, nc1, nc2, nfl1, nfl2, nfr1, nfr2,
            odl1, odl2, odr1, odr2, oc1, oc2, osd1, osf1,
            sop1, sop2, sop3, sop4, sop5;
    ;

    TableView<Game> allGames;
    TableColumn dateColumn, stadiumColumn, opponentColumn;
    TableView<Player> teamPlayers;
    TabPane lineupTabPane;
    AnchorPane lineupAnchorPane, ppAnchorPane, bpAnchorPane, nAnchorPane;
    GridPane lineupGrid, ppLineupGrid, bpLineupGrid, nLineupGrid;

    DBGameLoader dbGameLoader = new DBGameLoader();
    DBLineLoader dbLineLoader = new DBLineLoader();
    List<Game> allGameList;
    Team selectedTeam;

    @Override
    public void initializeControls(Pane root) {
        importFields(root);

        selectedTeam = globalTeam;

        allGameList = dbGameLoader.getGames("SELECT * FROM game WHERE team =" + selectedTeam.getID());
        customTableColumns.setStadiumNameColumn(stadiumColumn, Game::getStadiumName);

        allGames.getItems().clear();
        allGames.getItems().addAll(allGameList);

        gameTeam.setText(selectedTeam.getName());

        TextField[] textFields = {gameTeam, gameOpponent, gameStadium,
                captain, assistant1, assistant2,
                penalty1, penalty2, emptyNet1, emptyNet2,
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
                ndl1, ndl2, ndr1, ndr2, nc1, nc2, nfl1, nfl2, nfr1, nfr2,
                odl1, odl2, odr1, odr2, oc1, oc2, osd1, osf1,
                sop1, sop2, sop3, sop4, sop5};

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
            gameDate.setValue(LocalDate.from(newValue.getGameDate()));
            gameTime.setLocalTime(newValue.getGameTime().toLocalTime());
            gameStadium.setText(isNotNullElse(newValue.getStadium(), s -> s.getStadiumName(), ""));
            captain.setText(isNotNullElse(newValue.getCaptain(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            assistant1.setText(isNotNullElse(newValue.getAssistant1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            assistant2.setText(isNotNullElse(newValue.getAssistant2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            penalty1.setText(isNotNullElse(newValue.getPenalty1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            penalty2.setText(isNotNullElse(newValue.getPenalty2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            emptyNet1.setText(isNotNullElse(newValue.getEmptyNet1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            emptyNet2.setText(isNotNullElse(newValue.getEmptyNet2(), c -> c.getFullNameWithJersey(selectedTeam), ""));

            List<Line> lines = dbLineLoader.getLines("SELECT * FROM line WHERE gameID = " + newValue.getID());

            Line firstLine = lines.stream()
                    .filter(line -> line.getLineNr() == 1)
                    .findFirst().orElse(null);
            if (firstLine != null) {
                gk1.setText(isNotNullElse(firstLine.getGoalkeeper(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                dl1.setText(isNotNullElse(firstLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                dr1.setText(isNotNullElse(firstLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                c1.setText(isNotNullElse(firstLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                fl1.setText(isNotNullElse(firstLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                fr1.setText(isNotNullElse(firstLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            Line secondLine = lines.stream()
                    .filter(line -> line.getLineNr() == 2)
                    .findFirst().orElse(null);
            if (secondLine != null) {
                dr2.setText(isNotNullElse(secondLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                dl2.setText(isNotNullElse(secondLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                c2.setText(isNotNullElse(secondLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                fr2.setText(isNotNullElse(secondLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                fl2.setText(isNotNullElse(secondLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            Line thirdLine = lines.stream()
                    .filter(line -> line.getLineNr() == 3)
                    .findFirst().orElse(null);
            if (thirdLine != null) {
                dr3.setText(isNotNullElse(thirdLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                dl3.setText(isNotNullElse(thirdLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                c3.setText(isNotNullElse(thirdLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                fr3.setText(isNotNullElse(thirdLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                fl3.setText(isNotNullElse(thirdLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            Line fourthLine = lines.stream()
                    .filter(line -> line.getLineNr() == 4)
                    .findFirst().orElse(null);
            if (fourthLine != null) {
                dr4.setText(isNotNullElse(fourthLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                dl4.setText(isNotNullElse(fourthLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                c4.setText(isNotNullElse(fourthLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                fr4.setText(isNotNullElse(fourthLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                fl4.setText(isNotNullElse(fourthLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            List<PowerplayLine> ppLine = dbLineLoader.getPPLines("SELECT * FROM powerplayLine WHERE gameID = " + newValue.getID());

            PowerplayLine firstPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 1)
                    .findFirst().orElse(null);
            if (firstPPLine != null) {
                ppdl1.setText(isNotNullElse(firstPPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppdr1.setText(isNotNullElse(firstPPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppc1.setText(isNotNullElse(firstPPLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppfl1.setText(isNotNullElse(firstPPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppfr1.setText(isNotNullElse(firstPPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            PowerplayLine secondPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 2)
                    .findFirst().orElse(null);
            if (secondPPLine != null) {
                ppdl2.setText(isNotNullElse(secondPPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppdr2.setText(isNotNullElse(secondPPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppc2.setText(isNotNullElse(secondPPLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppfl2.setText(isNotNullElse(secondPPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppfr2.setText(isNotNullElse(secondPPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            PowerplayLine fillerPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 3)
                    .findFirst().orElse(null);
            if (fillerPPLine != null) {
                ppdlfiller.setText(isNotNullElse(fillerPPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppdrfiller.setText(isNotNullElse(fillerPPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppcfiller.setText(isNotNullElse(fillerPPLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppfrfiller.setText(isNotNullElse(fillerPPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ppfrfiller.setText(isNotNullElse(fillerPPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            List<BoxplayLine> bpLine = dbLineLoader.getBPLines("SELECT * FROM boxplayLine WHERE gameID = " + newValue.getID());

            BoxplayLine firstBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 1)
                    .findFirst().orElse(null);
            if (firstBPLine != null) {
                bpdl1.setText(isNotNullElse(firstBPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpdr1.setText(isNotNullElse(firstBPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpfl1.setText(isNotNullElse(firstBPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpfr1.setText(isNotNullElse(firstBPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            BoxplayLine secondBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 2)
                    .findFirst().orElse(null);
            if (secondBPLine != null) {
                bpdl2.setText(isNotNullElse(secondBPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpdr2.setText(isNotNullElse(secondBPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpfl2.setText(isNotNullElse(secondBPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpfr2.setText(isNotNullElse(secondBPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            BoxplayLine thirdBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 3)
                    .findFirst().orElse(null);
            if (thirdBPLine != null) {
                bpdlfiller.setText(isNotNullElse(thirdBPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpdrfiller.setText(isNotNullElse(thirdBPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpflfiller.setText(isNotNullElse(thirdBPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpfrfiller.setText(isNotNullElse(thirdBPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            SubstituteLine substituteLine = dbLineLoader.getSubLine("SELECT * FROM substituteLine WHERE gameID =" + newValue.getID());

            if (substituteLine != null) {
                sgk1.setText(isNotNullElse(substituteLine.getGoalkeeper1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sgk2.setText(isNotNullElse(substituteLine.getGoalkeeper2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sgk3.setText(isNotNullElse(substituteLine.getGoalkeeper3(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sd1.setText(isNotNullElse(substituteLine.getDefender1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sd2.setText(isNotNullElse(substituteLine.getDefender2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sd3.setText(isNotNullElse(substituteLine.getDefender3(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sf1.setText(isNotNullElse(substituteLine.getForward1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sf2.setText(isNotNullElse(substituteLine.getForward2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sf3.setText(isNotNullElse(substituteLine.getForward3(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpsd1.setText(isNotNullElse(substituteLine.getBoxplayDefender1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpsd2.setText(isNotNullElse(substituteLine.getBoxplayDefender2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpsf1.setText(isNotNullElse(substituteLine.getBoxplayForward1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                bpsf2.setText(isNotNullElse(substituteLine.getBoxplayForward2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            List<NuclearLine> nuclearLines = dbLineLoader.getNuclearLine("SELECT * FROM nuclearLine WHERE gameID =" + newValue.getID());

            NuclearLine firstNLine = nuclearLines.stream()
                    .filter(nuclearLine -> nuclearLine.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstNLine != null) {
                ndl1.setText(isNotNullElse(firstNLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ndr1.setText(isNotNullElse(firstNLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                nc1.setText(isNotNullElse(firstNLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                nfl1.setText(isNotNullElse(firstNLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                nfr1.setText(isNotNullElse(firstNLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            NuclearLine secondNLine = nuclearLines.stream()
                    .filter(nuclearLine -> nuclearLine.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondNLine != null) {
                ndl2.setText(isNotNullElse(secondNLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                ndr2.setText(isNotNullElse(secondNLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                nc2.setText(isNotNullElse(secondNLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                nfl2.setText(isNotNullElse(secondNLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                nfr2.setText(isNotNullElse(secondNLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            OvertimeLine overtimeLine = dbLineLoader.getOvertimeLine("SELECT * FROM overtimeLine WHERE gameID =" + newValue.getID());
            if (overtimeLine != null) {
                odl1.setText(isNotNullElse(overtimeLine.getDefenderLeft1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                odr1.setText(isNotNullElse(overtimeLine.getDefenderLeft2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                oc1.setText(isNotNullElse(overtimeLine.getCenter1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                odl2.setText(isNotNullElse(overtimeLine.getDefenderLeft2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                odr2.setText(isNotNullElse(overtimeLine.getDefenderRight2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                oc2.setText(isNotNullElse(overtimeLine.getCenter2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                osd1.setText(isNotNullElse(overtimeLine.getSubstituteDefender(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                osf1.setText(isNotNullElse(overtimeLine.getSubstituteForward(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }

            ShootoutLine shootoutLine = dbLineLoader.getShootoutLine("SELECT * FROM shootoutLine WHERE gameID =" + newValue.getID());
            if (shootoutLine != null) {
                sop1.setText(isNotNullElse(shootoutLine.getShooter1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sop2.setText(isNotNullElse(shootoutLine.getShooter2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sop3.setText(isNotNullElse(shootoutLine.getShooter3(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sop4.setText(isNotNullElse(shootoutLine.getShooter4(), c -> c.getFullNameWithJersey(selectedTeam), ""));
                sop5.setText(isNotNullElse(shootoutLine.getShooter5(), c -> c.getFullNameWithJersey(selectedTeam), ""));
            }
        });
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
        nAnchorPane = (AnchorPane) root.lookup("#nAnchorPane");

        lineupGrid = (GridPane) root.lookup("#lineupGrid");
        ppLineupGrid = (GridPane) root.lookup("#ppLineupGrid");
        bpLineupGrid = (GridPane) root.lookup("#bpLineupGrid");
        nLineupGrid = (GridPane) root.lookup("#nLineupGrid");

        dateColumn = (TableColumn) allGames.getVisibleLeafColumn(0);
        stadiumColumn = (TableColumn) allGames.getVisibleLeafColumn(1);
        opponentColumn = (TableColumn) allGames.getVisibleLeafColumn(2);

        gameDate = (DatePicker) root.lookup("#gameDate");

        gameTime = (LocalTimeTextField) root.lookup("#gameTime");

        gameTeam = (TextField) root.lookup("#gameTeam");
        gameOpponent = (TextField) root.lookup("#gameOpponent");
        gameStadium = (TextField) root.lookup("#gameStadium");
        captain = (TextField) root.lookup("#captain");
        assistant1 = (TextField) root.lookup("#assistant1");
        assistant2 = (TextField) root.lookup("#assistant2");
        penalty1 = (TextField) root.lookup("#penalty1");
        penalty2 = (TextField) root.lookup("#penalty2");
        emptyNet1 = (TextField) root.lookup("#emptyNet1");
        emptyNet2 = (TextField) root.lookup("#emptyNet2");

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

        ndl1 = (TextField) root.lookup("#ndl1");
        ndl2 = (TextField) root.lookup("#ndl2");
        ndr1 = (TextField) root.lookup("#ndr1");
        ndr2 = (TextField) root.lookup("#ndr2");
        nc1 = (TextField) root.lookup("#nc1");
        nc2 = (TextField) root.lookup("#nc2");
        nfl1 = (TextField) root.lookup("#nfl1");
        nfl2 = (TextField) root.lookup("#nfl2");
        nfr1 = (TextField) root.lookup("#nfr1");
        nfr2 = (TextField) root.lookup("#nfr2");

        odl1 = (TextField) root.lookup("#odl1");
        odl2 = (TextField) root.lookup("#odl2");
        odr1 = (TextField) root.lookup("#odr1");
        odr2 = (TextField) root.lookup("#odr2");
        oc1 = (TextField) root.lookup("#oc1");
        oc2 = (TextField) root.lookup("#oc2");
        osd1 = (TextField) root.lookup("#osd1");
        osf1 = (TextField) root.lookup("#osf1");

        sop1 = (TextField) root.lookup("#sop1");
        sop2 = (TextField) root.lookup("#sop2");
        sop3 = (TextField) root.lookup("#sop3");
        sop4 = (TextField) root.lookup("#sop4");
        sop5 = (TextField) root.lookup("#sop5");
    }
}
