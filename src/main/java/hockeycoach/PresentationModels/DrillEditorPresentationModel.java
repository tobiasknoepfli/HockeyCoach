package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBDrillLoader;
import hockeycoach.mainClasses.Drill;
import hockeycoach.supportClasses.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.util.List;
import java.util.Stack;

import static hockeycoach.AppStarter.*;

public class DrillEditorPresentationModel extends PresentationModel {
    DBDrillLoader dbDrillLoader = new DBDrillLoader();
    List<Drill> allDrillList, filteredDrills;
    ComboBoxPopulator comboBoxPopulator = new ComboBoxPopulator();
    ComboBoxFilter comboBoxFilter = new ComboBoxFilter();
    ButtonControls buttonControls = new ButtonControls();

    Stack<TextFieldAction> textFieldActions = new Stack<>();
    TextFieldAction textFieldAction= new TextFieldAction();

    Button backButton, newDrillButton, saveButton, editButton, cancelButton,
            deleteButton, closeWindowButton, searchButton, newCategoryButton,
            newTagButton, resetButton;
    TextField searchBox, drillName, newCategory, addNewTag;
    ComboBox drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
            drillPuckPositionFilter, drillStationFilter, drillCategory, drillParticipation,
            drillDifficulty, drillPuckPosition, drillStation, drillTagsFilter;
    TableView<Drill> allDrills;
    TableView<String> drillTags;
    ImageView drillImage;

    @Override
    public void initializeControls(Pane root) {
        backButton = (Button) root.lookup("#backButton");
        newDrillButton = (Button) root.lookup("#newDrillButton");
        saveButton = (Button) root.lookup("#saveButton");
        editButton = (Button) root.lookup("#editButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        deleteButton = (Button) root.lookup("#deleteButton");
        closeWindowButton = (Button) root.lookup("#closeWindowButton");
        searchBox = (TextField) root.lookup("#searchBox");
        searchButton = (Button) root.lookup("#searchButton");
        resetButton = (Button) root.lookup("#resetButton");
        drillCategoryFilter = (ComboBox) root.lookup("#drillCategoryFilter");
        drillParticipationFilter = (ComboBox) root.lookup("#drillParticipationFilter");
        drillDifficultyFilter = (ComboBox) root.lookup("#drillDifficultyFilter");
        drillPuckPositionFilter = (ComboBox) root.lookup("#drillPuckPositionFilter");
        drillStationFilter = (ComboBox) root.lookup("#drillStationFilter");
        allDrills = (TableView) root.lookup("#allDrills");
        drillImage = (ImageView) root.lookup("#drillImage");
        drillName = (TextField) root.lookup("#drillName");
        drillCategory = (ComboBox) root.lookup("#drillCategory");
        drillParticipation = (ComboBox) root.lookup("#drillParticipation");
        drillDifficulty = (ComboBox) root.lookup("#drillDifficulty");
        drillPuckPosition = (ComboBox) root.lookup("#drillPuckPosition");
        drillStation = (ComboBox) root.lookup("#drillStation");
        newCategory = (TextField) root.lookup("#newCategory");
        addNewTag = (TextField) root.lookup("#addNewTag");
        newCategoryButton = (Button) root.lookup("#newCategoryButton");
        newTagButton = (Button) root.lookup("#newTagButton");
        drillTags = (TableView) root.lookup("#drillTags");
        drillTagsFilter = (ComboBox) root.lookup("#drillTagsFilter");

        allDrillList = dbDrillLoader.getDrills("SELECT * FROM drill");

        allDrills.getItems().clear();
        allDrills.getItems().setAll(allDrillList);

        TextField[] textFields = {searchBox, drillName, newCategory, addNewTag};
        for (TextField t:textFields){
            textFieldAction.setupTextFieldUndo(t,textFieldActions);
        }

        getDBEntries(root);
        setupButtons(root);

        setupEventListeners(root);
    }

    @Override
    public void getDBEntries(Pane root) {

    }

    @Override
    public void setupButtons(Pane root) {
        backButton.setOnAction(event ->{
            textFieldAction.undoLastAction(textFieldActions);
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
        allDrills.getSelectionModel().selectedItemProperty().addListener((obs, oldDrill, newDrill) -> {
            drillName.setText(newDrill.getName());
            drillCategory.setValue(newDrill.getCategory());
            drillParticipation.setValue(newDrill.getParticipation());
            drillDifficulty.setValue(Difficulty.fromValue(newDrill.getDifficulty()));
            drillPuckPosition.setValue(newDrill.getPuckPosition());
            drillStation.setValue(newDrill.getStation());

            TableColumn<String, String> tagColumn = (TableColumn<String, String>) drillTags.getColumns().get(0);
            tagColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> param) {
                    return new ReadOnlyObjectWrapper<>(param.getValue());
                }
            });
            drillTags.getItems().clear();
            drillTags.getItems().addAll(newDrill.getTags());

            try {
                drillImage.setImage(new Image(newDrill.getImageLink()));
            } catch (Exception e) {
                drillImage.setImage(null);
            }
        });
        comboBoxPopulator.setCategory(allDrillList, drillCategoryFilter);
        comboBoxPopulator.setParticipation(allDrillList, drillParticipationFilter);
        comboBoxPopulator.setDifficulty(allDrillList, drillDifficultyFilter);
        comboBoxPopulator.setPuckPosition(allDrillList, drillPuckPositionFilter);
        comboBoxPopulator.setStation(allDrillList, drillStationFilter);
        comboBoxPopulator.setTags(allDrillList, drillTagsFilter);


        drillCategoryFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxFilter.setFilter(filteredDrills,allDrillList,allDrills,
                    drillCategoryFilter,drillParticipationFilter,drillDifficultyFilter,
                    drillPuckPositionFilter,drillStationFilter,drillTagsFilter);
        });

        drillParticipationFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxFilter.setFilter(filteredDrills,allDrillList,allDrills,
                    drillCategoryFilter,drillParticipationFilter,drillDifficultyFilter,
                    drillPuckPositionFilter,drillStationFilter,drillTagsFilter);
        });

        drillDifficultyFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxFilter.setFilter(filteredDrills,allDrillList,allDrills,
                    drillCategoryFilter,drillParticipationFilter,drillDifficultyFilter,
                    drillPuckPositionFilter,drillStationFilter,drillTagsFilter);
        });

        drillPuckPositionFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxFilter.setFilter(filteredDrills,allDrillList,allDrills,
                    drillCategoryFilter,drillParticipationFilter,drillDifficultyFilter,
                    drillPuckPositionFilter,drillStationFilter,drillTagsFilter);
        });

        drillStationFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxFilter.setFilter(filteredDrills,allDrillList,allDrills,
                    drillCategoryFilter,drillParticipationFilter,drillDifficultyFilter,
                    drillPuckPositionFilter,drillStationFilter,drillTagsFilter);
        });

        drillTagsFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxFilter.setFilter(filteredDrills,allDrillList,allDrills,
                    drillCategoryFilter,drillParticipationFilter,drillDifficultyFilter,
                    drillPuckPositionFilter,drillStationFilter,drillTagsFilter);
        });


    }
}
