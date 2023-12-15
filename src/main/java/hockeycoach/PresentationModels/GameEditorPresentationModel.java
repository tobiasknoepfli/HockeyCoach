package hockeycoach.PresentationModels;

import hockeycoach.DB.DBConverter.DBIDConverter;
import hockeycoach.DB.DBConverter.DBStringConverter;
import hockeycoach.DB.DBEditor.DBGameEditor;
import hockeycoach.DB.DBEditor.DBLineEditor;
import hockeycoach.DB.DBLoader.DBGameLoader;
import hockeycoach.DB.DBLoader.DBLineLoader;
import hockeycoach.DB.DBLoader.DBPlayerLoader;
import hockeycoach.DB.DBWriter.DBGameWriter;
import hockeycoach.DB.DBWriter.DBLineWriter;
import hockeycoach.DB.DBWriter.DBStadiumWriter;
import hockeycoach.DB.DBWriter.DBWriter;
import hockeycoach.mainClasses.*;
import hockeycoach.mainClasses.Lines.*;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.TextFieldAction;
import hockeycoach.supportClasses.checkups.NullCheck;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalTimeTextField;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

import static hockeycoach.AppStarter.*;
import static hockeycoach.supportClasses.checkups.NullCheck.isNotNullElse;

public class GameEditorPresentationModel extends PresentationModel {
    DBWriter dbWriter = new DBWriter();
    DBStringConverter dbStringConverter = new DBStringConverter();
    DBIDConverter dbidConverter = new DBIDConverter();
    DBGameLoader dbGameLoader = new DBGameLoader();
    DBLineLoader dbLineLoader = new DBLineLoader();
    DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
    DBStadiumWriter dbStadiumWriter = new DBStadiumWriter();
    DBGameWriter dbGameWriter = new DBGameWriter();
    DBLineWriter dbLineWriter = new DBLineWriter();
    DBGameEditor dbGameEditor = new DBGameEditor();
    DBLineEditor dbLineEditor = new DBLineEditor();
    TextFieldAction textFieldAction = new TextFieldAction();
    private Stack<TextFieldAction> textFieldActions = new Stack<>();

    ButtonControls buttonControls = new ButtonControls();

    Team selectedTeam;
    List<Player> playerList;
    Player draggedPlayer;

    List<Player> lineupList = new ArrayList<>();
    List<Player> powerplayList = new ArrayList<>();
    List<Player> boxplayList = new ArrayList<>();
    List<Player> nuclearList = new ArrayList<>();
    List<Player> overtimeList = new ArrayList<>();
    List<Player> shootoutList = new ArrayList<>();

    TabPane lineupTabPane;
    Tab lineupTab, powerplayTab, boxplayTab, nuclearTab, overtimeTab, shootoutTab;
    Tab activeTab;

    Line firstLine = new Line();
    Line secondLine = new Line();
    Line thirdLine = new Line();
    Line fourthLine = new Line();

    PowerplayLine ppFirstLine = new PowerplayLine();
    PowerplayLine ppSecondLine = new PowerplayLine();
    PowerplayLine ppFillerLine = new PowerplayLine();

    BoxplayLine bpFirstLine = new BoxplayLine();
    BoxplayLine bpSecondLine = new BoxplayLine();
    BoxplayLine bpFillerLine = new BoxplayLine();

    NuclearLine nFirstLine = new NuclearLine();
    NuclearLine nSecondLine = new NuclearLine();

    SubstituteLine subsLine = new SubstituteLine();

    OvertimeLine overtimeLine = new OvertimeLine();

    ShootoutLine shootoutLine = new ShootoutLine();

    TableView<Player> teamPlayers, availablePlayers;

    List<TextField> textFields, ppTextFields, bpTextFields, nTextFields, captainTeamList, otTextFields, soTextFields;
    Button refreshButton, saveButton, backButton, availablePlayersButton;
    AnchorPane lineupAnchorPane, ppAnchorPane, bpAnchorPane, nAnchorPane, overtimeAnchorPane, shootoutAnchorPane;
    GridPane lineupGrid, ppLineupGrid, bpLineupGrid, nLineupGrid, overtimeGrid, shootoutGrid;

    DatePicker gameDate;

    LocalTimeTextField gameTime;

    TextField gameStadium, gameTeam, gameOpponent,
            captain, assistant1, assistant2,
            penalty1, penalty2, emptyNet1, emptyNet2,
            gk1,
            dl1, dl2, dl3, dl4, dr1, dr2, dr3, dr4,
            c1, c2, c3, c4,
            fl1, fl2, fl3, fl4, fr1, fr2, fr3, fr4,
            sgk1, sgk2, sgk3, sd1, sd2, sd3, sf1, sf2, sf3,
            ppdl1, ppdl2, ppdlfiller, ppdr1, ppdr2, ppdrfiller,
            ppc1, ppc2, ppcfiller, ppfl1, ppfl2, ppflfiller, ppfr1, ppfr2, ppfrfiller,
            bpdl1, bpdl2, bpdlfiller, bpdr1, bpdr2, bpdrfiller,
            bpfl1, bpfl2, bpflfiller, bpfr1, bpfr2, bpfrfiller,
            bpsd1, bpsd2, bpsf1, bpsf2,
            ndl1, ndl2, ndr1, ndr2, nc1, nc2, nfl1, nfl2, nfr1, nfr2,
            odl1, odl2, odr1, odr2, oc1, oc2, osd1, osf1,
            sop1, sop2, sop3, sop4, sop5;
    Label lbfl1, lbfl2, lbfl3, lbfl4, lbfr1, lbfr2, lbfr3, lbfr4,
            lbc1, lbc2, lbc3, lbc4, lbgk1, lbgk2, lbgk3, lbgk4,
            lbdl1, lbdl2, lbdl3, lbdl4, lbdr1, lbdr2, lbdr3, lbdr4,
            lgGK1, lgGK2, lgGK3, lgGK4,
            lgRD1, lgRD2, lgRD3, lgRD4, lgLD1, lgLD2, lgLD3, lgLD4,
            lgRF1, lgRF2, lgRF3, lgRF4, lgC1, lgC2, lgC3, lgC4, lgLF1, lgLF2, lgLF3, lgLF4,
            ngGK1, ngGK2, ngGK3, ngGK4,
            ngRD1, ngRD2, ngRD3, ngRD4, ngLD1, ngLD2, ngLD3, ngLD4,
            ngRF1, ngRF2, ngRF3, ngRF4, ngC1, ngC2, ngC3, ngC4, ngLF1, ngLF2, ngLF3, ngLF4;

    CheckBox showAllPlayers;

