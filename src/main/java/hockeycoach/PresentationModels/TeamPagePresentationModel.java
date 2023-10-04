package hockeycoach.PresentationModels;

import hockeycoach.DB.DBEditor;
import hockeycoach.DB.DBLoader;
import hockeycoach.controllers.HeaderPageController;
import hockeycoach.mainClasses.ImageChooser;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class TeamPagePresentationModel extends PresentationModel {

    Team selectedTeam;
    MouseEvent event;

    ImageView teamLogo;
    TextField teamName;
    TextField stadiumName;
    TextField stadiumStreet;
    TextField stadiumZipCity;
    TextField stadiumCountry;
    TextField contactName;
    TextField contactPhone;
    TextField contactEmail;
    TextField website;
    TextField founded;
    TextField presidentName;
    TextField currentLeague;
    TextField headCoachName;
    TextField captainName;
    TableView<Player> teamPlayers;
    Button editPlayerButton;
    TextArea notes;
    Button saveButton;
    Button editButton;
    Button cancelButton;
    Button deleteButton;

    public void initializeControls(Pane root) {
        teamLogo = (ImageView) root.lookup("#teamLogo");
        teamName = (TextField) root.lookup("#teamName");
        stadiumName = (TextField) root.lookup("#stadiumName");
        stadiumStreet = (TextField) root.lookup("#stadiumStreet");
        stadiumZipCity = (TextField) root.lookup("#stadiumZipCity");
        stadiumCountry = (TextField) root.lookup("#stadiumCountry");
        contactName = (TextField) root.lookup("#contactName");
        contactPhone = (TextField) root.lookup("#contactPhone");
        contactEmail = (TextField) root.lookup("#contactEmail");
        website = (TextField) root.lookup("#website");
        founded = (TextField) root.lookup("#founded");
        presidentName = (TextField) root.lookup("#presidentName");
        currentLeague = (TextField) root.lookup("#currentLeague");
        headCoachName = (TextField) root.lookup("#headCoachName");
        captainName = (TextField) root.lookup("#captainName");
        teamPlayers = (TableView) root.lookup("#teamPlayers");
        notes = (TextArea) root.lookup("#notes");
        editPlayerButton = (Button) root.lookup("#editPlayerButton");
        saveButton = (Button) root.lookup("#saveButton");
        editButton = (Button) root.lookup("#editButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        deleteButton = (Button) root.lookup("#deleteButton");

        selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        List<Team> teamInfo = dbLoader.getTeam("SELECT * FROM team WHERE name LIKE '%" + selectedTeam.getName() + "%'");
        List<Player> playerList = dbLoader.getPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.playerID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());
        Team team = teamInfo.get(0);

        disableControls(true);

        try {
            if (!teamInfo.isEmpty()) {
                File imageFile = new File(team.getLogo());

                if (imageFile.exists()) {
                    teamLogo.setImage(new Image(imageFile.toURI().toString()));
                } else {
                    teamLogo.setImage(null);
                }
            }
        } catch (NullPointerException e) {
            teamLogo.setImage(null);
        }

        teamName.setText(team.getName());
        stadiumName.setText(team.getStadium());
        stadiumStreet.setText(team.getStreet());
        stadiumZipCity.setText(team.getZip() + " " + team.getCity());
        stadiumCountry.setText(team.getCountry());
        contactName.setText(team.getContactFirstName() + " " + team.getContactLastName());
        contactPhone.setText(team.getContactPhone());
        contactEmail.setText(team.getContactEmail());
        website.setText(team.getWebsite());
        founded.setText(String.valueOf(team.getFounded()));
        presidentName.setText(team.getPresidentFirstName() + " " + team.getPresidentLastName());
        currentLeague.setText(team.getLeague());
        headCoachName.setText(team.getHeadCoachFirstName() + " " + team.getHeadCoachLastName());
        captainName.setText(team.getCaptainFirstName() + " " + team.getCaptainLastName());
        notes.setText(team.getNotes());

        if (!playerList.isEmpty()) {
            teamPlayers.getItems().clear();
            teamPlayers.getItems().addAll(playerList);
        }
        setupEventListeners();

        teamLogo.setOnMouseClicked(event -> handleImageClick());
    }

    private void setupEventListeners() {
        editPlayerButton.setOnAction(event -> {
            HeaderPageController headerPageController = new HeaderPageController();
                PlayerToTeamPresentationModel pm = new PlayerToTeamPresentationModel();
                headerPageController.loadStages("TeamplayerEditor","/hockeycoach/player-to-team.fxml", pm);
        });

        editButton.setOnAction(event -> {
            disableControls(false);
            editButton.setDisable(true);
        });

        saveButton.setOnAction(event -> {
            Team team = getTeamData();
            team.setLogo(saveTeamLogo());
            DBEditor dbEditor = new DBEditor();
            dbEditor.editTeam(team);
        });
    }

    private void disableControls(boolean disabled) {
        teamName.setDisable(disabled);
        teamLogo.setDisable(disabled);
        stadiumName.setDisable(disabled);
        stadiumStreet.setDisable(disabled);
        stadiumZipCity.setDisable(disabled);
        stadiumCountry.setDisable(disabled);
        contactName.setDisable(disabled);
        contactEmail.setDisable(disabled);
        contactPhone.setDisable(disabled);
        website.setDisable(disabled);
        founded.setDisable(disabled);
        presidentName.setDisable(disabled);
        currentLeague.setDisable(disabled);
        headCoachName.setDisable(disabled);
        captainName.setDisable(disabled);
        notes.setDisable(disabled);
        saveButton.setDisable(disabled);
        cancelButton.setDisable(disabled);
        deleteButton.setDisable(disabled);
        teamPlayers.setDisable(disabled);
    }

    private Team getTeamData() {
        Team team = new Team();
        team.setTeamID(selectedTeam.getTeamID());
        team.setName(teamName.getText());
        team.setStadium(stadiumName.getText());
        team.setStreet(stadiumStreet.getText());

        String[] zc = stadiumZipCity.getText().split("\\s+");
        team.setZip(Integer.parseInt(zc[0]));
        team.setCity(zc[1]);

        team.setCountry(stadiumCountry.getText());

        String[] cn = contactName.getText().split("\\s+");
        team.setContactFirstName(cn[0]);
        team.setContactLastName(cn[1]);

        team.setContactPhone(contactPhone.getText());
        team.setContactEmail(contactEmail.getText());
        team.setWebsite(website.getText());
        team.setFounded(Integer.parseInt(founded.getText()));

        String[] pn = presidentName.getText().split("\\s+");
        team.setPresidentFirstName(pn[0]);
        team.setPresidentLastName(pn[1]);

        team.setLeague(currentLeague.getText());

        String[] hc = headCoachName.getText().split("\\s+");
        team.setHeadCoachFirstName(hc[0]);
        team.setHeadCoachLastName(hc[1]);

        String[] can = captainName.getText().split("\\s+");
        team.setCaptainFirstName(can[0]);
        team.setCaptainLastName(can[1]);

        team.setNotes(notes.getText());

        return team;
    }

    private void handleImageClick() {
        ImageChooser imageChooser = new ImageChooser();
        Image image = imageChooser.chooseImage(event);
        teamLogo.setImage(image);
    }

    private String saveTeamLogo() {
        Image selectedImage = teamLogo.getImage();
        if (selectedImage != null) {
            String imageNameText = teamName.getText().trim();
            if (imageNameText.isEmpty()) {
                imageNameText = teamName.getText() + "_Logo";
            }
            String imageFormat = selectedImage.getUrl().substring(selectedImage.getUrl().lastIndexOf(".") + 1).toLowerCase();
            if (!imageFormat.equals("jpg") && !imageFormat.equals("jpeg") && !imageFormat.equals("png")) {
                imageFormat = "jpg";
            }

            String destinationFileName = imageNameText + "." + imageFormat;
            String destinationDirectory = "src/main/java/hockeycoach/files/logos";

            try {
                URL imageUrl = new URL(selectedImage.getUrl());
                String decodedImageUrl = java.net.URLDecoder.decode(imageUrl.getFile(), "UTF-8");
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
}
