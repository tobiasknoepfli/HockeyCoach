package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.Stadium;
import hockeycoach.mainClasses.Team;

import java.sql.*;
import java.util.ArrayList;

import static hockeycoach.AppStarter.DB_URL;

public class DBTeamLoader extends DBLoader {
    DBStadiumLoader dbStadiumLoader = new DBStadiumLoader();
    DBImageLoader dbImageLoader =new DBImageLoader();
    Stadium stadium =new Stadium();

    public Team setTeam(ResultSet resultSet) {
        Team team = new Team();
        try {
            team.setID(resultSet.getInt("ID"));
            team.setName(resultSet.getString("name"));
            team.setStadium(dbStadiumLoader.getStadiumFromID(resultSet.getInt("stadium")));
            team.setContactFirstName(resultSet.getString("contactFirstName"));
            team.setContactLastName(resultSet.getString("contactLastName"));
            team.setContactPhone(resultSet.getString("contactPhoneNr"));
            team.setContactEmail(resultSet.getString("contactEMail"));
            team.setWebsite(resultSet.getString("website"));
            team.setFounded(resultSet.getInt("founded"));
            team.setPresidentFirstName(resultSet.getString("presidentFirstName"));
            team.setPresidentLastName(resultSet.getString("presidentLastName"));
            team.setLeague(resultSet.getString("league"));
            team.setHeadCoachFirstName(resultSet.getString("headCoachFirstName"));
            team.setHeadCoachLastName(resultSet.getString("headCoachLastName"));
            team.setNotes(resultSet.getString("notes"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }

    public Team getTeam(String query) {
        Team team = new Team();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                team = setTeam(resultSet);
            };

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }


    public ArrayList<Team> getAllTeams() {
        String query = "SELECT * FROM team";
        ArrayList<Team> allTeams = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Team team = new Team();
                team = setTeam(resultSet);

                allTeams.add(team);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTeams;
    }
}
