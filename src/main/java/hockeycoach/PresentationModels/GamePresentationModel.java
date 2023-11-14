package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBGameLoader;
import hockeycoach.DB.DBLoader.DBLineLoader;
import hockeycoach.mainClasses.*;
import hockeycoach.mainClasses.Lines.*;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.TextFieldAction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import jfxtras.scene.control.LocalTimeTextField;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

import static hockeycoach.AppStarter.*;
import static java.util.Objects.requireNonNullElse;

public class GamePresentationModel extends PresentationModel {
    ButtonControls buttonControls = new ButtonControls();
    TextFieldAction textFieldAction = new TextFieldAction();
    Stack<TextFieldAction> textFieldActions = new Stack<>();

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
            ndl1, ndl2, ndr1, ndr2, nc1, nc2, nfl1, nfl2, nfr1, nfr2;

    TableView<Game> allGames;
    TableColumn dateColumn, stadiumColumn, opponentColumn;
    TableView<Player> teamPlayers;
    TabPane lineupTabPane;
    AnchorPane lineupAnchorPane, ppAnchorPane, bpAnchorPane, nAnchorPane;
    ImageView boardImage, ppBoardImage, bpBoardImage, nBoardImage;
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
        stadiumColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Game, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Game, String> param) {
                return new SimpleStringProperty(param.getValue().getStadiumName());
            }
        });
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
            gameDate.setValue(LocalDate.from(newValue.getGameDate()));
            gameTime.setLocalTime(newValue.getGameTime().toLocalTime());
            gameStadium.setText(newValue.getStadium().getStadiumName());
            captain.setText(isNotNull(newValue.getCaptain()));
            assistant1.setText(isNotNull(newValue.getAssistant1()));
            assistant2.setText(isNotNull(newValue.getAssistant2()));
            penalty1.setText(isNotNull(newValue.getPenalty1()));
            penalty2.setText(isNotNull(newValue.getPenalty2()));
            emptyNet1.setText(isNotNull(newValue.getEmptyNet1()));
            emptyNet2.setText(isNotNull(newValue.getEmptyNet2()));

            List<Line> lines = dbLineLoader.getLines("SELECT * FROM line WHERE gameID = " + newValue.getID());

            Line firstLine = lines.stream()
                    .filter(line -> line.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstLine != null) {
                gk1.setText(isNotNull(firstLine.getGoalkeeper()));
                dl1.setText(isNotNull(firstLine.getDefenderLeft()));
                dr1.setText(isNotNull(firstLine.getDefenderRight()));
                c1.setText(isNotNull(firstLine.getCenter()));
                fl1.setText(isNotNull(firstLine.getForwardLeft()));
                fr1.setText(isNotNull(firstLine.getForwardRight()));
                ;
            }

            Line secondLine = lines.stream()
                    .filter(line -> line.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondLine != null) {
                dr2.setText(isNotNull(secondLine.getDefenderRight()));
                dl2.setText(isNotNull(secondLine.getDefenderLeft()));
                c2.setText(isNotNull(secondLine.getCenter()));
                fr2.setText(isNotNull(secondLine.getForwardRight()));
                fl2.setText(isNotNull(secondLine.getForwardLeft()));
            }

            Line thirdLine = lines.stream()
                    .filter(line -> line.getLineNr() == 3)
                    .findAny().orElse(null);
            if (thirdLine != null) {
                dr3.setText(isNotNull(thirdLine.getDefenderRight()));
                dl3.setText(isNotNull(thirdLine.getDefenderLeft()));
                c3.setText(isNotNull(thirdLine.getCenter()));
                fr3.setText(isNotNull(thirdLine.getForwardRight()));
                fl3.setText(isNotNull(thirdLine.getForwardLeft()));
            }

            Line fourthLine = lines.stream()
                    .filter(line -> line.getLineNr() == 4)
                    .findAny().orElse(null);
            if (fourthLine != null) {
                dr4.setText(isNotNull(fourthLine.getDefenderRight()));
                dl4.setText(isNotNull(fourthLine.getDefenderLeft()));
                c4.setText(isNotNull(fourthLine.getCenter()));
                fr4.setText(isNotNull(fourthLine.getForwardRight()));
                fl4.setText(isNotNull(fourthLine.getForwardLeft()));
            }

            List<PowerplayLine> ppLine = dbLineLoader.getPPLines("SELECT * FROM powerplayLine WHERE gameID = " + newValue.getID());

            PowerplayLine firstPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstPPLine != null) {
                ppdl1.setText(isNotNull(firstPPLine.getDefenderLeft()));
                ppdr1.setText(isNotNull(firstPPLine.getDefenderRight()));
                ppc1.setText(isNotNull(firstPPLine.getCenter()));
                ppfl1.setText(isNotNull(firstPPLine.getForwardLeft()));
                ppfr1.setText(isNotNull(firstPPLine.getForwardRight()));
            }

            PowerplayLine secondPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondPPLine != null) {
                ppdl2.setText(isNotNull(secondPPLine.getDefenderLeft()));
                ppdr2.setText(isNotNull(secondPPLine.getDefenderRight()));
                ppc2.setText(isNotNull(secondPPLine.getCenter()));
                ppfl2.setText(isNotNull(secondPPLine.getForwardLeft()));
                ppfr2.setText(isNotNull(secondPPLine.getForwardRight()));
            }

            PowerplayLine fillerPPLine = ppLine.stream()
                    .filter(powerplayLine -> powerplayLine.getLineNr() == 3)
                    .findAny().orElse(null);
            if (fillerPPLine != null) {
                ppdlfiller.setText(isNotNull(fillerPPLine.getDefenderLeft()));
                ppdrfiller.setText(isNotNull(fillerPPLine.getDefenderRight()));
                ppcfiller.setText(isNotNull(fillerPPLine.getCenter()));
                ppfrfiller.setText(isNotNull(fillerPPLine.getForwardLeft()));
                ppfrfiller.setText(isNotNull(fillerPPLine.getForwardRight()));
            }

            List<BoxplayLine> bpLine = dbLineLoader.getBPLines("SELECT * FROM boxplayLine WHERE gameID = " + newValue.getID());

            BoxplayLine firstBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstBPLine != null) {
                bpdl1.setText(isNotNull(firstBPLine.getDefenderLeft()));
                bpdr1.setText(isNotNull(firstBPLine.getDefenderRight()));
                bpfl1.setText(isNotNull(firstBPLine.getForwardLeft()));
                bpfr1.setText(isNotNull(firstBPLine.getForwardRight()));
            }

            BoxplayLine secondBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondBPLine != null) {
                bpdl2.setText(isNotNull(secondBPLine.getDefenderLeft()));
                bpdr2.setText(isNotNull(secondBPLine.getDefenderRight()));
                bpfl2.setText(isNotNull(secondBPLine.getForwardLeft()));
                bpfr2.setText(isNotNull(secondBPLine.getForwardRight()));
            }

            BoxplayLine thirdBPLine = bpLine.stream()
                    .filter(boxplayLine -> boxplayLine.getLineNr() == 3)
                    .findAny().orElse(null);
            if (thirdBPLine != null) {
                bpdlfiller.setText(isNotNull(thirdBPLine.getDefenderLeft()));
                bpdrfiller.setText(isNotNull(thirdBPLine.getDefenderRight()));
                bpflfiller.setText(isNotNull(thirdBPLine.getForwardLeft()));
                bpfrfiller.setText(isNotNull(thirdBPLine.getForwardRight()));
            }

            SubstituteLine substituteLine = dbLineLoader.getSubLine("SELECT * FROM substituteLine WHERE gameID =" + newValue.getID());

            if (substituteLine != null) {
                sgk1.setText(isNotNull(substituteLine.getGoalkeeper1()));
                sgk2.setText(isNotNull(substituteLine.getGoalkeeper2()));
                sgk3.setText(isNotNull(substituteLine.getGoalkeeper3()));
                sd1.setText(isNotNull(substituteLine.getDefender1()));
                sd2.setText(isNotNull(substituteLine.getDefender2()));
                sd3.setText(isNotNull(substituteLine.getDefender3()));
                sf1.setText(isNotNull(substituteLine.getForward1()));
                sf2.setText(isNotNull(substituteLine.getForward2()));
                sf3.setText(isNotNull(substituteLine.getForward3()));
                bpsd1.setText(isNotNull(substituteLine.getBoxplayDefender1()));
                bpsd2.setText(isNotNull(substituteLine.getBoxplayDefender2()));
                bpsf1.setText(isNotNull(substituteLine.getBoxplayForward1()));
                bpsf2.setText(isNotNull(substituteLine.getBoxplayForward2()));
            }

            List<NuclearLine> nuclearLines = dbLineLoader.getNuclearLine("SELECT * FROM nuclearLine WHERE gameID =" + newValue.getID());

            NuclearLine firstNLine = nuclearLines.stream()
                    .filter(nuclearLine -> nuclearLine.getLineNr() == 1)
                    .findAny().orElse(null);
            if (firstNLine != null) {
                ndl1.setText(requireNonNullElse(firstNLine.getDefenderLeft().getFullName(), ""));
                ndr1.setText(requireNonNullElse(firstNLine.getDefenderRight().getFullName(), ""));
                nc1.setText(requireNonNullElse(firstNLine.getCenter().getFullName(), ""));
                nfl1.setText(requireNonNullElse(firstNLine.getForwardLeft().getFullName(), ""));
                nfr1.setText(requireNonNullElse(firstNLine.getForwardRight().getFullName(), ""));
            }

            NuclearLine secondNLine = nuclearLines.stream()
                    .filter(nuclearLine -> nuclearLine.getLineNr() == 2)
                    .findAny().orElse(null);
            if (secondNLine != null) {
                ndl2.setText(requireNonNullElse(secondNLine.getDefenderLeft().getFullName(), ""));
                ndr2.setText(requireNonNullElse(secondNLine.getDefenderRight().getFullName(), ""));
                nc2.setText(requireNonNullElse(secondNLine.getCenter().getFullName(), ""));
                nfl2.setText(requireNonNullElse(secondNLine.getForwardLeft().getFullName(), ""));
                nfr2.setText(requireNonNullElse(secondNLine.getForwardRight().getFullName(), ""));
            }
        });
    }

    public static String isNotNull(Player player) {
        return Optional.ofNullable(player)
                .map(value -> {
                    return player.getFullName();
                })
                .orElse("");
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

        boardImage = (ImageView) root.lookup("#boardImage");
        ppBoardImage = (ImageView) root.lookup("#ppBoardImage");
        bpBoardImage = (ImageView) root.lookup("#bpBoardImage");
        nBoardImage = (ImageView) root.lookup("#nBoardImage");

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

    }
}
