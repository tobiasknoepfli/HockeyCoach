package hockeycoach.UI;

import hockeycoach.mainClasses.Drill;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class TrainingEditorPagePresentationModel {
    List<Drill> drills;

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
    TableView<Drill> drillTable;
    TableView<Drill> warmup;
    TableView<Drill> together;
    TableView<Drill> stations;
    TableView<Drill> backup;
    TextField trainingDate;
    TextField trainingTime;
    TextField trainingStadium;
    TextField trainingTeam;
    TextField trainingMainFocus;
    TextArea trainingPointers;


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
        trainingDate = (TextField) root.lookup("#trainingDate");
        trainingTime = (TextField) root.lookup("#trainingTime");
        trainingStadium = (TextField) root.lookup("#trainingStadium");
        trainingTeam = (TextField) root.lookup("#trainingTeam");
        trainingMainFocus = (TextField) root.lookup("#trainingMainFocus");
        trainingPointers = (TextArea) root.lookup("#trainingPointers");

        Team selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        drills = dbLoader.getDrills("SELECT * FROM drill");

        trainingTeam.setText(selectedTeam.getName());

        drillTable.getItems().clear();
        drillTable.getItems().addAll(drills);

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
    }

    public void eventListenersFromTable(TableView<Drill> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldDrill, newDrill) -> {
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

            TableColumn<String, String> tagColumn = (TableColumn<String, String>) drillTags.getColumns().get(0);

            tagColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> param) {
                    return new ReadOnlyObjectWrapper<>(param.getValue());
                }
            });

            drillTags.getItems().clear();
            drillTags.getItems().addAll(newDrill.getTags());
        });
    }
}


