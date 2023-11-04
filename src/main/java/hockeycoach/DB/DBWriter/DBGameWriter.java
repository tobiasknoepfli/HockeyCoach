package hockeycoach.DB.DBWriter;

import hockeycoach.mainClasses.Game;

import java.sql.*;

import static hockeycoach.AppStarter.DB_URL;

public class DBGameWriter {
    public PreparedStatement setGame(PreparedStatement preparedStatement, Game game) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(game.getGameDate()));
        preparedStatement.setTime(2, game.getGameTime());
        preparedStatement.setString(3, game.getOpponent());
        preparedStatement.setInt(4, game.getStadium().getStadiumID());
        preparedStatement.setInt(5, game.getTeam());
        preparedStatement.setInt(6, game.getCaptain().getPlayerID());
        preparedStatement.setInt(7, game.getAssistant1().getPlayerID());
        preparedStatement.setInt(8, game.getAssistant2().getPlayerID());
        preparedStatement.setInt(9, game.getPenalty1().getPlayerID());
        preparedStatement.setInt(10, game.getPenalty2().getPlayerID());
        preparedStatement.setInt(11, game.getEmptyNet1().getPlayerID());
        preparedStatement.setInt(12, game.getEmptyNet2().getPlayerID());

        return preparedStatement;
    }

    public int writeGame(Game game) {
        int generatedGameID = -1;

        String query = "INSERT INTO game" +
                "(gameDate, gameTime, opponent, stadium, team, captain, assistant1, assistant2, penalty1, penalty2, emptyNet1, emptyNet2) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            setGame(preparedStatement, game);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedGameID = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedGameID;
    }
}

