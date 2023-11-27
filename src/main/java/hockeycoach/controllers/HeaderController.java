package hockeycoach.controllers;

import hockeycoach.PresentationModels.*;

import hockeycoach.supportClasses.ButtonControls;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static hockeycoach.AppStarter.*;

public class HeaderController extends Controller {
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
        clearAllStages();
        StartPresentationModel pm = new StartPresentationModel();
        loadStages(HOME, HOME_FXML, pm);
    }

    @FXML
    public void teamButtonAction() {
        clearAllStages();
        TeamPresentationModel pm = new TeamPresentationModel();
        loadStages(TEAM, TEAM_FXML, pm);
    }

    @FXML
    private void playerButtonAction() {
        clearAllStages();
        PlayerPresentationModel pm = new PlayerPresentationModel();
        loadStages(PLAYER, PLAYER_FXML, pm);
    }

    @FXML
    private void trainingButtonAction() {
        clearAllStages();
        TrainingPresentationModel pm = new TrainingPresentationModel();
        loadStages(TRAINING, TRAINING_FXML, pm);
    }

    @FXML
    private void trainingEditorAction() {
        clearAllStages();
        TrainingEditorPresentationModel pm = new TrainingEditorPresentationModel();
        loadStages(TRAINING_EDITOR, TRAINING_EDITOR_FXML, pm);
    }

    @FXML
    private void newTeamButtonAction() {
        clearAllStages();
        NewTeamPresentationModel pm = new NewTeamPresentationModel();
        loadStages(NEW_TEAM, NEW_TEAM_FXML, pm);
    }

    @FXML
    private void newPlayerButtonAction() {
        clearAllStages();
        NewPlayerPresentationModel pm = new NewPlayerPresentationModel();
        loadStages(NEW_PLAYER, NEW_PLAYER_FXML, pm);
    }

    @FXML
    private void gameEditorAction() {
        clearAllStages();
        GameEditorPresentationModel pm = new GameEditorPresentationModel();
        loadStages(GAME_EDITOR, GAME_EDITOR_FXML, pm);
    }

    @FXML
    private void gameButtonAction() {
        clearAllStages();
        GamePresentationModel pm = new GamePresentationModel();
        loadStages(GAME, GAME_FXML, pm);
    }


    public void loadStages(String buttonName, String fxml, PresentationModel presentationModel) {
        if (openStages.containsKey(buttonName)) {
            Stage existingStage = openStages.get(buttonName);
            existingStage.show();
            existingStage.toFront();
//            existingStage.close();
//            openStages.remove(buttonName);
//            loadStages(buttonName,fxml,presentationModel);
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

                openStages.put(buttonName, newStage);
                newStage.show();

                presentationModel.initializeControls(newPane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearAllStages() {
        openStages.values().stream().forEach(stage -> stage.close());
        openStages.clear();
    }

}

