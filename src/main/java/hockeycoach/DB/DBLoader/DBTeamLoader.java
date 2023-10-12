package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.Team;

import java.sql.*;
import java.util.ArrayList;

import static hockeycoach.AppStarter.DB_URL;

public class DBTeamLoader extends DBLoader {
    public Team setTeam(ResultSet resultSet) {
        Team team = new Team();
        try {
            team.setTeamID(resultSet.getInt("teamID"));
            team.setName(resultSet.getString("name"));
            team.setStadium(resultSet.getString("stadium"));
            team.setStreet(resultSet.getString("street"));
            team.setZip(resultSet.getInt("zip"));
            team.setCity(resultSet.getString("city"));
            team.setCountry(resultSet.getString("country"));
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
            team.setCaptainFirstName(resultSet.getString("captainFirstName"));
            team.setCaptainLastName(resultSet.getString("captainLastName"));
            team.setLogo(resultSet.getString("logo"));
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

            resultSet.next();
            team = setTeam(resultSet);

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }


    public ArrayList<Team> getAllTeams(String query) {
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
