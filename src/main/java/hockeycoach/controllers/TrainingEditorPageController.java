package hockeycoach.controllers;

import hockeycoach.mainClasses.Drill;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class TrainingEditorPageController {
    @FXML
    private ImageView drillImage;

    @FXML
    private TextField drillName;

    @FXML
    private TextField drillCategory;

    @FXML
    private TextField drillDifficulty;

    @FXML
    private TextField drillParticipation;

    @FXML
    private CheckBox drillStation;

    @FXML
    private TextArea drillDescription;

    @FXML
    private TableView<String> drillTags;

    @FXML
    private ComboBox<String> cbCategory;

    @FXML
    private ComboBox<String> cbDifficulty;

    @FXML
    private ComboBox<String> cbParticipation;

    @FXML
    private ComboBox<Boolean> cbStation;

    @FXML
    private ComboBox<String> cbTags;

    @FXML
    private TextField searchBox;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Drill> drillTable;

    @FXML
    private TableView<Drill> warmup;

    @FXML
    private TableView<Drill> together;

    @FXML
    private TableView<Drill> stations;

    @FXML
    private TableView<Drill> backup;

    @FXML
    private TextField trainingDate;

    @FXML
    private TextField trainingTime;

    @FXML
    private TextField trainingStadium;

    @FXML
    private TextField trainingTeam;

    @FXML
    private TextField trainingMainFocus;

    @FXML
    private TextArea trainingPointers;
}
