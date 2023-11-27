package hockeycoach.PresentationModels;

import hockeycoach.DB.DBConverter.DBStringConverter;
import hockeycoach.DB.DBLoader.DBDrillLoader;
import hockeycoach.DB.DBLoader.DBDrillValuesLoader;
import hockeycoach.DB.DBLoader.DBImageLoader;
import hockeycoach.DB.DBWriter.DBDrillWriter;
import hockeycoach.DB.DBWriter.DBImageWriter;
import hockeycoach.mainClasses.Drills.*;
import hockeycoach.supportClasses.*;
import hockeycoach.supportClasses.filters.ComboBoxDrillFilter;
import hockeycoach.supportClasses.filters.ComboBoxPopulator;
import hockeycoach.supportClasses.filters.ListSearcher;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.w3c.dom.events.MouseEvent;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static hockeycoach.AppStarter.DRILLS;
import static hockeycoach.supportClasses.checkups.NullCheck.isNotNullElse;

public class DrillEditorPresentationModel extends PresentationModel {
    DBDrillLoader dbDrillLoader = new DBDrillLoader();
    DBDrillValuesLoader dbDrillValuesLoader = new DBDrillValuesLoader();
    DBImageWriter dbImageWriter = new DBImageWriter();
    ImageHandler imageHandler = new ImageHandler();
    DBImageLoader dbImageLoader = new DBImageLoader();


    DBDrillWriter dbDrillWriter = new DBDrillWriter();

    DBStringConverter dbStringConverter = new DBStringConverter();

    ImageChooser imageChooser = new ImageChooser();
    MouseEvent event;
    ComboBoxPopulator comboBoxPopulator = new ComboBoxPopulator();
    ComboBoxDrillFilter comboBoxDrillFilter = new ComboBoxDrillFilter();
    ButtonControls buttonControls = new ButtonControls();
    Stack<TextFieldAction> textFieldActions = new Stack<>();
    TextFieldAction textFieldAction = new TextFieldAction();
    DrillCategory dc = new DrillCategory();
    CustomTableColumns customTableColumns = new CustomTableColumns();
    ListSearcher listSearcher = new ListSearcher();

    List<Drill> allDrillList, filteredDrills;
    List<DrillCategory> drillCategoryList;
    List<DrillParticipation> drillParticipationList;
    List<DrillDifficulty> drillDifficultyList;

    Button backButton, newDrillButton, saveButton, editButton, cancelButton,
            deleteButton, closeWindowButton, searchButton, newCategoryButton,
            newTagButton, resetButton;
    TextField searchBox, drillName, newCategory, addNewTag;
    TextArea drillDescription;
    ComboBox drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
            drillStationFilter, drillCategory, drillParticipation,
            drillDifficulty, drillStation, drillTagsFilter;
    TableColumn drillCategoryCol, drillParticipationCol, drillDifficultyCol;
    TableView<Drill> allDrills;
    TableView<String> drillTags;
    ImageView drillImage;

    @Override
    public void initializeControls(Pane root) {
        importFields(root);

        allDrillList = dbDrillLoader.getAllDrills();

        allDrills.getItems().clear();
        allDrills.getItems().setAll(allDrillList);

        customTableColumns.setDrillCategoryColumn(drillCategoryCol);
        customTableColumns.setDrillDifficultyColumn(drillDifficultyCol);
        customTableColumns.setDrillParticipationColumn(drillParticipationCol);

        comboBoxPopulator.setAllCategories(dbDrillValuesLoader.getAllCategories(), drillCategory);
        comboBoxPopulator.setAllParticipations(dbDrillValuesLoader.getAllParticipations(), drillParticipation);
        comboBoxPopulator.setAllDifficulties(dbDrillValuesLoader.getAllDifficulties(), drillDifficulty);
        comboBoxPopulator.setAllStations(drillStation);

        drillCategoryFilter.getItems().addAll(dbDrillValuesLoader.getAllCategories().stream().map(DrillCategory::getCategory).distinct().sorted().toList());
        drillParticipationFilter.getItems().addAll(dbDrillValuesLoader.getAllParticipations().stream().map(DrillParticipation::getDrillParticipation).distinct().sorted().toList());
        drillDifficultyFilter.getItems().addAll(dbDrillValuesLoader.getAllDifficulties().stream().sorted(Comparator.comparingInt(DrillDifficulty::getDifficulty)).map(DrillDifficulty::getDifficultyName).distinct().toList());
        drillStationFilter.getItems().addAll(true, false);
        drillTagsFilter.getItems().addAll(dbDrillValuesLoader.getAllDrillTags().stream().map(DrillTag::getDrillTag).distinct().sorted().toList());


        TextField[] textFields = {searchBox, drillName, newCategory, addNewTag};
        Arrays.stream(textFields).forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        getDBEntries(root);

        setupButtons(root);

        setupEventListeners(root);

    }

    @Override
    public void setupFieldLists(Pane root) {

    }

    @Override
    public void getDBEntries(Pane root) {
        drillCategoryList = dbDrillValuesLoader.getAllCategories();
        drillParticipationList = dbDrillValuesLoader.getAllParticipations();
        drillDifficultyList = dbDrillValuesLoader.getAllDifficulties();
    }