    @Override
    public void initializeControls(Pane root) {
        importFields(root);

        TextField[] fields = {gameStadium, gameTeam, gameOpponent,
                captain, assistant1, assistant2, penalty1, penalty2, emptyNet1, emptyNet2};

        TextField[] tf = {gk1,
                dl1, dl2, dl3, dl4,
                dr1, dr2, dr3, dr4,
                c1, c2, c3, c4,
                fl1, fl2, fl3, fl4,
                fr1, fr2, fr3, fr4,
                sgk1, sgk2,
                sd1, sd2, sd3,
                sf1, sf2, sf3};

        TextField[] captainTeam = {captain, assistant1, assistant2, penalty1, penalty2, emptyNet1, emptyNet2};

        TextField[] pptf = {ppdl1, ppdl2, ppdlfiller,
                ppdr1, ppdr2, ppdrfiller,
                ppc1, ppc2, ppcfiller,
                ppfl1, ppfl2, ppflfiller,
                ppfr1, ppfr2, ppfrfiller};

        TextField[] bptf = {bpdl1, bpdl2, bpdlfiller,
                bpdr1, bpdr2, bpdrfiller,
                bpfl1, bpfl2, bpflfiller,
                bpfr1, bpfr2, bpfrfiller,
                bpsd1, bpsd2, bpsf1, bpsf2};

        TextField[] ntf = {ndl1, ndl2, ndr1, ndr2, nc1, nc2, nfl1, nfl2, nfr1, nfr2};

        TextField[] ottf = {odl1, odl2, odr1, odr2, oc1, oc2, osd1, osf1};

        TextField[] sotf = {sop1, sop2, sop3, sop4, sop5};

        TextField[] allTextFields = Stream.concat(Arrays.stream(fields), (Arrays.stream(captainTeam)))
                .toArray(TextField[]::new);

        Arrays.stream(allTextFields).forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        textFields = new ArrayList<>(Arrays.asList(tf));
        ppTextFields = new ArrayList<>(Arrays.asList(pptf));
        bpTextFields = new ArrayList<>(Arrays.asList(bptf));
        nTextFields = new ArrayList<>(Arrays.asList(ntf));
        captainTeamList = new ArrayList<>(Arrays.asList(captainTeam));
        otTextFields = new ArrayList<>(Arrays.asList(ottf));
        soTextFields = new ArrayList<>(Arrays.asList(sotf));

        textFields.stream().forEach(this::dragEvent);
        ppTextFields.stream().forEach(this::dragEvent);
        bpTextFields.stream().forEach(this::dragEvent);
        nTextFields.stream().forEach(this::dragEvent);
        captainTeamList.stream().forEach(this::dragCopyEvent);
        otTextFields.stream().forEach(this::dragCopyEvent);
        soTextFields.stream().forEach(this::dragCopyEvent);

        dragDetect(teamPlayers);

        doubleClick(textFields);
        doubleClick(ppTextFields);
        doubleClick(bpTextFields);
        doubleClick(nTextFields);
        doubleClick(otTextFields);
        doubleClick(soTextFields);
        doubleClickRemove(captainTeamList);

        selectedTeam = globalTeam;

        playerList = dbPlayerLoader.getTeamPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.ID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getID() + "'", selectedTeam.getID());

        teamPlayers.getItems().clear();
        teamPlayers.getItems().setAll(playerList);

        gameTeam.setText(selectedTeam.getName());

        showGameLines(lastGameLines(), nextGameLines());

