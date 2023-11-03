package hockeycoach.DB.DBWriter;

import hockeycoach.controllers.StadiumController;
import hockeycoach.mainClasses.Stadium;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;

import java.sql.*;

import static hockeycoach.AppStarter.DB_URL;

public class DBTrainingWriter {

    public PreparedStatement setTraining(PreparedStatement preparedStatement, Training training) throws SQLException {

        preparedStatement.setDate(1, (Date.valueOf(training.getTrainingDate())));
        preparedStatement.setTime(2, Time.valueOf(training.getTrainingTime()));
        preparedStatement.setInt(3,training.getStadium().getStadiumID());
        preparedStatement.setInt(4,training.getTeam());
        preparedStatement.setString(5,training.getMainFocus());
        preparedStatement.setString(6,training.getPointers());

        return preparedStatement;
    }

    public void writeNewTraining(Training training){
        String query = "INSERT INTO training" +  "(trainingDate,trainingTime,stadium,team,mainFocus,pointers)" +
                " VALUES ?,?,?,?,?,?";

        try(Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            setTraining(preparedStatement,training);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
