package hockeycoach.controllers;

import hockeycoach.mainClasses.Drills.Drill;
import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import jfxtras.scene.control.LocalTimeTextField;

public class TrainingEditorController extends Controller {
    @FXML
    private Button searchButton, resetFilters,
            warmupButton, togetherButton, stationsButton, backupButton,saveButton;

    @FXML
    private CheckBox drillStation;

    @FXML
    private ComboBox<Boolean> cbStation;

    @FXML
    private ComboBox<String> cbCategory, cbParticipation,
            cbTags, cbPuckPosition, cbDifficulty;

    @FXML
    private ImageView drillImage;

    @FXML
    private Label lgGK1, lgGK2, lgGK3, lgGK4,
            lgRD1, lgRD2, lgRD3, lgRD4, lgLD1, lgLD2, lgLD3, lgLD4,
            lgRF1, lgRF2, lgRF3, lgRF4, lgC1, lgC2, lgC3, lgC4, lgLF1, lgLF2, lgLF3, lgLF4,
            ngGK1, ngGK2, ngGK3, ngGK4,
            ngRD1, ngRD2, ngRD3, ngRD4, ngLD1, ngLD2, ngLD3, ngLD4,
            ngRF1, ngRF2, ngRF3, ngRF4, ngC1, ngC2, ngC3, ngC4, ngLF1, ngLF2, ngLF3, ngLF4;

    @FXML
    private Tab warmupTab, togetherTab, stationsTab, backupTab;

    @FXML
    private TableView<Drill> drillTable,
            warmup, together, stations, backup;

    @FXML
    private TableView<Player> playerList;

    @FXML
    private TableView<String> drillTags;

    @FXML
    private TabPane tablePane;

    @FXML
    private TableColumn<String,String> tagCol;

    @FXML
    private TextArea drillDescription,
            trainingPointers, drillPurpose;

    @FXML
    private DatePicker trainingDate;

    @FXML
    private LocalTimeTextField trainingTime;

    @FXML
    private TextField drillName, drillCategory, drillDifficulty, drillParticipation,
            searchBox,
            trainingStadium, trainingTeam, trainingMainFocus,
            puckPosition,
            jersey1, jersey2, jersey3, jersey4, jersey5, jersey6,
            gk1, gk2, gk3, gk4, gk5, gk6,
            dl1, dl2, dl3, dl4, dl5, dl6,
            dr1, dr2, dr3, dr4, dr5, dr6,
            c1, c2, c3, c4, c5, c6,
            fl1, fl2, fl3, fl4, fl5, fl6,
            fr1, fr2, fr3, fr4, fr5, fr6;
}
