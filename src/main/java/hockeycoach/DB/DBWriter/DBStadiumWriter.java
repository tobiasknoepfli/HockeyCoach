package hockeycoach.DB.DBWriter;

import hockeycoach.DB.DBLoader.DBStadiumLoader;
import hockeycoach.mainClasses.Stadium;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBStadiumWriter {
    DBStadiumLoader dbStadiumLoader = new DBStadiumLoader();

    public PreparedStatement setStadium(PreparedStatement preparedStatement,Stadium stadium) throws SQLException {
        preparedStatement.setString(1,(stadium.getStadiumName() != null) ? stadium.getStadiumName():"");
        preparedStatement.setString(2,(stadium.getStadiumAddress() !=null) ? stadium.getStadiumAddress():"");
        preparedStatement.setInt(3,(stadium.getStadiumZip() != 0) ? stadium.getStadiumZip() : 0);
        preparedStatement.setString(4,(stadium.getStadiumCity() != null) ? stadium.getStadiumCity() : "");
        preparedStatement.setString(5,(stadium.getStadiumCountry() != null) ? stadium.getStadiumCountry() : "");

        return preparedStatement;
    }

    public void writeStadium(Stadium stadium){
        String query = "INSERT INTO stadium" + "(stadiumName, stadiumAddress,stadiumZip,stadiumPlace,stadiumCountry) VALUES (?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setStadium(preparedStatement, stadium);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Stadium getStadiumFromName(String stadiumName){
        List<Stadium> allStadiums = dbStadiumLoader.getAllStadiums();
        Stadium stadium = new Stadium();
        stadium = allStadiums.stream().filter(std -> std.getStadiumName().equals(stadiumName)).findFirst().orElse(null);
        return  stadium;
    }
}
