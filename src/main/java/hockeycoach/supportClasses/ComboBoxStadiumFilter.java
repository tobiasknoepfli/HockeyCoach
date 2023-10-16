package hockeycoach.supportClasses;

import hockeycoach.mainClasses.Stadium;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComboBoxStadiumFilter extends ComboBoxFilter{
    public void setFilter(List<Stadium> allStadiumList, TableView<Stadium> allStadiums, ComboBox cityBox){
        List<Stadium> filteredStadiums = new ArrayList<>();

        if (cityBox.getValue() != null) {
            String selectedCity = (String) cityBox.getValue();
            filteredStadiums = allStadiumList.stream()
                    .filter(stadium -> stadium.getStadiumPlace().equals(selectedCity))
                    .collect(Collectors.toList());
        }
        allStadiums.getItems().clear();
        allStadiums.getItems().addAll(filteredStadiums);
    }

    public void clearFilter(List<Stadium> allStadiumList, TableView<Stadium> allStadiums, ComboBox cityBox){
        allStadiums.getSelectionModel().clearSelection();
        cityBox.setValue(null);

        allStadiums.getItems().clear();
        allStadiums.getItems().addAll(allStadiumList);

        setComboboxText(cityBox,"City");
    }


    @Override
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
