package hockeycoach.UI;

import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameEditorPresentationModel {
    Team selectedTeam;
    List<Player> playerList;
    Player draggedPlayer;

    List<Player> lineupList = new ArrayList<>();
    List<Player> powerplayList = new ArrayList<>();
    List<Player> boxplayList = new ArrayList<>();

    TabPane lineupTabPane;
    Tab lineupTab, powerplayTab, boxplayTab;
    Tab activeTab;

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
    List<Label> labels;
    Button refreshPlayerList;

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

    Label lbgk1;
    Label lbdr1, lbdr2, lbdr3, lbdr4;
    Label lbdl1, lbdl2, lbdl3, lbdl4;
    Label lbc1, lbc2, lbc3, lbc4;
    Label lbfl1, lbfl2, lbfl3, lbfl4;
    Label lbfr1, lbfr2, lbfr3, lbfr4;
    Label lbgks1, lbgks2, lbsd1, lbsd2, lbsd3, lbsf1, lbsf2, lbsf3;

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

        lbgk1 = (Label) root.lookup("#lbgk1");
        lbdr1 = (Label) root.lookup("#lbdr1");
        lbdr2 = (Label) root.lookup("#lbdr2");
        lbdr3 = (Label) root.lookup("#lbdr3");
        lbdr4 = (Label) root.lookup("#lbdr4");
        lbdl1 = (Label) root.lookup("#lbdl1");
        lbdl2 = (Label) root.lookup("#lbdl2");
        lbdl3 = (Label) root.lookup("#lbdl3");
        lbdl4 = (Label) root.lookup("#lbdl4");
        lbc1 = (Label) root.lookup("#lbc1");
        lbc2 = (Label) root.lookup("#lbc2");
        lbc3 = (Label) root.lookup("#lbc3");
        lbc4 = (Label) root.lookup("#lbc4");
        lbfl1 = (Label) root.lookup("#lbfl1");
        lbfl2 = (Label) root.lookup("#lbfl2");
        lbfl3 = (Label) root.lookup("#lbfl3");
        lbfl4 = (Label) root.lookup("#lbfl4");
        lbfr1 = (Label) root.lookup("#lbfr1");
        lbfr2 = (Label) root.lookup("#lbfr2");
        lbfr3 = (Label) root.lookup("#lbfr3");
        lbfr4 = (Label) root.lookup("#lbfr4");
        lbgks1 = (Label) root.lookup("#lbgks1");
        lbgks2 = (Label) root.lookup("#lbgks2");
        lbsd1 = (Label) root.lookup("#lbsd1");
        lbsd2 = (Label) root.lookup("#lbsd2");
        lbsd3 = (Label) root.lookup("#lbsd3");
        lbsf1 = (Label) root.lookup("#lbsf1");
        lbsf2 = (Label) root.lookup("#lbsf2");
        lbsf3 = (Label) root.lookup("#lbsf3");

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

        Label[] lb = {lbgk1, lbdr1, lbdr2, lbdr3, lbdr4,
                lbdl1, lbdl2, lbdl3, lbdl4,
                lbc1, lbc2, lbc3, lbc4,
                lbfl1, lbfl2, lbfl3, lbfl4,
                lbfr1, lbfr2, lbfr3, lbfr4,
                lbgks1, lbgks2, lbsd1, lbsd2, lbsd3, lbsf1, lbsf2, lbsf3};

        textFields = new ArrayList<>(Arrays.asList(tf));
        ppTextFields = new ArrayList<>(Arrays.asList(pptf));
        bpTextFields = new ArrayList<>(Arrays.asList(bptf));

        labels = new ArrayList<>(Arrays.asList(lb));

        textFields.stream().forEach(this::dragEvent);
        ppTextFields.stream().forEach(this::dragEvent);
        bpTextFields.stream().forEach(this::dragEvent);

        dragDetect(teamPlayers);

        doubleClick(textFields);
        doubleClick(ppTextFields);
        doubleClick(bpTextFields);

        lineupBindings(textFields, labels);

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
        lineupTabPane.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue)->{
            refreshPlayers();
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
                    if (!playerName.isEmpty()) {
                        String[] nameParts = playerName.split(" ");
                        if (nameParts.length >= 2) {
                            List<Player> retrievedPlayers = playerList.stream()
                                    .filter(player -> player.getFirstName().equals(nameParts[1]) &&
                                            player.getLastName().equals(nameParts[0]))
                                    .collect(Collectors.toList());
                            teamPlayers.getItems().addAll(retrievedPlayers);
                            textField.clear();

                            activeTab = lineupTabPane.getSelectionModel().getSelectedItem();

                            if (lineupTab == activeTab) {
                                lineupList.removeAll(retrievedPlayers);
                            } else if (powerplayTab == activeTab) {
                                powerplayList.removeAll(retrievedPlayers);
                            } else if (boxplayTab == activeTab) {
                                boxplayList.removeAll(retrievedPlayers);
                            }
                        }
                    }
                }
            });
        });
    }

    public void lineupBindings(List<TextField> textFields, List<Label> labels) {
        if (textFields.size() != labels.size()) {
            throw new IllegalArgumentException("List sizes don't match");
        }

        for (int i = 0; i < textFields.size(); i++) {
            TextField textField = textFields.get(i);
            Label label = labels.get(i);

            StringProperty textFieldTextProperty = textField.textProperty();
            StringProperty labelTextProperty = label.textProperty();

            labelTextProperty.bind(textFieldTextProperty);
        }
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
}