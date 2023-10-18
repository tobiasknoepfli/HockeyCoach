package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.Lines.TrainingLines;

import java.sql.*;

import static hockeycoach.AppStarter.DB_URL;

public class DBTrainingLinesLoader extends DBLoader {
    public TrainingLines setTrainingLines(ResultSet resultSet){
        TrainingLines trainingLines = new TrainingLines();
        try{
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
        }catch(SQLException e){
            e.printStackTrace();
        }
        return trainingLines;
    }

    public TrainingLines getTrainingLines(String query) {

        TrainingLines trainingLines = new TrainingLines();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                trainingLines = setTrainingLines(resultSet);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainingLines;
    }
}
