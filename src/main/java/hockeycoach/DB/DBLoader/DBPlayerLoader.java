package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.Player;

import java.sql.*;
import java.util.ArrayList;

import static hockeycoach.AppStarter.DB_URL;

public class DBPlayerLoader extends DBLoader {
    public Player setPlayer(ResultSet resultSet) {
        Player player = new Player();
        try {
            player.setPlayerID(resultSet.getInt("playerID"));
            player.setFirstName(resultSet.getString("firstName"));
            player.setLastName(resultSet.getString("lastName"));
            player.setStreet(resultSet.getString("street"));
            player.setZip(resultSet.getInt("zip"));
            player.setCity(resultSet.getString("city"));
            player.setCountry(resultSet.getString("country"));
            player.setaLicence(resultSet.getString("aLicence"));
            player.setbLicence(resultSet.getString("bLicence"));
            player.setPhone(resultSet.getString("phone"));
            player.seteMail(resultSet.getString("eMail"));
            player.setPositions(resultSet.getString("positions"));
            player.setStrengths(resultSet.getString("strengths"));
            player.setWeaknesses(resultSet.getString("weaknesses"));
            player.setStick(resultSet.getString("stick"));
            player.setPhotoPath(resultSet.getString("photoPath"));
            player.setNotes(resultSet.getString("notes"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    public ArrayList<Player> getAllPlayers(String query) {
        ArrayList<Player> allPlayers = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Player player = new Player();
                player = setPlayer(resultSet);
                allPlayers.add(player);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPlayers;
    }

    public ArrayList<Player> getTeamPlayers(String query, int selectedTeamID) {
        ArrayList<Player> playerList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Player player = new Player();
                player = setPlayer(resultSet);
                playerList.add(player);
                player.setJersey(getJersey("SELECT jersey FROM playerXteam WHERE playerID = "+player.getPlayerID()+" AND teamID = "+selectedTeamID));
                player.setRole(getRole("SELECT role FROM playerXteam WHERE playerID = "+player.getPlayerID()+" AND teamID = "+selectedTeamID));
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerList;
    }

    public int getJersey(String query) {
        int jersey = 0;
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                jersey = resultSet.getInt("jersey");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jersey;
    }

    public String getRole(String query) {
        String role = "";
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                role = resultSet.getString(("role"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

}
