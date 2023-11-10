package hockeycoach.supportClasses;

import hockeycoach.mainClasses.Drills.Drill;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class CustomTableColumns {
    public void setDrillCategoryColumn(TableColumn tableColumn){
        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Drill, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Drill,String> d){
                return new SimpleStringProperty(d.getValue().getCategory().getCategory());
            }
        });
    }

    public void setDrillParticipationColumn(TableColumn tableColumn){
        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Drill, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Drill,String> d){
                return new SimpleStringProperty(d.getValue().getParticipation().getDrillParticipation());
            }
        });
    }

    public void setDrillDifficultyColumn(TableColumn tableColumn){
        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Drill, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Drill,String> d){
                return new SimpleStringProperty(d.getValue().getDifficulty().getDifficultyName());
            }
        });
    }

    public void setDrillTagColumn(TableColumn tableColumn){
        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String,String> d){
                return new SimpleStringProperty(d.getValue());
            }
        });
    }

}
