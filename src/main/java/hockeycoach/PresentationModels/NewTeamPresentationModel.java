package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBTeamLoader;
import hockeycoach.DB.DBWriter;
import hockeycoach.controllers.HeaderController;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.ImageChooser;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
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

    ImageView teamLogo = new ImageView();

    TextField teamName,
            stadiumName, stadiumStreet, stadiumZipCity, stadiumCountry,
            contactName, contactPhone, contactEmail,
            website, founded, currentLeague,
            presidentName, headCoachName, captainName;

    TextArea notes;

    Label controlLabel, controlZip, controlContact, controlFounded,
            controlHeadCoach, controlCaptain, controlPresident;

    Button saveButton, cancelButton, closeWindowButton;


    @Override
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

        DBTeamLoader dbTeamLoader = new DBTeamLoader();
        teamList = dbTeamLoader.getAllTeams("SELECT * FROM team");

        setControlsDisabled(true);

        closeWindowButton.setOnAction(event -> {
            ButtonControls buttonControls = new ButtonControls();
            buttonControls.closeWindow(root,"NewTeam");
        });

        setupEventListeners();
    }

    public void setupEventListeners() {
        teamName.textProperty().addListener((obs, oldValue, newValue) -> {
            List<Team> newList = teamList.stream()
                    .filter(team -> team.getName().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());
            controlLabel.setText("found: " + newList.size());
            boolean disableControls = newValue.isEmpty() || newList.size() > 0;
            setControlsDisabled(disableControls);
        });

        teamLogo.setOnMouseClicked(event -> handleImageClick());

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

        stadiumZipCity.textProperty().addListener((obs, oldValue, newValue) -> {
            controlFields();
        });

        contactName.textProperty().addListener((obs, oldValue, newValue) -> {
            controlFields();
        });

        founded.textProperty().addListener((obs, oldValue, newValue) -> {
            controlFields();
        });

        presidentName.textProperty().addListener((obs, oldValue, newValue) -> {
            controlFields();
        });

        headCoachName.textProperty().addListener((obs, oldValue, newValue) -> {
            controlFields();
        });

        captainName.textProperty().addListener((obs, oldValue, newValue) -> {
            controlFields();
        });
    }

    public void setControlsDisabled(boolean disabled) {
        teamLogo.setDisable(disabled);
        stadiumName.setDisable(disabled);
        stadiumStreet.setDisable(disabled);
        stadiumZipCity.setDisable(disabled);
        stadiumCountry.setDisable(disabled);
        contactName.setDisable(disabled);
        contactPhone.setDisable(disabled);
        contactEmail.setDisable(disabled);
        website.setDisable(disabled);
        founded.setDisable(disabled);
        presidentName.setDisable(disabled);
        currentLeague.setDisable(disabled);
        headCoachName.setDisable(disabled);
        captainName.setDisable(disabled);
        notes.setDisable(disabled);
        saveButton.setDisable(disabled);
    }

    private void handleImageClick() {
        ImageChooser imageChooser = new ImageChooser();
        Image image = imageChooser.chooseImage(event);
        teamLogo.setImage(image);
    }

    private void controlFields() {
        String zipCityText = stadiumZipCity.getText();
        if (!zipCityText.isEmpty()) {
            String[] zip = zipCityText.split("\\s+");
            try {
                int z = Integer.parseInt(zip[0]);
                controlZip.setText("");
                saveButton.setDisable(false);
            } catch (NumberFormatException e) {
                controlZip.setText("x");
                saveButton.setDisable(true);
            }

        }
        String contact = contactName.getText();
        if (!contact.isEmpty()) {
            String[] c = contact.split("\\s+");
            if (c.length == 2) {
                controlContact.setText("");
                saveButton.setDisable(false);
            } else {
                controlContact.setText("x");
                saveButton.setDisable(true);
            }
        }

        String est = founded.getText();
        if (!est.isEmpty()) {
            try {
                int f = Integer.parseInt(est);
                controlFounded.setText("");
                saveButton.setDisable(false);
            } catch (NumberFormatException e) {
                controlFounded.setText("x");
                saveButton.setDisable(true);
            }
        }

        String president = presidentName.getText();
        if (!president.isEmpty()) {
            String[] p = president.split("\\s+");
            if (p.length == 2) {
                controlPresident.setText("");
                saveButton.setDisable(false);
            } else {
                controlPresident.setText("x");
                saveButton.setDisable(true);
            }
        }

        String headCoach = headCoachName.getText();
        if (!headCoach.isEmpty()) {
            String[] h = headCoach.split("\\s+");
            if (h.length == 2) {
                controlHeadCoach.setText("");
                saveButton.setDisable(false);
            } else {
                controlHeadCoach.setText("x");
                saveButton.setDisable(true);
            }
        }

        String captain = captainName.getText();
        if (!captain.isEmpty()) {
            String[] cpt = captain.split("\\s+");
            if (cpt.length == 2) {
                controlCaptain.setText("");
                saveButton.setDisable(false);
            } else {
                controlCaptain.setText("x");
                saveButton.setDisable(true);
            }
        }
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

        String[] zc = stadiumZipCity.getText().split("\\s+");
        newTeam.setZip(Integer.parseInt(zc[0]));
        newTeam.setCity(zc[1]);

        newTeam.setCountry(stadiumCountry.getText());

        String[] cFnLn = contactName.getText().split("\\s+");
        newTeam.setContactFirstName(cFnLn[0]);
        newTeam.setContactLastName(cFnLn[1]);

        newTeam.setContactPhone(contactPhone.getText());
        newTeam.setContactEmail(contactEmail.getText());
        newTeam.setWebsite(website.getText());
        newTeam.setFounded(Integer.parseInt(founded.getText()));

        String[] pFnLn = presidentName.getText().split("\\s+");
        newTeam.setPresidentFirstName(pFnLn[0]);
        newTeam.setPresidentLastName(pFnLn[1]);
        newTeam.setLeague(currentLeague.getText());

        String[] hCFnLn = headCoachName.getText().split("\\s+");
        newTeam.setHeadCoachFirstName(hCFnLn[0]);
        newTeam.setHeadCoachLastName(hCFnLn[1]);

        String[] capFnLn = captainName.getText().split("\\s+");
        newTeam.setCaptainFirstName(capFnLn[0]);
        newTeam.setCaptainLastName(capFnLn[1]);
        newTeam.setNotes(notes.getText());

        newTeam.setLogo(newImage);
        return newTeam;
    }

    private void clearAllFields() {
        teamName.clear();
        teamLogo.setImage(null);
        stadiumName.clear();
        stadiumStreet.clear();
        stadiumZipCity.clear();
        stadiumCountry.clear();
        contactName.clear();
        contactPhone.clear();
        contactEmail.clear();
        website.clear();
        founded.clear();
        presidentName.clear();
        currentLeague.clear();
        headCoachName.clear();
        captainName.clear();
    }

    private void callStartPage() {
        StartPresentationModel pm = new StartPresentationModel();
        HeaderController headerController = new HeaderController();
        headerController.loadStages("Home", HOME_FXML, pm);
    }

    public static void closeWindow(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}