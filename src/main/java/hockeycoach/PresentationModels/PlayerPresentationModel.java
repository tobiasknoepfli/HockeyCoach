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

    TableView<Player> teamPlayers;
    TableView<Team> playerTeams;
    ImageView playerPhoto;
    DatePicker playerBirthday;
    TextField playerFirstName, playerLastName, playerAge, team, street, zip, city, country,
            phone, email, jersey, positions, role,
            aLicence, bLicence, stick;
    TextArea strengths, weaknesses, notes;

    Button saveButton, editButton, cancelButton, deleteButton, newPlayerButton, backButton;

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
    }

    @Override
    public void disableFields(Boolean disabled) {
        textFieldList.stream().forEach(t->t.setEditable(disabled));
        textAreaList.stream().forEach(t->t.setEditable(disabled));
        playerBirthday.setEditable(disabled);
        playerPhoto.setDisable(!disabled);
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
    }
}
