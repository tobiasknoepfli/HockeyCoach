package hockeycoach.PresentationModels;

import hockeycoach.DB.DBDeleter.DBStadiumDeleter;
import hockeycoach.DB.DBEditor.DBStadiumEditor;
import hockeycoach.DB.DBLoader.DBStadiumLoader;
import hockeycoach.DB.DBWriter.DBStadiumWriter;
import hockeycoach.mainClasses.Stadium;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.CustomTableColumns;
import hockeycoach.supportClasses.NodeStatus;
import hockeycoach.supportClasses.SearchBox;
import hockeycoach.supportClasses.filters.ComboBoxPopulator;
import hockeycoach.supportClasses.filters.ComboBoxStadiumFilter;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import jfxtras.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hockeycoach.AppStarter.*;

public class StadiumPresentationModel extends PresentationModel {
    ButtonControls buttonControls = new ButtonControls();
    SearchBox searchBox = new SearchBox();

    DBStadiumLoader dbStadiumLoader = new DBStadiumLoader();
    DBStadiumWriter dbStadiumWriter = new DBStadiumWriter();
    DBStadiumEditor dbStadiumEditor = new DBStadiumEditor();
    DBStadiumDeleter dbStadiumDeleter = new DBStadiumDeleter();

    ComboBoxPopulator comboBoxPopulator = new ComboBoxPopulator();
    ComboBoxStadiumFilter comboBoxStadiumFilter = new ComboBoxStadiumFilter();
    Stadium stadium = new Stadium();
    CustomTableColumns customTableColumns = new CustomTableColumns();

    List<Stadium> allStadiumList = new ArrayList<>();
    List<TextField> textFieldList = new ArrayList<>();

    NodeStatus nodeStatus = new NodeStatus();

    Button newStadiumButton, saveButton, cancelButton, closeWindowButton, searchStadiumButton, clearFilters, deleteButton, editButton;

    TableView<Stadium> allStadiums;

    TextField searchStadium, stadiumName, stadiumAddress, stadiumZip, stadiumCity, stadiumCountry;

    ComboBox cityFilter;

    TableColumn cityColumn;

    @Override
    public void initializeControls(Pane root) {
        importFields(root);
        getDBEntries(root);

        allStadiums.getItems().addAll(allStadiumList);
        customTableColumns.setStadiumCityColumn(cityColumn, Stadium::getStadiumCity);

        comboBoxPopulator.setStadiumCities(allStadiumList, cityFilter);

        setupButtons(root);
        setupEventListeners(root);
        setupFieldLists(root);
        fillFields(root);

        nodeStatus.setCurrentStatus(NodeStatus.StatusType.IDLE);
        disableFields(true);
    }

    @Override
    public void setupFieldLists(Pane root) {
        TextField[] textFields = {searchStadium, stadiumName, stadiumAddress, stadiumZip, stadiumCity, stadiumCountry};
        textFieldList = Arrays.asList(textFields);
    }

    @Override
    public void getDBEntries(Pane root) {
        allStadiumList = dbStadiumLoader.getAllStadiums();
    }

    @Override
    public void fillFields(Pane root) {

    }

    @Override
    public void setupButtons(Pane root) {
        editButton.setOnAction(event -> {
            nodeStatus.setCurrentStatus(NodeStatus.StatusType.EDIT);
            disableFields(true);
        });

        deleteButton.setOnAction(event -> {
            stadium = allStadiums.getSelectionModel().getSelectedItem();
            dbStadiumDeleter.deleteStadium(stadium);
            nodeStatus.setCurrentStatus(NodeStatus.StatusType.IDLE);
            disableFields(true);
            refreshStadiumList();
        });

        cancelButton.setOnAction(event -> {
            nodeStatus.setCurrentStatus(NodeStatus.StatusType.IDLE);
            disableFields(true);
            refreshStadiumList();
        });

        clearFilters.setOnAction(event -> {
            comboBoxStadiumFilter.resetFilter(allStadiumList, allStadiums, cityFilter);
            searchBox.clearStadium(searchStadium, allStadiumList, allStadiums);
            refreshStadiumList();

            nodeStatus.setCurrentStatus(NodeStatus.StatusType.IDLE);
            disableFields(true);
        });

        searchStadiumButton.setOnAction(event -> {
            searchBox.searchStadium(searchStadium.getText(), allStadiumList, allStadiums);
        });

        saveButton.setOnAction(event -> {
            if (nodeStatus.getCurrentStatus().equals(NodeStatus.StatusType.EDIT)) {
                writeNewStadium();
                dbStadiumEditor.editStadium(stadium);
                nodeStatus.setCurrentStatus(NodeStatus.StatusType.IDLE);
                refreshStadiumList();
                disableFields(true);
            } else {
                writeNewStadium();
                dbStadiumWriter.writeStadium(stadium);
                refreshStadiumList();
                nodeStatus.setCurrentStatus(NodeStatus.StatusType.IDLE);
                disableFields(true);
            }
        });

        newStadiumButton.setOnMousePressed(event -> {
            nodeStatus.setCurrentStatus(NodeStatus.StatusType.NEW);
            disableFields(true);
            refreshStadiumList();
        });

        closeWindowButton.setOnAction(event -> {
            buttonControls.closeWindow(root,STADIUM);
        });
    }


