package hockeycoach.DB.DBWriter;

import hockeycoach.mainClasses.Lines.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static hockeycoach.AppStarter.DB_URL;

public class DBLineWriter {
    public PreparedStatement setLine(PreparedStatement preparedStatement, Line line) throws SQLException {
        preparedStatement.setInt(1, (line.getGameID() != 0) ? line.getGameID() : 0);
        preparedStatement.setInt(2, (line.getLineNr() != 0) ? line.getLineNr() : 0);
        preparedStatement.setInt(3, (line.getGoalkeeper() != null) ? line.getGoalkeeper().getID() : 0);
        preparedStatement.setInt(4, (line.getDefenderLeft() != null) ? line.getDefenderLeft().getID() : 0);
        preparedStatement.setInt(5, (line.getDefenderRight() != null) ? line.getDefenderRight().getID() : 0);
        preparedStatement.setInt(6, (line.getCenter() != null) ? line.getCenter().getID() : 0);
        preparedStatement.setInt(7, (line.getForwardLeft() != null) ? line.getForwardLeft().getID() : 0);
        preparedStatement.setInt(8, (line.getForwardRight() != null) ? line.getForwardRight().getID() : 0);

        return preparedStatement;
    }

    public PreparedStatement setPPLine(PreparedStatement preparedStatement, PowerplayLine powerplayLine) throws SQLException {
        preparedStatement.setInt(1, (powerplayLine.getGameID() != 0) ? powerplayLine.getGameID() : 0);
        preparedStatement.setInt(2, (powerplayLine.getLineNr() != 0) ? powerplayLine.getLineNr() : 0);
        preparedStatement.setInt(3, (powerplayLine.getDefenderLeft() != null) ? powerplayLine.getDefenderLeft().getID() : 0);
        preparedStatement.setInt(4, (powerplayLine.getDefenderRight() != null) ? powerplayLine.getDefenderRight().getID() : 0);
        preparedStatement.setInt(5, (powerplayLine.getCenter() != null) ? powerplayLine.getCenter().getID() : 0);
        preparedStatement.setInt(6, (powerplayLine.getForwardLeft() != null) ? powerplayLine.getForwardLeft().getID() : 0);
        preparedStatement.setInt(7, (powerplayLine.getForwardRight() != null) ? powerplayLine.getForwardRight().getID() : 0);

        return preparedStatement;
    }

    public PreparedStatement setBPLine(PreparedStatement preparedStatement, BoxplayLine boxplayLine) throws SQLException {
        preparedStatement.setInt(1, (boxplayLine.getGameID() != 0) ? boxplayLine.getGameID() : 0);
        preparedStatement.setInt(2, (boxplayLine.getLineNr() != 0) ? boxplayLine.getLineNr() : 0);
        preparedStatement.setInt(3, (boxplayLine.getDefenderLeft() != null) ? boxplayLine.getDefenderLeft().getID() : 0);
        preparedStatement.setInt(4, (boxplayLine.getDefenderRight() != null) ? boxplayLine.getDefenderRight().getID() : 0);
        preparedStatement.setInt(5, (boxplayLine.getForwardLeft() != null) ? boxplayLine.getForwardLeft().getID() : 0);
        preparedStatement.setInt(6, (boxplayLine.getForwardRight() != null) ? boxplayLine.getForwardRight().getID() : 0);

        return preparedStatement;
    }

