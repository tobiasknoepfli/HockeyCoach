package hockeycoach.UI;

import hockeycoach.mainClasses.Drill;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.util.List;


public class TrainingEditorPagePresentationModel {
    ImageView drillImage;
    TextField drillName;
    TextField drillCategory;
    TextField drillDifficulty;
    TextField drillParticipation;
    CheckBox drillStation;
    TextArea drillDescription;
    TableView<String> drillTags;
    ComboBox<String> cbCategory;
    ComboBox<String> cbDifficulty;
    ComboBox<String> cbParticipation;
    ComboBox<Boolean> cbStation;
    ComboBox<String> cbTags;
    TextField searchBox;
    Button searchButton;
    TableView<Drill> drillTable;
    TableView<Drill> warmup;
    TableView<Drill> together;
    TableView<Drill> stations;
    TableView<Drill> backup;

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

        Team selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        List<Drill> drills = dbLoader.getDrills("SELECT * FROM drill");

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
    }

    public void eventListenersFromTable(TableView<Drill> tableView){
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


