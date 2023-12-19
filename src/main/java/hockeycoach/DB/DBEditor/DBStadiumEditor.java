package hockeycoach.DB.DBEditor;

import hockeycoach.mainClasses.Stadium;
import hockeycoach.supportClasses.NodeStatus;
import hockeycoach.supportClasses.checkups.NullCheck;

import java.sql.*;

import static hockeycoach.AppStarter.DB_URL;
import static hockeycoach.supportClasses.checkups.NullCheck.*;

public class DBStadiumEditor {
    private PreparedStatement setStadium(PreparedStatement preparedStatement, Stadium stadium) throws SQLException{
        preparedStatement.setString(1, isNotNullElse(stadium.getStadiumName(),s->s,""));
        preparedStatement.setString(2,isNotNullElse(stadium.getStadiumAddress(),s->s,""));
        preparedStatement.setInt(3,isNotNullElse(stadium.getStadiumZip(),s->s,9999));
        preparedStatement.setString(4,isNotNullElse(stadium.getStadiumCity(),s->s,""));
        preparedStatement.setString(5,isNotNullElse(stadium.getStadiumCountry(),s->s,""));

        return preparedStatement;
    }

    public void editStadium(Stadium stadium){
        String query= "UPDATE stadium SET "+
                "stadiumName = ?, stadiumAddress = ?, stadiumZip = ?, "+
                "stadiumPlace = ?, stadiumCountry = ? "+
                "WHERE ID = " + stadium.getID();

        try(Connection connection = DriverManager.getConnection(DB_URL);
        PreparedStatement preparedStatement = connection.prepareStatement(query)){

            setStadium(preparedStatement,stadium);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
