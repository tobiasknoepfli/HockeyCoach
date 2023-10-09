package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.Player;
import hockeycoach.supportClasses.PlayerXTeam;

import java.sql.*;
import java.util.ArrayList;

import static hockeycoach.AppStarter.DB_URL;

public class DBPlayerXTeamLoader {
    public PlayerXTeam setPlayerXTeam(ResultSet resultSet){
        PlayerXTeam playerXteam =new PlayerXTeam();

        try{
            playerXteam.setID(resultSet.getInt("ID"));
            playerXteam.setPlayerID(resultSet.getInt("playerID"));
            playerXteam.setTeamID(resultSet.getInt("teamID"));
            playerXteam.setJersey(resultSet.getInt("jersey"));
            playerXteam.setRole(resultSet.getString("role"));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return  playerXteam;
    }


    public ArrayList<PlayerXTeam> getPlayerXTeam(String query) {
        ArrayList<PlayerXTeam> playerXteamList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                PlayerXTeam playerXteam = new PlayerXTeam();

                playerXteam=setPlayerXTeam(resultSet);

                playerXteamList.add(playerXteam);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerXteamList;
    }
}
