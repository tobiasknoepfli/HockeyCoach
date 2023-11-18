package hockeycoach.DB.DBWriter;

import hockeycoach.mainClasses.Picture;

import java.sql.*;

import static hockeycoach.AppStarter.DB_URL;

public class DBImageWriter {
    public int saveImage(Picture picture) {
        int imageID = -1;

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            String query = "INSERT INTO image (imageLink, imageName) VALUES (?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1,picture.getTargetPath());
                preparedStatement.setString(2, picture.getPictureName());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    imageID = generatedKeys.getInt(1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageID;
    }



}

