package hockeycoach.DB.DBWriter;

import hockeycoach.DB.DBLoader.DBTeamLoader;
import hockeycoach.mainClasses.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBTeamWriter {
    DBImageWriter dbImageWriter = new DBImageWriter();
    DBTeamLoader dbTeamLoader = new DBTeamLoader();

    public PreparedStatement setTeam(PreparedStatement preparedStatement,Team team) throws  SQLException{

        preparedStatement.setString(1,team.getName());
        preparedStatement.setInt(2,team.getStadiumID());
        preparedStatement.setString(3,team.getContactFirstName());
        preparedStatement.setString(4,team.getContactLastName());
        preparedStatement.setString(5,team.getContactPhone());
        preparedStatement.setString(6,team.getContactEmail());
        preparedStatement.setString(7,team.getWebsite());
        preparedStatement.setInt(8,team.getFounded());
        preparedStatement.setString(9,team.getPresidentFirstName());
        preparedStatement.setString(10,team.getPresidentLastName());
        preparedStatement.setString(11,team.getLeague());
        preparedStatement.setString(12,team.getHeadCoachFirstName());
        preparedStatement.setString(13,team.getHeadCoachLastName());
        preparedStatement.setInt(14,team.getLogo());
        preparedStatement.setString(15,team.getNotes());

        return preparedStatement;
    }

    public void writeNewTeam(Team team){
        String query = "INSERT INTO team" + "(name ,stadium, contactFirstName, contactLastName, contactPhoneNr, contactEMail, " +
                " website, founded, presidentFirstName, presidentLastName, league, headCoachFirstName, headCoachLastName, logoID, " +
                "notes) VALUES ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";

        try(Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            setTeam(preparedStatement,team);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Team getTeamFromName(String teamName){
        List<Team> allTeams = new ArrayList<>();
        Team team =new Team();
        allTeams = dbTeamLoader.getAllTeams("SELECT * FROM team");
        team = allTeams.stream().filter(t ->t.getName().equals(teamName)).findFirst().orElse(null);
        return team;
    }
}
