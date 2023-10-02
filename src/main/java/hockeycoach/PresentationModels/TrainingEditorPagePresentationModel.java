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
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class TrainingEditorPagePresentationModel extends PresentationModel{
    List<Drill> drills;
    FilteredList<Drill> filteredDrills;
    List<Player> availablePlayersList = new ArrayList<>();

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
    TableView<Player> availablePlayers;
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
        availablePlayers = (TableView) root.lookup("availablePlayers");
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

        drillTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        warmup.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        stations.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        together.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        backup.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Team selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        drills = dbLoader.getDrills("SELECT * FROM drill");

        trainingTeam.setText(selectedTeam.getName());

        drillTable.getItems().clear();
        drillTable.getItems().addAll(drills);

        availablePlayersList = dbLoader.getPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.playerID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());
        availablePlayers.getItems().clear();
        availablePlayers.getItems().addAll(availablePlayersList);
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
                                int newIndex = oldIndex-1;
                                Drill d  = tableView.getItems().remove(oldIndex);
                                tableView.getItems().add(newIndex,d);
                                tableView.getSelectionModel().clearSelection();
                                tableView.getSelectionModel().select(newIndex);
                                for(Drill drill:tableView.getItems()){
                                    drill.setSortingIndex(tableView.getItems().indexOf(drill));
                                }
                            }
                            tableView.refresh();
                        }
                        if (event.getCode() == KeyCode.D) {
                            int oldIndex = tableView.getSelectionModel().getSelectedIndex();
                            int tvSize= tableView.getItems().size()-1;
                            if (oldIndex < tvSize) {
                                int newIndex = oldIndex+1;
                                Drill d  = tableView.getItems().remove(oldIndex);
                                tableView.getItems().add(newIndex,d);
                                tableView.getSelectionModel().clearSelection();
                                tableView.getSelectionModel().select(newIndex);
                                for(Drill drill:tableView.getItems()){
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
}



