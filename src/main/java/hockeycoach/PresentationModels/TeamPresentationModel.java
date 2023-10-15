package hockeycoach.PresentationModels;

import hockeycoach.DB.DBEditor;
import hockeycoach.DB.DBLoader.DBLoader;
import hockeycoach.DB.DBLoader.DBPlayerLoader;
import hockeycoach.DB.DBLoader.DBTeamLoader;
import hockeycoach.controllers.HeaderController;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.ImageChooser;
import hockeycoach.mainClasses.Player;
import hockeycoach.supportClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import hockeycoach.supportClasses.TextFieldAction;
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
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static hockeycoach.AppStarter.*;

public class TeamPresentationModel extends PresentationModel {
    ButtonControls buttonControls = new ButtonControls();
    TextFieldAction textFieldAction = new TextFieldAction();
    Stack<TextFieldAction> textFieldActions = new Stack<>();

    Team selectedTeam;
    MouseEvent event;

    ImageView teamLogo;
    TextField teamName,
            stadiumName, stadiumStreet, stadiumZip, stadiumCity, stadiumCountry,
            contactFirstName, contactLastName, contactPhone, contactEmail,
            website, founded, currentLeague,
            presidentFirstName, presidentLastName, headCoachFirstName, headCoachLastName;
    TableView<Player> teamPlayers;
    Button editPlayerButton, saveButton, editButton, cancelButton, deleteButton, newTeamButton, backButton;
    TextArea notes;

    @Override
    public void initializeControls(Pane root) {
        importFields(root);

        selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
        DBTeamLoader dbTeamLoader = new DBTeamLoader();
        Team team = dbTeamLoader.getTeam("SELECT * FROM team WHERE teamID =" + selectedTeam.getTeamID());
        List<Player> playerList = dbPlayerLoader.getTeamPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.playerID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());

        TextField[] textFields = {teamName,
                stadiumName, stadiumStreet, stadiumZip, stadiumCity, stadiumCountry,
                contactFirstName, contactLastName, contactPhone, contactEmail,
                website, founded, currentLeague,
                presidentFirstName, presidentLastName, headCoachFirstName, headCoachLastName};
        Arrays.stream(textFields).forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        disableControls(true);

        try {
            if (team != null) {
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
        stadiumZip.setText(String.valueOf(team.getZip()));
        stadiumCity.setText(team.getCity());
        stadiumCountry.setText(team.getCountry());
        contactFirstName.setText(team.getContactFirstName());
        contactLastName.setText(team.getContactLastName());
        contactPhone.setText(team.getContactPhone());
        contactEmail.setText(team.getContactEmail());
        website.setText(team.getWebsite());
        founded.setText(String.valueOf(team.getFounded()));
        presidentFirstName.setText(team.getPresidentFirstName());
        presidentLastName.setText(team.getPresidentLastName());
        currentLeague.setText(team.getLeague());
        headCoachFirstName.setText(team.getHeadCoachFirstName());
        headCoachLastName.setText(team.getHeadCoachLastName());
        notes.setText(team.getNotes());

        if (!playerList.isEmpty()) {
            teamPlayers.getItems().clear();
            teamPlayers.getItems().addAll(playerList);
        }
        setupEventListeners(root);
        getDBEntries(root);
        setupButtons(root);

        teamLogo.setOnMouseClicked(event -> handleImageClick());
    }

    @Override
    public void getDBEntries(Pane root) {

    }

    @Override
    public void setupButtons(Pane root) {
        backButton.setOnAction(event -> {
            textFieldAction.undoLastAction(textFieldActions);
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
        editPlayerButton.setOnAction(event -> {
            HeaderController headerController = new HeaderController();
            PlayerToTeamPresentationModel pm = new PlayerToTeamPresentationModel();
            headerController.loadStages(PLAYER_TO_TEAM, PLAYER_TO_TEAM_FXML, pm);
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

        newTeamButton.setOnAction(event -> {
            buttonControls.openNewTeam(root, TEAM);
        });
    }

    private void disableControls(boolean disabled) {
        teamName.setDisable(disabled);
        teamLogo.setDisable(disabled);
        stadiumName.setDisable(disabled);
        stadiumStreet.setDisable(disabled);
        stadiumZip.setDisable(disabled);
        stadiumCity.setDisable(disabled);
        stadiumCountry.setDisable(disabled);
        contactFirstName.setDisable(disabled);
        contactLastName.setDisable(disabled);
        contactEmail.setDisable(disabled);
        contactPhone.setDisable(disabled);
        website.setDisable(disabled);
        founded.setDisable(disabled);
        presidentFirstName.setDisable(disabled);
        presidentLastName.setDisable(disabled);
        currentLeague.setDisable(disabled);
        headCoachFirstName.setDisable(disabled);
        headCoachLastName.setDisable(disabled);
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
        team.setZip(Integer.parseInt(stadiumZip.getText()));
        team.setCity(stadiumCity.getText());
        team.setCountry(stadiumCountry.getText());
        team.setContactFirstName(contactFirstName.getText());
        team.setContactLastName(contactLastName.getText());
        team.setContactPhone(contactPhone.getText());
        team.setContactEmail(contactEmail.getText());
        team.setWebsite(website.getText());
        team.setFounded(Integer.parseInt(founded.getText()));
        team.setPresidentFirstName(presidentFirstName.getText());
        team.setPresidentLastName(presidentLastName.getText());
        team.setLeague(currentLeague.getText());
        team.setHeadCoachFirstName(headCoachFirstName.getText());
        team.setHeadCoachLastName(headCoachLastName.getText());
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
            String destinationDirectory = LOGOS;

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

    @Override
    public void importFields(Pane root) {
        teamLogo = (ImageView) root.lookup("#teamLogo");

        teamName = (TextField) root.lookup("#teamName");
        stadiumName = (TextField) root.lookup("#stadiumName");
        stadiumStreet = (TextField) root.lookup("#stadiumStreet");
        stadiumZip = (TextField) root.lookup("#stadiumZip");
        stadiumCity = (TextField) root.lookup("#stadiumCity");
        stadiumCountry = (TextField) root.lookup("#stadiumCountry");
        contactFirstName = (TextField) root.lookup("#contactFirstName");
        contactLastName = (TextField) root.lookup("#contactLastName");
        contactPhone = (TextField) root.lookup("#contactPhone");
        contactEmail = (TextField) root.lookup("#contactEmail");
        website = (TextField) root.lookup("#website");
        founded = (TextField) root.lookup("#founded");
        presidentFirstName = (TextField) root.lookup("#presidentFirstName");
        presidentLastName = (TextField) root.lookup("#presidentLastName");
        currentLeague = (TextField) root.lookup("#currentLeague");
        headCoachFirstName = (TextField) root.lookup("#headCoachFirstName");
        headCoachLastName = (TextField) root.lookup("#headCoachLastName");

        teamPlayers = (TableView) root.lookup("#teamPlayers");

        notes = (TextArea) root.lookup("#notes");

        editPlayerButton = (Button) root.lookup("#editPlayerButton");
        saveButton = (Button) root.lookup("#saveButton");
        editButton = (Button) root.lookup("#editButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        deleteButton = (Button) root.lookup("#deleteButton");
        newTeamButton = (Button) root.lookup("#newTeamButton");
        backButton = (Button) root.lookup("#backButton");
    }
}
