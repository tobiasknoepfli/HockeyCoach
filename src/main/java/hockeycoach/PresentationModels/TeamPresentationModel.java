package hockeycoach.PresentationModels;

import hockeycoach.DB.DBEditor;
import hockeycoach.DB.DBLoader.DBLoader;
import hockeycoach.DB.DBLoader.DBPlayerLoader;
import hockeycoach.DB.DBLoader.DBTeamLoader;
import hockeycoach.controllers.HeaderController;
import hockeycoach.mainClasses.Stadium;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.ImageChooser;
import hockeycoach.mainClasses.Player;
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
    Stadium stadium = new Stadium();

    Team selectedTeam;
    ImageChooser imageChooser = new ImageChooser();
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

        selectedTeam = globalTeam;
        DBLoader dbLoader = new DBLoader();
        DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
        DBTeamLoader dbTeamLoader = new DBTeamLoader();
        Team team = dbTeamLoader.getTeam("SELECT * FROM team WHERE ID =" + selectedTeam.getID());
        List<Player> playerList = dbPlayerLoader.getTeamPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.ID = px.playerID WHERE px.ID LIKE '" + selectedTeam.getID() + "'", selectedTeam.getID());

        TextField[] textFields = {teamName,
                stadiumName, stadiumStreet, stadiumZip, stadiumCity, stadiumCountry,
                contactFirstName, contactLastName, contactPhone, contactEmail,
                website, founded, currentLeague,
                presidentFirstName, presidentLastName, headCoachFirstName, headCoachLastName};
        Arrays.stream(textFields).forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        teamName.setText(team.getName());
        stadiumName.setText(team.getStadium().getStadiumName());
        stadiumStreet.setText(team.getStadium().getStadiumAddress());
        stadiumZip.setText(String.valueOf(team.getStadium().getStadiumZip()));
        stadiumCity.setText(team.getStadium().getStadiumCity());
        stadiumCountry.setText(team.getStadium().getStadiumCountry());
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

        teamLogo.setOnMouseClicked(mouseEvent -> {
            teamLogo.setImage(imageChooser.chooseImage(event));
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
        editPlayerButton.setOnAction(event -> {
            HeaderController headerController = new HeaderController();
            PlayerToTeamPresentationModel pm = new PlayerToTeamPresentationModel();
            headerController.loadStages(PLAYER_TO_TEAM, PLAYER_TO_TEAM_FXML, pm);
        });

        saveButton.setOnAction(event -> {
            Team team = getTeamData();
//            team.setLogo(saveTeamLogo());
            DBEditor dbEditor = new DBEditor();
            dbEditor.editTeam(team);
        });

        newTeamButton.setOnAction(event -> {
            buttonControls.openNewTeamHide(root, TEAM);
        });

        stadiumName.setOnMousePressed(event -> {
            lastVisitedPM = TeamPresentationModel.this;
            lastVisitedFXML = TEAM_FXML;
            lastVisitedNodeName = TEAM;
            buttonControls.openStadiumHide(root, TEAM);
        });
    }

    public void fillStadium(Stadium stadium) {
        stadiumName.setText(stadium.getStadiumName());
        stadiumStreet.setText(stadium.getStadiumAddress());
        stadiumZip.setText(String.valueOf(stadium.getStadiumZip()));
        stadiumCity.setText(stadium.getStadiumCity());
        stadiumCountry.setText(stadium.getStadiumCountry());
    }


    private Team getTeamData() {
        Team team = new Team();
        team.setID(selectedTeam.getID());
        team.setName(teamName.getText());

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
