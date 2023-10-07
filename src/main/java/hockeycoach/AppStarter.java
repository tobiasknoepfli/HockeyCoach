package hockeycoach;

import hockeycoach.PresentationModels.StartPagePresentationModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppStarter extends Application {
    public static final String DB_URL = "jdbc:ucanaccess://src/main/resources/hockeycoach/files/database/hockeydb.accdb";

    public static double WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public static double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    public static double BAR_HEIGHT = 80;

    public static Map<String, Stage> openStages = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setWidth(WIDTH);
        stage.setHeight(BAR_HEIGHT);
        stage.setX(0);
        stage.setY(0);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader mainPaneLoader = new FXMLLoader(getClass().getResource("files/fxml/header-page.fxml"));
        Pane root = mainPaneLoader.load();
        Scene toolbarScene = new Scene(root, WIDTH, HEIGHT);

        stage.setScene(toolbarScene);
        stage.show();




        Stage contentStage = new Stage();

        contentStage.setX(0);
        contentStage.setY(80);
        contentStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader startPageLoader = new FXMLLoader(getClass().getResource("files/fxml/start-page.fxml"));
        Pane content = startPageLoader.load();

        Scene contentScene = new Scene(content, WIDTH, HEIGHT-BAR_HEIGHT);
        contentStage.setScene(contentScene);

        StartPagePresentationModel startPagePresentationModel = new StartPagePresentationModel();
        startPagePresentationModel.initializeControls(content);

        contentStage.show();
        openStages.put("Home",contentStage);
    }


    public static void main(String[] args) {
        launch();
    }
}