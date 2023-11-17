package hockeycoach.PresentationModels;

import hockeycoach.DB.DBConverter.DBStringConverter;
import hockeycoach.DB.DBLoader.DBStadiumLoader;
import hockeycoach.DB.DBLoader.DBTeamLoader;
import hockeycoach.DB.DBWriter.DBTeamWriter;
import hockeycoach.mainClasses.Picture;
import hockeycoach.mainClasses.Stadium;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.ImageChooser;
import hockeycoach.mainClasses.Team;
import hockeycoach.supportClasses.TextFieldAction;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.w3c.dom.events.MouseEvent;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static hockeycoach.AppStarter.*;
import static hockeycoach.supportClasses.checkups.NullCheck.isNotNullElse;
import static hockeycoach.supportClasses.checkups.PatternCheck.isNumericElse;


public class NewTeamPresentationModel extends PresentationModel {
    ImageChooser imageChooser = new ImageChooser();
    DBStringConverter dbStringConverter = new DBStringConverter();
    DBTeamWriter dbTeamWriter = new DBTeamWriter();

    Stadium stadium = new Stadium();

    MouseEvent event;
    int counter;
    List<Team> teamList = new ArrayList();
    List<Stadium> stadiumList = new ArrayList<>();
    List<Stadium> searchStadiums = new ArrayList<>();

    ButtonControls buttonControls = new ButtonControls();
    private TextField[] textFields;
    private Stack<TextFieldAction> textFieldActions = new Stack<>();
    private TextFieldAction textFieldAction = new TextFieldAction();

    DBTeamLoader dbTeamLoader = new DBTeamLoader();
    DBStadiumLoader dbStadiumLoader = new DBStadiumLoader();

    ImageView teamLogo = new ImageView();

    TextField teamName,
            stadiumName, stadiumStreet, stadiumZip, stadiumCity, stadiumCountry,
            contactFirstName, contactLastName, contactPhone, contactEmail,
            website, founded, currentLeague,
            presidentFirstName, presidentLastName, headCoachFirstName, headCoachLastName;

    TextArea notes;

    Label controlLabel;

    Button saveButton, cancelButton, closeWindowButton, backButton, fillStadium;


    @Override
    public void initializeControls(Pane root) {
        importFields(root);

        textFields = new TextField[]{teamName,
                stadiumName, stadiumStreet, stadiumZip, stadiumCity, stadiumCountry,
                contactFirstName, contactLastName, contactPhone, contactEmail,
                website, founded, currentLeague,
                presidentFirstName, presidentLastName, headCoachFirstName, headCoachLastName};

        Arrays.stream(textFields).forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        counter = 0;

        getDBEntries(root);
        setupButtons(root);
        setupEventListeners(root);
    }

    @Override
    public void getDBEntries(Pane root) {
        teamList = dbTeamLoader.getAllTeams();
        stadiumList = dbStadiumLoader.getAllStadiums();
    }

