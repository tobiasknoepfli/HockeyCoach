package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.Training;

import java.sql.*;
import java.util.ArrayList;

import static hockeycoach.AppStarter.DB_URL;

public class DBTrainingLoader extends DBLoader{
    public Training setTraining(ResultSet resultSet){
        Training training = new Training();
        try{
            training.setTrainingID(resultSet.getInt("trainingID"));
            training.setTrainingDate(resultSet.getDate("trainingDate"));
            training.setTrainingTime(resultSet.getTime("trainingTime"));
            training.setStadium(resultSet.getString("stadium"));
            training.setTeam(resultSet.getInt("team"));
            training.setMainFocus(resultSet.getString("mainFocus"));
            training.setPointers(resultSet.getString("pointers"));
        } catch(SQLException e){
            e.printStackTrace();
        }
        return training;
    }
    public ArrayList<Training> getTrainings(String query) {
        ArrayList<Training> trainingList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Training training = new Training();
                training = setTraining(resultSet);
                trainingList.add(training);
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainingList;
    }
}
