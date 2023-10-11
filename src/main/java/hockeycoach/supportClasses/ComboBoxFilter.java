package hockeycoach.supportClasses;

import hockeycoach.mainClasses.Drill;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ComboBoxFilter {
    public void setFilter(List<Drill> filteredDrills, List<Drill> allDrillList, TableView<Drill> allDrills,
                          ComboBox categoryBox, ComboBox participationBox, ComboBox difficultyBox,
                          ComboBox puckPositionBox, ComboBox stationBox, ComboBox tagsBox) {
        filteredDrills = new ArrayList<>(allDrillList);

        if (categoryBox.getValue() != null) {
            String selectedCategory = categoryBox.getValue().toString();
            filteredDrills = filteredDrills.stream()
                    .filter(drill -> drill.getCategory().equals(selectedCategory))
                    .collect(Collectors.toList());
        }

        if (participationBox.getValue() != null) {
            String selectedParticipation = participationBox.getValue().toString();
            filteredDrills = filteredDrills.stream()
                    .filter(drill -> drill.getParticipation().equals(selectedParticipation))
                    .collect(Collectors.toList());
        }

        if (difficultyBox.getValue() != null) {
            Difficulty selectedDifficulty = (Difficulty) difficultyBox.getValue();
            filteredDrills = filteredDrills.stream()
                    .filter(drill -> Difficulty.fromValue(drill.getDifficulty()) == selectedDifficulty)
                    .collect(Collectors.toList());
        }

        if (puckPositionBox.getValue() != null) {
            String selectedPuckPosition = puckPositionBox.getValue().toString();
            filteredDrills = filteredDrills.stream()
                    .filter(drill -> drill.getPuckPosition().equals(selectedPuckPosition))
                    .collect(Collectors.toList());
        }

        if (stationBox.getValue() != null) {
            Boolean selectedStation = false;
            if (stationBox.getValue().toString() == "true") {
                selectedStation = true;
            }
            if (selectedStation) {
                filteredDrills = filteredDrills.stream()
                        .filter(drill -> drill.getStation() == true)
                        .collect(Collectors.toList());
            } else {
                filteredDrills = filteredDrills.stream()
                        .filter(drill -> drill.getStation() != true)
                        .collect(Collectors.toList());
            }
        }

        if (tagsBox.getValue() != null) {
            String selectedTag = tagsBox.getValue().toString();
            filteredDrills = filteredDrills.stream()
                    .filter(drill -> drill.getTags().contains(selectedTag))
                    .collect(Collectors.toList());
        }

        allDrills.getItems().clear();
        allDrills.getItems().setAll(filteredDrills);
    }

    public void clearFilter(List<Drill> drillList, TableView<Drill> allDrills,
                            ComboBox categoryBox, ComboBox participationBox, ComboBox difficultyBox,
                            ComboBox puckPositionBox, ComboBox stationBox, ComboBox tagsBox) {

        allDrills.getSelectionModel().clearSelection();

        categoryBox.setValue(null);
        participationBox.setValue(null);
        difficultyBox.setValue(null);
        puckPositionBox.setValue(null);
        stationBox.setValue(null);
        tagsBox.setValue(null);

        allDrills.getItems().clear();
        allDrills.getItems().addAll(drillList);

        setComboboxText(categoryBox, "Category");
        setComboboxText(participationBox, "Participation");
        setComboboxText(difficultyBox, "Difficulty");
        setComboboxText(puckPositionBox, "Puck Position");
        setComboboxText(stationBox, "Station");
        setComboboxText(tagsBox, "Tag");
    }

    public void setComboboxText(ComboBox comboBox, String label) {
        comboBox.setPromptText(label);
        comboBox.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(label);
                } else {
                    setText(item);
                }
            }
        });
    }

}
