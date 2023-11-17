package hockeycoach.DB.DBWriter;

import hockeycoach.DB.DBLoader.DBImageLoader;
import hockeycoach.DB.DBLoader.DBTeamLoader;
import hockeycoach.mainClasses.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;
import static hockeycoach.supportClasses.checkups.NullCheck.isNotNullElse;

public class DBTeamWriter {
    DBTeamLoader dbTeamLoader = new DBTeamLoader();
    DBImageWriter dbImageWriter = new DBImageWriter();

    public PreparedStatement setTeam(PreparedStatement preparedStatement, Team team) throws SQLException {

        preparedStatement.setString(1, isNotNullElse(team, t -> t.getName(), ""));
        preparedStatement.setInt(2, isNotNullElse(team, t -> t.getStadiumID(), 0));
        preparedStatement.setString(3, isNotNullElse(team, t -> t.getContactFirstName(), ""));
        preparedStatement.setString(4, isNotNullElse(team, t -> t.getContactLastName(), ""));
        preparedStatement.setString(5, isNotNullElse(team, t -> t.getContactPhone(), ""));
        preparedStatement.setString(6, isNotNullElse(team, t -> t.getContactEmail(), ""));
        preparedStatement.setString(7, isNotNullElse(team, t -> t.getWebsite(), ""));
        preparedStatement.setInt(8, isNotNullElse(team, t -> t.getFounded(), 0));
        preparedStatement.setString(9, isNotNullElse(team, t -> t.getPresidentFirstName(), ""));
        preparedStatement.setString(10, isNotNullElse(team, t -> t.getPresidentLastName(), ""));
        preparedStatement.setString(11, isNotNullElse(team, t -> t.getLeague(), ""));
        preparedStatement.setString(12, isNotNullElse(team, t -> t.getHeadCoachFirstName(), ""));
        preparedStatement.setString(13, isNotNullElse(team, t -> t.getHeadCoachLastName(), ""));
        preparedStatement.setInt(14, dbImageWriter.saveImage(team.getLogo()));
        preparedStatement.setString(15, isNotNullElse(team, t -> t.getNotes(), ""));

        return preparedStatement;
    }

    public void writeNewTeam(Team team) {
        String query = "INSERT INTO team" + "(name ,stadium, contactFirstName, contactLastName, contactPhoneNr, contactEMail, " +
                " website, founded, presidentFirstName, presidentLastName, league, headCoachFirstName, headCoachLastName, logoID, " +
                "notes) VALUES ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setTeam(preparedStatement, team);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Team getTeamFromName(String teamName) {
        List<Team> allTeams = new ArrayList<>();
        Team team = new Team();
        allTeams = dbTeamLoader.getAllTeams();
        team = allTeams.stream().filter(t -> t.getName().equals(teamName)).findFirst().orElse(null);
        return team;
    }
}
