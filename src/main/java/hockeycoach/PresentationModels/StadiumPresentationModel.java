package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBStadiumLoader;
import hockeycoach.mainClasses.Stadium;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.SearchBox;
import hockeycoach.supportClasses.filters.ComboBoxPopulator;
import hockeycoach.supportClasses.filters.ComboBoxStadiumFilter;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.*;

public class StadiumPresentationModel extends PresentationModel {
    ButtonControls buttonControls = new ButtonControls();
    SearchBox searchBox = new SearchBox();
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
            comboBoxStadiumFilter.resetFilter(allStadiumList,allStadiums,cityFilter);
            searchBox.clearStadium(searchStadium,allStadiumList,allStadiums);
        });

        searchStadiumButton.setOnAction(event ->{
            searchBox.searchStadium(searchStadium.getText(),allStadiumList,allStadiums);
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

        allStadiums.setOnMousePressed(event ->{
            if(event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Stadium selectedStadium = allStadiums.getSelectionModel().getSelectedItem();
                if(selectedStadium !=null){
                    globalStadium.setStadiumID(selectedStadium.getStadiumID());
                    globalStadium.setStadiumName(selectedStadium.getStadiumName());
                    globalStadium.setStadiumAddress(selectedStadium.getStadiumAddress());
                    globalStadium.setStadiumZip(selectedStadium.getStadiumZip());
                    globalStadium.setStadiumPlace(selectedStadium.getStadiumPlace());
                    globalStadium.setStadiumCountry(selectedStadium.getStadiumCountry());

                    lastVisitedPM.fillStadium(globalStadium);
                    buttonControls.openPresentationModelClose(lastVisitedPM,lastVisitedNodeName,lastVisitedFXML,root,STADIUM);
                }

            }
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
