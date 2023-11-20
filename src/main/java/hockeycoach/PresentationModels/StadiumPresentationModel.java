package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBStadiumLoader;
import hockeycoach.DB.DBWriter.DBStadiumWriter;
import hockeycoach.mainClasses.Stadium;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.CustomTableColumns;
import hockeycoach.supportClasses.SearchBox;
import hockeycoach.supportClasses.filters.ComboBoxPopulator;
import hockeycoach.supportClasses.filters.ComboBoxStadiumFilter;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import jfxtras.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.*;

public class StadiumPresentationModel extends PresentationModel {
    ButtonControls buttonControls = new ButtonControls();
    SearchBox searchBox = new SearchBox();
    DBStadiumLoader dbStadiumLoader = new DBStadiumLoader();
    DBStadiumWriter dbStadiumWriter = new DBStadiumWriter();
    ComboBoxPopulator comboBoxPopulator = new ComboBoxPopulator();
    ComboBoxStadiumFilter comboBoxStadiumFilter = new ComboBoxStadiumFilter();
    Stadium stadium = new Stadium();
    CustomTableColumns customTableColumns = new CustomTableColumns();

    List<Stadium> allStadiumList = new ArrayList<>();

    Button newStadiumButton, saveButton, cancelButton, closeWindowButton, searchStadiumButton, clearFilters;

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
    }

    @Override
    public void setupFieldLists(Pane root) {

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
        clearFilters.setOnAction(event -> {
            comboBoxStadiumFilter.resetFilter(allStadiumList, allStadiums, cityFilter);
            searchBox.clearStadium(searchStadium, allStadiumList, allStadiums);
        });

        searchStadiumButton.setOnAction(event -> {
            searchBox.searchStadium(searchStadium.getText(), allStadiumList, allStadiums);
        });
        saveButton.setOnAction(event -> {
            writeNewStadium();
            dbStadiumWriter.writeStadium(stadium);
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

    @Override
    public void importFields(Pane root) {
        newStadiumButton = (Button) root.lookup("#newStadiumButton");
        saveButton = (Button) root.lookup("#saveButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        closeWindowButton = (Button) root.lookup("#closeWindowButton");
        searchStadiumButton = (Button) root.lookup("#searchStadiumButton");
        clearFilters = (Button) root.lookup("#clearFilters");

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

    @Override
    public void disableFields(Boolean disabled) {

    }
}
