package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBLoader {
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