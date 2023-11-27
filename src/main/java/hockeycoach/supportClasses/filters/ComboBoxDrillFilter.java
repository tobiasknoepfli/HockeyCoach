package hockeycoach.supportClasses.filters;

import hockeycoach.mainClasses.Drills.Drill;
import hockeycoach.mainClasses.Drills.DrillDifficulty;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComboBoxDrillFilter extends ComboBoxFilter {
    public void setFilter(List<Drill> filteredDrills, List<Drill> allDrillList, TableView<Drill> allDrills,
                          ComboBox categoryBox, ComboBox participationBox, ComboBox difficultyBox,
                          ComboBox stationBox, ComboBox tagsBox) {
        filteredDrills = new ArrayList<>(allDrillList);

        if (categoryBox.getValue() != null) {
            String selectedCategory = categoryBox.getValue().toString();
            filteredDrills = filteredDrills.stream()
                    .filter(drill -> drill.getCategory().getCategory().equals(selectedCategory))
                    .collect(Collectors.toList());
        }

        if (participationBox.getValue() != null) {
            String selectedParticipation = participationBox.getValue().toString();
            filteredDrills = filteredDrills.stream()
                    .filter(drill -> drill.getParticipation().getDrillParticipation().equals(selectedParticipation))
                    .collect(Collectors.toList());
        }

        if (difficultyBox.getValue() != null) {
            String selectedDifficulty = difficultyBox.getValue().toString();
            filteredDrills = filteredDrills.stream()
                    .filter(drill -> drill.getDifficulty().difficultyName == selectedDifficulty)
                    .collect(Collectors.toList());
        }

        if (stationBox.getValue() != null) {
            Boolean selectedStation = false;
            if (stationBox.getValue().toString() == "true") {
                selectedStation = true;
            }
            if (selectedStation) {
                filteredDrills = filteredDrills.stream()
                        .filter(drill -> drill.getStation().booleanValue() == true)
                        .collect(Collectors.toList());
            } else {
                filteredDrills = filteredDrills.stream()
                        .filter(drill -> drill.getStation().booleanValue() != true)
                        .collect(Collectors.toList());
            }
        }

        if (tagsBox.getValue() != null) {
            String selectedTag = tagsBox.getValue().toString();
            filteredDrills = filteredDrills.stream()
                    .filter(drill -> drill.getTags().contains(selectedTag))
                    .collect(Collectors.toList());
        }

        allDrills.setItems(FXCollections.observableArrayList(filteredDrills));
    }

    public void clearFilter(List<Drill> drillList, TableView<Drill> allDrills,
                            ComboBox categoryBox, ComboBox participationBox, ComboBox difficultyBox,
                            ComboBox stationBox, ComboBox tagsBox) {

        allDrills.getSelectionModel().clearSelection();

        categoryBox.setValue(null);
        participationBox.setValue(null);
        difficultyBox.setValue(null);
        stationBox.setValue(null);
        tagsBox.setValue(null);

        allDrills.setItems(FXCollections.observableArrayList(drillList));

        setComboboxText(categoryBox, "Category");
        setComboboxText(participationBox, "Participation");
        setComboboxText(difficultyBox, "Difficulty");
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
