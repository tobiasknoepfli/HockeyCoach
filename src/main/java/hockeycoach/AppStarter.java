package hockeycoach;

import hockeycoach.UI.PresentationModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppStarter extends Application {
    public static double WIDTH = 1220; //Screen.getPrimary().getVisualBounds().getWidth();
    public static double HEIGHT = 855; //Screen.getPrimary().getVisualBounds().getHeight();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        FXMLLoader fxmlLoader = new FXMLLoader(AppStarter.class.getResource("start-page.fxml"));
        Pane root = fxmlLoader.load();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Hockey Coach");
        stage.setScene(scene);
        stage.show();

        PresentationModel presentationModel = new PresentationModel();
        presentationModel.initializeControls(root);
    }

    public static void main(String[] args) {
        launch();
    }

}