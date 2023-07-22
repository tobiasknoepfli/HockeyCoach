package hockeycoach.UI;

import hockeycoach.mainClasses.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private static final String DB_URL = "jdbc:ucanaccess://src/main/java/hockeycoach/files/database/hockeydb.accdb";

    public List<Team> getAllTeamNames() {
        List<Team> teams = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM team");

            while (resultSet.next()){
                String name =  resultSet.getString("name");
                Team team = new Team(name);
                teams.add(team);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teams;
    }
}
