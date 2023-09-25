package hockeycoach.DB;

import hockeycoach.mainClasses.PlayerXTeam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDeleter {
    private static final String DB_URL = "jdbc:ucanaccess://src/main/java/hockeycoach/files/database/hockeydb.accdb";

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
