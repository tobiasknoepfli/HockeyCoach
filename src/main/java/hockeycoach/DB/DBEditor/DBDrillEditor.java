package hockeycoach.DB.DBEditor;

import hockeycoach.mainClasses.Drills.Drill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static hockeycoach.AppStarter.DB_URL;
import static hockeycoach.supportClasses.checkups.NullCheck.isNotNullElse;

public class DBDrillEditor {
    public PreparedStatement setDrill(PreparedStatement preparedStatement, Drill drill) throws SQLException {

        preparedStatement.setString(1, isNotNullElse(drill, d -> d.getName(), ""));
        preparedStatement.setInt(2, (isNotNullElse(drill, d -> d.getCategory().getID(), 0)));
        preparedStatement.setInt(3, (isNotNullElse(drill, d -> d.getDifficulty().getID(), 0)));
        preparedStatement.setInt(4, (isNotNullElse(drill, d -> d.getParticipation().getID(), 0)));
        preparedStatement.setString(5, (isNotNullElse(drill, d -> d.getDescription(), "")));
        preparedStatement.setBoolean(6, (isNotNullElse(drill, d -> d.getStation(), false)));
        preparedStatement.setInt(7, (isNotNullElse(drill, d -> d.getPicture().getID(), 0)));
        preparedStatement.setString(8, isNotNullElse(drill, d -> d.getPurpose(), ""));
        return preparedStatement;
    }

    public void editDrill(Drill drill) {
        String query = "UPDATE drill SET "+
                "name = ?, category = ?, difficulty = ?, participation = ?, description = ?, " +
                "station = ?, image = ?, purpose = ? "+
                "WHERE ID = " + drill.getID();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setDrill(preparedStatement, drill);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
