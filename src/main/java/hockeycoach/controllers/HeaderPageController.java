package hockeycoach.controllers;

import hockeycoach.PresentationModels.*;

import hockeycoach.supportClasses.pmInterface;
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
import java.net.URL;

import static hockeycoach.AppStarter.*;

public class HeaderPageController extends Controller {
    @FXML
    private AnchorPane mainPane, headerPane;

    @FXML
    public Button closeButton, homeButton, teamButton, playerButton, trainingButton, gameButton;

    @FXML
    private void closeButtonAction() {
        Platform.exit();
    }

    @FXML
    private void homeButtonAction() {
        StartPagePresentationModel pm = new StartPagePresentationModel();
        loadStages("Home", HOME_FXML, pm);
    }

    @FXML
    public void teamButtonAction() {
        TeamPagePresentationModel pm = new TeamPagePresentationModel();
        loadStages("Team", TEAM_FXML, pm);
    }

    @FXML
    private void playerButtonAction() {
        PlayerPagePresentationModel pm = new PlayerPagePresentationModel();
        loadStages("Player", PLAYER_FXML, pm);
    }

    @FXML
    private void trainingButtonAction() {
        TrainingPagePresentationModel pm = new TrainingPagePresentationModel();
        loadStages("Training", TRAINING_FXML, pm);
    }

    @FXML
    private void trainingEditorAction() {
        TrainingEditorPagePresentationModel pm = new TrainingEditorPagePresentationModel();
        loadStages("TrainingEditor", TRAINING_EDITOR_FXML, pm);
    }

    @FXML
    private void newTeamButtonAction() {
        NewTeamPagePresentationModel pm = new NewTeamPagePresentationModel();
        loadStages("NewTeam", NEW_TEAM_FXML, pm);
    }

    @FXML
    private void newPlayerButtonAction() {
        NewPlayerPresentationModel pm = new NewPlayerPresentationModel();
        loadStages("NewPlayer", NEW_PLAYER_FXML, pm);
    }

    @FXML
    private void gameEditorAction() {
        GameEditorPresentationModel pm = new GameEditorPresentationModel();
        loadStages("GameEditor", GAME_EDITOR_FXML, pm);
    }

    @FXML
    private void gameButtonAction() {
        GamePresentationModel pm = new GamePresentationModel();
        loadStages("Game", GAME_FXML, pm);
    }

    public void loadStages(String buttonAction, String fxml, PresentationModel presentationModel) {
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

