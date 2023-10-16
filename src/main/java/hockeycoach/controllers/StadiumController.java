package hockeycoach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StadiumController {
    @FXML
    private Button  newStadiumButton, saveButton, cancelButton, closeWindowButton, searchStadiumButton,clearFilters;
    @FXML
    private TableView allStadiums;
    @FXML
    private TextField  searchStadium, stadiumName, stadiumAddress, stadiumZip, stadiumCity, stadiumCountry;
    @FXML
    private ComboBox cityFilter;

}