    public PreparedStatement setSubstituteLine(PreparedStatement preparedStatement, SubstituteLine substituteLine) throws SQLException {
        preparedStatement.setInt(1, (substituteLine.getGameID() != 0) ? substituteLine.getGameID() : 0);
        preparedStatement.setInt(2, (substituteLine.getLineNr() != 0) ? substituteLine.getLineNr() : 0);
        preparedStatement.setInt(3, (substituteLine.getGoalkeeper1() != null) ? substituteLine.getGoalkeeper1().getID() : 0);
        preparedStatement.setInt(4, (substituteLine.getGoalkeeper2() != null) ? substituteLine.getGoalkeeper2().getID() : 0);
        preparedStatement.setInt(5, (substituteLine.getGoalkeeper3() != null) ? substituteLine.getGoalkeeper3().getID() : 0);
        preparedStatement.setInt(6, (substituteLine.getDefender1() != null) ? substituteLine.getDefender1().getID() : 0);
        preparedStatement.setInt(7, (substituteLine.getDefender2() != null) ? substituteLine.getDefender2().getID() : 0);
        preparedStatement.setInt(8, (substituteLine.getDefender3() != null) ? substituteLine.getDefender3().getID() : 0);
        preparedStatement.setInt(9, (substituteLine.getForward1() != null) ? substituteLine.getForward1().getID() : 0);
        preparedStatement.setInt(10, (substituteLine.getForward2() != null) ? substituteLine.getForward2().getID() : 0);
        preparedStatement.setInt(11, (substituteLine.getForward3() != null) ? substituteLine.getForward3().getID() : 0);
        preparedStatement.setInt(12, (substituteLine.getBoxplayDefender1() != null) ? substituteLine.getBoxplayDefender1().getID() : 0);
        preparedStatement.setInt(13, (substituteLine.getBoxplayDefender2() != null) ? substituteLine.getBoxplayDefender2().getID() : 0);
        preparedStatement.setInt(14, (substituteLine.getBoxplayForward1() != null) ? substituteLine.getBoxplayForward1().getID() : 0);
        preparedStatement.setInt(15, (substituteLine.getBoxplayForward2() != null) ? substituteLine.getBoxplayForward2().getID() : 0);

        return preparedStatement;
    }

    public PreparedStatement setNLine(PreparedStatement preparedStatement, NuclearLine nuclearLine) throws SQLException {
        preparedStatement.setInt(1, (nuclearLine.getGameID() != 0) ? nuclearLine.getGameID() : 0);
        preparedStatement.setInt(2, (nuclearLine.getLineNr() != 0) ? nuclearLine.getLineNr() : 0);
        preparedStatement.setInt(3, (nuclearLine.getDefenderLeft() != null) ? nuclearLine.getDefenderLeft().getID() : 0);
        preparedStatement.setInt(4, (nuclearLine.getDefenderRight() != null) ? nuclearLine.getDefenderRight().getID() : 0);
        preparedStatement.setInt(5, (nuclearLine.getCenter() != null) ? nuclearLine.getCenter().getID() : 0);
        preparedStatement.setInt(6, (nuclearLine.getForwardLeft() != null) ? nuclearLine.getForwardLeft().getID() : 0);
        preparedStatement.setInt(7, (nuclearLine.getForwardRight() != null) ? nuclearLine.getForwardRight().getID() : 0);

        return preparedStatement;
    }


    public void writeNewLine(Line line) {
        String query = "INSERT INTO line" +
                "(gameID,lineNr,goalkeeper,defenderLeft,defenderRight,center,forwardLeft,forwardRight) " +
                "VALUES ?, ?, ?, ?, ?, ?, ?, ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setLine(preparedStatement, line);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writePPLine(PowerplayLine powerplayLine) {
        String query = "INSERT INTO powerplayLine" +
                "(gameID, lineNr,defenderLeft,defenderRight,center,forwardLeft,forwardRight) " +
                "VALUES ?, ?, ?, ?, ?, ?, ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setPPLine(preparedStatement, powerplayLine);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeBPLine(BoxplayLine boxplayLine) {
        String query = "INSERT INTO boxplayLine" +
                "(gameID, lineNr,defenderLeft,defenderRight,forwardLeft,forwardRight) " +
                "VALUES ?, ?, ?, ?, ?, ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setBPLine(preparedStatement, boxplayLine);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeSubstituteLine(SubstituteLine substituteLine) {
        String query = "INSERT INTO substituteLine " +
                "(gameID,lineNr,goalkeeper1,goalkeeper2,goalkeeper3,defender1,defender2,defender3,forward1,forward2,forward3,boxplayDefender1,boxplayDefender2,boxplayForward1,boxplayForward2)" +
                "VALUES ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setSubstituteLine(preparedStatement, substituteLine);

            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeNuclearLine(NuclearLine nuclearLine) {
        String query = "INSERT INTO nuclearLine " +
                "(gameID,lineNr,defenderLeft,defenderRight,center,forwardLeft,forwardRight)" +
                "VALUES ?,?,?,?,?,?,?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setNLine(preparedStatement, nuclearLine);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
