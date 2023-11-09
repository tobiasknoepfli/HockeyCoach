package hockeycoach.supportClasses.filters;

import hockeycoach.mainClasses.*;
import hockeycoach.supportClasses.Difficulty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComboBoxPopulator {
    ObservableList<String> observableList = FXCollections.observableArrayList();

    public void setAllCategories(List<DrillCategory> allCategories, ComboBox<String> comboBox) {
        List<String> stringList = allCategories.stream()
                .map(DrillCategory::getDrillCategory)
                .distinct()
                .sorted()
                .toList();
        comboBox.getItems().addAll(stringList);
    }

    public void setAllParticipations(List<DrillParticipation> allParticipations, ComboBox<String> comboBox) {
        List<String> stringList = allParticipations.stream()
                .map(DrillParticipation::getDrillParticipation)
                .distinct()
                .sorted()
                .toList();
        comboBox.getItems().addAll(stringList);
    }

    public void setAllPuckPositions(List<DrillPuckPosition> allPuckPositions, ComboBox<String> comboBox) {
        List<String> stringList = allPuckPositions.stream()
                .map(DrillPuckPosition::getPuckPosition)
                .distinct()
                .sorted()
                .toList();
        comboBox.getItems().addAll(stringList);
    }

    public void setAllDifficulties(ComboBox<String> comboBox) {
        List<String> stringList = new ArrayList<>();
        int i = 1;
        for (Difficulty d : Difficulty.values()) {
            stringList.add(Difficulty.stringFromInt(i));
            i++;
        }
        comboBox.getItems().addAll(stringList);
    }

    public void setAllStations(ComboBox<String> comboBox){
        List<String> stringList =new ArrayList<>();
        stringList.add("true");
        stringList.add("false");
        comboBox.getItems().addAll(stringList);
    }

    public void setAllTags(List<DrillTag> allTags, ComboBox<String> comboBox) {
        List<String> stringList = allTags.stream()
                .map(DrillTag::getDrillTag)
                .distinct()
                .sorted()
                .toList();
        comboBox.getItems().addAll(stringList);
    }

    public void setCategory(List<Drill> allDrillList, ComboBox<String> comboBox) {
        List<String> categoryList = new ArrayList<>();
        allDrillList.stream()
                .forEach(drill -> {
                    String category = drill.getCategory().getDrillCategory();
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
                    String participation = drill.getParticipation().getDrillParticipation();
                    participationList.add(participation);
                });
        observableList = participationList.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        comboBox.getItems().addAll(observableList);
    }

    public void setDifficulty(List<Drill> allDrillList, ComboBox<Difficulty> comboBox) {
        List<Difficulty> difficultyList = allDrillList.stream()
                .map(drill -> Difficulty.fromValue(drill.getDifficulty()))
                .distinct()
                .sorted(Comparator.comparing(Difficulty::getValue))
                .collect(Collectors.toList());

        comboBox.getItems().addAll(difficultyList);
    }


    public void setPuckPosition(List<Drill> allDrillList, ComboBox<String> comboBox) {
        List<String> puckPositionList = new ArrayList<>();
        allDrillList.stream()
                .forEach(drill -> {
                    String puckPosition = drill.getPuckPosition().getPuckPosition();
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

    public void setStadiumCities(List<Stadium> allStadiumList, ComboBox<String> comboBox) {
        List<String> cityList = new ArrayList<>();
        allStadiumList.stream()
                .forEach(stadium -> {
                    String city = stadium.getStadiumPlace();
                    cityList.add(city);
                });
        observableList = cityList.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        comboBox.getItems().addAll(observableList);
    }
}
