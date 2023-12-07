package hockeycoach.PresentationModels;

import hockeycoach.DB.DBEditor;
import hockeycoach.DB.DBLoader.DBImageLoader;
import hockeycoach.DB.DBLoader.DBLoader;
import hockeycoach.DB.DBLoader.DBPlayerLoader;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.ImageChooser;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
import hockeycoach.supportClasses.TextFieldAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static hockeycoach.AppStarter.*;

public class PlayerPresentationModel extends PresentationModel {
    MouseEvent event;
    Boolean disabled = false;

    ImageChooser imageChooser = new ImageChooser();
    ButtonControls buttonControls = new ButtonControls();
    TextFieldAction textFieldAction = new TextFieldAction();

    Player selectedPlayer;
    Team selectedTeam;

    Stack<TextFieldAction> textFieldActions = new Stack<>();

    DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
    DBImageLoader dbImageLoader =new DBImageLoader();

    List<Player> playerList = new ArrayList<>();
    List<TextField> textFieldList = new ArrayList<>();
    List<TextArea> textAreaList = new ArrayList<>();
    List<Slider> sliderList = new ArrayList<>();

    TableView<Player> teamPlayers;
    TableView<Team> playerTeams;
    ImageView playerPhoto;
    DatePicker playerBirthday;
    TextField playerFirstName, playerLastName, playerAge, team, street, zip, city, country,
            phone, email, jersey, positions, role,
            aLicence, bLicence, stick;
    TextArea strengths, weaknesses, notes;

    Button saveButton, editButton, cancelButton, deleteButton, newPlayerButton, backButton;

    Slider puckSkills,defenceSkills,sensesSkills,skatingSkills,shotsSkills,physicalSkills;

    Label puckSkillsLabel,defenceSkillsLabel,sensesSkillsLabel,skatingSkillsLabel,shotsSkillsLabel,physicalSkillsLabel,overallSkillsLabel;

    @Override
    public void initializeControls(Pane root) {
        importFields(root);

        selectedTeam = globalTeam;

        getDBEntries(root);

        setupFieldLists(root);

        textFieldList.stream().forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        teamPlayers.getItems().clear();
        teamPlayers.getItems().addAll(playerList);

        team.setText(selectedTeam.getName());

        setupButtons(root);
        setupEventListeners(root);
        disableFields(disabled);
    }

    @Override
    public void setupFieldLists(Pane root) {
        TextField[] textFields = {playerFirstName, playerLastName, team, street, zip, city, country,playerAge,
                phone, email, jersey, positions, role,
                aLicence, bLicence, stick};
        textFieldList = Arrays.stream(textFields).toList();

        TextArea[] textAreas = {strengths,weaknesses,notes};
        textAreaList = Arrays.stream(textAreas).toList();

        Slider[] sliders ={puckSkills,defenceSkills,sensesSkills,skatingSkills,shotsSkills,physicalSkills};
        sliderList = Arrays.stream(sliders).toList();

    }

