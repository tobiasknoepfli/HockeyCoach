package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.Picture;
import javafx.scene.image.Image;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBImageLoader extends DBLoader {
    private Picture setPicture(ResultSet resultSet) {
        Picture picture = new Picture();
        try {
            picture.setID(resultSet.getInt("ID"));
            picture.setTargetPath(resultSet.getString("imageLink"));
            picture.setPictureName(resultSet.getString("imageName"));

            String imagePath = "file:"+ picture.getTargetPath();
            picture.setImage(new Image(imagePath));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return picture;
    }

    public Picture getPicture(String query) {
        Picture picture = new Picture();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                picture = setPicture(resultSet);
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return picture;
    }

    public List<Picture> getAllPictures() {
        String query = "SELECT * FROM image";
        List<Picture> allPictures = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                Picture picture = setPicture(resultSet);
                allPictures.add(picture);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPictures;
    }
}
