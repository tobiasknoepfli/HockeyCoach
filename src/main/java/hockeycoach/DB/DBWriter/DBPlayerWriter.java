package hockeycoach.DB.DBWriter;

import hockeycoach.DB.DBLoader.DBPlayerLoader;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;

import java.sql.*;

import static hockeycoach.AppStarter.DB_URL;

public class DBPlayerWriter {
    DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();

    public PreparedStatement setPlayer(PreparedStatement preparedStatement, Player player) throws SQLException {

        preparedStatement.setString(1, (player.getFirstName() != null) ? player.getFirstName() : "");
        preparedStatement.setString(2, (player.getLastName() != null) ? player.getLastName() : "");
        preparedStatement.setDate(3, (player.getBirthday() != null) ? Date.valueOf(player.getBirthday()) : Date.valueOf("01.01.1900"));
        preparedStatement.setString(4, (player.getStreet() != null) ? player.getStreet() : "");
        preparedStatement.setInt(5, (player.getZip() != 0) ? player.getZip() : 0);
        preparedStatement.setString(6, (player.getCity() != null) ? player.getCity() : "");
        preparedStatement.setString(7, (player.getCountry() != null) ? player.getCountry() : "");
        preparedStatement.setString(8, (player.getaLicence() != null) ? player.getaLicence() : "");
        preparedStatement.setString(9, (player.getbLicence() != null) ? player.getbLicence() : "");
        preparedStatement.setString(10, (player.getPhone() != null) ? player.getPhone() : "");
        preparedStatement.setString(11, (player.geteMail() != null) ? player.geteMail() : "");
        preparedStatement.setString(12, (player.getPositions() != null) ? player.getPositions() : "");
        preparedStatement.setString(13, (player.getStrengths() != null) ? player.getStrengths() : "");
        preparedStatement.setString(14, (player.getWeaknesses() != null) ? player.getWeaknesses() : "");
        preparedStatement.setString(15, (player.getStick() != null) ? player.getStick() : "");
        preparedStatement.setInt(16, (player.getPhotoID() != 0) ? player.getPhotoID() : 0);
        preparedStatement.setString(17, (player.getNotes() != null) ? player.getNotes() : "");

        return preparedStatement;
    }

    public void writeNewPlayer(Player player) {
        String query = "INSERT INTO player" + "(firstName, lastName,birthday, street, zip,city, country,aLicence, bLicence, phone, eMail, positions, " +
                "strengths,weaknesses,stick,photoID,notes) VALUES ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setPlayer(preparedStatement, player);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPlayerToTeam(Team team, Player player) {
        String query = "INSERT INTO playerXteam" + "(playerID, teamID, jersey, role) VALUES (?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, (player != null) ? player.getID() : 0);
            preparedStatement.setInt(2, (team != null) ? team.getID() : 0);
            preparedStatement.setInt(3, 0);
            preparedStatement.setString(4, "none");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
