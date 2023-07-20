package hockeycoach;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;

import java.io.IOException;
import java.sql.*;

public class AppStarter extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //determine screen size
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());

        FXMLLoader fxmlLoader = new FXMLLoader(AppStarter.class.getResource("start-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        stage.setTitle("Hockey Coach");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            String url = "jdbc:ucanaccess://src/main/java/hockeycoach/files/database/hockeydb.accdb";
            Connection connection = DriverManager.getConnection(url);

            Statement statement =connection.createStatement();

            String sql = "SELECT * FROM team";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getInt("zip"));
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}