package hockeycoach.DB;

import hockeycoach.mainClasses.*;
import javafx.scene.shape.Box;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DBWriter {
    private static final String DB_URL = "jdbc:ucanaccess://src/main/java/hockeycoach/files/database/hockeydb.accdb";

    public void addPlayerToTeam(Team team, Player player) {
        String query = "INSERT INTO playerXteam" + "(playerID, teamID, jersey, role) VALUES (?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, player.getPlayerID());
            preparedStatement.setInt(2, team.getTeamID());
            preparedStatement.setInt(3, 0);
            preparedStatement.setString(4, "none");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeNewPlayer(Player player) {
        String query = "INSERT INTO player" + "(firstName, lastName, street, zip, country,aLicence, bLicence, phone, eMail, positions, " +
                "strengths,weaknesses,stick,photoPath,notes) VALUES ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, player.getFirstName());
            preparedStatement.setString(2, player.getLastName());
            preparedStatement.setString(3, player.getStreet());
            preparedStatement.setInt(4, player.getZip());
            preparedStatement.setString(5, player.getCountry());
            preparedStatement.setString(6, player.getaLicence());
            preparedStatement.setString(7, player.getbLicence());
            preparedStatement.setString(8, player.getPhone());
            preparedStatement.setString(9, player.geteMail());
            preparedStatement.setString(10, player.getPositions());
            preparedStatement.setString(11, player.getStrengths());
            preparedStatement.setString(12, player.getWeaknesses());
            preparedStatement.setString(13, player.getStick());
            preparedStatement.setString(14, player.getPhotoPath());
            preparedStatement.setString(15, player.getNotes());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeNewTeam(Team team) {
        String query = "INSERT INTO team" + "(name ,stadium, street, zip, city, country, contactFirstName, contactLastName, contactPhoneNr, contactEMail, " +
                " website, founded, presidentFirstName, presidentLastName, league, headCoachFirstName, headCoachLastName, captainFirstName, captainLastName, " +
                "logo, notes) VALUES ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, team.getName());
            preparedStatement.setString(2, team.getStadium());
            preparedStatement.setString(3, team.getStreet());
            preparedStatement.setInt(4, team.getZip());
            preparedStatement.setString(5, team.getCity());
            preparedStatement.setString(6, team.getCountry());
            preparedStatement.setString(7, team.getContactFirstName());
            preparedStatement.setString(8, team.getContactLastName());
            preparedStatement.setString(9, team.getContactPhone());
            preparedStatement.setString(10, team.getContactEmail());
            preparedStatement.setString(11, team.getWebsite());
            preparedStatement.setInt(12, team.getFounded());
            preparedStatement.setString(13, team.getPresidentFirstName());
            preparedStatement.setString(14, team.getPresidentLastName());
            preparedStatement.setString(15, team.getLeague());
            preparedStatement.setString(16, team.getHeadCoachFirstName());
            preparedStatement.setString(17, team.getHeadCoachLastName());
            preparedStatement.setString(18, team.getCaptainFirstName());
            preparedStatement.setString(19, team.getCaptainLastName());
            preparedStatement.setString(20, team.getLogo());
            preparedStatement.setString(21, team.getNotes());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
            preparedStatement.setString(4, game.getStadium());
            preparedStatement.setInt(5, game.getTeam());
            preparedStatement.setInt(6,game.getCaptain().getPlayerID());
            preparedStatement.setInt(7,game.getAssistant1().getPlayerID());
            preparedStatement.setInt(8,game.getAssistant2().getPlayerID());

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
                "(gameID,lineNr,goalkeeper1,goalkeeper2,defender1,defender2,defender3,forward1,forward2,forward3,boxplayDefender1,boxplayDefender2,boxplayForward1,boxplayForward2)" +
                "VALUES ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, substituteLine.getGameID());
            preparedStatement.setInt(2,substituteLine.getLineNr());
            preparedStatement.setInt(3, substituteLine.getGoalkeeper1().getPlayerID());
            preparedStatement.setInt(4,substituteLine.getGoalkeeper2().getPlayerID());
            preparedStatement.setInt(5,substituteLine.getDefender1().getPlayerID());
            preparedStatement.setInt(6, substituteLine.getDefender2().getPlayerID());
            preparedStatement.setInt(7, substituteLine.getDefender3().getPlayerID());
            preparedStatement.setInt(8,substituteLine.getForward1().getPlayerID());
            preparedStatement.setInt(9, substituteLine.getForward2().getPlayerID());
            preparedStatement.setInt(10,substituteLine.getForward3().getPlayerID());
            preparedStatement.setInt(11,substituteLine.getBoxplayDefender1().getPlayerID());
            preparedStatement.setInt(12,substituteLine.getBoxplayDefender2().getPlayerID());
            preparedStatement.setInt(13,substituteLine.getBoxplayForward1().getPlayerID());
            preparedStatement.setInt(14,substituteLine.getBoxplayForward2().getPlayerID());

            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
