package hockeycoach.PresentationModels;

import hockeycoach.DB.DBEditor;
import hockeycoach.DB.DBLoader.DBLoader;
import hockeycoach.DB.DBLoader.DBPlayerLoader;
import hockeycoach.supportClasses.ImageChooser;
import hockeycoach.mainClasses.Player;
import hockeycoach.supportClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import java.util.List;

import static hockeycoach.AppStarter.PHOTOS;

public class PlayerPresentationModel extends PresentationModel {
    MouseEvent event;
    Player selectedPlayer;
    Team selectedTeam;

    TableView<Player> teamPlayers;
    TableView<Team> playerTeams;
    ImageView playerPhoto;
    TextField playerName;
    TextField team;
    TextField street;
    TextField zipCity;
    TextField country;
    TextField phone;
    TextField email;
    TextField jersey;
    TextField positions;
    TextArea strengths;
    TextArea weaknesses;
    TextArea notes;
    TextField role;
    TextField aLicence;
    TextField bLicence;
    TextField stick;
    Button saveButton;
    Button editButton;
    Button cancelButton;
    Button deleteButton;

    public void initializeControls(Pane root) {
        teamPlayers = (TableView) root.lookup("#teamPlayers");
        playerTeams = (TableView) root.lookup("#playerTeams");
        playerPhoto = (ImageView) root.lookup("#playerPhoto");
        playerName = (TextField) root.lookup("#playerName");
        team = (TextField) root.lookup("#team");
        street = (TextField) root.lookup("#street");
        zipCity = (TextField) root.lookup("#zipCity");
        country = (TextField) root.lookup("#country");
        phone = (TextField) root.lookup("#phone");
        email = (TextField) root.lookup("#email");
        jersey = (TextField) root.lookup("#jersey");
        positions = (TextField) root.lookup("#positions");
        strengths = (TextArea) root.lookup("#strengths");
        weaknesses = (TextArea) root.lookup("#weaknesses");
        notes = (TextArea) root.lookup("#notes");
        role = (TextField) root.lookup("#role");
        aLicence = (TextField) root.lookup("#aLicence");
        bLicence = (TextField) root.lookup("#bLicence");
        stick = (TextField) root.lookup("#stick");
        saveButton = (Button) root.lookup("#saveButton");
        editButton = (Button) root.lookup("#editButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        deleteButton = (Button) root.lookup("#deleteButton");


        selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
        List<Player> playerList = dbPlayerLoader.getTeamPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.playerID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());

        if (!playerList.isEmpty()) {
            teamPlayers.getItems().clear();
            teamPlayers.getItems().addAll(playerList);
        }

        setupEventListeners();

        team.setText(selectedTeam.getName());

        disableControls(true);
    }

    public void setupEventListeners() {
        teamPlayers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedPlayer, newSelectedPlayer) -> {
            if (newSelectedPlayer != null) {
                try {
                    File imageFile = new File(newSelectedPlayer.getPhotoPath());

                    if (imageFile.exists()) {
                        playerPhoto.setImage(new Image(imageFile.toURI().toString()));
                    } else {
                        playerPhoto.setImage(null);
                    }
                } catch (NullPointerException e) {
                    playerPhoto.setImage(null);
                }
                selectedPlayer = newSelectedPlayer;
                playerName.setText(newSelectedPlayer.getFirstName() + " " + newSelectedPlayer.getLastName());
                street.setText(newSelectedPlayer.getStreet());
                zipCity.setText(String.valueOf(newSelectedPlayer.getZip()) + " " + newSelectedPlayer.getCity());
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

                DBLoader dbLoader = new DBLoader();
                List<Team> teamsForPlayer = dbLoader.getTeamsForPlayer("SELECT t.* FROM team t INNER JOIN playerXteam tx ON t.teamID = tx.teamID WHERE tx.playerID = '" + newSelectedPlayer.getPlayerID() + "'");
                if (!teamsForPlayer.isEmpty()) {
                    ObservableList<Team> teamList = FXCollections.observableArrayList(teamsForPlayer);
                    playerTeams.setItems(teamList);
                }
            }
        });

        editButton.setOnAction(event -> {
            if (teamPlayers.getSelectionModel().getSelectedItem() != null) {
                disableControls(false);
            }
        });

        saveButton.setOnAction(event -> {
            Player player = getPlayerData();
            player.setPhotoPath(savePlayerPhoto());
            DBEditor dbEditor = new DBEditor();
            dbEditor.editPlayer(player);
            dbEditor.editJerseyAndRole(player, selectedTeam);
        });
    }

    private void disableControls(boolean disabled) {
        playerTeams.setDisable(disabled);
        playerPhoto.setDisable(disabled);
        playerName.setDisable(disabled);
        team.setDisable(disabled);
        street.setDisable(disabled);
        zipCity.setDisable(disabled);
        country.setDisable(disabled);
        phone.setDisable(disabled);
        email.setDisable(disabled);
        jersey.setDisable(disabled);
        positions.setDisable(disabled);
        strengths.setDisable(disabled);
        weaknesses.setDisable(disabled);
        notes.setDisable(disabled);
        role.setDisable(disabled);
        aLicence.setDisable(disabled);
        bLicence.setDisable(disabled);
        stick.setDisable(disabled);
        saveButton.setDisable(disabled);
        cancelButton.setDisable(disabled);
        deleteButton.setDisable(disabled);
    }

    private Player getPlayerData(){
        Player player = new Player();

        player.setPlayerID(selectedPlayer.getPlayerID());

        String[] pn = playerName.getText().split("\\s+");
        player.setFirstName(pn[0]);
        player.setLastName(pn[1]);

        player.setStreet(street.getText());

        String[] zc = zipCity.getText().split("\\s+");
        player.setZip(Integer.parseInt(zc[0]));
        player.setCity(zc[1]);

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

    private void handleImageClick() {
        ImageChooser imageChooser = new ImageChooser();
        Image image = imageChooser.chooseImage(event);
        playerPhoto.setImage(image);
    }

    private String savePlayerPhoto() {
        Image selectedImage = playerPhoto.getImage();
        if (selectedImage != null) {
            String playerPhotoText = playerName.getText() + "_Photo";

            String imageFormat = selectedImage.getUrl().substring(selectedImage.getUrl().lastIndexOf(".") + 1).toLowerCase();
            if (!imageFormat.equals("jpg") && !imageFormat.equals("jpeg") && !imageFormat.equals("png")) {
                imageFormat = "jpg";
            }

            String destinationFileName = playerPhotoText + "." + imageFormat;
            String destinationDirectory = PHOTOS;

            try {
                URL imageUrl = new URL(selectedImage.getUrl());
                String decodedImageUrl = decodeUrl(imageUrl.getPath()); // Decode the URL
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
}
