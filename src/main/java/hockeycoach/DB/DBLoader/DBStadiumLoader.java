package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.Stadium;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBStadiumLoader {
    public Stadium setStadium(ResultSet resultSet) {
        Stadium stadium = new Stadium();

        try {
            stadium.setStadiumID(resultSet.getInt("ID"));
            stadium.setStadiumName(resultSet.getString("stadiumName"));
            stadium.setStadiumAddress(resultSet.getString("stadiumAddress"));
            stadium.setStadiumZip(resultSet.getInt("stadiumZip"));
            stadium.setStadiumPlace(resultSet.getString("stadiumPlace"));
            stadium.setStadiumCountry(resultSet.getString("stadiumCountry"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stadium;
    }

    public List<Stadium> getAllStadiums(String query) {
        List<Stadium> allStadiums = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Stadium stadium = new Stadium();
                stadium = setStadium(resultSet);
                allStadiums.add(stadium);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStadiums;
    }

    public Stadium getStadium(int stadiumID) {
        Stadium stadium = new Stadium();
        String query = "SELECT * FROM stadium WHERE ID =" + stadiumID;

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            stadium = setStadium(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stadium;
    }
}
