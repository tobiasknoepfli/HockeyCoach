package hockeycoach.UI;

import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TeamPagePresentationModel {
    private Pane contentPane;

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

    public TeamPagePresentationModel(Pane contentPane){
        this.contentPane = contentPane;
    }

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
        editPlayerButton = (Button) root.lookup("#editPlayerButton");

        Team selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        List<Team> teamInfo = dbLoader.getTeam("SELECT * FROM team WHERE name LIKE '%" + selectedTeam.getName() + "%'");
        List<Player> playerList = dbLoader.getPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.playerID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());
        Team team = teamInfo.get(0);

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
    }

    private void setupEventListeners(){
        editPlayerButton.setOnAction(event ->{
            try {
                FXMLLoader playerToTeamLoader = new FXMLLoader(getClass().getResource("/hockeycoach/player-to-team.fxml"));
                Pane playerToTeamPage = playerToTeamLoader.load();
                contentPane.getChildren().clear();
                contentPane.getChildren().add(playerToTeamPage);

                PlayerToTeamPresentationModel  playerToTeamPresentationModel = new PlayerToTeamPresentationModel();
                playerToTeamPresentationModel.initializeControls(playerToTeamPage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