    @Override
    public void setupEventListeners(Pane root) {
        allStadiums.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                stadiumName.setText(newValue.getStadiumName());
                stadiumAddress.setText(newValue.getStadiumAddress());
                stadiumZip.setText(Integer.toString(newValue.getStadiumZip()));
                stadiumCity.setText(newValue.getStadiumCity());
                stadiumCountry.setText(newValue.getStadiumCountry());

                nodeStatus.setCurrentStatus(NodeStatus.StatusType.SELECTED);
                disableFields(true);
            }
        });

        cityFilter.valueProperty().addListener((obs, oldValue, newValue) -> {
            comboBoxStadiumFilter.setFilter(allStadiumList, allStadiums, cityFilter);
        });

        allStadiums.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Stadium selectedStadium = allStadiums.getSelectionModel().getSelectedItem();
                if (selectedStadium != null) {
                    globalStadium.setID(selectedStadium.getID());
                    globalStadium.setStadiumName(selectedStadium.getStadiumName());
                    globalStadium.setStadiumAddress(selectedStadium.getStadiumAddress());
                    globalStadium.setStadiumZip(selectedStadium.getStadiumZip());
                    globalStadium.setStadiumCity(selectedStadium.getStadiumCity());
                    globalStadium.setStadiumCountry(selectedStadium.getStadiumCountry());

                    if (lastVisitedPM != null) {
                        lastVisitedPM.fillStadium(globalStadium);
                        buttonControls.openPresentationModelClose(lastVisitedPM, lastVisitedNodeName, lastVisitedFXML, root, STADIUM);
                    }
                }

            }
        });
    }

    public void writeNewStadium() {
        stadium.setStadiumName(stadiumName.getText());
        stadium.setStadiumAddress(stadiumAddress.getText());
        stadium.setStadiumZip(Integer.parseInt(stadiumZip.getText()));
        stadium.setStadiumCity(stadiumCity.getText());
        stadium.setStadiumCountry(stadiumCountry.getText());
    }

    public void refreshStadiumList() {
        allStadiumList = dbStadiumLoader.getAllStadiums();
        allStadiums.getItems().clear();
        allStadiums.getItems().addAll(allStadiumList);
        textFieldList.forEach(t -> t.clear());
        comboBoxStadiumFilter.resetFilter(allStadiumList, allStadiums, cityFilter);
        searchBox.clearStadium(searchStadium, allStadiumList, allStadiums);
    }

    @Override
    public void disableFields(Boolean disabled) {
        switch (nodeStatus.getCurrentStatus()){
            case SELECTED:
                textFieldList.forEach(t -> t.setEditable(!disabled));
                saveButton.setDisable(disabled);
                cancelButton.setDisable(disabled);
                newStadiumButton.setDisable(!disabled);
                editButton.setDisable(!disabled);
                deleteButton.setDisable(!disabled);
                break;
            case EDIT:
                textFieldList.forEach(t -> t.setEditable(disabled));
                saveButton.setDisable(!disabled);
                cancelButton.setDisable(!disabled);
                newStadiumButton.setDisable(disabled);
                editButton.setDisable(disabled);
                deleteButton.setDisable(!disabled);
                break;
            case NEW:
                textFieldList.forEach(t -> t.setEditable(disabled));
                saveButton.setDisable(!disabled);
                cancelButton.setDisable(!disabled);
                newStadiumButton.setDisable(disabled);
                editButton.setDisable(disabled);
                deleteButton.setDisable(disabled);
                break;
            default:
                textFieldList.forEach(t -> t.setEditable(!disabled));
                saveButton.setDisable(disabled);
                cancelButton.setDisable(disabled);
                newStadiumButton.setDisable(!disabled);
                editButton.setDisable(disabled);
                deleteButton.setDisable(disabled);
                break;
        }
    }

    @Override
    public void importFields(Pane root) {
        newStadiumButton = (Button) root.lookup("#newStadiumButton");
        saveButton = (Button) root.lookup("#saveButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        closeWindowButton = (Button) root.lookup("#closeWindowButton");
        searchStadiumButton = (Button) root.lookup("#searchStadiumButton");
        clearFilters = (Button) root.lookup("#clearFilters");
        deleteButton = (Button) root.lookup("#deleteButton");
        editButton = (Button) root.lookup("#editButton");

        cityFilter = (ComboBox) root.lookup("#cityFilter");

        allStadiums = (TableView) root.lookup("#allStadiums");

        stadiumName = (TextField) root.lookup("#stadiumName");
        stadiumAddress = (TextField) root.lookup("#stadiumAddress");
        stadiumZip = (TextField) root.lookup("#stadiumZip");
        stadiumCity = (TextField) root.lookup("#stadiumCity");
        stadiumCountry = (TextField) root.lookup("#stadiumCountry");
        searchStadium = (TextField) root.lookup("#searchStadium");

        cityColumn = allStadiums.getVisibleLeafColumn(1);
    }
}
