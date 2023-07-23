package hockeycoach.UI;

import hockeycoach.mainClasses.Player;

import java.sql.*;
import java.util.ArrayList;

public class GeneralDAO {
    private static final String DB_URL = "jdbc:ucanaccess://src/main/java/hockeycoach/files/database/hockeydb.accdb";

    public ArrayList<Player> getPlayers(String query) {
        ArrayList<Player> playerList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Player player = new Player();
                player.setFirstName(resultSet.getString("firstName"));
                player.setLastName(resultSet.getString("lastName"));
                player.setTeam(resultSet.getString("team"));
                player.setStrengths(resultSet.getString("street"));
                player.setZip(resultSet.getInt("zip"));
                player.setCity(resultSet.getString("city"));
                player.setCountry(resultSet.getString("country"));
                player.setaLicence(resultSet.getString("aLicence"));
                player.setbLicence(resultSet.getString("bLicence"));
                player.setPhone(resultSet.getString("phone"));
                player.seteMail(resultSet.getString("eMail"));
                player.setJersey(resultSet.getInt("jersey"));
                player.setPositions(resultSet.getString("positions"));
                player.setStrengths(resultSet.getString("strengths"));
                player.setWeaknesses(resultSet.getString("weaknesses"));
                player.setRole(resultSet.getString("role"));
                player.setStick(resultSet.getString("stick"));
                player.setPhotoPath(resultSet.getString("photoPath"));


                playerList.add(player);

                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerList;
    }


}
