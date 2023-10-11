package hockeycoach.supportClasses;

import hockeycoach.mainClasses.Drill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ComboBoxPopulator {
    ObservableList<String> observableList = FXCollections.observableArrayList();

    public void setCategory(List<Drill> allDrillList, ComboBox<String> comboBox) {
        List<String> categoryList = new ArrayList<>();
        allDrillList.stream()
                .forEach(drill -> {
                    String category = drill.getCategory();
                    categoryList.add(category);
                });
        observableList = categoryList.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        comboBox.getItems().addAll(observableList);
    }

    public void setParticipation(List<Drill> allDrillList, ComboBox<String> comboBox) {
        List<String> participationList = new ArrayList<>();
        allDrillList.stream()
                .forEach(drill -> {
                    String participation = drill.getParticipation();
                    participationList.add(participation);
                });
        observableList = participationList.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        comboBox.getItems().addAll(observableList);
    }

    public void setDifficulty(List<Drill> allDrillList, ComboBox<String> comboBox) {
        List<String> difficultyList = new ArrayList<>();
        allDrillList.stream()
                .forEach(drill -> {
                    int difficulty = drill.getDifficulty();
                    String diff = String.valueOf(Difficulty.fromValue(difficulty));
                    difficultyList.add(diff);
                });
        observableList = difficultyList.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        comboBox.getItems().addAll(observableList);
    }

    public void setPuckPosition(List<Drill> allDrillList, ComboBox<String> comboBox) {
        List<String> puckPositionList = new ArrayList<>();
        allDrillList.stream()
                .forEach(drill -> {
                    String puckPosition = drill.getPuckPosition();
                    puckPositionList.add(puckPosition);
                });
        observableList = puckPositionList.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        comboBox.getItems().addAll(observableList);
    }

    public void setStation(List<Drill> allDrillList, ComboBox<Boolean> comboBox) {
        List<Boolean> stationList = new ArrayList<>();
        ObservableList<Boolean> obsList = FXCollections.observableArrayList();
        allDrillList.stream()
                .forEach(drill -> {
                    Boolean station = drill.getStation();
                    stationList.add(station);
                });
        obsList = stationList.stream()
                .distinct()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        comboBox.getItems().addAll(obsList);
    }

    public void setTags(List<Drill> allDrillList, ComboBox<String> comboBox) {
        List<String> tagList = new ArrayList<>();
        allDrillList.stream()
                .forEach(drill -> {
                    ArrayList<String> drillTags = drill.getTags();
                    tagList.addAll(drillTags);
                });
        observableList = tagList.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        comboBox.getItems().addAll(observableList);
    }
}
