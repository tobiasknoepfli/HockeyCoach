package hockeycoach;

import hockeycoach.UI.PresentationModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Screen;

import java.io.IOException;
import java.sql.*;

public class AppStarter extends Application {
    public static double WIDTH = 1200; //Screen.getPrimary().getVisualBounds().getWidth();
    public static double HEIGHT= 800; //Screen.getPrimary().getVisualBounds().getHeight();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        FXMLLoader fxmlLoader = new FXMLLoader(AppStarter.class.getResource("start-page.fxml"));
        Pane root = fxmlLoader.load();

        Scene scene = new Scene(root, WIDTH,HEIGHT);
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