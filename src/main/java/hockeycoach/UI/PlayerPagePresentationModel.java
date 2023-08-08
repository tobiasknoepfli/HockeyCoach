package hockeycoach.UI;

import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class PlayerPagePresentationModel {
    TableView<Player> teamPlayers;
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

    public void initializeControls(Pane root) {
        teamPlayers = (TableView) root.lookup("#teamPlayers");
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

        Team selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        List<Player> playerList = dbLoader.getPlayers("SELECT * FROM player WHERE team LIKE '%" + selectedTeam.getName() + "%'");

        if (!playerList.isEmpty()) {
            teamPlayers.getItems().clear();
            teamPlayers.getItems().addAll(playerList);
        }

        setupEventListeners();
    }

    public void setupEventListeners() {
        teamPlayers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedPlayer, newSelectedPlayer) -> {
            if (newSelectedPlayer != null) {
                try {
                    playerPhoto.setImage(new Image(newSelectedPlayer.getPhotoPath()));
                } catch (Exception e) {
                    playerPhoto.setImage(null);
                }
                playerName.setText(newSelectedPlayer.getFirstName() + " " + newSelectedPlayer.getLastName());
                team.setText(newSelectedPlayer.getTeam());
                street.setText(newSelectedPlayer.getStreet());
                zipCity.setText(String.valueOf(newSelectedPlayer.getZip()) + " " + newSelectedPlayer.getCity());
                country.setText(newSelectedPlayer.getCountry());
                phone.setText(newSelectedPlayer.getPhone());
                email.setText(newSelectedPlayer.geteMail());
                jersey.setText("#" + String.valueOf(newSelectedPlayer.getJersey()));
                positions.setText(newSelectedPlayer.getPositions());
                strengths.setText(newSelectedPlayer.getStrengths());
                weaknesses.setText(newSelectedPlayer.getWeaknesses());
                notes.setText(newSelectedPlayer.getNotes());
                role.setText(newSelectedPlayer.getRole());
                aLicence.setText(newSelectedPlayer.getaLicence());
                bLicence.setText(newSelectedPlayer.getbLicence());
                stick.setText(newSelectedPlayer.getStick());
            }
        });
    }
}
