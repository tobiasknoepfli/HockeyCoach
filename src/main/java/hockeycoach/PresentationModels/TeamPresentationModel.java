package hockeycoach.PresentationModels;

import hockeycoach.DB.DBEditor_Old;
import hockeycoach.DB.DBLoader.DBImageLoader;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.w3c.dom.events.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static hockeycoach.AppStarter.*;

public class TeamPresentationModel extends PresentationModel {
    ButtonControls buttonControls = new ButtonControls();
    TextFieldAction textFieldAction = new TextFieldAction();
    ImageChooser imageChooser = new ImageChooser();

    Boolean disabled = false;
    MouseEvent event;

    Stack<TextFieldAction> textFieldActions = new Stack<>();

    Team team = new Team();
    Team selectedTeam;

    List<Player> playerList = new ArrayList<>();
    List<TextField> textFieldList = new ArrayList<>();

    DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
    DBTeamLoader dbTeamLoader = new DBTeamLoader();
    DBImageLoader dbImageLoader = new DBImageLoader();

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
        setupFieldLists(root);

        selectedTeam = globalTeam;

        getDBEntries(root);
        fillFields(root);

        textFieldList.stream().forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        teamPlayers.getItems().clear();
        teamPlayers.getItems().addAll(playerList);

        setupEventListeners(root);

        setupButtons(root);

        disableFields(disabled);

    }

    @Override
    public void setupFieldLists(Pane root) {
        TextField[] textFields = {teamName,
                stadiumName, stadiumStreet, stadiumZip, stadiumCity, stadiumCountry,
                contactFirstName, contactLastName, contactPhone, contactEmail,
                website, founded, currentLeague,
                presidentFirstName, presidentLastName, headCoachFirstName, headCoachLastName};

        textFieldList = Arrays.stream(textFields).toList();
    }

    @Override
    public void fillFields(Pane root) {
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
        teamLogo.setImage(team.getLogo().getImage());
    }

    @Override
    public void getDBEntries(Pane root) {
        team = dbTeamLoader.getTeam("SELECT * FROM team WHERE ID =" + selectedTeam.getID());
        team.setLogo(dbImageLoader.getPicture("SELECT i.* FROM image i INNER JOIN team t ON t.logoID = i.ID WHERE t.ID =" + selectedTeam.getID()));
        playerList = dbPlayerLoader.getTeamPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.ID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getID() + "'", selectedTeam.getID());
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
            DBEditor_Old dbEditorOld = new DBEditor_Old();
            dbEditorOld.editTeam(team);
        });

        newTeamButton.setOnAction(event -> {
            buttonControls.openNewTeamHide(root, TEAM);
        });

        stadiumName.setOnMousePressed(event -> {
            if (stadiumName.isEditable()) {
                lastVisitedPM = TeamPresentationModel.this;
                lastVisitedFXML = TEAM_FXML;
                lastVisitedNodeName = TEAM;
                buttonControls.openStadiumHide(root, TEAM);
            }
        });

        teamLogo.setOnMouseClicked(mouseEvent -> {
            teamLogo.setImage(imageChooser.chooseImage(event));
        });
    }

    public void fillStadium(Stadium stadium) {
        stadiumName.setText(stadium.getStadiumName());
        stadiumStreet.setText(stadium.getStadiumAddress());
        stadiumZip.setText(String.valueOf(stadium.getStadiumZip()));
        stadiumCity.setText(stadium.getStadiumCity());
        stadiumCountry.setText(stadium.getStadiumCountry());
    }

    @Override
    public void disableFields(Boolean disabled) {
        textFieldList.stream().forEach(tf -> tf.setEditable(disabled));
        notes.setEditable(disabled);
        teamLogo.setDisable(!disabled);
        saveButton.setDisable(!disabled);
        cancelButton.setDisable(!disabled);
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
