package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.Picture;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBImageLoader extends DBLoader {
    private Picture setPicture(ResultSet resultSet) {
        Picture picture = new Picture();
        try {
            picture.setID(resultSet.getInt("ID"));
            picture.setImage(picture.byteToImage(loadImage(picture.getID())));
            picture.setPictureName(resultSet.getString("name"));
        } catch (SQLException | IOException e) {
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
                Picture picture = new Picture();

                picture = setPicture(resultSet);

                allPictures.add(picture);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPictures;

    }


    private byte[] loadImage(int imageID) throws SQLException, IOException {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT image FROM image WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, imageID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        InputStream inputStream = resultSet.getBinaryStream("image");
                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        int nRead;
                        byte[] data = new byte[1024];
                        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                            buffer.write(data, 0, nRead);
                        }
                        return buffer.toByteArray();
                    }
                }
            }
        }
        return null;
    }


//    public void showImage() {
//
//        try {
//            byte[] imageData = dbImageWriter.retrieveImage(1);
//            if (imageData != null) {
//                Image image = new Image(new ByteArrayInputStream(imageData));
//                teamLogo.setImage(image);
//            } else {
//                // Handle the case where no image was found
//            }
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//    }
}
