package hockeycoach.DB.DBWriter;

import hockeycoach.controllers.StadiumController;
import hockeycoach.mainClasses.Drill;
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

    public int writeNewTraining(Training training){
        int generatedTrainingID = -1;
        String query = "INSERT INTO training" +  "(trainingDate,trainingTime,stadium,team,mainFocus,pointers)" +
                " VALUES ?,?,?,?,?,?";

        try(Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            setTraining(preparedStatement,training);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedTrainingID = generatedKeys.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return generatedTrainingID;
    }

    public void addDrillToTraining(int trainingID, Drill drill, String tableName, int sortingIndex){
        String query = "INSERT INTO trainingXdrills" + "(trainingID,drillID,tableName,sortingIndex)" + "VALUES ?,?,?,?";

        try(Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1,trainingID);
            preparedStatement.setInt(2,drill.getDrillID());
            preparedStatement.setString(3,tableName);
            preparedStatement.setInt(4,sortingIndex);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
