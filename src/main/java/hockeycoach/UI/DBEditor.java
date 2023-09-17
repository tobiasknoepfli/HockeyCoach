package hockeycoach.UI;

import hockeycoach.mainClasses.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBEditor {
    private static final String DB_URL = "jdbc:ucanaccess://src/main/java/hockeycoach/files/database/hockeydb.accdb";

    public void editTeam(Team team) {
        String query = "UPDATE team SET " +
                "name = ?, " +
                "stadium = ?, " +
                "street = ?, " +
                "zip = ?, " +
                "city = ?, " +
                "country = ?, " +
                "contactFirstName = ?, " +
                "contactLastName = ?, " +
                "contactPhoneNr = ?, " +
                "contactEMail = ?, " +
                "website = ?, " +
                "founded = ?, " +
                "presidentFirstName = ?, " +
                "presidentLastName = ?, " +
                "league = ?, " +
                "headCoachFirstName = ?, " +
                "headCoachLastName = ?, " +
                "captainFirstName = ?, " +
                "captainLastName = ?, " +
                "logo = ?, " +
                "notes = ? " +
                "WHERE teamID = " + team.getTeamID();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            System.out.println(team.getPresidentLastName());
            preparedStatement.setString(1, team.getName());
            preparedStatement.setString(2, team.getStadium());
            preparedStatement.setString(3, team.getStreet());
            preparedStatement.setInt(4, team.getZip());
            preparedStatement.setString(5, team.getCity());
            preparedStatement.setString(6, team.getCountry());
            preparedStatement.setString(7, team.getContactFirstName());
            preparedStatement.setString(8, team.getContactLastName());
            preparedStatement.setString(9, team.getContactPhone());
            preparedStatement.setString(10, team.getContactEmail());
            preparedStatement.setString(11, team.getWebsite());
            preparedStatement.setInt(12, team.getFounded());
            preparedStatement.setString(13, team.getPresidentFirstName());
            preparedStatement.setString(14, team.getPresidentLastName());
            preparedStatement.setString(15, team.getLeague());
            preparedStatement.setString(16, team.getHeadCoachFirstName());
            preparedStatement.setString(17, team.getHeadCoachLastName());
            preparedStatement.setString(18, team.getCaptainFirstName());
            preparedStatement.setString(19, team.getCaptainLastName());
            preparedStatement.setString(20, team.getLogo());
            preparedStatement.setString(21, team.getNotes());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
