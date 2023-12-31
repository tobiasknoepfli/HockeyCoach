package hockeycoach.DB.DBLoader;

import hockeycoach.DB.DBConverter.DBIDConverter;
import hockeycoach.mainClasses.Game;

import java.sql.*;
import java.util.ArrayList;

import static hockeycoach.AppStarter.DB_URL;

public class DBGameLoader extends DBLoader{
    DBIDConverter dbidConverter = new DBIDConverter();

    public Game setGame(ResultSet resultSet){
        Game game = new Game();
        try{
            game.setID(resultSet.getInt("ID"));
            game.setGameDate(resultSet.getDate("gameDate").toLocalDate());
            game.setGameTime(resultSet.getTime("gameTime").toLocalTime());
            game.setOpponent(resultSet.getString("opponent"));
            game.setStadium(dbidConverter.getStadiumFromID(resultSet.getInt("stadium")));
            game.setTeam(dbidConverter.getTeamFromID(resultSet.getInt("team")));
            game.setCaptain(dbidConverter.getPlayerFromID(resultSet.getInt("captain")));
            game.setAssistant1(dbidConverter.getPlayerFromID(resultSet.getInt("assistant1")));
            game.setAssistant2(dbidConverter.getPlayerFromID(resultSet.getInt("assistant2")));
            game.setPenalty1(dbidConverter.getPlayerFromID(resultSet.getInt("penalty1")));
            game.setPenalty2(dbidConverter.getPlayerFromID(resultSet.getInt("penalty2")));
            game.setEmptyNet1(dbidConverter.getPlayerFromID(resultSet.getInt("emptyNet1")));
            game.setEmptyNet2(dbidConverter.getPlayerFromID(resultSet.getInt("emptyNet2")));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return game;
    }

    public ArrayList<Game> getAllGames(){
        String query = "SELECT * FROM game";
        ArrayList<Game> allGamesList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Game game = new Game();
                game = setGame(resultSet);
                allGamesList.add(game);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGamesList;
    }


    public ArrayList<Game> getGames(String query) {
        ArrayList<Game> gameList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Game game = new Game();
                game = setGame(resultSet);
                gameList.add(game);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameList;
    }
}
