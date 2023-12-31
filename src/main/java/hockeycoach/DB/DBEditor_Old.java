package hockeycoach.DB;

import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;

import java.sql.*;

import static hockeycoach.AppStarter.DB_URL;

public class DBEditor_Old {


    public void editPlayer(Player player) {
        String query = "UPDATE player SET " +
                "firstName = ?, " +
                "lastName = ?, " +
                "street = ?, " +
                "zip = ?, " +
                "city = ?, " +
                "country = ?, " +
                "aLicence = ?, " +
                "bLicence = ?, " +
                "phone = ?, " +
                "eMail = ?, " +
                "positions = ?, " +
                "strengths = ?, " +
                "weaknesses = ?, " +
                "stick = ?, " +
                "photoPath = ?, " +
                "notes = ? " +
                "WHERE playerID = " + player.getID();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, player.getFirstName());
            preparedStatement.setString(2, player.getLastName());
            preparedStatement.setString(3, player.getStreet());
            preparedStatement.setInt(4, player.getZip());
            preparedStatement.setString(5, player.getCity());
            preparedStatement.setString(6, player.getCountry());
            preparedStatement.setString(7, player.getaLicence());
            preparedStatement.setString(8, player.getbLicence());
            preparedStatement.setString(9, player.getPhone());
            preparedStatement.setString(10, player.geteMail());
            preparedStatement.setString(11, player.getPositions());
            preparedStatement.setString(12, player.getStrengths());
            preparedStatement.setString(13, player.getWeaknesses());
            preparedStatement.setString(14, player.getStick());
//            preparedStatement.setString(15, player.getPhotoID());
            preparedStatement.setString(16, player.getNotes());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editJerseyAndRole(Player player, Team team) {
        String checkQuery = "SELECT COUNT(*) FROM playerXteam WHERE playerID = ? AND teamID = ?";
        String updateQuery = "UPDATE playerXteam SET jersey = ?, role = ? WHERE playerID = ? AND teamID = ?";
        String insertQuery = "INSERT INTO playerXteam (playerID, teamID, jersey, role) VALUES (?, ?, ?, ?)";

        boolean combinationExists = false;

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
             PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            checkStatement.setInt(1, player.getID());
            checkStatement.setInt(2, team.getID());
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                combinationExists = true;
                updateStatement.setInt(1, player.getJersey());
                updateStatement.setString(2, player.getRole());
                updateStatement.setInt(3, player.getID());
                updateStatement.setInt(4, team.getID());
                updateStatement.executeUpdate();
            }
            else {
                insertStatement.setInt(1, player.getID());
                insertStatement.setInt(2, team.getID());
                insertStatement.setInt(3, player.getJersey());
                insertStatement.setString(4, player.getRole());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                "WHERE teamID = " + team.getID();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, team.getName());
//            preparedStatement.setString(2, team.getStadium());
//            preparedStatement.setString(3, team.getStreet());
//            preparedStatement.setInt(4, team.getZip());
//            preparedStatement.setString(5, team.getCity());
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
//            preparedStatement.setString(18, team.getLogo());
            preparedStatement.setString(19, team.getNotes());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
