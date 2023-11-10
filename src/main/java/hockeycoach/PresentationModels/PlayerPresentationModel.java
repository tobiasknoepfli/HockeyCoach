package hockeycoach.PresentationModels;

import hockeycoach.DB.DBEditor;
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
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static hockeycoach.AppStarter.*;

public class PlayerPresentationModel extends PresentationModel {
    MouseEvent event;
    ImageChooser imageChooser = new ImageChooser();
    Player selectedPlayer;
    Team selectedTeam;
    ButtonControls buttonControls = new ButtonControls();
    TextFieldAction textFieldAction = new TextFieldAction();
    Stack<TextFieldAction> textFieldActions = new Stack<>();

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
        DBLoader dbLoader = new DBLoader();
        DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
        List<Player> playerList = dbPlayerLoader.getTeamPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.ID = px.playerID WHERE px.ID LIKE '" + selectedTeam.getID() + "'", selectedTeam.getID());

        TextField[] textFields = {playerFirstName,playerLastName, team, street, zip, city, country,
                phone, email, jersey, positions, role,
                aLicence, bLicence, stick};
        Arrays.stream(textFields).forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        if (!playerList.isEmpty()) {
            teamPlayers.getItems().clear();
            teamPlayers.getItems().addAll(playerList);
        }

        team.setText(selectedTeam.getName());
        getDBEntries(root);
        setupButtons(root);
        setupEventListeners(root);
    }

    @Override
    public void getDBEntries(Pane root) {

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

                playerBirthday.valueProperty().addListener((observable, oldValue, newValue) -> {
                    playerAge.setText(calculatePlayerAge(newValue));
                });

                DBLoader dbLoader = new DBLoader();
                List<Team> teamsForPlayer = dbLoader.getTeamsForPlayer("SELECT t.* FROM team t INNER JOIN playerXteam tx ON t.teamID = tx.teamID WHERE tx.playerID = '" + newSelectedPlayer.getID() + "'");
                if (!teamsForPlayer.isEmpty()) {
                    ObservableList<Team> teamList = FXCollections.observableArrayList(teamsForPlayer);
                    playerTeams.setItems(teamList);
                }
            }
        });
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

    private String savePlayerPhoto() {
        Image selectedImage = playerPhoto.getImage();
        if (selectedImage != null) {
            String playerPhotoText = playerLastName.getText()+playerFirstName.getText() + "_Photo";

            String imageFormat = selectedImage.getUrl().substring(selectedImage.getUrl().lastIndexOf(".") + 1).toLowerCase();
            if (!imageFormat.equals("jpg") && !imageFormat.equals("jpeg") && !imageFormat.equals("png")) {
                imageFormat = "jpg";
            }

            String destinationFileName = playerPhotoText + "." + imageFormat;
            String destinationDirectory = PHOTOS;

            try {
                URL imageUrl = new URL(selectedImage.getUrl());
                String decodedImageUrl = decodeUrl(imageUrl.getPath());
                File selectedImageFile = new File(decodedImageUrl);
                Path destinationPath = Path.of(destinationDirectory, destinationFileName);

                Files.copy(selectedImageFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                return destinationPath.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String decodeUrl(String url) {
        try {
            return java.net.URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return url;
        }
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