    @Override
    public void fillFields(Pane root) {

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
            Drill drill = setDrillValues();
            drill.getPicture().setID(dbImageWriter.saveImage(drill.getPicture()));
            imageHandler.copyImage(drill.getPicture());

            dbDrillWriter.writeNewDrill(drill);
        });

        resetButton.setOnAction(event -> {
            comboBoxDrillFilter.clearFilter(allDrillList, allDrills, drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter, drillStationFilter, drillTagsFilter);
            listSearcher.clearSearchBox(searchBox);
        });

        searchButton.setOnAction(event -> {
            listSearcher.searchDrillTable(allDrills, searchBox);
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
        allDrills.getSelectionModel().selectedItemProperty().addListener((obs, oldDrill, newDrill) -> {
            newDrill.setPicture(dbImageLoader.getPicture("SELECT i.* FROM image i INNER JOIN drill d ON d.image = i.ID WHERE d.ID =" + newDrill.getID()));

            drillName.setText(isNotNullElse(newDrill, d -> d.getName(), ""));
            drillCategory.setValue(isNotNullElse(newDrill, d -> d.getCategory().getCategory(), "Category"));
            drillParticipation.setValue(isNotNullElse(newDrill, d -> d.getParticipation().getDrillParticipation(), "Participation"));
            drillDifficulty.setValue(isNotNullElse(newDrill, d -> d.getDifficulty().getDifficultyName(), "Difficulty"));
            drillStation.setValue(isNotNullElse(newDrill, d -> d.getStation(), "Station"));
            drillImage.setImage(isNotNullElse(newDrill, d -> d.getPicture().getImage(), null));
            drillDescription.setText(isNotNullElse(newDrill, d->d.getDescription(),""));


            TableColumn<String, String> tagColumn = (TableColumn<String, String>) drillTags.getColumns().get(0);
            customTableColumns.setDrillTagColumn(tagColumn);

            drillTags.getItems().clear();
            drillTags.getItems().addAll(newDrill.getTags());
        });


        drillCategoryFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxDrillFilter.setFilter(filteredDrills, allDrillList, allDrills,
                    drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
                    drillStationFilter, drillTagsFilter);
        });

        drillParticipationFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxDrillFilter.setFilter(filteredDrills, allDrillList, allDrills,
                    drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
                    drillStationFilter, drillTagsFilter);
        });

        drillDifficultyFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxDrillFilter.setFilter(filteredDrills, allDrillList, allDrills,
                    drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
                    drillStationFilter, drillTagsFilter);
        });

        drillStationFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxDrillFilter.setFilter(filteredDrills, allDrillList, allDrills,
                    drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
                    drillStationFilter, drillTagsFilter);
        });

        drillTagsFilter.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBoxDrillFilter.setFilter(filteredDrills, allDrillList, allDrills,
                    drillCategoryFilter, drillParticipationFilter, drillDifficultyFilter,
                    drillStationFilter, drillTagsFilter);
        });
    }

    public Drill setDrillValues() {
        Drill drill = new Drill();
        drill.setName(isNotNullElse(drillName, d -> d.getText(), ""));
        drill.setCategory(dbStringConverter.getDrillCategoryFromString(isNotNullElse(drillCategory, d -> d.getValue().toString(), "")));
        drill.setDifficulty(dbStringConverter.getDrillDifficultyFromString(isNotNullElse(drillDifficulty, d -> d.getValue().toString(), "")));
        drill.setParticipation(dbStringConverter.getDrillParticipationFromString(isNotNullElse(drillParticipation, d -> d.getValue().toString(), "")));
        drill.setDescription(isNotNullElse(drill, d -> d.getDescription(), ""));
        drill.setStation(isNotNullElse(drill, d -> d.getStation(), false));
        drill.setPicture(imageHandler.setPicture(drillImage, drillName.getText(), "", DRILLS));
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

        drillDescription = (TextArea) root.lookup("#drillDescription");

        drillCategoryFilter = (ComboBox) root.lookup("#drillCategoryFilter");
        drillParticipationFilter = (ComboBox) root.lookup("#drillParticipationFilter");
        drillDifficultyFilter = (ComboBox) root.lookup("#drillDifficultyFilter");
        drillStationFilter = (ComboBox) root.lookup("#drillStationFilter");
        drillCategory = (ComboBox) root.lookup("#drillCategory");
        drillParticipation = (ComboBox) root.lookup("#drillParticipation");
        drillDifficulty = (ComboBox) root.lookup("#drillDifficulty");
        drillStation = (ComboBox) root.lookup("#drillStation");
        drillTagsFilter = (ComboBox) root.lookup("#drillTagsFilter");

        allDrills = (TableView) root.lookup("#allDrills");
        drillTags = (TableView) root.lookup("#drillTags");

        drillImage = (ImageView) root.lookup("#drillImage");

        drillCategoryCol = allDrills.getVisibleLeafColumn(1);
        drillDifficultyCol = allDrills.getVisibleLeafColumn(2);
        drillParticipationCol = allDrills.getVisibleLeafColumn(3);
    }

    @Override
    public void disableFields(Boolean disabled) {

    }
}