        getDBEntries(root);
        setupButtons(root);
        setupEventListeners(root);

    }

    @Override
    public void setupFieldLists(Pane root) {

    }

    @Override
    public void getDBEntries(Pane root) {

    }

    @Override
    public void fillFields(Pane root) {

    }

    @Override
    public void setupButtons(Pane root) {
        availablePlayersButton.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                FXMLLoader newStageLoader = new FXMLLoader(getClass().getResource(AVAILABLE_PLAYERS_FXML));
                Pane availablePlayerPane = newStageLoader.load();

                Scene contentScene = new Scene(availablePlayerPane);
                stage.setScene(contentScene);

                AvailablePlayerPresentationModel availablePlayerPresentationModel = new AvailablePlayerPresentationModel();
                availablePlayerPresentationModel.initializeControls(availablePlayerPane);

                stage.showAndWait();
                teamPlayers.getItems().clear();
                teamPlayers.getItems().addAll(globalAvailablePlayerList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        refreshButton.setOnAction(event -> {
            teamPlayers.getItems().clear();
            if (globalAvailablePlayerList.size() != 0) {
                teamPlayers.getItems().addAll(globalAvailablePlayerList);
            } else {
                teamPlayers.getItems().addAll(playerList);
            }
        });

        backButton.setOnAction(event -> {
            textFieldAction.undoLastAction(textFieldActions);
        });

        saveButton.setOnAction(event -> {
            Game game = saveGame();

            saveLines();
            savePPLines();
            saveBPLines();
            saveSubstitutes();
            saveNuclearLines();
            saveOvertimeLines();
            saveShootoutLines();

            if (globalEditGame) {
                game.setID(globalGame.getID());
                globalGame = game;
                firstLine.setGameID(globalGame.getID());
                secondLine.setGameID(globalGame.getID());
                thirdLine.setGameID(globalGame.getID());
                fourthLine.setGameID(globalGame.getID());

                ppFirstLine.setGameID(globalGame.getID());
                ppSecondLine.setGameID(globalGame.getID());
                ppFillerLine.setGameID(globalGame.getID());

                bpFirstLine.setGameID(globalGame.getID());
                bpSecondLine.setGameID(globalGame.getID());
                bpFillerLine.setGameID(globalGame.getID());

                nFirstLine.setGameID(globalGame.getID());
                nSecondLine.setGameID(globalGame.getID());

                subsLine.setGameID(globalGame.getID());

                overtimeLine.setGameID(globalGame.getID());
                shootoutLine.setGameID(globalGame.getID());

                dbGameEditor.editGame(globalGame);
                dbLineEditor.editLine(firstLine, globalGame);
                dbLineEditor.editLine(secondLine, globalGame);
                dbLineEditor.editLine(thirdLine, globalGame);
                dbLineEditor.editLine(fourthLine, globalGame);
                dbLineEditor.editPPLine(ppFirstLine, globalGame);
                dbLineEditor.editPPLine(ppSecondLine, globalGame);
                dbLineEditor.editPPLine(ppFillerLine, globalGame);
                dbLineEditor.editBPLine(bpFirstLine, globalGame);
                dbLineEditor.editBPLine(bpSecondLine, globalGame);
                dbLineEditor.editBPLine(bpFillerLine, globalGame);
                dbLineEditor.editNLine(nFirstLine, globalGame);
                dbLineEditor.editNLine(nSecondLine, globalGame);
                dbLineEditor.editSubstituteLine(subsLine, globalGame);
                dbLineEditor.editOvertimeLine(overtimeLine, globalGame);
                dbLineEditor.editShootoutLine(shootoutLine, globalGame);

                globalEditGame = false;
                buttonControls.openGameClose(root, GAME_EDITOR);
            } else {
                game.setID(dbGameWriter.writeGame(game));

                firstLine.setGameID(game.getID());
                secondLine.setGameID(game.getID());
                thirdLine.setGameID(game.getID());
                fourthLine.setGameID(game.getID());

                ppFirstLine.setGameID(game.getID());
                ppSecondLine.setGameID(game.getID());
                ppFillerLine.setGameID(game.getID());

                bpFirstLine.setGameID(game.getID());
                bpSecondLine.setGameID(game.getID());
                bpFillerLine.setGameID(game.getID());

                nFirstLine.setGameID(game.getID());
                nSecondLine.setGameID(game.getID());

                subsLine.setGameID(game.getID());

                overtimeLine.setGameID(game.getID());
                shootoutLine.setGameID(game.getID());

                dbLineWriter.writeNewLine(firstLine);
                dbLineWriter.writeNewLine(secondLine);
                dbLineWriter.writeNewLine(thirdLine);
                dbLineWriter.writeNewLine(fourthLine);

                dbLineWriter.writePPLine(ppFirstLine);
                dbLineWriter.writePPLine(ppSecondLine);
                dbLineWriter.writePPLine(ppFillerLine);

                dbLineWriter.writeBPLine(bpFirstLine);
                dbLineWriter.writeBPLine(bpSecondLine);
                dbLineWriter.writeBPLine(bpFillerLine);

                dbLineWriter.writeNuclearLine(nFirstLine);
                dbLineWriter.writeNuclearLine(nSecondLine);

                dbLineWriter.writeSubstituteLine(subsLine);

                dbLineWriter.writeOvertimeLine(overtimeLine);

                dbLineWriter.writeShootoutLine(shootoutLine);
            }
        });
    }

    public void setupEventListeners(Pane root) {
        textFields.stream().forEach(this::addLineupText);

        gameStadium.setOnMousePressed(event -> {
            lastVisitedPM = GameEditorPresentationModel.this;
            lastVisitedFXML = GAME_EDITOR_FXML;
            lastVisitedNodeName = GAME_EDITOR;
            buttonControls.openStadiumHide(root, GAME_EDITOR);
        });
    }

    public void dragCopyEvent(TextField textField) {
        textField.setOnDragOver(event -> {
            if (event.getGestureSource() != textField && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        textField.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString() && !textField.getText().equals(db.getString()) && textField.getText().isEmpty()) {
                textField.setText(db.getString());
                success = true;
            }

            Player selectedPlayer = playerList.stream()
                    .filter(player -> (player.getLastName() + " " + player.getFirstName() + " #" + player.getJersey()).equals(db.getString()))
                    .findFirst()
                    .orElse(null);

            event.setDropCompleted(success);
            event.consume();
        });
    }

    public void dragEvent(TextField textField) {
        textField.setOnDragOver(event -> {
            if (event.getGestureSource() != textField && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        textField.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString() && !textField.getText().equals(db.getString()) && textField.getText().isEmpty()) {
                textField.setText(db.getString());
                success = true;
            }

            Player selectedPlayer = playerList.stream()
                    .filter(player -> (player.getLastName() + " " + player.getFirstName() + " #" + player.getJersey()).equals(db.getString()))
                    .findFirst()
                    .orElse(null);

            event.setDropCompleted(success);

            if (event.isDropCompleted()) {
                teamPlayers.getItems().remove(draggedPlayer);
            }

            event.consume();
        });
    }

    public void dragDetect(TableView<Player> tableView) {
        tableView.setOnDragDetected(event -> {
            Player selectedPlayer = tableView.getSelectionModel().getSelectedItem();

            if (selectedPlayer != null) {
                Dragboard dragboard = tableView.startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                content.putString(selectedPlayer.getLastName() + " " + selectedPlayer.getFirstName() + " #" + selectedPlayer.getJersey());
                dragboard.setContent(content);
                draggedPlayer = selectedPlayer;
            }
            event.consume();
        });
    }

    public void doubleClick(List<TextField> textFields) {
        textFields.stream().forEach(this::dragEvent);

        textFields.stream().forEach(textField -> {
            textField.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    String playerName = textField.getText();
                    Player retrievedPlayer = dbPlayerLoader.getPlayerFromName(playerName);
                    teamPlayers.getItems().add(retrievedPlayer);
                    textField.clear();
                }
            });
        });
    }

    public void doubleClickRemove(List<TextField> textFields) {
        textFields.stream().forEach(this::dragEvent);

        textFields.stream().forEach(textField -> {
            textField.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    textField.clear();
                }
            });
        });
    }

    private void addLineupText(TextField textField) {
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            lbfl1.setText(fl1.getText());
            lbfl2.setText(fl2.getText());
            lbfl3.setText(fl3.getText());
            lbfl4.setText(fl4.getText());
            lbfr1.setText(fr1.getText());
            lbfr2.setText(fr2.getText());
            lbfr3.setText(fr3.getText());
            lbfr4.setText(fr4.getText());
            lbc1.setText(c1.getText());
            lbc2.setText(c2.getText());
            lbc3.setText(c3.getText());
            lbc4.setText(c4.getText());
            lbgk1.setText(gk1.getText());
            lbgk2.setText(sgk1.getText());
            lbgk3.setText(sgk2.getText());
            lbgk4.setText(sgk3.getText());
            lbdl1.setText(dl1.getText());
            lbdl2.setText(dl2.getText());
            lbdl3.setText(dl3.getText());
            lbdl4.setText(dl4.getText());
            lbdr1.setText(dr1.getText());
            lbdr2.setText(dr2.getText());
            lbdr3.setText(dr3.getText());
            lbdr4.setText(dr4.getText());
        });
    }

    private Game saveGame() {
        Game game = new Game();

        game.setGameDate(isNotNullElse(gameDate, g -> g.getValue(), LocalDate.now()));
        game.setGameTime(isNotNullElse(gameTime, g -> g.getLocalTime(), LocalTime.now()));
        game.setOpponent(isNotNullElse(gameOpponent, g -> g.getText(), ""));
        game.setStadium(dbStringConverter.getStadiumFromString(isNotNullElse(gameStadium, g -> g.getText(), "")));
        game.setTeam(dbidConverter.getTeamFromID(isNotNullElse(selectedTeam, g -> g.getID(), 0)));
        game.setCaptain(dbStringConverter.getPlayerFromString(isNotNullElse(captain, g -> g.getText(), "")));
        game.setAssistant1(dbStringConverter.getPlayerFromString(isNotNullElse(assistant1, g -> g.getText(), "")));
        game.setAssistant2(dbStringConverter.getPlayerFromString(isNotNullElse(assistant2, g -> g.getText(), "")));
        game.setPenalty1(dbStringConverter.getPlayerFromString(isNotNullElse(penalty1, g -> g.getText(), "")));
        game.setPenalty2(dbStringConverter.getPlayerFromString(isNotNullElse(penalty2, g -> g.getText(), "")));
        game.setEmptyNet1(dbStringConverter.getPlayerFromString(isNotNullElse(emptyNet1, g -> g.getText(), "")));
        game.setEmptyNet2(dbStringConverter.getPlayerFromString(isNotNullElse(emptyNet2, g -> g.getText(), "")));

        return game;
    }

    private void saveLines() {
        firstLine = new Line(1);
        firstLine.setGoalkeeper(dbStringConverter.getPlayerFromString(gk1.getText()));
        firstLine.setDefenderLeft(dbStringConverter.getPlayerFromString(dl1.getText()));
        firstLine.setDefenderRight(dbStringConverter.getPlayerFromString(dr1.getText()));
        firstLine.setCenter(dbStringConverter.getPlayerFromString(c1.getText()));
        firstLine.setForwardLeft(dbStringConverter.getPlayerFromString(fl1.getText()));
        firstLine.setForwardRight(dbStringConverter.getPlayerFromString(fr1.getText()));

        secondLine = new Line(2);
        secondLine.setDefenderLeft(dbStringConverter.getPlayerFromString(dl2.getText()));
        secondLine.setDefenderRight(dbStringConverter.getPlayerFromString(dr2.getText()));
        secondLine.setCenter(dbStringConverter.getPlayerFromString(c2.getText()));
        secondLine.setForwardLeft(dbStringConverter.getPlayerFromString(fl2.getText()));
        secondLine.setForwardRight(dbStringConverter.getPlayerFromString(fr2.getText()));

        thirdLine = new Line(3);
        thirdLine.setDefenderLeft(dbStringConverter.getPlayerFromString(dl3.getText()));
        thirdLine.setDefenderRight(dbStringConverter.getPlayerFromString(dr3.getText()));
        thirdLine.setCenter(dbStringConverter.getPlayerFromString(c3.getText()));
        thirdLine.setForwardLeft(dbStringConverter.getPlayerFromString(fl3.getText()));
        thirdLine.setForwardRight(dbStringConverter.getPlayerFromString(fr3.getText()));

        fourthLine = new Line(4);
        fourthLine.setDefenderLeft(dbStringConverter.getPlayerFromString(dl4.getText()));
        fourthLine.setDefenderRight(dbStringConverter.getPlayerFromString(dr4.getText()));
        fourthLine.setCenter(dbStringConverter.getPlayerFromString(c4.getText()));
        fourthLine.setForwardLeft(dbStringConverter.getPlayerFromString(fl4.getText()));
        fourthLine.setForwardRight(dbStringConverter.getPlayerFromString(fr4.getText()));
    }


    private void savePPLines() {
        ppFirstLine = new PowerplayLine(1);
        ppFirstLine.setDefenderLeft(dbStringConverter.getPlayerFromString(ppdl1.getText()));
        ppFirstLine.setDefenderRight(dbStringConverter.getPlayerFromString(ppdr1.getText()));
        ppFirstLine.setCenter(dbStringConverter.getPlayerFromString(ppc1.getText()));
        ppFirstLine.setForwardLeft(dbStringConverter.getPlayerFromString(ppfl1.getText()));
        ppFirstLine.setForwardRight(dbStringConverter.getPlayerFromString(ppfr1.getText()));

        ppSecondLine = new PowerplayLine(2);
        ppSecondLine.setDefenderLeft(dbStringConverter.getPlayerFromString(ppdl2.getText()));
        ppSecondLine.setDefenderRight(dbStringConverter.getPlayerFromString(ppdr2.getText()));
        ppSecondLine.setCenter(dbStringConverter.getPlayerFromString(ppc2.getText()));
        ppSecondLine.setForwardLeft(dbStringConverter.getPlayerFromString(ppfl2.getText()));
        ppSecondLine.setForwardRight(dbStringConverter.getPlayerFromString(ppfr2.getText()));

        ppFillerLine = new PowerplayLine(3);
        ppFillerLine.setDefenderLeft(dbStringConverter.getPlayerFromString(ppdlfiller.getText()));
        ppFillerLine.setDefenderRight(dbStringConverter.getPlayerFromString(ppdrfiller.getText()));
        ppFillerLine.setCenter(dbStringConverter.getPlayerFromString(ppcfiller.getText()));
        ppFillerLine.setForwardLeft(dbStringConverter.getPlayerFromString(ppflfiller.getText()));
        ppFillerLine.setForwardRight(dbStringConverter.getPlayerFromString(ppfrfiller.getText()));
    }

    private void saveBPLines() {
        bpFirstLine = new BoxplayLine(1);
        bpFirstLine.setDefenderLeft(dbStringConverter.getPlayerFromString(bpdl1.getText()));
        bpFirstLine.setDefenderRight(dbStringConverter.getPlayerFromString(bpdr1.getText()));
        bpFirstLine.setForwardLeft(dbStringConverter.getPlayerFromString(bpfl1.getText()));
        bpFirstLine.setForwardRight(dbStringConverter.getPlayerFromString(bpfr1.getText()));

        bpSecondLine = new BoxplayLine(2);
        bpSecondLine.setDefenderLeft(dbStringConverter.getPlayerFromString(bpdl2.getText()));
        bpSecondLine.setDefenderRight(dbStringConverter.getPlayerFromString(bpdr2.getText()));
        bpSecondLine.setForwardLeft(dbStringConverter.getPlayerFromString(bpfl2.getText()));
        bpSecondLine.setForwardRight(dbStringConverter.getPlayerFromString(bpfr2.getText()));

        bpFillerLine = new BoxplayLine(3);
        bpFillerLine.setDefenderLeft(dbStringConverter.getPlayerFromString(bpdlfiller.getText()));
        bpFillerLine.setDefenderRight(dbStringConverter.getPlayerFromString(bpdrfiller.getText()));
        bpFillerLine.setForwardLeft(dbStringConverter.getPlayerFromString(bpflfiller.getText()));
        bpFillerLine.setForwardRight(dbStringConverter.getPlayerFromString(bpfrfiller.getText()));
    }

    private void saveSubstitutes() {
        subsLine = new SubstituteLine();
        subsLine.setGoalkeeper1(dbStringConverter.getPlayerFromString(sgk1.getText()));
        subsLine.setGoalkeeper2(dbStringConverter.getPlayerFromString(sgk2.getText()));
        subsLine.setDefender1(dbStringConverter.getPlayerFromString(sd1.getText()));
        subsLine.setDefender2(dbStringConverter.getPlayerFromString(sd2.getText()));
        subsLine.setDefender3(dbStringConverter.getPlayerFromString(sd3.getText()));
        subsLine.setForward1(dbStringConverter.getPlayerFromString(sf1.getText()));
        subsLine.setForward2(dbStringConverter.getPlayerFromString(sf2.getText()));
        subsLine.setForward3(dbStringConverter.getPlayerFromString(sf3.getText()));
        subsLine.setBoxplayDefender1(dbStringConverter.getPlayerFromString(bpsd1.getText()));
        subsLine.setBoxplayDefender2(dbStringConverter.getPlayerFromString(bpsd2.getText()));
        subsLine.setBoxplayForward1(dbStringConverter.getPlayerFromString(bpsf1.getText()));
        subsLine.setBoxplayForward2(dbStringConverter.getPlayerFromString(bpsf2.getText()));
    }

    private void saveNuclearLines() {
        nFirstLine = new NuclearLine(1);
        nFirstLine.setDefenderLeft(dbStringConverter.getPlayerFromString(ndl1.getText()));
        nFirstLine.setDefenderRight(dbStringConverter.getPlayerFromString(ndr1.getText()));
        nFirstLine.setCenter(dbStringConverter.getPlayerFromString(nc1.getText()));
        nFirstLine.setForwardLeft(dbStringConverter.getPlayerFromString(nfl1.getText()));
        nFirstLine.setForwardRight(dbStringConverter.getPlayerFromString(nfr1.getText()));

        nSecondLine = new NuclearLine(2);
        nSecondLine.setDefenderLeft(dbStringConverter.getPlayerFromString(ndl2.getText()));
        nSecondLine.setDefenderRight(dbStringConverter.getPlayerFromString(ndr2.getText()));
        nSecondLine.setCenter(dbStringConverter.getPlayerFromString(nc2.getText()));
        nSecondLine.setForwardLeft(dbStringConverter.getPlayerFromString(nfl2.getText()));
        nSecondLine.setForwardRight(dbStringConverter.getPlayerFromString(nfr2.getText()));
    }

    private void saveOvertimeLines() {
        overtimeLine = new OvertimeLine();
        overtimeLine.setDefenderLeft1(dbStringConverter.getPlayerFromString(odl1.getText()));
        overtimeLine.setDefenderRight1(dbStringConverter.getPlayerFromString(odr1.getText()));
        overtimeLine.setCenter1(dbStringConverter.getPlayerFromString(oc1.getText()));
        overtimeLine.setDefenderLeft2(dbStringConverter.getPlayerFromString(odl2.getText()));
        overtimeLine.setDefenderRight2(dbStringConverter.getPlayerFromString(odr2.getText()));
        overtimeLine.setCenter2(dbStringConverter.getPlayerFromString(oc2.getText()));
        overtimeLine.setSubstituteDefender(dbStringConverter.getPlayerFromString(osd1.getText()));
        overtimeLine.setSubstituteForward(dbStringConverter.getPlayerFromString(osf1.getText()));
    }

    private void saveShootoutLines() {
        shootoutLine = new ShootoutLine();
        shootoutLine.setShooter1(dbStringConverter.getPlayerFromString(sop1.getText()));
        shootoutLine.setShooter2(dbStringConverter.getPlayerFromString(sop2.getText()));
        shootoutLine.setShooter3(dbStringConverter.getPlayerFromString(sop3.getText()));
        shootoutLine.setShooter4(dbStringConverter.getPlayerFromString(sop4.getText()));
        shootoutLine.setShooter5(dbStringConverter.getPlayerFromString(sop5.getText()));
    }

    public void showGameLines(List<Line> pastGameLines, List<Line> nextGameLines) {
        Line firstLineLastGame = pastGameLines.stream()
                .filter(line -> line.getLineNr() == 1)
                .findAny().orElse(null);
        if (firstLineLastGame != null) {
            lgGK1.setText(getPlayerName(firstLineLastGame.getGoalkeeper()));
            lgLD1.setText(getPlayerName(firstLineLastGame.getDefenderLeft()));
            lgRD1.setText(getPlayerName(firstLineLastGame.getDefenderRight()));
            lgLF1.setText(getPlayerName(firstLineLastGame.getForwardLeft()));
            lgC1.setText(getPlayerName(firstLineLastGame.getCenter()));
            lgRF1.setText(getPlayerName(firstLineLastGame.getForwardRight()));
        }

        Line secondLineLastGame = pastGameLines.stream()
                .filter(line -> line.getLineNr() == 2)
                .findAny().orElse(null);
        if (secondLineLastGame != null) {
            lgGK2.setText(getPlayerName(secondLineLastGame.getGoalkeeper()));
            lgRD2.setText(getPlayerName(secondLineLastGame.getDefenderRight()));
            lgLD2.setText(getPlayerName(secondLineLastGame.getDefenderLeft()));
            lgRF2.setText(getPlayerName(secondLineLastGame.getForwardRight()));
            lgC2.setText(getPlayerName(secondLineLastGame.getCenter()));
            lgLF2.setText(getPlayerName(secondLineLastGame.getForwardLeft()));
        }

        Line thirdLineLastGame = pastGameLines.stream()
                .filter(line -> line.getLineNr() == 3)
                .findAny().orElse(null);
        if (thirdLineLastGame != null) {
            lgGK3.setText(getPlayerName(thirdLineLastGame.getGoalkeeper()));
            lgRD3.setText(getPlayerName(thirdLineLastGame.getDefenderRight()));
            lgLD3.setText(getPlayerName(thirdLineLastGame.getDefenderLeft()));
            lgRF3.setText(getPlayerName(thirdLineLastGame.getForwardRight()));
            lgC3.setText(getPlayerName(thirdLineLastGame.getCenter()));
            lgLF3.setText(getPlayerName(thirdLineLastGame.getForwardLeft()));
        }

        Line fourthLineLastGame = pastGameLines.stream()
                .filter(line -> line.getLineNr() == 4)
                .findAny().orElse(null);
        if (fourthLineLastGame != null) {
            lgGK4.setText(getPlayerName(fourthLineLastGame.getGoalkeeper()));
            lgRD4.setText(getPlayerName(fourthLineLastGame.getDefenderRight()));
            lgLD4.setText(getPlayerName(fourthLineLastGame.getDefenderLeft()));
            lgRF4.setText(getPlayerName(fourthLineLastGame.getForwardRight()));
            lgC4.setText(getPlayerName(fourthLineLastGame.getCenter()));
            lgLF4.setText(getPlayerName(fourthLineLastGame.getForwardLeft()));
        }

        Line firstLineNextGame = nextGameLines.stream()
                .filter(line -> line.getLineNr() == 1)
                .findAny().orElse(null);
        if (firstLineNextGame != null) {
            ngGK1.setText(getPlayerName(firstLineNextGame.getGoalkeeper()));
            ngLD1.setText(getPlayerName(firstLineNextGame.getDefenderLeft()));
            ngRD1.setText(getPlayerName(firstLineNextGame.getDefenderRight()));
            ngLF1.setText(getPlayerName(firstLineNextGame.getForwardLeft()));
            ngC1.setText(getPlayerName(firstLineNextGame.getCenter()));
            ngRF1.setText(getPlayerName(firstLineNextGame.getForwardRight()));
        }

        Line secondLineNextGame = nextGameLines.stream()
                .filter(line -> line.getLineNr() == 2)
                .findAny().orElse(null);
        if (secondLineNextGame != null) {
            ngGK2.setText(getPlayerName(secondLineNextGame.getGoalkeeper()));
            ngLD2.setText(getPlayerName(secondLineNextGame.getDefenderLeft()));
            ngRD2.setText(getPlayerName(secondLineNextGame.getDefenderRight()));
            ngLF2.setText(getPlayerName(secondLineNextGame.getForwardLeft()));
            ngC2.setText(getPlayerName(secondLineNextGame.getCenter()));
            ngRF2.setText(getPlayerName(secondLineNextGame.getForwardRight()));
        }

        Line thirdLineNextGame = nextGameLines.stream()
                .filter(line -> line.getLineNr() == 3)
                .findAny().orElse(null);
        if (thirdLineNextGame != null) {
            ngGK3.setText(getPlayerName(thirdLineNextGame.getGoalkeeper()));
            ngLD3.setText(getPlayerName(thirdLineNextGame.getDefenderLeft()));
            ngRD3.setText(getPlayerName(thirdLineNextGame.getDefenderRight()));
            ngLF3.setText(getPlayerName(thirdLineNextGame.getForwardLeft()));
            ngC3.setText(getPlayerName(thirdLineNextGame.getCenter()));
            ngRF3.setText(getPlayerName(thirdLineNextGame.getForwardRight()));
        }

        Line fourthLineNextGame = nextGameLines.stream()
                .filter(line -> line.getLineNr() == 4)
                .findAny().orElse(null);
        if (fourthLineNextGame != null) {
            ngGK4.setText(getPlayerName(fourthLineNextGame.getGoalkeeper()));
            ngLD4.setText(getPlayerName(fourthLineNextGame.getDefenderLeft()));
            ngRD4.setText(getPlayerName(fourthLineNextGame.getDefenderRight()));
            ngLF4.setText(getPlayerName(fourthLineNextGame.getForwardLeft()));
            ngC4.setText(getPlayerName(fourthLineNextGame.getCenter()));
            ngRF4.setText(getPlayerName(fourthLineNextGame.getForwardRight()));
        }
    }

    public String getPlayerName(Player player) {
        if (player != null && player.getID() > 0) {
            return player.getLastName() + " " + player.getFirstName();
        } else {
            return "";
        }
    }

    public List<Line> lastGameLines() {
        LocalDate today = LocalDate.now();
        List<Game> teamGames = dbGameLoader.getGames("SELECT * FROM game WHERE team = " + selectedTeam.getID());
        Game closestPastGame = teamGames.stream()
                .filter(game -> game.getGameDate().isBefore(today))
                .max(Comparator.comparing(Game::getGameDate))
                .orElse(null);
        if (closestPastGame == null) {
            closestPastGame = new Game();
        }
        List<Line> lines = dbLineLoader.getLines("SELECT * FROM line WHERE gameID = " + closestPastGame.getID());

        return lines;
    }

    public List<Line> nextGameLines() {
        LocalDate today = LocalDate.now();
        List<Game> teamGames = dbGameLoader.getGames("SELECT * FROM game WHERE team = " + selectedTeam.getID());
        Game closestNextGame = teamGames.stream()
                .filter(game -> game.getGameDate().isAfter(today))
                .min(Comparator.comparing(Game::getGameDate))
                .orElse(null);
        if (closestNextGame == null) {
            closestNextGame = new Game();
        }
        List<Line> lines = dbLineLoader.getLines("SELECT * FROM line WHERE gameID = " + closestNextGame.getID());

        return lines;
    }

    public void fillStadium(Stadium stadium) {
        gameStadium.setText(stadium.getStadiumName());
    }

    public void editGame() {
        gameTeam.setText(isNotNullElse(globalGame.getTeam(), g -> g.getName(), ""));
        gameOpponent.setText(isNotNullElse(globalGame.getOpponent(), g -> g, ""));
        gameDate.setValue(isNotNullElse(globalGame.getGameDate(), g -> g, LocalDate.now()));
        gameTime.setLocalTime((globalGame.getGameTime().toLocalTime() != null) ? globalGame.getGameTime().toLocalTime() : LocalTime.now());
        gameStadium.setText(isNotNullElse(globalGame.getStadiumName(), g -> g, ""));

        captain.setText(isNotNullElse(globalGame.getCaptain(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        assistant1.setText(isNotNullElse(globalGame.getAssistant1(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        assistant2.setText(isNotNullElse(globalGame.getAssistant2(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        penalty1.setText(isNotNullElse(globalGame.getPenalty1(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        penalty2.setText(isNotNullElse(globalGame.getPenalty2(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        emptyNet1.setText(isNotNullElse(globalGame.getEmptyNet1(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        emptyNet2.setText(isNotNullElse(globalGame.getEmptyNet2(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
    }

    public void editGameLines() {
        fl1.setText(isNotNullElse(globalFirstLine.getForwardLeft(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        fr1.setText(isNotNullElse(globalFirstLine.getForwardRight(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        c1.setText(isNotNullElse(globalFirstLine.getCenter(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        dl1.setText(isNotNullElse(globalFirstLine.getDefenderLeft(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        dr1.setText(isNotNullElse(globalFirstLine.getDefenderRight(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        gk1.setText(isNotNullElse(globalFirstLine.getGoalkeeper(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));

        fl2.setText(isNotNullElse(globalSecondLine.getForwardLeft(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        fr2.setText(isNotNullElse(globalSecondLine.getForwardRight(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        c2.setText(isNotNullElse(globalSecondLine.getCenter(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        dl2.setText(isNotNullElse(globalSecondLine.getDefenderLeft(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        dr2.setText(isNotNullElse(globalSecondLine.getDefenderRight(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));

        fl3.setText(isNotNullElse(globalThirdLine.getForwardLeft(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        fr3.setText(isNotNullElse(globalThirdLine.getForwardRight(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        c3.setText(isNotNullElse(globalThirdLine.getCenter(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        dl3.setText(isNotNullElse(globalThirdLine.getDefenderLeft(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        dr3.setText(isNotNullElse(globalThirdLine.getDefenderRight(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));

        fl4.setText(isNotNullElse(globalFourthLine.getForwardLeft(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        fr4.setText(isNotNullElse(globalFourthLine.getForwardRight(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        c4.setText(isNotNullElse(globalFourthLine.getCenter(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        dl4.setText(isNotNullElse(globalFourthLine.getDefenderLeft(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));
        dr4.setText(isNotNullElse(globalFourthLine.getDefenderRight(), g -> g.getFullNameWithJersey(globalGame.getTeam()), ""));

        ppdl1.setText(isNotNullElse(globalFirstPPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppdr1.setText(isNotNullElse(globalFirstPPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppc1.setText(isNotNullElse(globalFirstPPLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppfl1.setText(isNotNullElse(globalFirstPPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppfr1.setText(isNotNullElse(globalFirstPPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));

        ppdl2.setText(isNotNullElse(globalSecondPPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppdr2.setText(isNotNullElse(globalSecondPPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppc2.setText(isNotNullElse(globalSecondPPLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppfl2.setText(isNotNullElse(globalSecondPPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppfr2.setText(isNotNullElse(globalSecondPPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));

        ppdlfiller.setText(isNotNullElse(globalFillerPPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppdrfiller.setText(isNotNullElse(globalFillerPPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppcfiller.setText(isNotNullElse(globalFillerPPLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppflfiller.setText(isNotNullElse(globalFillerPPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ppfrfiller.setText(isNotNullElse(globalFillerPPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));

        bpdl1.setText(isNotNullElse(globalFirstBPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpdr1.setText(isNotNullElse(globalFirstBPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpfl1.setText(isNotNullElse(globalFirstBPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpfr1.setText(isNotNullElse(globalFirstBPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));

        bpdl2.setText(isNotNullElse(globalSecondBPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpdr2.setText(isNotNullElse(globalSecondBPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpfl2.setText(isNotNullElse(globalSecondBPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpfr2.setText(isNotNullElse(globalSecondBPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));

        bpdlfiller.setText(isNotNullElse(globalFillerBPLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpdrfiller.setText(isNotNullElse(globalFillerBPLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpflfiller.setText(isNotNullElse(globalFillerBPLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpfrfiller.setText(isNotNullElse(globalFillerBPLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));

        sgk1.setText(isNotNullElse(globalSubstituteLine.getGoalkeeper1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sgk2.setText(isNotNullElse(globalSubstituteLine.getGoalkeeper2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sgk3.setText(isNotNullElse(globalSubstituteLine.getGoalkeeper3(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sd1.setText(isNotNullElse(globalSubstituteLine.getDefender1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sd2.setText(isNotNullElse(globalSubstituteLine.getDefender2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sd3.setText(isNotNullElse(globalSubstituteLine.getDefender3(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sf1.setText(isNotNullElse(globalSubstituteLine.getForward1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sf2.setText(isNotNullElse(globalSubstituteLine.getForward2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sf3.setText(isNotNullElse(globalSubstituteLine.getForward3(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpsd1.setText(isNotNullElse(globalSubstituteLine.getBoxplayDefender1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpsd2.setText(isNotNullElse(globalSubstituteLine.getBoxplayDefender2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpsf1.setText(isNotNullElse(globalSubstituteLine.getBoxplayForward1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        bpsf2.setText(isNotNullElse(globalSubstituteLine.getBoxplayForward2(), c -> c.getFullNameWithJersey(selectedTeam), ""));

        ndl1.setText(isNotNullElse(globalFirstNLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ndr1.setText(isNotNullElse(globalFirstNLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        nc1.setText(isNotNullElse(globalFirstNLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        nfl1.setText(isNotNullElse(globalFirstNLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        nfr1.setText(isNotNullElse(globalFirstNLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));

        ndl2.setText(isNotNullElse(globalSecondNLine.getDefenderLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        ndr2.setText(isNotNullElse(globalSecondNLine.getDefenderRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        nc2.setText(isNotNullElse(globalSecondNLine.getCenter(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        nfl2.setText(isNotNullElse(globalSecondNLine.getForwardLeft(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        nfr2.setText(isNotNullElse(globalSecondNLine.getForwardRight(), c -> c.getFullNameWithJersey(selectedTeam), ""));

        odl1.setText(isNotNullElse(globalOvertimeLine.getDefenderLeft1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        odr1.setText(isNotNullElse(globalOvertimeLine.getDefenderRight1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        oc1.setText(isNotNullElse(globalOvertimeLine.getCenter1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        odl2.setText(isNotNullElse(globalOvertimeLine.getDefenderLeft2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        odr2.setText(isNotNullElse(globalOvertimeLine.getDefenderRight2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        oc2.setText(isNotNullElse(globalOvertimeLine.getCenter2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        osd1.setText(isNotNullElse(globalOvertimeLine.getSubstituteDefender(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        osf1.setText(isNotNullElse(globalOvertimeLine.getSubstituteForward(), c -> c.getFullNameWithJersey(selectedTeam), ""));

        sop1.setText(isNotNullElse(globalShootoutLine.getShooter1(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sop2.setText(isNotNullElse(globalShootoutLine.getShooter2(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sop3.setText(isNotNullElse(globalShootoutLine.getShooter3(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sop4.setText(isNotNullElse(globalShootoutLine.getShooter4(), c -> c.getFullNameWithJersey(selectedTeam), ""));
        sop5.setText(isNotNullElse(globalShootoutLine.getShooter5(), c -> c.getFullNameWithJersey(selectedTeam), ""));
    }

    @Override
    public void importFields(Pane root) {
        teamPlayers = (TableView) root.lookup("#teamPlayers");
        availablePlayers = (TableView) root.lookup("#availablePlayers");

        refreshButton = (Button) root.lookup("#refreshButton");
        saveButton = (Button) root.lookup("#saveButton");
        backButton = (Button) root.lookup("#backButton");
        availablePlayersButton = (Button) root.lookup("#availablePlayersButton");

        lineupTabPane = (TabPane) root.lookup("#lineupTabPane");

        lineupAnchorPane = (AnchorPane) root.lookup("#lineupAnchorPane");
        ppAnchorPane = (AnchorPane) root.lookup("#ppAnchorPane");
        bpAnchorPane = (AnchorPane) root.lookup("#bpAnchorPane");
        nAnchorPane = (AnchorPane) root.lookup("#nAnchorPane");
        overtimeAnchorPane = (AnchorPane) root.lookup("#overtimeAnchorPane");
        shootoutAnchorPane = (AnchorPane) root.lookup("#shootoutAnchorPane");

        lineupGrid = (GridPane) root.lookup("#lineupGrid");
        ppLineupGrid = (GridPane) root.lookup("#ppLineupGrid");
        bpLineupGrid = (GridPane) root.lookup("#bpLineupGrid");
        nLineupGrid = (GridPane) root.lookup("#nLineupGrid");
        shootoutGrid = (GridPane) root.lookup("#shootoutGrid");
        overtimeGrid = (GridPane) root.lookup("#overtimeGrid");

        lineupTab = lineupTabPane.getTabs().get(0);
        powerplayTab = lineupTabPane.getTabs().get(1);
        boxplayTab = lineupTabPane.getTabs().get(2);
        nuclearTab = lineupTabPane.getTabs().get(3);
        overtimeTab = lineupTabPane.getTabs().get(4);
        shootoutTab = lineupTabPane.getTabs().get(5);

        gameDate = (DatePicker) root.lookup("#gameDate");
        gameTime = (LocalTimeTextField) root.lookup("#gameTime");

        showAllPlayers = (CheckBox) root.lookup("#showAllPlayers");

        gameStadium = (TextField) root.lookup("#gameStadium");
        gameTeam = (TextField) root.lookup("#gameTeam");
        gameOpponent = (TextField) root.lookup("#gameOpponent");
        captain = (TextField) root.lookup("#captain ");
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

        bpsf1 = (TextField) root.lookup("#bpsf1");
        bpsf2 = (TextField) root.lookup("#bpsf2");
        bpsd1 = (TextField) root.lookup("#bpsd1");
        bpsd2 = (TextField) root.lookup("#bpsd2");

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

        lbfl1 = (Label) root.lookup("#lbfl1");
        lbfl2 = (Label) root.lookup("#lbfl2");
        lbfl3 = (Label) root.lookup("#lbfl3");
        lbfl4 = (Label) root.lookup("#lbfl4");
        lbfr1 = (Label) root.lookup("#lbfr1");
        lbfr2 = (Label) root.lookup("#lbfr2");
        lbfr3 = (Label) root.lookup("#lbfr3");
        lbfr4 = (Label) root.lookup("#lbfr4");
        lbc1 = (Label) root.lookup("#lbc1");
        lbc2 = (Label) root.lookup("#lbc2");
        lbc3 = (Label) root.lookup("#lbc3");
        lbc4 = (Label) root.lookup("#lbc4");
        lbgk1 = (Label) root.lookup("#lbgk1");
        lbgk2 = (Label) root.lookup("#lbgk2");
        lbgk3 = (Label) root.lookup("#lbgk3");
        lbgk4 = (Label) root.lookup("#lbgk4");
        lbdl1 = (Label) root.lookup("#lbdl1");
        lbdl2 = (Label) root.lookup("#lbdl2");
        lbdl3 = (Label) root.lookup("#lbdl3");
        lbdl4 = (Label) root.lookup("#lbdl4");
        lbdr1 = (Label) root.lookup("#lbdr1");
        lbdr2 = (Label) root.lookup("#lbdr2");
        lbdr3 = (Label) root.lookup("#lbdr3");
        lbdr4 = (Label) root.lookup("#lbdr4");

        lgGK1 = (Label) root.lookup("#lgGK1");
        lgGK2 = (Label) root.lookup("#lgGK2");
        lgGK3 = (Label) root.lookup("#lgGK3");
        lgGK4 = (Label) root.lookup("#lgGK4");
        lgRD1 = (Label) root.lookup("#lgRD1");
        lgRD2 = (Label) root.lookup("#lgRD2");
        lgRD3 = (Label) root.lookup("#lgRD3");
        lgRD4 = (Label) root.lookup("#lgRD4");
        lgLD1 = (Label) root.lookup("#lgLD1");
        lgLD2 = (Label) root.lookup("#lgLD2");
        lgLD3 = (Label) root.lookup("#lgLD3");
        lgLD4 = (Label) root.lookup("#lgLD4");
        lgRF1 = (Label) root.lookup("#lgRF1");
        lgRF2 = (Label) root.lookup("#lgRF2");
        lgRF3 = (Label) root.lookup("#lgRF3");
        lgRF4 = (Label) root.lookup("#lgRF4");
        lgC1 = (Label) root.lookup("#lgC1");
        lgC2 = (Label) root.lookup("#lgC2");
        lgC3 = (Label) root.lookup("#lgC3");
        lgC4 = (Label) root.lookup("#lgC4");
        lgLF1 = (Label) root.lookup("#lgLF1");
        lgLF2 = (Label) root.lookup("#lgLF2");
        lgLF3 = (Label) root.lookup("#lgLF3");
        lgLF4 = (Label) root.lookup("#lgLF4");
        ngGK1 = (Label) root.lookup("#ngGK1");
        ngGK2 = (Label) root.lookup("#ngGK2");
        ngGK3 = (Label) root.lookup("#ngGK3");
        ngGK4 = (Label) root.lookup("#ngGK4");
        ngRD1 = (Label) root.lookup("#ngRD1");
        ngRD2 = (Label) root.lookup("#ngRD2");
        ngRD3 = (Label) root.lookup("#ngRD3");
        ngRD4 = (Label) root.lookup("#ngRD4");
        ngLD1 = (Label) root.lookup("#ngLD1");
        ngLD2 = (Label) root.lookup("#ngLD2");
        ngLD3 = (Label) root.lookup("#ngLD3");
        ngLD4 = (Label) root.lookup("#ngLD4");
        ngRF1 = (Label) root.lookup("#ngRF1");
        ngRF2 = (Label) root.lookup("#ngRF2");
        ngRF3 = (Label) root.lookup("#ngRF3");
        ngRF4 = (Label) root.lookup("#ngRF4");
        ngC1 = (Label) root.lookup("#ngC1");
        ngC2 = (Label) root.lookup("#ngC2");
        ngC3 = (Label) root.lookup("#ngC3");
        ngC4 = (Label) root.lookup("#ngC4");
        ngLF1 = (Label) root.lookup("#ngLF1");
        ngLF2 = (Label) root.lookup("#ngLF2");
        ngLF3 = (Label) root.lookup("#ngLF3");
        ngLF4 = (Label) root.lookup("#ngLF4");
    }

    @Override
    public void disableFields(Boolean disabled) {

    }
}