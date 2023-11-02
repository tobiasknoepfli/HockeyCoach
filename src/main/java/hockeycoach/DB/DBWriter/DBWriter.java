package hockeycoach.DB.DBWriter;

import hockeycoach.mainClasses.*;
import hockeycoach.mainClasses.Lines.BoxplayLine;
import hockeycoach.mainClasses.Lines.Line;
import hockeycoach.mainClasses.Lines.PowerplayLine;
import hockeycoach.mainClasses.Lines.SubstituteLine;

import java.sql.*;
import java.sql.Date;

import static hockeycoach.AppStarter.DB_URL;

public class DBWriter {
    public void writeNewLine(Line line) {
        String query = "INSERT INTO line" +
                "(gameID,lineNr,goalkeeper,defenderLeft,defenderRight,center,forwardLeft,forwardRight) " +
                "VALUES ?, ?, ?, ?, ?, ?, ?, ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, line.getGameID());
            preparedStatement.setInt(2, line.getLineNr());
            preparedStatement.setInt(3, line.getGoalkeeper().getPlayerID());
            preparedStatement.setInt(4, line.getDefenderLeft().getPlayerID());
            preparedStatement.setInt(5, line.getDefenderRight().getPlayerID());
            preparedStatement.setInt(6, line.getCenter().getPlayerID());
            preparedStatement.setInt(7, line.getForwardLeft().getPlayerID());
            preparedStatement.setInt(8, line.getForwardRight().getPlayerID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int writeGame(Game game) {
        int generatedGameID = -1;

        String query = "INSERT INTO game" +
                "(gameDate, gameTime, opponent, stadium, team, captain, assistant1, assistant2) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setDate(1, Date.valueOf(game.getGameDate()));
            preparedStatement.setTime(2, game.getGameTime());
            preparedStatement.setString(3, game.getOpponent());
            preparedStatement.setInt(4, game.getStadium().getStadiumID());
            preparedStatement.setInt(5, game.getTeam());
            preparedStatement.setInt(6, game.getCaptain().getPlayerID());
            preparedStatement.setInt(7, game.getAssistant1().getPlayerID());
            preparedStatement.setInt(8, game.getAssistant2().getPlayerID());

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

    public void writeLine(Game game, Line line) {
        String query = "INSERT INTO line" +
                "(gameID, lineNr, goalkeeper,defenderLeft,defenderRight,center,forwardLeft,forwardRight) " +
                "VALUES ?, ?, ?, ?, ?, ?, ?, ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, line.getGameID());
            preparedStatement.setInt(2, line.getLineNr());
            preparedStatement.setInt(3, line.getGoalkeeper().getPlayerID());
            preparedStatement.setInt(4, line.getDefenderLeft().getPlayerID());
            preparedStatement.setInt(5, line.getDefenderRight().getPlayerID());
            preparedStatement.setInt(6, line.getCenter().getPlayerID());
            preparedStatement.setInt(7, line.getForwardLeft().getPlayerID());
            preparedStatement.setInt(8, line.getForwardRight().getPlayerID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writePPLine(Game game, PowerplayLine powerplayLine) {
        String query = "INSERT INTO powerplayLine" +
                "(gameID, lineNr,defenderLeft,defenderRight,center,forwardLeft,forwardRight) " +
                "VALUES ?, ?, ?, ?, ?, ?, ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, powerplayLine.getGameID());
            preparedStatement.setInt(2, powerplayLine.getLineNr());
            preparedStatement.setInt(3, powerplayLine.getDefenderLeft().getPlayerID());
            preparedStatement.setInt(4, powerplayLine.getDefenderRight().getPlayerID());
            preparedStatement.setInt(5, powerplayLine.getCenter().getPlayerID());
            preparedStatement.setInt(6, powerplayLine.getForwardLeft().getPlayerID());
            preparedStatement.setInt(7, powerplayLine.getForwardRight().getPlayerID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeBPLine(Game game, BoxplayLine boxplayLine) {
        String query = "INSERT INTO boxplayLine" +
                "(gameID, lineNr,defenderLeft,defenderRight,forwardLeft,forwardRight) " +
                "VALUES ?, ?, ?, ?, ?, ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, boxplayLine.getGameID());
            preparedStatement.setInt(2, boxplayLine.getLineNr());
            preparedStatement.setInt(3, boxplayLine.getDefenderLeft().getPlayerID());
            preparedStatement.setInt(4, boxplayLine.getDefenderRight().getPlayerID());
            preparedStatement.setInt(5, boxplayLine.getForwardLeft().getPlayerID());
            preparedStatement.setInt(6, boxplayLine.getForwardRight().getPlayerID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeSubstituteLine(Game game, SubstituteLine substituteLine) {
        String query = "INSERT INTO substituteLine " +
                "(gameID,lineNr,goalkeeper1,goalkeeper2,goalkeeper3,defender1,defender2,defender3,forward1,forward2,forward3,boxplayDefender1,boxplayDefender2,boxplayForward1,boxplayForward2)" +
                "VALUES ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, substituteLine.getGameID());
            preparedStatement.setInt(2, substituteLine.getLineNr());
            preparedStatement.setInt(3, substituteLine.getGoalkeeper1().getPlayerID());
            preparedStatement.setInt(4, substituteLine.getGoalkeeper2().getPlayerID());
            preparedStatement.setInt(5, substituteLine.getGoalkeeper3().getPlayerID());
            preparedStatement.setInt(6, substituteLine.getDefender1().getPlayerID());
            preparedStatement.setInt(7, substituteLine.getDefender2().getPlayerID());
            preparedStatement.setInt(8, substituteLine.getDefender3().getPlayerID());
            preparedStatement.setInt(9, substituteLine.getForward1().getPlayerID());
            preparedStatement.setInt(10, substituteLine.getForward2().getPlayerID());
            preparedStatement.setInt(11, substituteLine.getForward3().getPlayerID());
            preparedStatement.setInt(12, substituteLine.getBoxplayDefender1().getPlayerID());
            preparedStatement.setInt(13, substituteLine.getBoxplayDefender2().getPlayerID());
            preparedStatement.setInt(14, substituteLine.getBoxplayForward1().getPlayerID());
            preparedStatement.setInt(15, substituteLine.getBoxplayForward2().getPlayerID());

            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
