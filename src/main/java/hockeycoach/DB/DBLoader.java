package hockeycoach.DB;

import hockeycoach.mainClasses.*;
import hockeycoach.supportClasses.PlayerXTeam;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static hockeycoach.AppStarter.DB_URL;

public class DBLoader {
    public ArrayList<Player> getAllPlayers(String query) {
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
                game.setGameID(resultSet.getInt("ID"));
                game.setGameDate(resultSet.getDate("gameDate").toLocalDate());
                game.setGameTime(resultSet.getTime("gameTime").toLocalTime());
                game.setOpponent(resultSet.getString("opponent"));
                game.setStadium(resultSet.getString("stadium"));
                game.setTeam(resultSet.getInt("team"));
                game.setCaptain(getPlayerByID(resultSet.getInt("captain")));
                game.setAssistant1(getPlayerByID(resultSet.getInt("assistant1")));
                game.setAssistant2(getPlayerByID(resultSet.getInt("assistant2")));

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

    public TrainingLines getTrainingLines(String query) {

        TrainingLines trainingLines = new TrainingLines();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                trainingLines.setTrainingID(resultSet.getInt("trainingID"));
                trainingLines.setJersey1(resultSet.getString("jersey1"));
                trainingLines.setJersey2(resultSet.getString("jersey2"));
                trainingLines.setJersey3(resultSet.getString("jersey3"));
                trainingLines.setJersey4(resultSet.getString("jersey4"));
                trainingLines.setJersey5(resultSet.getString("jersey5"));
                trainingLines.setJersey6(resultSet.getString("jersey6"));
                trainingLines.setGk1(getPlayerByID(resultSet.getInt("gk1")));
                trainingLines.setGk2(getPlayerByID(resultSet.getInt("gk2")));
                trainingLines.setGk3(getPlayerByID(resultSet.getInt("gk3")));
                trainingLines.setGk4(getPlayerByID(resultSet.getInt("gk4")));
                trainingLines.setGk5(getPlayerByID(resultSet.getInt("gk5")));
                trainingLines.setGk6(getPlayerByID(resultSet.getInt("gk6")));
                trainingLines.setDl1(getPlayerByID(resultSet.getInt("Dl1")));
                trainingLines.setDl2(getPlayerByID(resultSet.getInt("Dl2")));
                trainingLines.setDl3(getPlayerByID(resultSet.getInt("Dl3")));
                trainingLines.setDl4(getPlayerByID(resultSet.getInt("Dl4")));
                trainingLines.setDl5(getPlayerByID(resultSet.getInt("Dl5")));
                trainingLines.setDl6(getPlayerByID(resultSet.getInt("Dl6")));
                trainingLines.setDr1(getPlayerByID(resultSet.getInt("Dr1")));
                trainingLines.setDr2(getPlayerByID(resultSet.getInt("Dr2")));
                trainingLines.setDr3(getPlayerByID(resultSet.getInt("Dr3")));
                trainingLines.setDr4(getPlayerByID(resultSet.getInt("Dr4")));
                trainingLines.setDr5(getPlayerByID(resultSet.getInt("Dr5")));
                trainingLines.setDr6(getPlayerByID(resultSet.getInt("Dr6")));
                trainingLines.setC1(getPlayerByID(resultSet.getInt("C1")));
                trainingLines.setC2(getPlayerByID(resultSet.getInt("C2")));
                trainingLines.setC3(getPlayerByID(resultSet.getInt("C3")));
                trainingLines.setC4(getPlayerByID(resultSet.getInt("C4")));
                trainingLines.setC5(getPlayerByID(resultSet.getInt("C5")));
                trainingLines.setC6(getPlayerByID(resultSet.getInt("C6")));
                trainingLines.setFl1(getPlayerByID(resultSet.getInt("Fl1")));
                trainingLines.setFl2(getPlayerByID(resultSet.getInt("Fl2")));
                trainingLines.setFl3(getPlayerByID(resultSet.getInt("Fl3")));
                trainingLines.setFl4(getPlayerByID(resultSet.getInt("Fl4")));
                trainingLines.setFl5(getPlayerByID(resultSet.getInt("Fl5")));
                trainingLines.setFl6(getPlayerByID(resultSet.getInt("Fl6")));
                trainingLines.setFr1(getPlayerByID(resultSet.getInt("Fr1")));
                trainingLines.setFr2(getPlayerByID(resultSet.getInt("Fr2")));
                trainingLines.setFr3(getPlayerByID(resultSet.getInt("Fr3")));
                trainingLines.setFr4(getPlayerByID(resultSet.getInt("Fr4")));
                trainingLines.setFr5(getPlayerByID(resultSet.getInt("Fr5")));
                trainingLines.setFr6(getPlayerByID(resultSet.getInt("Fr6")));

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainingLines;
    }

    public Player getPlayerByID(int playerID) {
        Player player = new Player();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            String query = "SELECT * FROM player WHERE playerID = " + playerID;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
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
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
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

        return drillTagList;
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

                if (drill != null) {
                    drill.setTable(table);
                    drill.setSortingIndex(getSortingIndex(drillID, trainingID));
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

    public ArrayList<PlayerXTeam> getPlayerXTeam(String query) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerXteamList;
    }

    public List<Line> getLines(String query) {
        List<Line> lines = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Line line = new Line();
                line.setGameID(resultSet.getInt("gameID"));
                line.setLineNr(resultSet.getInt("lineNr"));
                line.setGoalkeeper(getPlayerByID(resultSet.getInt("goalkeeper")));
                line.setDefenderLeft(getPlayerByID(resultSet.getInt("defenderLeft")));
                line.setDefenderRight(getPlayerByID(resultSet.getInt("defenderRight")));
                line.setCenter(getPlayerByID(resultSet.getInt("center")));
                line.setForwardLeft(getPlayerByID(resultSet.getInt("forwardLeft")));
                line.setForwardRight(getPlayerByID(resultSet.getInt("forwardRight")));

                lines.add(line);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<PowerplayLine> getPPLines(String query) {
        List<PowerplayLine> lines = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                PowerplayLine line = new PowerplayLine();
                line.setGameID(resultSet.getInt("gameID"));
                line.setLineNr(resultSet.getInt("lineNr"));
                line.setDefenderLeft(getPlayerByID(resultSet.getInt("defenderLeft")));
                line.setDefenderRight(getPlayerByID(resultSet.getInt("defenderRight")));
                line.setCenter(getPlayerByID(resultSet.getInt("center")));
                line.setForwardLeft(getPlayerByID(resultSet.getInt("forwardLeft")));
                line.setForwardRight(getPlayerByID(resultSet.getInt("forwardRight")));

                lines.add(line);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<BoxplayLine> getBPLines(String query) {
        List<BoxplayLine> lines = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                BoxplayLine line = new BoxplayLine();
                line.setGameID(resultSet.getInt("gameID"));
                line.setLineNr(resultSet.getInt("lineNr"));
                line.setDefenderLeft(getPlayerByID(resultSet.getInt("defenderLeft")));
                line.setDefenderRight(getPlayerByID(resultSet.getInt("defenderRight")));
                line.setForwardLeft(getPlayerByID(resultSet.getInt("forwardLeft")));
                line.setForwardRight(getPlayerByID(resultSet.getInt("forwardRight")));

                lines.add(line);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public SubstituteLine getSubLines(String query) {
        SubstituteLine line = new SubstituteLine();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                line.setGameID(resultSet.getInt("gameID"));
                line.setLineNr(resultSet.getInt("lineNr"));
                line.setGoalkeeper1(getPlayerByID(resultSet.getInt("goalkeeper1")));
                line.setGoalkeeper2(getPlayerByID(resultSet.getInt("goalkeeper2")));
                line.setGoalkeeper3(getPlayerByID(resultSet.getInt("goalkeeper3")));
                line.setDefender1(getPlayerByID(resultSet.getInt("defender1")));
                line.setDefender2(getPlayerByID(resultSet.getInt("defender2")));
                line.setDefender3(getPlayerByID(resultSet.getInt("defender3")));
                line.setForward1(getPlayerByID(resultSet.getInt("forward1")));
                line.setForward2(getPlayerByID(resultSet.getInt("forward2")));
                line.setForward3(getPlayerByID(resultSet.getInt("forward3")));
                line.setBoxplayDefender1(getPlayerByID(resultSet.getInt("boxplayDefender1")));
                line.setBoxplayDefender2(getPlayerByID(resultSet.getInt("boxplayDefender2")));
                line.setBoxplayForward1(getPlayerByID(resultSet.getInt("boxplayForward1")));
                line.setBoxplayForward2(getPlayerByID(resultSet.getInt("boxplayForward2")));

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return line;
    }
}