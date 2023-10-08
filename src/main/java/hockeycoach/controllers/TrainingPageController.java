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
    private ImageView drillImage;

    @FXML
    private TableView<Drill> warmup, together, stations, backup;

    @FXML
    private TableView<Training> trainingTable;

    @FXML
    private TextArea pointers;

    @FXML
    private TextField trainingDate, trainingTime,
            team,
            stadium,
            mainFocus,
            drillName,
            jersey1, jersey2, jersey3, jersey4, jersey5, jersey6,
            gk1, gk2, gk3, gk4, gk5, gk6,
            dl1, dl2, dl3, dl4, dl5, dl6,
            dr1, dr2, dr3, dr4, dr5, dr6,
            c1, c2, c3, c4, c5, c6,
            fl1, fl2, fl3, fl4, fl5, fl6,
            fr1, fr2, fr3, fr4, fr5, fr6;
}
