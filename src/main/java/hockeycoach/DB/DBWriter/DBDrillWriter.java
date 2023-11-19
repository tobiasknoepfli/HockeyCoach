package hockeycoach.DB.DBWriter;

import hockeycoach.mainClasses.Drills.Drill;
import hockeycoach.supportClasses.checkups.NullCheck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static hockeycoach.AppStarter.DB_URL;
import static hockeycoach.supportClasses.checkups.NullCheck.isNotNullElse;

public class DBDrillWriter {
    public PreparedStatement setDrill(PreparedStatement preparedStatement, Drill drill) throws SQLException {

        preparedStatement.setString(1, isNotNullElse(drill, d -> d.getName(), ""));
        preparedStatement.setInt(2, (isNotNullElse(drill, d -> d.getCategory().getID(), 0)));
        preparedStatement.setInt(3, (isNotNullElse(drill, d -> d.getDifficulty().getID(), 0)));
        preparedStatement.setInt(4, (isNotNullElse(drill, d -> d.getParticipation().getID(), 0)));
        preparedStatement.setString(5, (isNotNullElse(drill, d -> d.getDescription(), "")));
        preparedStatement.setBoolean(6, (isNotNullElse(drill, d -> d.getStation(), false)));
        preparedStatement.setInt(7, (isNotNullElse(drill, d -> d.getPicture().getID(), 0)));
        preparedStatement.setInt(8, (isNotNullElse(drill, d -> d.getPuckPosition().getID(), 0)));
        return preparedStatement;
    }

    public void writeNewDrill(Drill drill) {
        String query = "INSERT INTO drill" +
                "(name,category,difficulty,participation,description,station,image,puckPosition)" + "VALUES (?,?,?,?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setDrill(preparedStatement, drill);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
