package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.Game;

import java.sql.*;
import java.util.ArrayList;

import static hockeycoach.AppStarter.DB_URL;

public class DBGameLoader extends DBLoader{
    public Game setGame(ResultSet resultSet){
        Game game = new Game();
        try{
            game.setGameID(resultSet.getInt("ID"));
            game.setGameDate(resultSet.getDate("gameDate").toLocalDate());
            game.setGameTime(resultSet.getTime("gameTime").toLocalTime());
            game.setOpponent(resultSet.getString("opponent"));
            game.setStadium(resultSet.getString("stadium"));
            game.setTeam(resultSet.getInt("team"));
            game.setCaptain(getPlayerByID(resultSet.getInt("captain")));
            game.setAssistant1(getPlayerByID(resultSet.getInt("assistant1")));
            game.setAssistant2(getPlayerByID(resultSet.getInt("assistant2")));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return game;
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