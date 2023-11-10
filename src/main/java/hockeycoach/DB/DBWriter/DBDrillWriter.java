package hockeycoach.DB.DBWriter;

import hockeycoach.mainClasses.Drills.Drill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static hockeycoach.AppStarter.DB_URL;

public class DBDrillWriter {
    public PreparedStatement setDrill(PreparedStatement preparedStatement, Drill drill) throws SQLException {

        preparedStatement.setString(1, (drill.getName() != null) ? drill.getName() : "");
        preparedStatement.setInt(2, (drill.getCategory().getID() != 0) ? drill.getCategory().getID() : 0);
        preparedStatement.setInt(3, (drill.getDifficulty().getID() != 0) ? drill.getDifficulty().getID() : 0);
        preparedStatement.setInt(4, (drill.getParticipation().getID() != 0) ? drill.getParticipation().getID() : 0);
        preparedStatement.setString(5, (drill.getDescription() != null) ? drill.getDescription() : "");
        preparedStatement.setBoolean(6, (drill.getStation() != null) ? drill.getStation() : false);
        preparedStatement.setInt(7, (drill.getPicture().getID() != 0) ? drill.getPicture().getID() : 0);

        return preparedStatement;
    }

    public void writeNewDrill(Drill drill) {
        String query = "INSERT INTO drill" +
                "(name,category,difficulty,participation,description,station,imageID)" + "VALUES (?,?,?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setDrill(preparedStatement, drill);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
