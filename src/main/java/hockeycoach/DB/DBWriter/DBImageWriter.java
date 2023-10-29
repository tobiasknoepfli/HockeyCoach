package hockeycoach.DB.DBWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import static hockeycoach.AppStarter.DB_URL;

public class DBImageWriter {
    public int saveImage(String imagePath,String imageName) {
        int imageID = -1;
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            String query = "INSERT INTO image (image, imageName) VALUES (?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                FileInputStream fileInputStream = new FileInputStream(imagePath);
                preparedStatement.setBinaryStream(1, fileInputStream, (int) new File(imagePath).length());
                preparedStatement.setString(2,imageName);
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()){
                    imageID = generatedKeys.getInt(1);
                }

                fileInputStream.close();
            } catch (SQLException | IOException e) {
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

