package hockeycoach.DB.DBEditor;

import hockeycoach.mainClasses.Game;
import hockeycoach.supportClasses.checkups.NullCheck;

import java.net.ConnectException;
import java.sql.*;

import static hockeycoach.AppStarter.DB_URL;
import static hockeycoach.supportClasses.checkups.NullCheck.isNotNullElse;

public class DBGameEditor {
    private PreparedStatement setGame(PreparedStatement preparedStatement, Game game) throws SQLException {
        preparedStatement.setDate(1, (game.getGameDate() != null) ? Date.valueOf(game.getGameDate()) : Date.valueOf("01.01.1900"));
        preparedStatement.setTime(2,(game.getGameDate() != null) ? game.getGameTime() : Time.valueOf("00:00"));
        preparedStatement.setString(3,isNotNullElse(game.getOpponent(),g->g,""));
        preparedStatement.setInt(4,isNotNullElse(game.getStadium(),g->g.getID(),0));
        preparedStatement.setInt(5,isNotNullElse(game.getTeam(),g->g.getID(),0));
        preparedStatement.setInt(6,isNotNullElse(game.getCaptain(),g->g.getID(),0));
        preparedStatement.setInt(7,isNotNullElse(game.getAssistant1(),g->g.getID(),0));
        preparedStatement.setInt(8,isNotNullElse(game.getAssistant2(),g->g.getID(),0));
        preparedStatement.setInt(9,isNotNullElse(game.getPenalty1(),g->g.getID(),0));
        preparedStatement.setInt(10,isNotNullElse(game.getPenalty2(),g->g.getID(),0));
        preparedStatement.setInt(11,isNotNullElse(game.getEmptyNet1(),g->g.getID(),0));
        preparedStatement.setInt(12,isNotNullElse(game.getEmptyNet2(),g->g.getID(),0));

        return preparedStatement;
    }

    public void editGame(Game game){
        String query = "UPDATE game SET "+
                "gameDate = ?, gameTime = ?, opponent = ?, stadium = ?, team = ?, " +
                "captain = ?, assistant1 = ?, assistant2 = ?, penalty1 = ?, penalty2 = ?, " +
                "emptyNet1 = ?, emptyNet2 = ? "+
                "WHERE ID = " + game.getID();

        try(Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            setGame(preparedStatement,game);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