    @Override
    public void getDBEntries(Pane root) {
        playerList = dbPlayerLoader.getTeamPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.ID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getID() + "'", selectedTeam.getID());
    }

    @Override
    public void fillFields(Pane root) {

    }

    @Override
    public void setupButtons(Pane root) {
        saveButton.setOnAction(event -> {
            Player player = getPlayerData();
            DBEditor dbEditor = new DBEditor();
            dbEditor.editPlayer(player);
            dbEditor.editJerseyAndRole(player, selectedTeam);
            disableFields(disabled);
        });

        editButton.setOnAction(event->{
            if(teamPlayers.getSelectionModel().getSelectedItem() != null){
                disableFields(!disabled);
            }

        });

        newPlayerButton.setOnAction(event -> {
            buttonControls.openNewPlayerHide(root, PLAYER);
        });

        backButton.setOnAction(event -> {
            textFieldAction.undoLastAction(textFieldActions);
        });

        playerPhoto.setOnMouseClicked(mouseEvent -> {
            playerPhoto.setImage(imageChooser.chooseImage(event));
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
        teamPlayers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedPlayer, newSelectedPlayer) -> {
            if (newSelectedPlayer != null) {
                newSelectedPlayer.setPicture(dbImageLoader.getPicture("SELECT i.* FROM image i INNER JOIN player p ON p.photoID = i.ID WHERE p.ID =" +   newSelectedPlayer.getID()));

                selectedPlayer = newSelectedPlayer;
                playerFirstName.setText(newSelectedPlayer.getFirstName());
                playerLastName.setText(newSelectedPlayer.getLastName());
                playerBirthday.setValue(newSelectedPlayer.getBirthday());
                playerAge.setText(calculatePlayerAge(newSelectedPlayer.getBirthday()));
                street.setText(newSelectedPlayer.getStreet());
                zip.setText(String.valueOf(newSelectedPlayer.getZip()));
                city.setText(newSelectedPlayer.getCity());
                country.setText(newSelectedPlayer.getCountry());
                phone.setText(newSelectedPlayer.getPhone());
                email.setText(newSelectedPlayer.geteMail());
                jersey.setText(String.valueOf(newSelectedPlayer.getJersey()));
                positions.setText(newSelectedPlayer.getPositions());
                strengths.setText(newSelectedPlayer.getStrengths());
                weaknesses.setText(newSelectedPlayer.getWeaknesses());
                notes.setText(newSelectedPlayer.getNotes());
                role.setText(newSelectedPlayer.getRole());
                aLicence.setText(newSelectedPlayer.getaLicence());
                bLicence.setText(newSelectedPlayer.getbLicence());
                stick.setText(newSelectedPlayer.getStick());
                playerPhoto.setImage(newSelectedPlayer.getPicture().getImage());

                puckSkills.setValue(newSelectedPlayer.getRatingPuckSkills());
                defenceSkills.setValue(newSelectedPlayer.getRatingDefence());
                sensesSkills.setValue(newSelectedPlayer.getRatingSenses());
                skatingSkills.setValue(newSelectedPlayer.getRatingSkating());
                shotsSkills.setValue(newSelectedPlayer.getRatingShots());
                physicalSkills.setValue(newSelectedPlayer.getRatingPhysical());
                overallSkillsLabel.setText(String.valueOf(newSelectedPlayer.getRatingOverall()));

                playerBirthday.valueProperty().addListener((observable, oldValue, newValue) -> {
                    playerAge.setText(calculatePlayerAge(newValue));
                });

                DBLoader dbLoader = new DBLoader();
                List<Team> teamsForPlayer = dbLoader.getTeamsForPlayer("SELECT t.* FROM team t INNER JOIN playerXteam tx ON t.ID = tx.teamID WHERE tx.playerID = '" + newSelectedPlayer.getID() + "'");
                if (!teamsForPlayer.isEmpty()) {
                    ObservableList<Team> teamList = FXCollections.observableArrayList(teamsForPlayer);
                    playerTeams.setItems(teamList);
                }
            }
        });
        puckSkills.valueProperty().addListener((obs,oldValue,newValue) ->{
            puckSkillsLabel.setText(String.valueOf(Math.round(puckSkills.getValue())));
            overallSkillsLabel.setText(String.valueOf(Math.round(calculateOverallValue())));
        });
        defenceSkills.valueProperty().addListener((obs,oldValue,newValue) ->{
            defenceSkillsLabel.setText(String.valueOf(Math.round(defenceSkills.getValue())));
            overallSkillsLabel.setText(String.valueOf(Math.round(calculateOverallValue())));
        });
        sensesSkills.valueProperty().addListener((obs,oldValue,newValue) ->{
            sensesSkillsLabel.setText(String.valueOf(Math.round(sensesSkills.getValue())));
            overallSkillsLabel.setText(String.valueOf(Math.round(calculateOverallValue())));
        });
        skatingSkills.valueProperty().addListener((obs,oldValue,newValue) ->{
            skatingSkillsLabel.setText(String.valueOf(Math.round(skatingSkills.getValue())));
            overallSkillsLabel.setText(String.valueOf(Math.round(calculateOverallValue())));
        });
        shotsSkills.valueProperty().addListener((obs,oldValue,newValue) ->{
            shotsSkillsLabel.setText(String.valueOf(Math.round(shotsSkills.getValue())));
            overallSkillsLabel.setText(String.valueOf(Math.round(calculateOverallValue())));
        });
        physicalSkills.valueProperty().addListener((obs,oldValue,newValue) ->{
            physicalSkillsLabel.setText(String.valueOf(Math.round(physicalSkills.getValue())));
            overallSkillsLabel.setText(String.valueOf(Math.round(calculateOverallValue())));
        });
    }

    public double calculateOverallValue(){
        return (int) sliderList.stream().mapToDouble(s->s.getValue()).average().getAsDouble();
    }

    @Override
    public void disableFields(Boolean disabled) {
        textFieldList.stream().forEach(t->t.setEditable(disabled));
        textAreaList.stream().forEach(t->t.setEditable(disabled));
        playerBirthday.setEditable(disabled);
        playerPhoto.setDisable(!disabled);
        editButton.setDisable(disabled);
        saveButton.setDisable(!disabled);
        cancelButton.setDisable(!disabled);
    }

    private Player getPlayerData() {
        Player player = new Player();

        player.setID(selectedPlayer.getID());
        player.setFirstName(playerFirstName.getText());
        player.setLastName(playerLastName.getText());
        player.setStreet(street.getText());
        player.setZip(Integer.parseInt(zip.getText()));
        player.setCity(city.getText());
        player.setCountry(country.getText());
        player.setPhone(phone.getText());
        player.seteMail(email.getText());
        player.setJersey(Integer.parseInt(jersey.getText()));
        player.setPositions(positions.getText());
        player.setStrengths(strengths.getText());
        player.setWeaknesses(weaknesses.getText());
        player.setNotes(notes.getText());
        player.setRole(role.getText());
        player.setaLicence(aLicence.getText());
        player.setbLicence(bLicence.getText());
        player.setStick(stick.getText());

        player.setRatingPuckSkills((int)  puckSkills.getValue());
        player.setRatingDefence((int) defenceSkills.getValue());
        player.setRatingSenses((int) sensesSkills.getValue());
        player.setRatingSkating((int) skatingSkills.getValue());
        player.setRatingSkating((int) shotsSkills.getValue());
        player.setRatingPhysical((int) physicalSkills.getValue());
        player.setRatingOverall(Integer.parseInt(overallSkillsLabel.getText()));

        return player;
    }

    public String calculatePlayerAge(LocalDate localDate) {
        if (localDate != null) {
            LocalDate today = LocalDate.now();
            int age = Period.between(playerBirthday.getValue(), today).getYears();
            return String.valueOf(age);
        }
        return null;
    }

    @Override
    public void importFields(Pane root) {
        teamPlayers = (TableView) root.lookup("#teamPlayers");
        playerTeams = (TableView) root.lookup("#playerTeams");

        playerPhoto = (ImageView) root.lookup("#playerPhoto");

        playerBirthday = (DatePicker) root.lookup("#playerBirthday");

        playerFirstName = (TextField) root.lookup("#playerFirstName");
        playerLastName = (TextField) root.lookup("#playerLastName");
        playerAge = (TextField) root.lookup("#playerAge");
        team = (TextField) root.lookup("#team");
        street = (TextField) root.lookup("#street");
        zip = (TextField) root.lookup("#zip");
        city = (TextField) root.lookup("#city");
        country = (TextField) root.lookup("#country");
        phone = (TextField) root.lookup("#phone");
        email = (TextField) root.lookup("#email");
        jersey = (TextField) root.lookup("#jersey");
        positions = (TextField) root.lookup("#positions");
        role = (TextField) root.lookup("#role");
        aLicence = (TextField) root.lookup("#aLicence");
        bLicence = (TextField) root.lookup("#bLicence");
        stick = (TextField) root.lookup("#stick");

        strengths = (TextArea) root.lookup("#strengths");
        weaknesses = (TextArea) root.lookup("#weaknesses");
        notes = (TextArea) root.lookup("#notes");

        saveButton = (Button) root.lookup("#saveButton");
        editButton = (Button) root.lookup("#editButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        deleteButton = (Button) root.lookup("#deleteButton");
        newPlayerButton = (Button) root.lookup("#newPlayerButton");
        backButton = (Button) root.lookup("#backButton");

        puckSkills =(Slider) root.lookup("#puckSkills");
        defenceSkills =(Slider) root.lookup("#defenceSkills");
        sensesSkills =(Slider) root.lookup("#sensesSkills");
        skatingSkills =(Slider) root.lookup("#skatingSkills");
        shotsSkills =(Slider) root.lookup("#shotsSkills");
        physicalSkills =(Slider) root.lookup("#physicalSkills");

        puckSkillsLabel = (Label) root.lookup("#puckSkillsLabel");
        defenceSkillsLabel = (Label) root.lookup("#defenceSkillsLabel");
        sensesSkillsLabel = (Label) root.lookup("#sensesSkillsLabel");
        skatingSkillsLabel = (Label) root.lookup("#skatingSkillsLabel");
        shotsSkillsLabel = (Label) root.lookup("#shotsSkillsLabel");
        physicalSkillsLabel = (Label) root.lookup("#physicalSkillsLabel");
        overallSkillsLabel = (Label) root.lookup("#overallSkillsLabel");
    }
}
