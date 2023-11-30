package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class PlayerController extends Controller {
    @FXML
    private Button saveButton, editButton, cancelButton, deleteButton, newPlayerButton, backButton;

    @FXML
    private ImageView playerPhoto;

    @FXML
    private TableView<Player> teamPlayers;

    @FXML
    private TableView<Team> playerTeams;

    @FXML
    private TextArea strengths, weaknesses, notes;

    @FXML
    private TextField playerFirstName, playerLastName, playerAge, team, street, zip, city, country, phone, email,
            jersey, positions;

    @FXML
    private DatePicker playerBirthday;

    @FXML
    private Slider puckSkills,defenceSkills,sensesSkills,skatingSkills,shotsSkills,physicalSkills;

    @FXML
    private Label puckSkillsLabel,defenceSkillsLabel,sensesSkillsLabel,skatingSkillsLabel,shotsSkillsLabel,physicalSkillsLabel,overallSkillsLabel;
}
