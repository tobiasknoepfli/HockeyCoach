package hockeycoach.DB.DBWriter;

import hockeycoach.mainClasses.Player;

import java.sql.*;

import static hockeycoach.AppStarter.DB_URL;

public class DBPlayerWriter {
    public PreparedStatement setPlayer(PreparedStatement preparedStatement, Player player) throws SQLException {

        preparedStatement.setString(1, player.getFirstName());
        preparedStatement.setString(2, player.getLastName());
        preparedStatement.setDate(3, Date.valueOf(player.getBirthday()));
        preparedStatement.setString(4, player.getStreet());
        preparedStatement.setInt(5, player.getZip());
        preparedStatement.setString(6,player.getCity());
        preparedStatement.setString(7, player.getCountry());
        preparedStatement.setString(8, player.getaLicence());
        preparedStatement.setString(9, player.getbLicence());
        preparedStatement.setString(10, player.getPhone());
        preparedStatement.setString(11, player.geteMail());
        preparedStatement.setString(12, player.getPositions());
        preparedStatement.setString(13, player.getStrengths());
        preparedStatement.setString(14, player.getWeaknesses());
        preparedStatement.setString(15, player.getStick());
        preparedStatement.setInt(16, player.getPhotoID());
        preparedStatement.setString(17, player.getNotes());

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
}
