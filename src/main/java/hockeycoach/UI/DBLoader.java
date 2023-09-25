package hockeycoach.UI;

import hockeycoach.mainClasses.*;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DBLoader {
    private static final String DB_URL = "jdbc:ucanaccess://src/main/java/hockeycoach/files/database/hockeydb.accdb";
    public ArrayList<Player> getAllPlayers(String query){
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
                player.setPositions(resultSet.getString("positions"));
                player.setStrengths(resultSet.getString("strengths"));
                player.setWeaknesses(resultSet.getString("weaknesses"));
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
                player.setJersey(getJersey("SELECT jersey FROM playerXteam WHERE playerID = " + player.getPlayerID() + " AND teamID = " + selectedTeamID));
                player.setPositions(resultSet.getString("positions"));
                player.setStrengths(resultSet.getString("strengths"));
                player.setWeaknesses(resultSet.getString("weaknesses"));
                player.setRole(getRole("SELECT role FROM playerXteam WHERE playerID = " + player.getPlayerID() + " AND teamID = " + selectedTeamID));
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
        int jersey = 0;
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                jersey = resultSet.getInt("jersey");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jersey;
    }

    public String getRole(String query) {
        String role = "";
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                role = resultSet.getString(("role"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
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
                game.setTeam(resultSet.getInt("team"));

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
                training.setTrainingID(resultSet.getInt("trainingID"));
                training.setTrainingDate(resultSet.getDate("trainingDate"));
                training.setTrainingTime(resultSet.getTime("trainingTime"));
                training.setStadium(resultSet.getString("stadium"));
                training.setTeam(resultSet.getString("team"));
                training.setMainFocus(resultSet.getString("mainFocus"));
                training.setPointers(resultSet.getString("pointers"));

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

    public List<Team> getTeamsForPlayer(String query) {
        List<Team> teamList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Team team = new Team();
                team.setName(resultSet.getString("name"));
                team.setTeamID(resultSet.getInt("teamID"));
                teamList.add(team);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamList;
    }

    public List<Drill> getDrills(String query) {
        List<Drill> drillList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Drill drill = new Drill();
                drill.setDrillID(resultSet.getInt("drillID"));
                drill.setName(resultSet.getString("name"));
                drill.setCategory(resultSet.getString("category"));
                drill.setDifficulty(resultSet.getInt("difficulty"));
                drill.setParticipation(resultSet.getString("participation"));
                drill.setDescription(resultSet.getString("description"));
                drill.setStation(resultSet.getBoolean("station"));
                drill.setTags(getDrillTags("SELECT drillTag FROM drillXtag RIGHT JOIN tag ON drillID =" + drill.getDrillID()));
                drill.setImageLink(resultSet.getString("imageLink"));
                drill.setPuckPosition(resultSet.getString("puckPosition"));
                drillList.add(drill);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drillList;
    }

    public ArrayList<String> getDrillTags(String query) {
        ArrayList<String> drillTags = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                drillTags.add(resultSet.getString(("drillTag")));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<String> drillTagList = drillTags.stream()
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));

        return  drillTagList;
    }

    public List<Drill> getTrainingDrills(String query, List<Drill> drillList, String table, int trainingID) {
        List<Drill> trainingDrillList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int drillID = resultSet.getInt("drillID");
                Drill drill = drillList.stream()
                        .filter(d -> d.getDrillID() == drillID)
                        .findFirst()
                        .orElse(null);

                if (drill !=null) {
                    drill.setTable(table);
                    drill.setSortingIndex(getSortingIndex(drillID,trainingID));
                    trainingDrillList.add(drill);
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        trainingDrillList.sort(Comparator.comparingInt(Drill::getSortingIndex));
        return trainingDrillList;
    }

    public int getSortingIndex(int drillID, int trainingID) {
        String query = "SELECT sortingIndex FROM trainingXdrills WHERE drillID = " + drillID + " AND trainingID = " + trainingID;
        int sortingIndex = 0;
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                sortingIndex = (resultSet.getInt(("sortingIndex")));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sortingIndex;
    }

//    public boolean getPriority(int drillID, int trainingID) {
//        String query = "SELECT priority FROM trainingXdrills WHERE drillID = " + drillID + " AND trainingID = " + trainingID;
//        boolean priority = false;
//        try {
//            Connection connection = DriverManager.getConnection(DB_URL);
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                priority = (resultSet.getBoolean("priority"));
//            }
//
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return priority;
//    }

    public ArrayList<PlayerXTeam> getPlayerXTeam(String query){
        ArrayList<PlayerXTeam> playerXteamList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                PlayerXTeam playerXteam = new PlayerXTeam();
                playerXteam.setID(resultSet.getInt("ID"));
                playerXteam.setPlayerID(resultSet.getInt("playerID"));
                playerXteam.setTeamID(resultSet.getInt("teamID"));
                playerXteam.setJersey(resultSet.getInt("jersey"));
                playerXteam.setRole(resultSet.getString("role"));

                playerXteamList.add(playerXteam);

                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return playerXteamList;
    }
}