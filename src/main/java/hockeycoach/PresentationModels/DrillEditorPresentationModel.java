package hockeycoach.PresentationModels;

import hockeycoach.DB.DBConverter.DBStringConverter;
import hockeycoach.DB.DBLoader.DBDrillLoader;
import hockeycoach.DB.DBLoader.DBDrillValuesLoader;
import hockeycoach.DB.DBWriter.DBDrillWriter;
import hockeycoach.mainClasses.Drills.*;
import hockeycoach.supportClasses.*;
import hockeycoach.supportClasses.filters.ComboBoxDrillFilter;
import hockeycoach.supportClasses.filters.ComboBoxPopulator;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.w3c.dom.events.MouseEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class DrillEditorPresentationModel extends PresentationModel {
    DBDrillLoader dbDrillLoader = new DBDrillLoader();
    DBDrillValuesLoader dbDrillValuesLoader = new DBDrillValuesLoader();

    DBDrillWriter dbDrillWriter = new DBDrillWriter();

    DBStringConverter dbStringConverter = new DBStringConverter();

    ImageChooser imageChooser = new ImageChooser();
    MouseEvent event;
    ComboBoxPopulator comboBoxPopulator = new ComboBoxPopulator();
    ComboBoxDrillFilter comboBoxDrillFilter = new ComboBoxDrillFilter();
    ButtonControls buttonControls = new ButtonControls();
    Stack<TextFieldAction> textFieldActions = new Stack<>();
    TextFieldAction textFieldAction = new TextFieldAction();

    List<Drill> allDrillList, filteredDrills;
    List<DrillPuckPosition> drillPuckPositionsList;
    List<DrillCategory> drillCategoryList;
    List<DrillParticipation> drillParticipationList;
    List<DrillDifficulty> drillDifficultyList;

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
    Label puckPositionName;

    @Override
    public void initializeControls(Pane root) {
        importFields(root);

        allDrillList = dbDrillLoader.getAllDrills();

        allDrills.getItems().clear();
        allDrills.getItems().setAll(allDrillList);

        TextField[] textFields = {searchBox, drillName, newCategory, addNewTag};
        Arrays.stream(textFields).forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        getDBEntries(root);
        setupButtons(root);

        setupEventListeners(root);
    }

    @Override
    public void getDBEntries(Pane root) {
        drillCategoryList = dbDrillValuesLoader.getAllCategories();
        drillParticipationList = dbDrillValuesLoader.getAllParticipations();
        drillPuckPositionsList = dbDrillValuesLoader.getAllPuckPositions();
        drillDifficultyList =dbDrillValuesLoader.getAllDifficulties();
    }

    @Override
    public void setupButtons(Pane root) {
        backButton.setOnAction(event -> {
            textFieldAction.undoLastAction(textFieldActions);
        });

        drillImage.setOnMouseClicked(mouseEvent -> {
            drillImage.setImage(imageChooser.chooseImage(event));
        });

        saveButton.setOnAction(event -> {
            dbDrillWriter.writeNewDrill(writeDrill());
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
        allDrills.getSelectionModel().selectedItemProperty().addListener((obs, oldDrill, newDrill) -> {
            drillName.setText(newDrill.getName());
            drillCategory.setValue(newDrill.getCategory().getCategory());
            drillParticipation.setValue(newDrill.getParticipation().getDrillParticipation());
            drillDifficulty.setValue(newDrill.getDifficulty().getDifficultyName());
            drillPuckPosition.setValue(newDrill.getPuckPosition().getPuckPosition());
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

        });

        comboBoxPopulator.setAllCategories(dbDrillValuesLoader.getAllCategories(), drillCategory);
        comboBoxPopulator.setAllParticipations(dbDrillValuesLoader.getAllParticipations(), drillParticipation);
        comboBoxPopulator.setAllPuckPositions(dbDrillValuesLoader.getAllPuckPositions(), drillPuckPosition);
        comboBoxPopulator.setAllDifficulties(dbDrillValuesLoader.getAllDifficulties(), drillDifficulty);
        comboBoxPopulator.setAllStations(drillStation);

        drillPuckPosition.valueProperty().addListener((obs, oldValue, newValue) -> {
            DrillPuckPosition drillPuckPositionNew = drillPuckPositionsList.stream().filter(dpp -> dpp.getPuckPosition().equals(newValue.toString())).findFirst().orElse(null);
            puckPositionName.setText(drillPuckPositionNew.getPuckPositionName());
        });


        drillCategoryFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxDrillFilter.setFilter(filteredDrills, allDrillList, allDrills,
                    drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
                    drillPuckPositionFilter, drillStationFilter, drillTagsFilter);
        });

        drillParticipationFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxDrillFilter.setFilter(filteredDrills, allDrillList, allDrills,
                    drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
                    drillPuckPositionFilter, drillStationFilter, drillTagsFilter);
        });

        drillDifficultyFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxDrillFilter.setFilter(filteredDrills, allDrillList, allDrills,
                    drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
                    drillPuckPositionFilter, drillStationFilter, drillTagsFilter);
        });

        drillPuckPositionFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxDrillFilter.setFilter(filteredDrills, allDrillList, allDrills,
                    drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
                    drillPuckPositionFilter, drillStationFilter, drillTagsFilter);
        });

        drillStationFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxDrillFilter.setFilter(filteredDrills, allDrillList, allDrills,
                    drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
                    drillPuckPositionFilter, drillStationFilter, drillTagsFilter);
        });

        drillTagsFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxDrillFilter.setFilter(filteredDrills, allDrillList, allDrills,
                    drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
                    drillPuckPositionFilter, drillStationFilter, drillTagsFilter);
        });
    }

    public Drill writeDrill() {
        Drill drill = new Drill();
        drill.setName(drillName.getText());
        drill.setCategory(dbStringConverter.getDrillCategoryFromString(drillCategory.getValue().toString()));
        drill.setDifficulty(dbStringConverter.getDrillDifficultyFromString(drillDifficulty.getValue().toString()));
        drill.setParticipation(dbStringConverter.getDrillParticipationFromString(drillParticipation.getValue().toString()));
        drill.setDescription(drill.getDescription());
        drill.setStation(drill.getStation());
        drill.setPicture(drill.getPicture());

        return drill;
    }

    @Override
    public void importFields(Pane root) {
        backButton = (Button) root.lookup("#backButton");
        newDrillButton = (Button) root.lookup("#newDrillButton");
        saveButton = (Button) root.lookup("#saveButton");
        editButton = (Button) root.lookup("#editButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        deleteButton = (Button) root.lookup("#deleteButton");
        closeWindowButton = (Button) root.lookup("#closeWindowButton");
        searchButton = (Button) root.lookup("#searchButton");
        resetButton = (Button) root.lookup("#resetButton");
        newCategoryButton = (Button) root.lookup("#newCategoryButton");
        newTagButton = (Button) root.lookup("#newTagButton");

        searchBox = (TextField) root.lookup("#searchBox");
        drillName = (TextField) root.lookup("#drillName");
        newCategory = (TextField) root.lookup("#newCategory");
        addNewTag = (TextField) root.lookup("#addNewTag");

        drillCategoryFilter = (ComboBox) root.lookup("#drillCategoryFilter");
        drillParticipationFilter = (ComboBox) root.lookup("#drillParticipationFilter");
        drillDifficultyFilter = (ComboBox) root.lookup("#drillDifficultyFilter");
        drillPuckPositionFilter = (ComboBox) root.lookup("#drillPuckPositionFilter");
        drillStationFilter = (ComboBox) root.lookup("#drillStationFilter");
        drillCategory = (ComboBox) root.lookup("#drillCategory");
        drillParticipation = (ComboBox) root.lookup("#drillParticipation");
        drillDifficulty = (ComboBox) root.lookup("#drillDifficulty");
        drillPuckPosition = (ComboBox) root.lookup("#drillPuckPosition");
        drillStation = (ComboBox) root.lookup("#drillStation");
        drillTagsFilter = (ComboBox) root.lookup("#drillTagsFilter");

        allDrills = (TableView) root.lookup("#allDrills");
        drillTags = (TableView) root.lookup("#drillTags");

        drillImage = (ImageView) root.lookup("#drillImage");
        puckPositionName = (Label) root.lookup("#puckPositionName");
    }
}
