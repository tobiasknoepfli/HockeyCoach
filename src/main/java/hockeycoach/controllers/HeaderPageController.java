package hockeycoach.controllers;

import hockeycoach.PresentationModels.*;

import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static hockeycoach.AppStarter.*;

public class HeaderPageController {

    @FXML
    private Button closeButton;

    @FXML
    private AnchorPane headerPane;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button homeButton;

    @FXML
    private Button teamButton;

    @FXML
    private Button playerButton;

    @FXML
    private Button trainingButton;

    @FXML
    private Button gameEditorButton;

    @FXML
    private void closeButtonAction() {
        Platform.exit();
    }

    @FXML
    private void homeButtonAction() {
        StartPagePresentationModel pm = new StartPagePresentationModel();
        loadStages("Home","/hockeycoach/start-page.fxml",pm);
    }

    @FXML
    public void teamButtonAction() {
        TeamPagePresentationModel pm = new TeamPagePresentationModel();
        loadStages("Team","/hockeycoach/team-page.fxml",pm);
    }

    @FXML
    private void playerButtonAction() {
        PlayerPagePresentationModel pm = new PlayerPagePresentationModel();
        loadStages("Player", "/hockeycoach/player-page.fxml", pm);
    }

    @FXML
    private void trainingButtonAction() {
        TrainingPagePresentationModel pm = new TrainingPagePresentationModel();
        loadStages("Training", "/hockeycoach/training-page.fxml",pm);
    }

    @FXML
    private void trainingEditorAction() {
        TrainingEditorPagePresentationModel pm = new TrainingEditorPagePresentationModel();
        loadStages("TrainingEditor", "/hockeycoach/training-editor-page.fxml", pm);
    }

    @FXML
    private void newTeamButtonAction() {
        NewTeamPagePresentationModel pm = new NewTeamPagePresentationModel();
        loadStages("NewTeam","/hockeycoach/new-team-pageOLD.fxml", pm);
    }

    @FXML
    private void newPlayerButtonAction() {
        NewPlayerPresentationModel pm = new NewPlayerPresentationModel();
        loadStages("NewPlayer","/hockeycoach/new-player-page.fxml",pm);
    }

    @FXML
    private void gameEditorAction() {
        GameEditorPresentationModel pm = new GameEditorPresentationModel();
        loadStages("GameEditor","/hockeycoach/game-editor-page.fxml", pm);
    }

    private void loadStages(String buttonAction, String fxml, PresentationModel presentationModel) {
        if (openStages.containsKey(buttonAction)) {
            Stage existingStage = openStages.get(buttonAction);
            existingStage.toFront();
        } else {
            try {
                Stage newStage = new Stage();

                newStage.setX(0);
                newStage.setY(80);
                newStage.initStyle(StageStyle.UNDECORATED);

                FXMLLoader newStageLoader = new FXMLLoader(getClass().getResource(fxml));
                Pane newPane = newStageLoader.load();

                Scene contentScene = new Scene(newPane, WIDTH, HEIGHT - BAR_HEIGHT);
                newStage.setScene(contentScene);

                newStage.show();

                presentationModel.initializeControls(newPane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

