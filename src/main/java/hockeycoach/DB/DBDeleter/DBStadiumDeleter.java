package hockeycoach.DB.DBDeleter;

import hockeycoach.mainClasses.Stadium;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static hockeycoach.AppStarter.DB_URL;

public class DBStadiumDeleter {
    public void deleteStadium(Stadium stadium)    {
        String query = "DELETE FROM stadium WHERE ID = " + stadium.getID();

        try(Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
