package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBLoader {
    public Player getPlayerByID(int playerID) {
        Player player = new Player();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            String query = "SELECT * FROM player WHERE ID = " + player.getID();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                DBPlayerLoader dbPlayerLoader =new DBPlayerLoader();
                player = dbPlayerLoader.setPlayer(resultSet);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    public List<Team> getTeamsForPlayer(String query) {
        List<Team> teamList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Team team = new Team();
                DBTeamLoader dbTeamLoader = new DBTeamLoader();

                team = dbTeamLoader.getTeam("SELECT * FROM team WHERE ID ="+resultSet.getInt("ID"));

                teamList.add(team);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamList;
    }
}