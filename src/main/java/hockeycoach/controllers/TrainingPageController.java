package hockeycoach.controllers;

import hockeycoach.mainClasses.Drill;
import hockeycoach.mainClasses.Training;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class TrainingPageController {

    @FXML
    private TableView<Training> trainingTable;

    @FXML
    private TextField trainingDate;

    @FXML
    private TextField trainingTime;

    @FXML
    private TextField team;

    @FXML
    private TextField stadium;

    @FXML
    private TextField mainFocus;

    @FXML
    private TextArea pointers;

    @FXML
    private TableView<Drill> warmup;

    @FXML
    private TableView<Drill> together;

    @FXML
    private TableView<Drill> stations;

    @FXML
    private TableView<Drill> backup;

    @FXML
    private ImageView drillImage;

    @FXML
    private TextField drillName;
}
