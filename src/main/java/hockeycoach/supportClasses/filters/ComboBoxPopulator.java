package hockeycoach.supportClasses.filters;

import hockeycoach.mainClasses.*;
import hockeycoach.mainClasses.Drills.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComboBoxPopulator {
    ObservableList<String> observableList = FXCollections.observableArrayList();

    public void setAllCategories(List<DrillCategory> allCategories, ComboBox<String> comboBox) {
        List<String> stringList = allCategories.stream()
                .map(DrillCategory::getCategory)
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

    public void setAllDifficulties(List<DrillDifficulty> allDifficulties, ComboBox<String> comboBox) {
        List<String> stringList = allDifficulties.stream()
                .sorted(Comparator.comparingInt(DrillDifficulty::getDifficulty))
                .map(DrillDifficulty::getDifficultyName)
                .distinct()
                .toList();

        comboBox.getItems().addAll(stringList);
    }

    public void setAllStations(ComboBox<String> comboBox) {
        List<String> stringList = new ArrayList<>();
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
                    String category = drill.getCategory().getCategory();
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

    public void setDifficulty(List<Drill> allDrillList, ComboBox<String> comboBox) {
        List<String> difficultyList = new ArrayList<>();
        allDrillList.stream()
                .forEach(drill -> {
                    String difficulty = drill.getDifficulty().getDifficultyName();
                    difficultyList.add(difficulty);
                });
        observableList = difficultyList.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        comboBox.getItems().addAll(difficultyList);
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
                    String city = stadium.getStadiumCity();
                    cityList.add(city);
                });
        observableList = cityList.stream()
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        comboBox.getItems().addAll(observableList);
    }
}
