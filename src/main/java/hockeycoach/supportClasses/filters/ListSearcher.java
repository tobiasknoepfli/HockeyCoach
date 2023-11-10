package hockeycoach.supportClasses.filters;

import hockeycoach.mainClasses.Drills.Drill;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class ListSearcher {
    public void searchDrillTable(TableView<Drill> drillTable, TextField searchBox) {
        drillTable.getSelectionModel().clearSelection();
        String searchInput = searchBox.getText().trim();

        List<Drill> drillList = drillTable.getItems().stream()
                .filter(drill -> drill.getCategory().getCategory().toLowerCase().contains(searchInput.toLowerCase()) ||
                        drill.getParticipation().getDrillParticipation().toLowerCase().contains(searchInput.toLowerCase()) ||
                        drill.getDifficulty().getDifficultyName().toLowerCase().contains(searchInput.toLowerCase()) ||
                        drill.getName().toLowerCase().contains(searchInput.toLowerCase()))
                .toList();

        drillTable.getItems().clear();
        drillTable.getItems().addAll(drillList);
    }

    public void clearSearchBox(TextField searchBox){
        searchBox.clear();
    }
}
