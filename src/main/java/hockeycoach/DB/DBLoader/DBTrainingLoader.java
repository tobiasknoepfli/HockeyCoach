package hockeycoach.DB.DBLoader;

import hockeycoach.DB.DBConverter.DBIDConverter;
import hockeycoach.mainClasses.Training;

import java.sql.*;
import java.util.ArrayList;

import static hockeycoach.AppStarter.DB_URL;

public class DBTrainingLoader extends DBLoader{
    DBIDConverter dbidConverter = new DBIDConverter();

    public Training setTraining(ResultSet resultSet){
        Training training = new Training();
        try{
            training.setTrainingID(resultSet.getInt("ID"));
            training.setTrainingDate(resultSet.getDate("trainingDate").toLocalDate());
            training.setTrainingTime(resultSet.getTime("trainingTime").toLocalTime());
            training.setStadium(dbidConverter.getStadiumFromID(resultSet.getInt("stadium")));
            training.setTeam(dbidConverter.getTeamFromID(resultSet.getInt("team")));
            training.setMainFocus(resultSet.getString("mainFocus"));
            training.setPointers(resultSet.getString("pointers"));
        } catch(SQLException e){
            e.printStackTrace();
        }
        return training;
    }

    public ArrayList<Training> getAllTrainings() {
        String query = "SELECT * FROM training";
        ArrayList<Training> allTrainingsList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Training training = new Training();
                training = setTraining(resultSet);
                allTrainingsList.add(training);
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTrainingsList;
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
