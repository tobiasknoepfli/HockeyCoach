package hockeycoach.UI;

import hockeycoach.mainClasses.Game;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;

import java.sql.*;
import java.util.ArrayList;

public class DBLoader {
    private static final String DB_URL = "jdbc:ucanaccess://src/main/java/hockeycoach/files/database/hockeydb.accdb";

    public ArrayList<Player> getPlayers(String query, int selectedTeamID) {
        ArrayList<Player> playerList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Player player = new Player();
                player.setPlayerID(resultSet.getInt("playerID"));
                player.setFirstName(resultSet.getString("firstName"));
                player.setLastName(resultSet.getString("lastName"));
                player.setStreet(resultSet.getString("street"));
                player.setZip(resultSet.getInt("zip"));
                player.setCity(resultSet.getString("city"));
                player.setCountry(resultSet.getString("country"));
                player.setaLicence(resultSet.getString("aLicence"));
                player.setbLicence(resultSet.getString("bLicence"));
                player.setPhone(resultSet.getString("phone"));
                player.seteMail(resultSet.getString("eMail"));
                player.setJersey(getJersey("SELECT jersey FROM playerXteam WHERE playerID = "+ player.getPlayerID() + " AND teamID = "+ selectedTeamID));
                player.setPositions(resultSet.getString("positions"));
                player.setStrengths(resultSet.getString("strengths"));
                player.setWeaknesses(resultSet.getString("weaknesses"));
                player.setRole(resultSet.getString("role"));
                player.setStick(resultSet.getString("stick"));
                player.setPhotoPath(resultSet.getString("photoPath"));
                player.setNotes(resultSet.getString("notes"));

                playerList.add(player);

                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerList;
    }

    public int getJersey(String query) {
        int jersey=0;
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                jersey= resultSet.getInt("jersey");
            }


            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jersey;
    }

    public ArrayList<Game> getGames(String query) {
        ArrayList<Game> gameList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Game game = new Game();
                game.setGameDate(resultSet.getDate("gameDate"));
                game.setGameTime(resultSet.getTime("gameTime"));
                game.setOpponent(resultSet.getString("opponent"));
                game.setStadium(resultSet.getString("stadium"));
                game.setTeam(resultSet.getString("team"));

                gameList.add(game);

                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameList;
    }

    public ArrayList<Training> getTrainings(String query) {
        ArrayList<Training> trainingList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Training training = new Training();
                training.setTrainingDate(resultSet.getDate("trainingDate"));
                training.setTrainingTime(resultSet.getTime("trainingTime"));
                training.setStadium(resultSet.getString("stadium"));
                training.setTeam(resultSet.getString("team"));

                trainingList.add(training);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainingList;
    }

    public ArrayList<Team> getTeam(String query) {
        ArrayList<Team> teamList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Team team = new Team();
                team.setTeamID(resultSet.getInt("teamID"));
                team.setName(resultSet.getString("name"));
                team.setStadium(resultSet.getString("stadium"));
                team.setStreet(resultSet.getString("street"));
                team.setZip(resultSet.getInt("zip"));
                team.setCity(resultSet.getString("city"));
                team.setCountry(resultSet.getString("country"));
                team.setContactFirstName(resultSet.getString("contactFirstName"));
                team.setContactLastName(resultSet.getString("contactLastName"));
                team.setContactPhone(resultSet.getString("contactPhoneNr"));
                team.setContactEmail(resultSet.getString("contactEMail"));
                team.setWebsite(resultSet.getString("website"));
                team.setFounded(resultSet.getInt("founded"));
                team.setPresidentFirstName(resultSet.getString("presidentFirstName"));
                team.setPresidentLastName(resultSet.getString("presidentLastName"));
                team.setLeague(resultSet.getString("league"));
                team.setHeadCoachFirstName(resultSet.getString("headCoachFirstName"));
                team.setHeadCoachLastName(resultSet.getString("headCoachLastName"));
                team.setCaptainFirstName(resultSet.getString("captainFirstName"));
                team.setCaptainLastName(resultSet.getString("captainLastName"));
                team.setComments(resultSet.getString("comments"));
                team.setLogo(resultSet.getString("logo"));
                team.setNotes(resultSet.getString("notes"));

                teamList.add(team);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamList;
    }
}