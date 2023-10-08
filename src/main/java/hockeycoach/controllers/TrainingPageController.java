package hockeycoach.controllers;

import hockeycoach.mainClasses.Drill;
import hockeycoach.mainClasses.Training;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class TrainingPageController extends Controller {

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

    @FXML
    private TextField jersey1, jersey2, jersey3, jersey4, jersey5, jersey6;

    @FXML
    private TextField gk1, gk2, gk3, gk4, gk5, gk6;

    @FXML
    private TextField dl1, dl2, dl3, dl4, dl5, dl6;

    @FXML
    private TextField dr1, dr2, dr3, dr4, dr5, dr6;

    @FXML
    private TextField c1, c2, c3, c4, c5, c6;

    @FXML
    private TextField fl1, fl2, fl3, fl4, fl5, fl6;

    @FXML
    private TextField fr1, fr2, fr3, fr4, fr5, fr6;
}
