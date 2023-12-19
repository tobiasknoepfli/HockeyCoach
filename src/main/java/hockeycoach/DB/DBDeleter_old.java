package hockeycoach.DB;

import hockeycoach.supportClasses.PlayerXTeam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static hockeycoach.AppStarter.DB_URL;

public class DBDeleter_old {


    public void removeFromPlayerXList(PlayerXTeam playerXTeam) {
        String query = "DELETE FROM playerXteam WHERE id = " + playerXTeam.getID();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
