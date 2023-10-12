package hockeycoach.PresentationModels;

import hockeycoach.DB.*;
import hockeycoach.DB.DBLoader.DBGameLoader;
import hockeycoach.DB.DBLoader.DBLineLoader;
import hockeycoach.DB.DBLoader.DBLoader;
import hockeycoach.DB.DBLoader.DBPlayerLoader;
import hockeycoach.mainClasses.*;
import hockeycoach.supportClasses.SingletonTeam;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static hockeycoach.AppStarter.BOARD;

public class GameEditorPresentationModel extends PresentationModel {
    DBWriter dbWriter = new DBWriter();

    DBLoader dbLoader = new DBLoader();
    DBGameLoader dbGameLoader = new DBGameLoader();
    DBLineLoader dbLineLoader = new DBLineLoader();

    Team selectedTeam;
    List<Player> playerList;
    Player draggedPlayer;

    List<Player> lineupList = new ArrayList<>();
    List<Player> powerplayList = new ArrayList<>();
    List<Player> boxplayList = new ArrayList<>();

    TabPane lineupTabPane;
    Tab lineupTab, powerplayTab, boxplayTab;
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

    SubstituteLine subsLine = new SubstituteLine();

    TextField gameDate,gameTime,gameStadium,gameTeam,gameOpponent,
            captain, assistant1, assistant2;
    TableView<Player> teamPlayers,availablePlayers;

    ImageView boardImage,ppBoardImage,bpBoardImage;

    List<TextField> textFields, ppTextFields, bpTextFields, captainTeamList;
    Button refreshPlayerList,saveButton;
    AnchorPane lineupAnchorPane, ppAnchorPane, bpAnchorPane;
    GridPane lineupGrid, ppLineupGrid, bpLineupGrid;

    TextField gk1,
            dl1, dl2, dl3, dl4, dr1, dr2, dr3, dr4,
            c1, c2, c3, c4,
            fl1, fl2, fl3, fl4, fr1, fr2, fr3, fr4,
            sgk1, sgk2, sgk3,sd1, sd2, sd3,sf1, sf2, sf3,
            ppdl1, ppdl2, ppdlfiller, ppdr1, ppdr2, ppdrfiller,
            ppc1, ppc2, ppcfiller, ppfl1, ppfl2, ppflfiller, ppfr1, ppfr2, ppfrfiller,
            bpdl1, bpdl2, bpdlfiller, bpdr1, bpdr2, bpdrfiller,
            bpfl1, bpfl2, bpflfiller, bpfr1, bpfr2, bpfrfiller,
            bpsd1, bpsd2, bpsf1, bpsf2;
    Label lbfl1, lbfl2, lbfl3, lbfl4, lbfr1, lbfr2, lbfr3, lbfr4,
            lbc1, lbc2, lbc3, lbc4, lbgk1, lbgk2, lbgk3, lbgk4,
            lbdl1, lbdl2, lbdl3, lbdl4, lbdr1, lbdr2, lbdr3, lbdr4,
            lgGK1, lgGK2, lgGK3, lgGK4,
            lgRD1, lgRD2, lgRD3, lgRD4, lgLD1, lgLD2, lgLD3, lgLD4,
            lgRF1, lgRF2, lgRF3, lgRF4, lgC1, lgC2, lgC3, lgC4, lgLF1, lgLF2, lgLF3, lgLF4,
            ngGK1, ngGK2, ngGK3, ngGK4,
            ngRD1, ngRD2, ngRD3, ngRD4, ngLD1, ngLD2, ngLD3, ngLD4,
            ngRF1, ngRF2, ngRF3, ngRF4, ngC1, ngC2, ngC3, ngC4, ngLF1, ngLF2, ngLF3, ngLF4;

