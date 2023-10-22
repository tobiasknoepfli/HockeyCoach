package hockeycoach.DB.DBWriter;

import hockeycoach.mainClasses.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static hockeycoach.AppStarter.DB_URL;

public class DBPlayerWriter {
    public Player setPlayer(PreparedStatement preparedStatement) throws SQLException {
        Player player = new Player();

        preparedStatement.setString(1, player.getFirstName());
        preparedStatement.setString(2, player.getLastName());
        preparedStatement.setString(3, player.getStreet());
        preparedStatement.setInt(4, player.getZip());
        preparedStatement.setString(5, player.getCountry());
        preparedStatement.setString(6, player.getaLicence());
        preparedStatement.setString(7, player.getbLicence());
        preparedStatement.setString(8, player.getPhone());
        preparedStatement.setString(9, player.geteMail());
        preparedStatement.setString(10, player.getPositions());
        preparedStatement.setString(11, player.getStrengths());
        preparedStatement.setString(12, player.getWeaknesses());
        preparedStatement.setString(13, player.getStick());
//            preparedStatement.setString(14, player.getPhotoID());
        preparedStatement.setString(15, player.getNotes());

        return player;
    }

    public void writeNewPlayer(Player player) {
        String query = "INSERT INTO player" + "(firstName, lastName, street, zip, country,aLicence, bLicence, phone, eMail, positions, " +
                "strengths,weaknesses,stick,photoPath,notes) VALUES ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            player = setPlayer(preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
