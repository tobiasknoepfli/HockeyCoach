package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBStadiumLoader;
import hockeycoach.mainClasses.Stadium;
import hockeycoach.supportClasses.ComboBoxPopulator;
import hockeycoach.supportClasses.ComboBoxStadiumFilter;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class StadiumPresentationModel extends PresentationModel {
    DBStadiumLoader dbStadiumLoader = new DBStadiumLoader();
    ComboBoxPopulator comboBoxPopulator = new ComboBoxPopulator();
    ComboBoxStadiumFilter comboBoxStadiumFilter = new ComboBoxStadiumFilter();

    List<Stadium> allStadiumList = new ArrayList<>();

    Button newStadiumButton, saveButton, cancelButton, closeWindowButton, searchStadiumButton,clearFilters;

    TableView<Stadium> allStadiums;

    TextField searchStadium, stadiumName, stadiumAddress, stadiumZip, stadiumCity, stadiumCountry;

    ComboBox cityFilter;

    @Override
    public void initializeControls(Pane root) {
        importFields(root);
        getDBEntries(root);

        allStadiums.getItems().addAll(allStadiumList);
        comboBoxPopulator.setStadiumCities(allStadiumList,cityFilter);

        setupButtons(root);
        setupEventListeners(root);
    }

    @Override
    public void getDBEntries(Pane root) {
        allStadiumList = dbStadiumLoader.getAllStadiums("SELECT * FROM stadium");
    }

    @Override
    public void setupButtons(Pane root) {
        clearFilters.setOnAction(event ->{
            comboBoxStadiumFilter.clearFilter(allStadiumList,allStadiums,cityFilter);
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
        allStadiums.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue!=null) {
                stadiumName.setText(newValue.getStadiumName());
                stadiumAddress.setText(newValue.getStadiumAddress());
                stadiumZip.setText(Integer.toString(newValue.getStadiumZip()));
                stadiumCity.setText(newValue.getStadiumPlace());
                stadiumCountry.setText(newValue.getStadiumCountry());
            }
        });

        cityFilter.valueProperty().addListener((obs,oldValue, newValue) ->{
            comboBoxStadiumFilter.setFilter(allStadiumList,allStadiums,cityFilter);
        });
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

    }
}
