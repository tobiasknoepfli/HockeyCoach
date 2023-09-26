package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader;
import hockeycoach.DB.DBWriter;
import hockeycoach.mainClasses.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class GameEditorPresentationModel {
    DBWriter dbWriter = new DBWriter();

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

    TextField gameDate;
    TextField gameTime;
    TextField gameStadium;
    TextField gameTeam;
    TextField gameOpponent;
    TableView<Player> teamPlayers;
    TableView<Player> availablePlayers;
    ImageView boardImage;
    ImageView ppBoardImage;
    ImageView bpBoardImage;
    List<TextField> textFields, ppTextFields, bpTextFields;
    Button refreshPlayerList;
    Button saveButton;

    TextField gk1;
    TextField dl1, dl2, dl3, dl4;
    TextField dr1, dr2, dr3, dr4;
    TextField c1, c2, c3, c4;
    TextField fl1, fl2, fl3, fl4;
    TextField fr1, fr2, fr3, fr4;
    TextField sgk1, sgk2;
    TextField sd1, sd2, sd3;
    TextField sf1, sf2, sf3;
    TextField ppdl1, ppdl2, ppdlfiller, ppdr1, ppdr2, ppdrfiller;
    TextField ppc1, ppc2, ppcfiller, ppfl1, ppfl2, ppflfiller, ppfr1, ppfr2, ppfrfiller;
    TextField bpdl1, bpdl2, bpdlfiller, bpdr1, bpdr2, bpdrfiller;
    TextField bpfl1, bpfl2, bpflfiller, bpfr1, bpfr2, bpfrfiller;
    TextField bpsd1, bpsd2, bpsf1, bpsf2;

    Label lineOutput;

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

        lineOutput = (Label) root.lookup("#lineOutput");

        File file = new File("src/main/java/hockeycoach/files/background/board.jpg");
        Image image = new Image(file.toURI().toString());
        boardImage.setImage(image);
        ppBoardImage.setImage(image);
        bpBoardImage.setImage(image);

        TextField[] tf = {gk1,
                dl1, dl2, dl3, dl4,
                dr1, dr2, dr3, dr4,
                c1, c2, c3, c4,
                fl1, fl2, fl3, fl4,
                fr1, fr2, fr3, fr4,
                sgk1, sgk2,
                sd1, sd2, sd3,
                sf1, sf2, sf3};

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

        textFields.stream().forEach(this::dragEvent);
        ppTextFields.stream().forEach(this::dragEvent);
        bpTextFields.stream().forEach(this::dragEvent);

        dragDetect(teamPlayers);

        doubleClick(textFields);
        doubleClick(ppTextFields);
        doubleClick(bpTextFields);

        selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        playerList = dbLoader.getPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.playerID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());

        teamPlayers.getItems().clear();
        teamPlayers.getItems().setAll(playerList);

        gameTeam.setText(selectedTeam.getName());

        refreshPlayerList.setOnAction(event -> {
            refreshPlayers();
        });

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

            dbWriter.writeSubstituteLine(game,subsLine);
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
            firstLine = new Line(1);
            firstLine.setGoalkeeper(getPlayerFromTextField(gk1.getText()));
            firstLine.setDefenderLeft(getPlayerFromTextField(dl1.getText()));
            firstLine.setDefenderRight(getPlayerFromTextField(dr1.getText()));
            firstLine.setCenter(getPlayerFromTextField(c1.getText()));
            firstLine.setForwardLeft(getPlayerFromTextField(fl1.getText()));
            firstLine.setForwardRight(getPlayerFromTextField(fr1.getText()));

            secondLine = new Line(2);
            secondLine.setGoalkeeper(getPlayerFromTextField(gk1.getText()));
            secondLine.setDefenderLeft(getPlayerFromTextField(dl2.getText()));
            secondLine.setDefenderRight(getPlayerFromTextField(dr2.getText()));
            secondLine.setCenter(getPlayerFromTextField(c2.getText()));
            secondLine.setForwardLeft(getPlayerFromTextField(fl2.getText()));
            secondLine.setForwardRight(getPlayerFromTextField(fr2.getText()));

            thirdLine = new Line(3);
            thirdLine.setGoalkeeper(getPlayerFromTextField(gk1.getText()));
            thirdLine.setDefenderLeft(getPlayerFromTextField(dl3.getText()));
            thirdLine.setDefenderRight(getPlayerFromTextField(dr3.getText()));
            thirdLine.setCenter(getPlayerFromTextField(c3.getText()));
            thirdLine.setForwardLeft(getPlayerFromTextField(fl3.getText()));
            thirdLine.setForwardRight(getPlayerFromTextField(fr3.getText()));

            fourthLine = new Line(4);
            fourthLine.setGoalkeeper(getPlayerFromTextField(gk1.getText()));
            fourthLine.setDefenderLeft(getPlayerFromTextField(dl3.getText()));
            fourthLine.setDefenderRight(getPlayerFromTextField(dr3.getText()));
            fourthLine.setCenter(getPlayerFromTextField(c3.getText()));
            fourthLine.setForwardLeft(getPlayerFromTextField(fl3.getText()));
            fourthLine.setForwardRight(getPlayerFromTextField(fr3.getText()));

            lineOutput.setText(firstLine.lineupToString() + "\n\n\n\n" +
                    secondLine.lineupToString() + "\n\n\n\n" +
                    thirdLine.lineupToString() + "\n\n\n\n" +
                    fourthLine.lineupToString());
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


}