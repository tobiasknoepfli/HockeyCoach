package hockeycoach.UI;

import hockeycoach.mainClasses.ImageChooser;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.w3c.dom.events.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewTeamPagePresentationModel {
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
    TextArea notes;
    Label controlLabel;
    ImageView logoPicture;
    TextField imageName;

    List<Team> teamList = new ArrayList();

    public void intializeControls(Pane root) {
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
        logoPicture= (ImageView) root.lookup("#logoPicture");
        imageName= (TextField) root.lookup("#imageName");

        DBLoaderTeamList dbLoaderTeamList = new DBLoaderTeamList();
        teamList = dbLoaderTeamList.getAllTeamNames();

        setControlsDisabled(true);
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

        logoPicture.setOnMouseClicked(event -> handleImageClick());

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
    }

    private void handleImageClick() {
        ImageChooser imageChooser = new ImageChooser();
        Image image = imageChooser.chooseImage(event);
        teamLogo.setImage(image);
    }
}