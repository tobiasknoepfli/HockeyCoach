package hockeycoach.UI;

import hockeycoach.mainClasses.Game;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;

import java.util.List;

public class TeamPagePresentationModel {
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

    public void intializeControls(Pane root) {
        teamLogo = (ImageView) root.lookup("#teamLogo");
        teamName = (TextField) root.lookup("#teamName");
        stadiumName = (TextField) root.lookup("#stadiumName");
        stadiumStreet = (TextField) root.lookup("#stadiumStreet");
        stadiumZipCity = (TextField) root.lookup("#stadiumZipCity");
        stadiumCountry = (TextField) root.lookup("#stadiumCountry");
        contactName = (TextField) root.lookup("#contactName");
        contactPhone= (TextField) root.lookup("#contactPhone");
        contactEmail= (TextField) root.lookup("#contactEmail");
        website= (TextField) root.lookup("#website");
        founded= (TextField) root.lookup("#founded");
        presidentName= (TextField) root.lookup("#presidentName");
        currentLeague= (TextField) root.lookup("#currentLeague");
        headCoachName= (TextField) root.lookup("#headCoachName");
        captainName= (TextField) root.lookup("#captainName");
        teamPlayers= (TableView<Player>) root.lookup("#teamPlayers");
        notes= (TextArea) root.lookup("#notes");

        Team selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        List<Team> teamInfo = dbLoader.getTeam("SELECT * FROM team WHERE name LIKE '%" + selectedTeam.getName() + "%'");

        if (!teamInfo.isEmpty()) {
            Team team = teamInfo.get(0);

            try {
                teamLogo.setImage(new Image(team.getLogo()));
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
            //            teamPlayers

        }
    }
}
