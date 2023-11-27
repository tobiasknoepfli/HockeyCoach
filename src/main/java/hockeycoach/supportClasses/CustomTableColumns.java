package hockeycoach.supportClasses;

import hockeycoach.mainClasses.Drills.Drill;
import hockeycoach.mainClasses.Stadium;
import hockeycoach.mainClasses.Training;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.function.Function;

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

    public <T> void setStadiumCityColumn(TableColumn tableColumn, Function<T,String> objectValue){
        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<T,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<T,String> s){
                return new SimpleStringProperty(objectValue.apply(s.getValue()));
            }
        });
    }

    public <T> void setStadiumNameColumn(TableColumn tableColumn, Function<T, String> objectValue) {
        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<T, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<T, String> s) {
                return new SimpleStringProperty(objectValue.apply(s.getValue()));
            }
        });
    }


}
