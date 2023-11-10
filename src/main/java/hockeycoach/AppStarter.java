package hockeycoach;

import hockeycoach.DB.DBLoader.*;
import hockeycoach.PresentationModels.PresentationModel;
import hockeycoach.PresentationModels.StartPresentationModel;
import hockeycoach.mainClasses.*;
import hockeycoach.mainClasses.Drills.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppStarter extends Application {
    public static final String DB_URL = "jdbc:ucanaccess://src/main/resources/hockeycoach/files/database/hockeydb.accdb";

    public static final String HOME = "home";
    public static final String TEAM = "team";
    public static final String PLAYER = "player";
    public static final String TRAINING = "training";
    public static final String GAME = "game";
    public static final String TRAINING_EDITOR = "trainingEditor";
    public static final String GAME_EDITOR = "gameEditor";
    public static final String NEW_TEAM = "newTeam";
    public static final String NEW_PLAYER = "newPlayer";
    public static final String PLAYER_TO_TEAM = "playerToTeam";
    public static final String NEW_DRILL = "drillEditor";
    public static final String STADIUM = "stadium";

    public static final String HOME_FXML = "/hockeycoach/files/fxml/start.fxml";
    public static final String TEAM_FXML = "/hockeycoach/files/fxml/team.fxml";
    public static final String PLAYER_FXML = "/hockeycoach/files/fxml/player.fxml";
    public static final String TRAINING_FXML = "/hockeycoach/files/fxml/training.fxml";
    public static final String GAME_FXML = "/hockeycoach/files/fxml/game.fxml";
    public static final String TRAINING_EDITOR_FXML = "/hockeycoach/files/fxml/training-editor.fxml";
    public static final String GAME_EDITOR_FXML = "/hockeycoach/files/fxml/game-editor.fxml";
    public static final String NEW_TEAM_FXML = "/hockeycoach/files/fxml/new-team.fxml";
    public static final String NEW_PLAYER_FXML = "/hockeycoach/files/fxml/new-player.fxml";
    public static final String PLAYER_TO_TEAM_FXML = "/hockeycoach/files/fxml/player-to-team.fxml";
    public static final String NEW_DRILL_FXML = "/hockeycoach/files/fxml/drill-editor.fxml";
    public static final String STADIUM_FXML = "/hockeycoach/files/fxml/stadium.fxml";

    public static final String LOGOS = "src/main/resources/hockeycoach/files/logos/";
    public static final String PHOTOS = "src/main/resources/hockeycoach/files/photos";

    public static final String BOARD = "src/main/resources/hockeycoach/files/background/Board.jpg";

    public static double WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public static double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    public static double BAR_HEIGHT = 80;

    public static Map<String, Stage> openStages = new HashMap<>();
    public static PresentationModel lastVisitedPM;
    public static String lastVisitedFXML = new String();
    public static String lastVisitedNodeName = new String();

    public static Stadium globalStadium = new Stadium();
    public static Team globalTeam = new Team();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setWidth(WIDTH);
        stage.setHeight(BAR_HEIGHT);
        stage.setX(0);
        stage.setY(0);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader mainPaneLoader = new FXMLLoader(getClass().getResource("files/fxml/header.fxml"));
        Pane root = mainPaneLoader.load();
        Scene toolbarScene = new Scene(root, WIDTH, HEIGHT);

        stage.setScene(toolbarScene);
        stage.show();


        Stage contentStage = new Stage();

        contentStage.setX(0);
        contentStage.setY(80);
        contentStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader startPageLoader = new FXMLLoader(getClass().getResource("files/fxml/start.fxml"));
        Pane content = startPageLoader.load();

        Scene contentScene = new Scene(content, WIDTH, HEIGHT - BAR_HEIGHT);
        contentStage.setScene(contentScene);

        StartPresentationModel startPresentationModel = new StartPresentationModel();
        startPresentationModel.initializeControls(content);

        contentStage.show();
        openStages.put(HOME, contentStage);
    }


    public static void main(String[] args) {
        launch();
    }
}