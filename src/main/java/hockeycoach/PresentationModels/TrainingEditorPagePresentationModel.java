package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader;
import hockeycoach.mainClasses.Drill;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class TrainingEditorPagePresentationModel extends PresentationModel {
    List<Drill> drills;
    FilteredList<Drill> filteredDrills;
    List<Player> availablePlayersList;
    List<Player> allPlayers;
    Player draggedPlayer;


    Team selectedTeam;
    DBLoader dbLoader;
    ImageView drillImage;
    TextField drillName;
    TextField drillCategory;
    TextField drillDifficulty;
    TextField drillParticipation;
    CheckBox drillStation;
    TextArea drillDescription;
    TableView<String> drillTags;
    ComboBox<String> cbCategory;
    ComboBox<Object> cbDifficulty;
    ComboBox<String> cbParticipation;
    ComboBox<Object> cbStation;
    ComboBox<String> cbTags;
    TextField searchBox;
    Button searchButton;
    Button resetFilters;
    TableView<Drill> drillTable;
    TableView<Drill> warmup;
    TableView<Drill> together;
    TableView<Drill> stations;
    TableView<Drill> backup;
    TableView<Player> playerList;
    TextField trainingDate;
    TextField trainingTime;
    TextField trainingStadium;
    TextField trainingTeam;
    TextField trainingMainFocus;
    TextField puckPosition;
    TextArea trainingPointers;
    Button warmupButton;
    Button togetherButton;
    Button stationsButton;
    Button backupButton;
    TabPane tablePane;
    Tab warmupTab;
    Tab togetherTab;
    Tab stationsTab;
    Tab backupTab;
    TextField jersey1, jersey2, jersey3, jersey4, jersey5, jersey6;
    TextField gk1, gk2, gk3, gk4, gk5, gk6;
    TextField dl1, dl2, dl3, dl4, dl5, dl6;
    TextField dr1, dr2, dr3, dr4, dr5, dr6;
    TextField c1, c2, c3, c4, c5, c6;
    TextField fl1, fl2, fl3, fl4, fl5, fl6;
    TextField fr1, fr2, fr3, fr4, fr5, fr6;
    private ArrayList<TextField> jerseysArrayList;
    private ArrayList<TextField> playersArrayList;

    public void initializeControls(Pane root) {
        drillImage = (ImageView) root.lookup("#drillImage");
        drillName = (TextField) root.lookup("#drillName");
        drillCategory = (TextField) root.lookup("#drillCategory");
        drillDifficulty = (TextField) root.lookup("#drillDifficulty");
        drillParticipation = (TextField) root.lookup("#drillParticipation");
        drillStation = (CheckBox) root.lookup("#drillStation");
        drillDescription = (TextArea) root.lookup("#drillDescription");
        drillTags = (TableView) root.lookup("#drillTags");
        cbCategory = (ComboBox) root.lookup("#cbCategory");
        cbDifficulty = (ComboBox) root.lookup("#cbDifficulty");
        cbParticipation = (ComboBox) root.lookup("#cbParticipation");
        cbStation = (ComboBox) root.lookup("#cbStation");
        cbTags = (ComboBox) root.lookup("#cbTags");
        searchBox = (TextField) root.lookup("#searchBox");
        searchButton = (Button) root.lookup("#searchButton");
        drillTable = (TableView) root.lookup("#drillTable");
        warmup = (TableView) root.lookup("#warmup");
        together = (TableView) root.lookup("#together");
        stations = (TableView) root.lookup("#stations");
        backup = (TableView) root.lookup("#backup");
        playerList = (TableView) root.lookup("#playerList");
        trainingDate = (TextField) root.lookup("#trainingDate");
        trainingTime = (TextField) root.lookup("#trainingTime");
        trainingStadium = (TextField) root.lookup("#trainingStadium");
        trainingTeam = (TextField) root.lookup("#trainingTeam");
        trainingMainFocus = (TextField) root.lookup("#trainingMainFocus");
        trainingPointers = (TextArea) root.lookup("#trainingPointers");
        resetFilters = (Button) root.lookup("#resetFilters");
        warmupButton = (Button) root.lookup("#warmupButton");
        togetherButton = (Button) root.lookup("#togetherButton");
        stationsButton = (Button) root.lookup("#stationsButton");
        backupButton = (Button) root.lookup("#backupButton");
        tablePane = (TabPane) root.lookup("#tablePane");
        puckPosition = (TextField) root.lookup("#puckPosition");
        warmupTab = tablePane.getTabs().get(0);
        togetherTab = tablePane.getTabs().get(1);
        stationsTab = tablePane.getTabs().get(2);
        backupTab = tablePane.getTabs().get(3);

        jersey1 = (TextField) root.lookup("#jersey1");
        jersey2 = (TextField) root.lookup("#jersey2");
        jersey3 = (TextField) root.lookup("#jersey3");
        jersey4 = (TextField) root.lookup("#jersey4");
        jersey5 = (TextField) root.lookup("#jersey5");
        jersey6 = (TextField) root.lookup("#jersey6");
        gk1 = (TextField) root.lookup("#gk1");
        gk2 = (TextField) root.lookup("#gk2");
        gk3 = (TextField) root.lookup("#gk3");
        gk4 = (TextField) root.lookup("#gk4");
        gk5 = (TextField) root.lookup("#gk5");
        gk6 = (TextField) root.lookup("#gk6");
        dl1 = (TextField) root.lookup("#dl1");
        dl2 = (TextField) root.lookup("#dl2");
        dl3 = (TextField) root.lookup("#dl3");
        dl4 = (TextField) root.lookup("#dl4");
        dl5 = (TextField) root.lookup("#dl5");
        dl6 = (TextField) root.lookup("#dl6");
        dr1 = (TextField) root.lookup("#dr1");
        dr2 = (TextField) root.lookup("#dr2");
        dr3 = (TextField) root.lookup("#dr3");
        dr4 = (TextField) root.lookup("#dr4");
        dr5 = (TextField) root.lookup("#dr5");
        dr6 = (TextField) root.lookup("#dr6");
        c1 = (TextField) root.lookup("#c1");
        c2 = (TextField) root.lookup("#c2");
        c3 = (TextField) root.lookup("#c3");
        c4 = (TextField) root.lookup("#c4");
        c5 = (TextField) root.lookup("#c5");
        c6 = (TextField) root.lookup("#c6");
        fl1 = (TextField) root.lookup("#fl1");
        fl2 = (TextField) root.lookup("#fl2");
        fl3 = (TextField) root.lookup("#fl3");
        fl4 = (TextField) root.lookup("#fl4");
        fl5 = (TextField) root.lookup("#fl5");
        fl6 = (TextField) root.lookup("#fl6");
        fr1 = (TextField) root.lookup("#fr1");
        fr2 = (TextField) root.lookup("#fr2");
        fr3 = (TextField) root.lookup("#fr3");
        fr4 = (TextField) root.lookup("#fr4");
        fr5 = (TextField) root.lookup("#fr5");
        fr6 = (TextField) root.lookup("#fr6");

        drillTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        warmup.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        stations.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        together.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        backup.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        dbLoader = new DBLoader();
        drills = dbLoader.getDrills("SELECT * FROM drill");

        allPlayers = dbLoader.getPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.playerID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());

        TextField[] jerseys = {jersey1, jersey2, jersey3, jersey4, jersey5, jersey6};
        TextField[] players = {gk1, gk2, gk3, gk4, gk5, gk6,
                dl1, dl2, dl3, dl4, dl5, dl6,
                dr1, dr2, dr3, dr4, dr5, dr6,
                c1, c2, c3, c4, c5, c6,
                fl1, fl2, fl3, fl4, fl5, fl6,
                fr1, fr2, fr3, fr4, fr5, fr6,};

        jerseysArrayList = new ArrayList<>(Arrays.asList(jerseys));
        playersArrayList = new ArrayList<>(Arrays.asList(players));

        jerseysArrayList.stream().forEach(this::dragEvent);
        playersArrayList.stream().forEach(this::dragEvent);

        dragDetect(playerList);

        doubleClick(playersArrayList);

        trainingTeam.setText(selectedTeam.getName());

        drillTable.getItems().clear();
        drillTable.getItems().addAll(drills);

        filteredDrills = new FilteredList<>(FXCollections.observableList(drills), d -> true);
        drillTable.setItems(filteredDrills);
        cbCategory.setOnAction(event -> filterDrillTable());
        cbTags.setOnAction(event -> filterDrillTable());
        cbStation.setOnAction(event -> filterDrillTable());
        cbParticipation.setOnAction(event -> filterDrillTable());
        cbDifficulty.setOnAction(event -> filterDrillTable());
        searchButton.setOnAction(event -> searchDrillTable());
        resetFilters.setOnAction(event -> resetAllFilters());

        removeDrillSetup(warmup);
        removeDrillSetup(together);
        removeDrillSetup(stations);
        removeDrillSetup(backup);

        moveDrill(warmup);
        moveDrill(together);
        moveDrill(stations);
        moveDrill(backup);

        setupEventListeners();

    }

    public void setupEventListeners() {
        eventListenersFromTable(drillTable);
        eventListenersFromTable(warmup);
        eventListenersFromTable(together);
        eventListenersFromTable(stations);
        eventListenersFromTable(backup);

        ObservableList<String> categories = FXCollections.observableArrayList();
        drills.forEach(drill -> {
            String category = drill.getCategory();
            categories.add(category);
        });
        ObservableList<String> categoryList = categories.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        categoryList.add(0, "Category");
        cbCategory.setItems(categoryList);
        cbCategory.getSelectionModel().select(0);

        ObservableList<Object> difficulties = FXCollections.observableArrayList();
        drills.forEach(drill -> {
            int difficulty = drill.getDifficulty();
            difficulties.add(difficulty);
        });
        ObservableList<Object> difficultiesList = difficulties.stream()
                .distinct()
                .map(item -> (Integer) item)
                .sorted(Integer::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        difficultiesList.add(0, "Difficulty");
        cbDifficulty.setItems(difficultiesList);
        cbDifficulty.getSelectionModel().select(0);

        ObservableList<String> participations = FXCollections.observableArrayList();
        drills.forEach(drill -> {
            String participation = drill.getParticipation();
            participations.add(participation);
        });
        ObservableList<String> participationList = participations.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        participationList.add(0, "Participation");
        cbParticipation.setItems(participationList);
        cbParticipation.getSelectionModel().select(0);

        ObservableList<Object> stations = FXCollections.observableArrayList();
        drills.forEach(drill -> {
            Boolean station = drill.getStation();
            stations.add(station);
        });
        ObservableList<Object> stationsList = stations.stream()
                .distinct()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        stationsList.add(0, "Stations");
        cbStation.setItems(stationsList);
        cbStation.getSelectionModel().select(0);

        ObservableList<String> tags = FXCollections.observableArrayList();
        drills.forEach(drill -> {
            ArrayList<String> tag = drill.getTags();
            tags.addAll(tag);
        });
        ObservableList<String> tagList = tags.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        tagList.add(0, "Tags");
        cbTags.setItems((ObservableList<String>) tagList);
        cbTags.getSelectionModel().select(0);

        cbCategory.setOnAction(event -> filterDrillTable());

        warmupButton.setOnAction(event -> moveSelectedDrills(warmup, warmupTab));
        togetherButton.setOnAction(event -> moveSelectedDrills(together, togetherTab));
        backupButton.setOnAction(event -> moveSelectedDrills(backup, backupTab));
        stationsButton.setOnAction(event -> moveDrillsIfStationsTrue());

        availablePlayersList = dbLoader.getPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.playerID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());
        playerList.getItems().clear();
        playerList.getItems().addAll(availablePlayersList);
    }

    public void eventListenersFromTable(TableView<Drill> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldDrill, newDrill) -> {
            if (newDrill != null) {
                try {
                    drillImage.setImage(new Image(newDrill.getImageLink()));
                } catch (Exception e) {
                    drillImage.setImage(null);
                }

                drillName.setText(newDrill.getName());
                drillCategory.setText(newDrill.getCategory());
                drillDifficulty.setText(String.valueOf(newDrill.getDifficulty()));
                drillParticipation.setText(newDrill.getParticipation());
                drillStation.setSelected(newDrill.getStation());
                drillDescription.setText(newDrill.getDescription());
                puckPosition.setText(newDrill.getPuckPosition());

                TableColumn<String, String> tagColumn = (TableColumn<String, String>) drillTags.getColumns().get(0);

                tagColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> param) {
                        return new ReadOnlyObjectWrapper<>(param.getValue());
                    }
                });

                drillTags.getItems().clear();
                drillTags.getItems().addAll(newDrill.getTags());
            }
        });
    }

    public void filterDrillTable() {
        drillTable.getSelectionModel().clearSelection();
        Predicate<Drill> filterPredicate = drill ->
                (cbCategory.getValue() == null || cbCategory.getValue().equals("Category") || cbCategory.getValue().equals(drill.getCategory())) &&
                        (cbDifficulty.getValue() == null || cbDifficulty.getValue().equals("Difficulty") || cbDifficulty.getValue().equals(drill.getDifficulty())) &&
                        (cbParticipation.getValue() == null || cbParticipation.getValue().equals("Participation") || cbParticipation.getValue().equals(drill.getParticipation())) &&
                        (cbStation.getValue() == null || cbStation.getValue().equals("Stations") || cbStation.getValue().equals(drill.getStation())) &&
                        (cbTags.getValue() == null || cbTags.getValue().equals("Tags") || drill.getTags().contains(cbTags.getValue()));

        filteredDrills.setPredicate(filterPredicate);
    }

    public void searchDrillTable() {
        drillTable.getSelectionModel().clearSelection();
        String searchInput = searchBox.getText().trim();
        String[] searchWords = searchInput.split("\\s+");

        Predicate<Drill> filterPredicate = drill -> {
            if (searchWords.length == 0) {
                return true;
            }
            for (String word : searchWords) {
                if (word.isEmpty()) {
                    continue;
                }
                if (drill.getName().toLowerCase().contains(word.toLowerCase()) ||
                        drill.getCategory().toLowerCase().contains(word.toLowerCase()) ||
                        String.valueOf(drill.getDifficulty()).equals(word) ||
                        drill.getParticipation().toLowerCase().contains(word.toLowerCase()) ||
                        String.valueOf(drill.getStation()).equals(word) ||
                        drill.getTags().stream().anyMatch(tag -> tag.toLowerCase().contains(word.toLowerCase()))) {
                    return true;
                }
            }
            return false;
        };
        filteredDrills.setPredicate(filterPredicate);
    }

    @FXML
    private void resetAllFilters() {
        drillTable.getSelectionModel().clearSelection();
        cbCategory.getSelectionModel().select(0);
        cbDifficulty.getSelectionModel().select(0);
        cbParticipation.getSelectionModel().select(0);
        cbTags.getSelectionModel().select(0);
        cbStation.getSelectionModel().select(0);
        searchBox.clear();

        filteredDrills.setPredicate(null);
    }

    private void moveSelectedDrills(TableView<Drill> targetList, Tab tab) {
        ObservableList<Drill> selectedDrills = drillTable.getSelectionModel().getSelectedItems();

        for (Drill d : selectedDrills) {
            if (!targetList.getItems().contains(d)) {
                int initialIndex = targetList.getItems().size();
                d.setSortingIndex(initialIndex);
                targetList.getItems().add(d);
            }
        }

        tablePane.getSelectionModel().select(tab);
    }

    private void moveDrillsIfStationsTrue() {
        ObservableList<Drill> selectedDrills = drillTable.getSelectionModel().getSelectedItems().stream()
                .filter(Drill::getStation)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        for (Drill d : selectedDrills) {
            if (!stations.getItems().contains(d)) {
                int initialIndex = stations.getItems().size();
                d.setSortingIndex(initialIndex);
                stations.getItems().add(d);
            }
        }

        tablePane.getSelectionModel().select(stationsTab);
    }

    private void removeDrillSetup(TableView<Drill> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldDrill, newDrill) -> {
            if (newDrill != null) {
                try {
                    tableView.setOnKeyReleased(event -> {
                        if (event.getCode() == KeyCode.DELETE) {
                            tableView.getItems().remove(newDrill);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void moveDrill(TableView<Drill> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldDrill, newDrill) -> {
            if (newDrill != null) {
                try {
                    tableView.setOnKeyPressed(event -> {
                        if (event.getCode() == KeyCode.U) {
                            int oldIndex = tableView.getSelectionModel().getSelectedIndex();
                            if (oldIndex > 0) {
                                int newIndex = oldIndex - 1;
                                Drill d = tableView.getItems().remove(oldIndex);
                                tableView.getItems().add(newIndex, d);
                                tableView.getSelectionModel().clearSelection();
                                tableView.getSelectionModel().select(newIndex);
                                for (Drill drill : tableView.getItems()) {
                                    drill.setSortingIndex(tableView.getItems().indexOf(drill));
                                }
                            }
                            tableView.refresh();
                        }
                        if (event.getCode() == KeyCode.D) {
                            int oldIndex = tableView.getSelectionModel().getSelectedIndex();
                            int tvSize = tableView.getItems().size() - 1;
                            if (oldIndex < tvSize) {
                                int newIndex = oldIndex + 1;
                                Drill d = tableView.getItems().remove(oldIndex);
                                tableView.getItems().add(newIndex, d);
                                tableView.getSelectionModel().clearSelection();
                                tableView.getSelectionModel().select(newIndex);
                                for (Drill drill : tableView.getItems()) {
                                    drill.setSortingIndex(tableView.getItems().indexOf(drill));
                                }
                            }
                            tableView.refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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

            Player selectedPlayer = allPlayers.stream()
                    .filter(player -> (player.getLastName() + " " + player.getFirstName()).equals(db.getString()))
                    .findFirst()
                    .orElse(null);

            event.setDropCompleted(success);

            if (event.isDropCompleted()) {
                playerList.getItems().remove(draggedPlayer);
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
                    playerList.getItems().add(retrievedPlayer);
                    textField.clear();
                }
            });
        });
    }

    public Player getPlayerFromTextField(String playerName) {
        if (!playerName.isEmpty()) {
            String[] nameParts = playerName.split(" ");
            if (nameParts.length >= 2) {
                List<Player> retrievedPlayers = allPlayers.stream()
                        .filter(player -> player.getFirstName().equals(nameParts[1]) &&
                                player.getLastName().equals(nameParts[0]))
                        .collect(Collectors.toList());
                return retrievedPlayers.get(0);
            }
        }
        return new Player("", "", selectedTeam.getName());
    }

}



