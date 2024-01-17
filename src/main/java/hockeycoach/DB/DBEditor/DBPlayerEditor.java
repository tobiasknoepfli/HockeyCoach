package hockeycoach.DB.DBEditor;

import hockeycoach.mainClasses.Game;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
import hockeycoach.supportClasses.checkups.NullCheck;

import java.sql.*;

import static hockeycoach.AppStarter.DB_URL;
import static hockeycoach.supportClasses.checkups.NullCheck.isNotNullElse;

public class DBPlayerEditor {
    private PreparedStatement setPlayer(PreparedStatement preparedStatement, Player player) throws SQLException{
        preparedStatement.setString(1, isNotNullElse(player.getFirstName(),p->p,""));
        preparedStatement.setString(2,isNotNullElse(player.getLastName(),p->p,""));
        preparedStatement.setDate(3, (player.getBirthday() != null) ? Date.valueOf(player.getBirthday()) : Date.valueOf("01.01.1900"));
        preparedStatement.setString(4,isNotNullElse(player.getStreet(),p->p,""));
        preparedStatement.setInt(5,isNotNullElse(player.getZip(),p->p,0));
        preparedStatement.setString(6,isNotNullElse(player.getCity(),p->p,""));
        preparedStatement.setString(7,isNotNullElse(player.getCountry(),p->p,""));
        preparedStatement.setString(8,isNotNullElse(player.getaLicence(),p->p,""));
        preparedStatement.setString(9,isNotNullElse(player.getbLicence(),p->p,""));
        preparedStatement.setString(10,isNotNullElse(player.getPhone(),p->p,""));
        preparedStatement.setString(11,isNotNullElse(player.geteMail(),p->p,""));
        preparedStatement.setString(12,isNotNullElse(player.getPositions(),p->p,""));
        preparedStatement.setString(13,isNotNullElse(player.getStrengths(),p->p,""));
        preparedStatement.setString(14,isNotNullElse(player.getWeaknesses(),p->p,""));
        preparedStatement.setString(15,isNotNullElse(player.getStick(),p->p,""));
        preparedStatement.setInt(16,isNotNullElse(player.getPicture(),p->p.getID(),0));
        preparedStatement.setString(17, isNotNullElse(player.getNotes(),p->p,""));
        preparedStatement.setInt(18,isNotNullElse(player.getRatingPuckSkills(),p->p,51));
        preparedStatement.setInt(19,isNotNullElse(player.getRatingDefence(),p->p,51));
        preparedStatement.setInt(20,isNotNullElse(player.getRatingSenses(),p->p,51));
        preparedStatement.setInt(21,isNotNullElse(player.getRatingSkating(),p->p,51));
        preparedStatement.setInt(22,isNotNullElse(player.getRatingShots(),p->p,51));
        preparedStatement.setInt(23,isNotNullElse(player.getRatingPhysical(),p->p,51));
        preparedStatement.setInt(24,isNotNullElse(player.getRatingOverall(),p->p,51));

        return preparedStatement;
    }

    public void editPlayer(Player player){
        String query = "UPDATE player SET "+
                "firstName = ?, lastName = ?, birthday = ?, street = ?, zip = ?, city = ?, country = ?, " +
                "aLicence = ?, bLicence = ?, phone = ?, eMail = ?, positions = ?, strengths = ?, weaknesses = ?, stick = ?, " +
                "photoID = ?, notes = ?, " +
                "ratingPuckSkills = ?, ratingDefence = ?, ratingSenses = ?, ratingSkating = ?, ratingShots = ?, ratingPhysical = ?, ratingOverall = ? " +
                "WHERE ID = " + player.getID();

        try(Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            setPlayer(preparedStatement,player);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editJerseyAndRole(Player player, Team team) {
        String checkQuery = "SELECT COUNT(*) FROM playerXteam WHERE playerID = ? AND teamID = ?";
        String updateQuery = "UPDATE playerXteam SET jersey = ?, role = ? WHERE playerID = ? AND teamID = ?";
        String insertQuery = "INSERT INTO playerXteam (playerID, teamID, jersey, role) VALUES (?, ?, ?, ?)";

        boolean combinationExists = false;

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
             PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            checkStatement.setInt(1, player.getID());
            checkStatement.setInt(2, team.getID());
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                combinationExists = true;
                updateStatement.setInt(1, player.getJersey());
                updateStatement.setString(2, player.getRole());
                updateStatement.setInt(3, player.getID());
                updateStatement.setInt(4, team.getID());
                updateStatement.executeUpdate();
            }
            else {
                insertStatement.setInt(1, player.getID());
                insertStatement.setInt(2, team.getID());
                insertStatement.setInt(3, player.getJersey());
                insertStatement.setString(4, player.getRole());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