    @Override
    public void setupButtons(Pane root) {
        saveButton.setOnAction(event -> {
            Team team = writeTeam();
            Picture picture = new Picture();

            dbTeamWriter.writeNewTeam(team);
            picture.copyImage(Path.of(team.getLogo().getImagePath()),Path.of(LOGOS), team.getLogo().getPictureName());
        });

        cancelButton.setOnAction(event -> {

        });

        backButton.setOnAction(event -> {
            textFieldAction.undoLastAction(textFieldActions);
        });

        fillStadium.setOnAction(event -> {
            lastVisitedPM = NewTeamPresentationModel.this;
            lastVisitedFXML = NEW_TEAM_FXML;
            lastVisitedNodeName = NEW_TEAM;
            buttonControls.openStadiumHide(root, NEW_TEAM);
        });

        teamLogo.setOnMouseClicked(mouseEvent -> {
            teamLogo.setImage(imageChooser.chooseImage(event));
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
        teamName.textProperty().addListener((obs, oldValue, newValue) -> {
            List<Team> newList = teamList.stream()
                    .filter(team -> team.getName().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());
            controlLabel.setText("found: " + newList.size());
        });


    }

    private Team writeTeam() {
        Team team = new Team();
        team.setName(isNotNullElse(teamName, t -> t.getText(), ""));
        team.setStadium(isNotNullElse(stadium, t -> dbStringConverter.getStadiumFromString(stadiumName.getText()),new Stadium()));
        team.setContactFirstName(isNotNullElse(contactFirstName, t -> t.getText(), ""));
        team.setContactLastName(isNotNullElse(contactLastName, t -> t.getText(), ""));
        team.setContactPhone(isNotNullElse(contactPhone, t -> t.getText(), ""));
        team.setContactEmail(isNotNullElse(contactEmail, t -> t.getText(), ""));
        team.setWebsite(isNotNullElse(website, t -> t.getText(), ""));
        team.setFounded(isNumericElse(founded.getText(),1900));
        team.setPresidentFirstName(isNotNullElse(presidentFirstName, t -> t.getText(), ""));
        team.setPresidentLastName(isNotNullElse(presidentLastName, t -> t.getText(), ""));
        team.setLeague(isNotNullElse(currentLeague, t -> t.getText(), ""));
        team.setHeadCoachFirstName(isNotNullElse(headCoachFirstName, t -> t.getText(), ""));
        team.setHeadCoachLastName(isNotNullElse(headCoachLastName, t -> t.getText(), ""));
        team.setNotes(isNotNullElse(notes, t -> t.getText(), ""));
        team.setLogo(saveTeamLogo());
        return team;
    }

    private Picture saveTeamLogo() {
        Picture picture = new Picture();
        picture.setImage(teamLogo.getImage());
        picture.setPictureName(isNotNullElse(teamName,t->t.getText() + "_Logo",""));

        URI imageUri = URI.create(teamLogo.getImage().getUrl());
        Path imagePath = Paths.get(imageUri);

        picture.setImagePath(imagePath.toString());
        return picture;
    }

//    private void clearAllFields() {
//        teamName.clear();
//        teamLogo.setImage(null);
//        stadiumName.clear();
//        stadiumStreet.clear();
//        stadiumZip.clear();
//        stadiumCity.clear();
//        stadiumCountry.clear();
//        contactFirstName.clear();
//        contactLastName.clear();
//        contactPhone.clear();
//        contactEmail.clear();
//        website.clear();
//        founded.clear();
//        presidentFirstName.clear();
//        presidentLastName.clear();
//        currentLeague.clear();
//        headCoachFirstName.clear();
//        headCoachLastName.clear();
//    }

//    private void callStartPage() {
//        StartPresentationModel pm = new StartPresentationModel();
//        HeaderController headerController = new HeaderController();
//        headerController.loadStages(HOME, HOME_FXML, pm);
//    }

    public void fillStadium(Stadium stadium) {
        stadiumName.setText(stadium.getStadiumName());
        stadiumStreet.setText(stadium.getStadiumAddress());
        stadiumZip.setText(Integer.toString(stadium.getStadiumZip()));
        stadiumCity.setText(stadium.getStadiumCity());
        stadiumCountry.setText(stadium.getStadiumCountry());
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
        currentLeague = (TextField) root.lookup("#currentLeague");
        presidentFirstName = (TextField) root.lookup("#presidentFirstName");
        presidentLastName = (TextField) root.lookup("#presidentLastName");
        headCoachFirstName = (TextField) root.lookup("#headCoachFirstName");
        headCoachLastName = (TextField) root.lookup("#headCoachLastName");

        notes = (TextArea) root.lookup("#notes");

        saveButton = (Button) root.lookup("#saveButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        closeWindowButton = (Button) root.lookup("#closeWindowButton");
        backButton = (Button) root.lookup("#backButton");
        fillStadium = (Button) root.lookup("#fillStadium");

        controlLabel = (Label) root.lookup("#controlLabel");
    }
}