    public void initializeControls(Pane root) {
        gameDate = (TextField) root.lookup("#gameDate");
        gameTime = (TextField) root.lookup("#gameTime");
        gameStadium = (TextField) root.lookup("#gameStadium");
        gameTeam = (TextField) root.lookup("#gameTeam");
        gameOpponent = (TextField) root.lookup("#gameOpponent");
        teamPlayers = (TableView) root.lookup("#teamPlayers");
        availablePlayers = (TableView) root.lookup("#availablePlayers");
        boardImage = (ImageView) root.lookup("#boardImage");
        ppBoardImage = (ImageView) root.lookup("#ppBoardImage");
        bpBoardImage = (ImageView) root.lookup("#bpBoardImage");
        refreshPlayerList = (Button) root.lookup("#refreshPlayerList");
        saveButton = (Button) root.lookup("#saveButton");
        lineupTabPane = (TabPane) root.lookup("#lineupTabPane");
        captain = (TextField) root.lookup("#captain ");
        assistant1 = (TextField) root.lookup("#assistant1");
        assistant2 = (TextField) root.lookup("#assistant2");
        lineupAnchorPane = (AnchorPane) root.lookup("#lineupAnchorPane");
        ppAnchorPane = (AnchorPane) root.lookup("#ppAnchorPane");
        bpAnchorPane = (AnchorPane) root.lookup("#bpAnchorPane");
        lineupGrid = (GridPane) root.lookup("#lineupGrid");
        ppLineupGrid = (GridPane) root.lookup("#ppLineupGrid");
        bpLineupGrid = (GridPane) root.lookup("#bpLineupGrid");

        lineupTab = lineupTabPane.getTabs().get(0);
        powerplayTab = lineupTabPane.getTabs().get(1);
        boxplayTab = lineupTabPane.getTabs().get(2);

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


        File file = new File(BOARD);
        Image image = new Image(file.toURI().toString());
        boardImage.setImage(image);
        boardImage.fitWidthProperty().bind(lineupAnchorPane.widthProperty());
        boardImage.fitWidthProperty().bind(lineupGrid.widthProperty());
        boardImage.setPreserveRatio(false);

        ppBoardImage.setImage(image);
        ppBoardImage.fitWidthProperty().bind(ppAnchorPane.widthProperty());
        ppBoardImage.fitWidthProperty().bind(ppLineupGrid.widthProperty());
        ppBoardImage.setPreserveRatio(false);

        bpBoardImage.setImage(image);
        bpBoardImage.fitWidthProperty().bind(bpAnchorPane.widthProperty());
        bpBoardImage.fitWidthProperty().bind(bpLineupGrid.widthProperty());
        bpBoardImage.setPreserveRatio(false);

        TextField[] tf = {gk1,
                dl1, dl2, dl3, dl4,
                dr1, dr2, dr3, dr4,
                c1, c2, c3, c4,
                fl1, fl2, fl3, fl4,
                fr1, fr2, fr3, fr4,
                sgk1, sgk2,
                sd1, sd2, sd3,
                sf1, sf2, sf3};

        TextField[] captainTeam = {captain, assistant1, assistant2};

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

        textFields = new ArrayList<>(Arrays.asList(tf));
        ppTextFields = new ArrayList<>(Arrays.asList(pptf));
        bpTextFields = new ArrayList<>(Arrays.asList(bptf));
        captainTeamList = new ArrayList<>(Arrays.asList(captainTeam));

        textFields.stream().forEach(this::dragEvent);
        ppTextFields.stream().forEach(this::dragEvent);
        bpTextFields.stream().forEach(this::dragEvent);
        captainTeamList.stream().forEach(this::dragCopyEvent);

        dragDetect(teamPlayers);

        doubleClick(textFields);
        doubleClick(ppTextFields);
        doubleClick(bpTextFields);
        doubleClickRemove(captainTeamList);

        selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        DBPlayerLoader dbPlayerLoader  = new DBPlayerLoader();
        playerList = dbPlayerLoader.getTeamPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.playerID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());

        teamPlayers.getItems().clear();
        teamPlayers.getItems().setAll(playerList);

        gameTeam.setText(selectedTeam.getName());

        refreshPlayerList.setOnAction(event -> {
            refreshPlayers();
        });

        showGameLines(lastGameLines(), nextGameLines());

