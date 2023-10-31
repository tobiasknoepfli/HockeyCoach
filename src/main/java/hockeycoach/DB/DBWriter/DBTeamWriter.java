package hockeycoach.DB.DBWriter;

import hockeycoach.mainClasses.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static hockeycoach.AppStarter.DB_URL;

public class DBTeamWriter {
    DBImageWriter dbImageWriter = new DBImageWriter();

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
}
