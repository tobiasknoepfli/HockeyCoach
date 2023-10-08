package hockeycoach.controllers;

import hockeycoach.mainClasses.Drill;
import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class TrainingEditorPageController extends Controller{
    @FXML
    private TableView<Player> playerList;

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

    @FXML
    private Button resetFilters;

    @FXML
    private Tab warmupTab;

    @FXML
    private Tab togetherTab;

    @FXML
    private Tab stationsTab;

    @FXML
    private Tab backupTab;

    @FXML
    private TabPane tablePane;

    @FXML
    private Button warmupButton;

    @FXML
    private Button togetherButton;

    @FXML
    private Button stationsButton;

    @FXML
    private Button backupButton;

    @FXML
    private TextField puckPosition;

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

    @FXML
    private Label lgGK1, lgGK2, lgGK3, lgGK4;

    @FXML
    private Label lgRD1, lgRD2, lgRD3, lgRD4, lgLD1, lgLD2, lgLD3, lgLD4;

    @FXML
    private Label lgRF1, lgRF2, lgRF3, lgRF4, lgC1, lgC2, lgC3, lgC4, lgLF1, lgLF2, lgLF3, lgLF4;

    @FXML
    private Label ngGK1, ngGK2, ngGK3, ngGK4;

    @FXML
    private Label ngRD1, ngRD2, ngRD3, ngRD4, ngLD1, ngLD2, ngLD3, ngLD4;

    @FXML
    private Label ngRF1, ngRF2, ngRF3, ngRF4, ngC1, ngC2, ngC3, ngC4, ngLF1, ngLF2, ngLF3, ngLF4;
}
