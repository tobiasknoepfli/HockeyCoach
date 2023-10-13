package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBTeamLoader;
import hockeycoach.DB.DBWriter;
import hockeycoach.controllers.HeaderController;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.ImageChooser;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
import hockeycoach.supportClasses.TextFieldAction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

import static hockeycoach.AppStarter.*;


public class NewTeamPresentationModel extends PresentationModel {
    MouseEvent event;
    TableView<Player> teamPlayers;
    List<Team> teamList = new ArrayList();
    ButtonControls buttonControls = new ButtonControls();
    private TextField[] textFields;
    private Stack<TextFieldAction> textFieldActions = new Stack<>();
    private TextFieldAction textFieldAction = new TextFieldAction();

    DBTeamLoader dbTeamLoader = new DBTeamLoader();

    ImageView teamLogo = new ImageView();

    TextField teamName,
            stadiumName, stadiumStreet, stadiumZip, stadiumCity, stadiumCountry,
            contactFirstName, contactLastName, contactPhone, contactEmail,
            website, founded, currentLeague,
            presidentFirstName, presidentLastName, headCoachFirstName, headCoachLastName;

    TextArea notes;

    Label controlLabel, controlZip, controlContact, controlFounded,
            controlHeadCoach, controlCaptain, controlPresident;

    Button saveButton, cancelButton, closeWindowButton, backButton;


    @Override
    public void initializeControls(Pane root) {
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
        teamPlayers = (TableView) root.lookup("#teamPlayers");
        notes = (TextArea) root.lookup("#notes");
        controlLabel = (Label) root.lookup("#controlLabel");
        saveButton = (Button) root.lookup("#saveButton");
        cancelButton = (Button) root.lookup("#cancelButton");
        closeWindowButton = (Button) root.lookup("#closeWindowButton");
        controlZip = (Label) root.lookup("#controlZip");
        controlContact = (Label) root.lookup("#controlContact");
        controlFounded = (Label) root.lookup("#controlFounded");
        controlHeadCoach = (Label) root.lookup("#controlHeadCoach");
        controlCaptain = (Label) root.lookup("#controlCaptain");
        controlPresident = (Label) root.lookup("#controlPresident");
        backButton = (Button) root.lookup("#backButton");

        textFields = new TextField[]{teamName,
                stadiumName, stadiumStreet, stadiumZip, stadiumCity, stadiumCountry,
                contactFirstName, contactLastName, contactPhone, contactEmail,
                website, founded, currentLeague,
                presidentFirstName, presidentLastName, headCoachFirstName, headCoachLastName};

        Arrays.stream(textFields).forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        setControlsDisabled(true);

        getDBEntries(root);
        setupButtons(root);
        setupEventListeners(root);
    }

    @Override
    public void getDBEntries(Pane root) {
        teamList = dbTeamLoader.getAllTeams("SELECT * FROM team");
    }

    @Override
    public void setupButtons(Pane root) {
        saveButton.setOnAction(event -> {
            String logoPath = saveTeamLogo();
            Team newTeam = readData(logoPath);
            DBWriter dbWriter = new DBWriter();
            dbWriter.writeNewTeam(newTeam);
            clearAllFields();
            callStartPage();
        });

        cancelButton.setOnAction(event -> {
            clearAllFields();
            callStartPage();
        });

        closeWindowButton.setOnAction(event -> {
            buttonControls.closeWindow(root, NEW_TEAM);
        });

        backButton.setOnAction(event -> {
            textFieldAction.undoLastAction(textFieldActions);
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
        teamName.textProperty().addListener((obs, oldValue, newValue) -> {
            List<Team> newList = teamList.stream()
                    .filter(team -> team.getName().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());
            controlLabel.setText("found: " + newList.size());
            boolean disableControls = newValue.isEmpty() || newList.size() > 0;
            setControlsDisabled(disableControls);
        });

        teamLogo.setOnMouseClicked(event -> handleImageClick());


    }

    public void setControlsDisabled(boolean disabled) {
        teamLogo.setDisable(disabled);
        stadiumName.setDisable(disabled);
        stadiumStreet.setDisable(disabled);
        stadiumZip.setDisable(disabled);
        stadiumCity.setDisable(disabled);
        stadiumCountry.setDisable(disabled);
        contactFirstName.setDisable(disabled);
        contactLastName.setDisable(disabled);
        contactPhone.setDisable(disabled);
        contactEmail.setDisable(disabled);
        website.setDisable(disabled);
        founded.setDisable(disabled);
        presidentFirstName.setDisable(disabled);
        presidentLastName.setDisable(disabled);
        currentLeague.setDisable(disabled);
        headCoachFirstName.setDisable(disabled);
        headCoachLastName.setDisable(disabled);
        notes.setDisable(disabled);
        saveButton.setDisable(disabled);
    }

    private void handleImageClick() {
        ImageChooser imageChooser = new ImageChooser();
        Image image = imageChooser.chooseImage(event);
        teamLogo.setImage(image);
    }


    private String saveTeamLogo() {
        Image selectedImage = teamLogo.getImage();
        if (selectedImage != null) {
            String imageNameText = teamName.getText() + "_Logo";
            String imageFormat = selectedImage.getUrl().substring(selectedImage.getUrl().lastIndexOf(".") + 1).toLowerCase();
            if (!imageFormat.equals("jpg") && !imageFormat.equals("jpeg") & !imageFormat.equals("png")) {
                imageFormat = "jpg";
            }

            String destinationFileName = imageNameText + "." + imageFormat;
            String destinationDirectory = LOGOS;

            try {
                URL imageUrl = new URL(selectedImage.getUrl());
                String decodedImageUrl = Objects.requireNonNull(imageUrl.getPath());
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

    private Team readData(String newImage) {
        Team newTeam = new Team();
        newTeam.setName(teamName.getText());
        newTeam.setStadium(stadiumName.getText());
        newTeam.setStreet(stadiumStreet.getText());
        newTeam.setZip(Integer.parseInt(stadiumZip.getText()));
        newTeam.setCity(stadiumCity.getText());
        newTeam.setCountry(stadiumCountry.getText());

        newTeam.setContactFirstName(contactFirstName.getText());
        newTeam.setContactLastName(contactLastName.getText());
        newTeam.setContactPhone(contactPhone.getText());
        newTeam.setContactEmail(contactEmail.getText());
        newTeam.setWebsite(website.getText());
        newTeam.setFounded(Integer.parseInt(founded.getText()));
        newTeam.setPresidentFirstName(presidentFirstName.getText());
        newTeam.setPresidentLastName(presidentLastName.getText());
        newTeam.setLeague(currentLeague.getText());
        newTeam.setHeadCoachFirstName(headCoachFirstName.getText());
        newTeam.setHeadCoachLastName(headCoachLastName.getText());
        newTeam.setNotes(notes.getText());

        newTeam.setLogo(newImage);
        return newTeam;
    }

    private void clearAllFields() {
        teamName.clear();
        teamLogo.setImage(null);
        stadiumName.clear();
        stadiumStreet.clear();
        stadiumZip.clear();
        stadiumCity.clear();
        stadiumCountry.clear();
        contactFirstName.clear();
        contactLastName.clear();
        contactPhone.clear();
        contactEmail.clear();
        website.clear();
        founded.clear();
        presidentFirstName.clear();
        presidentLastName.clear();
        currentLeague.clear();
        headCoachFirstName.clear();
        headCoachLastName.clear();
    }

    private void callStartPage() {
        StartPresentationModel pm = new StartPresentationModel();
        HeaderController headerController = new HeaderController();
        headerController.loadStages(HOME, HOME_FXML, pm);
    }
}