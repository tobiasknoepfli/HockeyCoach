package hockeycoach.DB.DBWriter;

import hockeycoach.DB.DBLoader.DBPlayerLoader;
import hockeycoach.mainClasses.Picture;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
import hockeycoach.supportClasses.checkups.NullCheck;

import java.sql.*;
import java.time.LocalDate;

import static hockeycoach.AppStarter.DB_URL;
import static hockeycoach.supportClasses.checkups.NullCheck.isNotNullElse;

public class DBPlayerWriter {
    DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();

    public PreparedStatement setPlayer(PreparedStatement preparedStatement, Player player) throws SQLException {

        preparedStatement.setString(1, isNotNullElse(player, p -> p.getFirstName(), ""));
        preparedStatement.setString(2, isNotNullElse(player, p -> p.getLastName(), ""));
        LocalDate birthday = isNotNullElse(player, p -> p.getBirthday(), LocalDate.of(1900, 1, 1));
        preparedStatement.setDate(3, Date.valueOf(birthday));
        preparedStatement.setString(4, isNotNullElse(player, p -> p.getStreet(), ""));
        preparedStatement.setInt(5, isNotNullElse(player, p -> p.getZip(), 0));
        preparedStatement.setString(6, isNotNullElse(player, p -> p.getCity(), ""));
        preparedStatement.setString(7, isNotNullElse(player, p -> p.getCountry(), ""));
        preparedStatement.setString(8, isNotNullElse(player, p -> p.getaLicence(), ""));
        preparedStatement.setString(9, isNotNullElse(player, p -> p.getbLicence(), ""));
        preparedStatement.setString(10, isNotNullElse(player, p -> p.getPhone(), ""));
        preparedStatement.setString(11, isNotNullElse(player, p -> p.geteMail(), ""));
        preparedStatement.setString(12, isNotNullElse(player, p -> p.getPositions(), ""));
        preparedStatement.setString(13, isNotNullElse(player, p -> p.getStrengths(), ""));
        preparedStatement.setString(14, isNotNullElse(player, p -> p.getWeaknesses(), ""));
        preparedStatement.setString(15, isNotNullElse(player, p -> p.getStick(), ""));
        preparedStatement.setInt(16, isNotNullElse(player.getPicture(), Picture::getID, 0));
        preparedStatement.setString(17, isNotNullElse(player, p -> p.getNotes(), ""));
        preparedStatement.setInt(18,isNotNullElse(player,p -> p.getRatingPuckSkills(),51));
        preparedStatement.setInt(19,isNotNullElse(player,p-> p.getRatingDefence(),51));
        preparedStatement.setInt(20,isNotNullElse(player,p->p.getRatingSenses(),51));
        preparedStatement.setInt(21,isNotNullElse(player,p->p.getRatingSkating(),51));
        preparedStatement.setInt(22,isNotNullElse(player,p->p.getRatingShots(),51));
        preparedStatement.setInt(23,isNotNullElse(player,p->p.getRatingPhysical(),51));
        preparedStatement.setInt(24,isNotNullElse(player,p->p.getRatingOverall(),51));

        return preparedStatement;
    }

    public void writeNewPlayer(Player player) {
        String query = "INSERT INTO player" + "(firstName, lastName,birthday, street, zip,city, country,aLicence, bLicence, phone, eMail, positions, " +
                "strengths,weaknesses,stick,photoID,notes, " +
                "ratingPuckSkills, ratingDefence,ratingSenses,ratingSkating,ratingShots,ratingPhysical,ratingOverall) VALUES ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setPlayer(preparedStatement, player);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPlayerToTeam(Team team, Player player) {
        String query = "INSERT INTO playerXteam" + "(playerID, teamID, jersey, role) VALUES (?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, (player != null) ? player.getID() : 0);
            preparedStatement.setInt(2, (team != null) ? team.getID() : 0);
            preparedStatement.setInt(3, 0);
            preparedStatement.setString(4, "none");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