        setupEventListeners();

    }

    public void setupEventListeners() {
        lineupTabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            refreshPlayers();
        });

        textFields.stream().forEach(this::addLineupText);

        saveButton.setOnAction(event -> {
            Game game = saveGameToDB();

            savePPLines();
            saveBPLines();
            saveSubstitutes();

            firstLine.setGameID(game.getGameID());
            secondLine.setGameID(game.getGameID());
            thirdLine.setGameID(game.getGameID());
            fourthLine.setGameID(game.getGameID());

            ppFirstLine.setGameID(game.getGameID());
            ppSecondLine.setGameID(game.getGameID());
            ppFillerLine.setGameID(game.getGameID());

            bpFirstLine.setGameID(game.getGameID());
            bpSecondLine.setGameID(game.getGameID());
            bpFillerLine.setGameID(game.getGameID());

            subsLine.setGameID(game.getGameID());

            dbWriter.writeLine(game, firstLine);
            dbWriter.writeLine(game, secondLine);
            dbWriter.writeLine(game, thirdLine);
            dbWriter.writeLine(game, fourthLine);

            dbWriter.writePPLine(game, ppFirstLine);
            dbWriter.writePPLine(game, ppSecondLine);
            dbWriter.writePPLine(game, ppFillerLine);

            dbWriter.writeBPLine(game, bpFirstLine);
            dbWriter.writeBPLine(game, bpSecondLine);
            dbWriter.writeBPLine(game, bpFillerLine);

            dbWriter.writeSubstituteLine(game, subsLine);
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
                    .filter(player -> (player.getLastName() + " " + player.getFirstName()).equals(db.getString()))
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
                    .filter(player -> (player.getLastName() + " " + player.getFirstName()).equals(db.getString()))
                    .findFirst()
                    .orElse(null);

            activeTab = lineupTabPane.getSelectionModel().getSelectedItem();
            if (lineupTab == activeTab) {
                lineupList.add(selectedPlayer);
            } else if (powerplayTab == activeTab) {
                powerplayList.add(selectedPlayer);
            } else if (boxplayTab == activeTab) {
                boxplayList.add(selectedPlayer);
            }

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
                content.putString(selectedPlayer.getLastName() + " " + selectedPlayer.getFirstName());
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
                    Player retrievedPlayer = getPlayerFromTextField(playerName);
                    teamPlayers.getItems().add(retrievedPlayer);
                    textField.clear();

                    activeTab = lineupTabPane.getSelectionModel().getSelectedItem();

                    if (lineupTab == activeTab) {
                        lineupList.remove(retrievedPlayer);
                    } else if (powerplayTab == activeTab) {
                        powerplayList.remove(retrievedPlayer);
                    } else if (boxplayTab == activeTab) {
                        boxplayList.remove(retrievedPlayer);
                    }
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

    public void refreshPlayers() {
        teamPlayers.getItems().clear();

        List<Player> inactivePlayers = new ArrayList<>(playerList);

        activeTab = lineupTabPane.getSelectionModel().getSelectedItem();

        if (lineupTab == activeTab) {
            inactivePlayers.removeAll(lineupList);
        } else if (powerplayTab == activeTab) {
            inactivePlayers.removeAll(powerplayList);
        } else if (boxplayTab == activeTab) {
            inactivePlayers.removeAll(boxplayList);
        }

        teamPlayers.getItems().addAll(inactivePlayers);
    }

    public Player getPlayerFromTextField(String playerName) {
        if (!playerName.isEmpty()) {
            String[] nameParts = playerName.split(" ");
            if (nameParts.length >= 2) {
                List<Player> retrievedPlayers = playerList.stream()
                        .filter(player -> player.getFirstName().equals(nameParts[1]) &&
                                player.getLastName().equals(nameParts[0]))
                        .collect(Collectors.toList());
                return retrievedPlayers.get(0);
            }
        }
        return new Player("", "", selectedTeam.getName());
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

    private Game saveGameToDB() {
        Game game = new Game();

        String s = gameDate.getText();
        String t = gameTime.getText();
        int day = Integer.parseInt(s.substring(0, 2));
        int month = Integer.parseInt(s.substring(3, 5));
        int year = Integer.parseInt(s.substring(6, 10));
        int hours = Integer.parseInt(t.substring(0, 2));
        int minutes = Integer.parseInt(t.substring(3, 5));
        LocalDate date = LocalDate.of(year, month, day);
        LocalTime time = LocalTime.of(hours, minutes);

        game.setGameDate(date);
        game.setGameTime(time);
        game.setOpponent(gameOpponent.getText());
        game.setStadium(gameStadium.getText());
        game.setTeam(selectedTeam.getTeamID());
        game.setCaptain(getPlayerFromTextField(captain.getText()));
        game.setAssistant1(getPlayerFromTextField(assistant1.getText()));
        game.setAssistant2(getPlayerFromTextField(assistant2.getText()));

        game.setGameID(dbWriter.writeGame(game));
        return game;
    }

    private void savePPLines() {
        ppFirstLine = new PowerplayLine(1);
        ppFirstLine.setDefenderLeft(getPlayerFromTextField(ppdl1.getText()));
        ppFirstLine.setDefenderRight(getPlayerFromTextField(ppdr1.getText()));
        ppFirstLine.setCenter(getPlayerFromTextField(ppc1.getText()));
        ppFirstLine.setForwardLeft(getPlayerFromTextField(ppfl1.getText()));
        ppFirstLine.setForwardRight(getPlayerFromTextField(ppfr1.getText()));

        ppSecondLine = new PowerplayLine(2);
        ppSecondLine.setDefenderLeft(getPlayerFromTextField(ppdl2.getText()));
        ppSecondLine.setDefenderRight(getPlayerFromTextField(ppdr2.getText()));
        ppSecondLine.setCenter(getPlayerFromTextField(ppc2.getText()));
        ppSecondLine.setForwardLeft(getPlayerFromTextField(ppfl2.getText()));
        ppSecondLine.setForwardRight(getPlayerFromTextField(ppfr2.getText()));

        ppFillerLine = new PowerplayLine(3);
        ppFillerLine.setDefenderLeft(getPlayerFromTextField(ppdlfiller.getText()));
        ppFillerLine.setDefenderRight(getPlayerFromTextField(ppfrfiller.getText()));
        ppFillerLine.setCenter(getPlayerFromTextField(ppcfiller.getText()));
        ppFillerLine.setForwardLeft(getPlayerFromTextField(ppflfiller.getText()));
        ppFillerLine.setForwardRight(getPlayerFromTextField(ppfrfiller.getText()));
    }

    private void saveBPLines() {
        bpFirstLine = new BoxplayLine(1);
        bpFirstLine.setDefenderLeft(getPlayerFromTextField(bpdl1.getText()));
        bpFirstLine.setDefenderRight(getPlayerFromTextField(bpdr1.getText()));
        bpFirstLine.setForwardLeft(getPlayerFromTextField(bpfl1.getText()));
        bpFirstLine.setForwardRight(getPlayerFromTextField(bpfr1.getText()));

        bpSecondLine = new BoxplayLine(2);
        bpSecondLine.setDefenderLeft(getPlayerFromTextField(bpdl2.getText()));
        bpSecondLine.setDefenderRight(getPlayerFromTextField(bpdr2.getText()));
        bpSecondLine.setForwardLeft(getPlayerFromTextField(bpfl2.getText()));
        bpSecondLine.setForwardRight(getPlayerFromTextField(bpfr2.getText()));

        bpFillerLine = new BoxplayLine(3);
        bpFillerLine.setDefenderLeft(getPlayerFromTextField(bpdlfiller.getText()));
        bpFillerLine.setDefenderRight(getPlayerFromTextField(bpfrfiller.getText()));
        bpFillerLine.setForwardLeft(getPlayerFromTextField(bpflfiller.getText()));
        bpFillerLine.setForwardRight(getPlayerFromTextField(bpfrfiller.getText()));
    }

    private void saveSubstitutes() {
        subsLine = new SubstituteLine();
        subsLine.setGoalkeeper1(getPlayerFromTextField(sgk1.getText()));
        subsLine.setGoalkeeper2(getPlayerFromTextField(sgk2.getText()));
        subsLine.setDefender1(getPlayerFromTextField(sd1.getText()));
        subsLine.setDefender2(getPlayerFromTextField(sd2.getText()));
        subsLine.setDefender3(getPlayerFromTextField(sd3.getText()));
        subsLine.setForward1(getPlayerFromTextField(sf1.getText()));
        subsLine.setForward2(getPlayerFromTextField(sf2.getText()));
        subsLine.setForward3(getPlayerFromTextField(sf3.getText()));
        subsLine.setBoxplayDefender1(getPlayerFromTextField(bpsd1.getText()));
        subsLine.setBoxplayDefender2(getPlayerFromTextField(bpsd2.getText()));
        subsLine.setBoxplayForward1(getPlayerFromTextField(bpsf1.getText()));
        subsLine.setBoxplayForward2(getPlayerFromTextField(bpsf2.getText()));
    }

    public void showGameLines(List<Line> pastGameLines, List<Line> nextGameLines) {
        Line firstLineLastGame = pastGameLines.stream()
                .filter(line -> line.getLineNr() == 1)
                .findAny().orElse(null);
        if(firstLineLastGame!=null) {
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
        if(secondLineLastGame !=null) {
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
        if(thirdLineLastGame!=null) {
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
        if(fourthLineLastGame!=null) {
            lgGK4.setText(getPlayerName(fourthLineLastGame.getGoalkeeper()));
            lgRD4.setText(getPlayerName(fourthLineLastGame.getDefenderRight()));
            lgLD4.setText(getPlayerName(fourthLineLastGame.getDefenderLeft()));
            lgRF4.setText(getPlayerName(fourthLineLastGame.getForwardRight()));
            lgC4.setText(getPlayerName(fourthLineLastGame.getCenter()));
            lgLF4.setText(getPlayerName(fourthLineLastGame.getForwardLeft()));
        }

        Line firstLineNextGame = nextGameLines.stream()
                .filter(line -> line.getLineNr()==1)
                .findAny().orElse(null);
        if(firstLineNextGame!=null) {
            ngGK1.setText(getPlayerName(firstLineNextGame.getGoalkeeper()));
            ngLD1.setText(getPlayerName(firstLineNextGame.getDefenderLeft()));
            ngRD1.setText(getPlayerName(firstLineNextGame.getDefenderRight()));
            ngLF1.setText(getPlayerName(firstLineNextGame.getForwardLeft()));
            ngC1.setText(getPlayerName(firstLineNextGame.getCenter()));
            ngRF1.setText(getPlayerName(firstLineNextGame.getForwardRight()));
        }

        Line secondLineNextGame = nextGameLines.stream()
                .filter(line -> line.getLineNr()==2)
                .findAny().orElse(null);
        if(secondLineNextGame!=null) {
            ngGK2.setText(getPlayerName(secondLineNextGame.getGoalkeeper()));
            ngLD2.setText(getPlayerName(secondLineNextGame.getDefenderLeft()));
            ngRD2.setText(getPlayerName(secondLineNextGame.getDefenderRight()));
            ngLF2.setText(getPlayerName(secondLineNextGame.getForwardLeft()));
            ngC2.setText(getPlayerName(secondLineNextGame.getCenter()));
            ngRF2.setText(getPlayerName(secondLineNextGame.getForwardRight()));
        }

        Line thirdLineNextGame = nextGameLines.stream()
                .filter(line -> line.getLineNr()==3)
                .findAny().orElse(null);
        if(thirdLineNextGame!=null) {
            ngGK3.setText(getPlayerName(thirdLineNextGame.getGoalkeeper()));
            ngLD3.setText(getPlayerName(thirdLineNextGame.getDefenderLeft()));
            ngRD3.setText(getPlayerName(thirdLineNextGame.getDefenderRight()));
            ngLF3.setText(getPlayerName(thirdLineNextGame.getForwardLeft()));
            ngC3.setText(getPlayerName(thirdLineNextGame.getCenter()));
            ngRF3.setText(getPlayerName(thirdLineNextGame.getForwardRight()));
        }

        Line fourthLineNextGame = nextGameLines.stream()
                .filter(line -> line.getLineNr()==4)
                .findAny().orElse(null);
        if(fourthLineNextGame!=null) {
            ngGK4.setText(getPlayerName(fourthLineNextGame.getGoalkeeper()));
            ngLD4.setText(getPlayerName(fourthLineNextGame.getDefenderLeft()));
            ngRD4.setText(getPlayerName(fourthLineNextGame.getDefenderRight()));
            ngLF4.setText(getPlayerName(fourthLineNextGame.getForwardLeft()));
            ngC4.setText(getPlayerName(fourthLineNextGame.getCenter()));
            ngRF4.setText(getPlayerName(fourthLineNextGame.getForwardRight()));
        }
    }

    public String getPlayerName(Player player) {
        if (player.getPlayerID() >0) {
            return player.getLastName() + " " + player.getFirstName();
        } else {
            return "";
        }
    }

    public List<Line> lastGameLines() {
        LocalDate today = LocalDate.now();
        List<Game> teamGames = dbGameLoader.getGames("SELECT * FROM game WHERE team = " + selectedTeam.getTeamID());
        Game closestPastGame = teamGames.stream()
                .filter(game -> game.getGameDate().isBefore(today))
                .max(Comparator.comparing(Game::getGameDate))
                .orElse(null);
        if(closestPastGame==null){closestPastGame=new Game();}
        List<Line> lines = dbLineLoader.getLines("SELECT * FROM line WHERE gameID = " + closestPastGame.getGameID());

        return lines;
    }

    public List<Line> nextGameLines() {
        LocalDate today = LocalDate.now();
        List<Game> teamGames = dbGameLoader.getGames("SELECT * FROM game WHERE team = " + selectedTeam.getTeamID());
        Game closestNextGame = teamGames.stream()
                .filter(game -> game.getGameDate().isAfter(today))
                .min(Comparator.comparing(Game::getGameDate))
                .orElse(null);
        if(closestNextGame==null){closestNextGame=new Game();}
        List<Line> lines = dbLineLoader.getLines("SELECT * FROM line WHERE gameID = " + closestNextGame.getGameID());

        return lines;
    }
}