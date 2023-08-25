package hockeycoach.controllers;

import hockeycoach.UI.*;

import hockeycoach.mainClasses.Team;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class RootPageController {
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
    private void homeButtonAction() {
        try {
            FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/hockeycoach/start-page.fxml"));
            Pane homePage = homePageLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(homePage);

            StartPagePresentationModel startPagePresentationModel = new StartPagePresentationModel();
            startPagePresentationModel.initializeControls(contentPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void teamButtonAction() {
        try {
            FXMLLoader teamPageLoader = new FXMLLoader(getClass().getResource("/hockeycoach/team-page.fxml"));
            Pane teamPage = teamPageLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(teamPage);

            TeamPagePresentationModel teamPagePresentationModel = new TeamPagePresentationModel();
            teamPagePresentationModel.intializeControls(teamPage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void playerButtonAction() {
        try {
            FXMLLoader playerPageLoader = new FXMLLoader(getClass().getResource("/hockeycoach/player-page.fxml"));
            Pane playerPage = playerPageLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(playerPage);

            PlayerPagePresentationModel playerPagePresentationModel = new PlayerPagePresentationModel();
            playerPagePresentationModel.initializeControls(playerPage);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void trainingButtonAction() {
        try {
            FXMLLoader trainingPageLoader = new FXMLLoader(getClass().getResource("/hockeycoach/training-page.fxml"));
            Pane trainingPage = trainingPageLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(trainingPage);

            TrainingPagePresentationModel trainingPagePresentationModel = new TrainingPagePresentationModel();
            trainingPagePresentationModel.initializeControls(trainingPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void trainingEditorAction() {
        try {
            FXMLLoader trainingEditorLoader = new FXMLLoader(getClass().getResource("/hockeycoach/training-editor-page.fxml"));
            Pane trainingEditorPage = trainingEditorLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(trainingEditorPage);

            TrainingEditorPagePresentationModel trainingEditorPagePresentationModel = new TrainingEditorPagePresentationModel();
            trainingEditorPagePresentationModel.initializeControls(trainingEditorPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void newTeamButtonAction() {
        try {
            FXMLLoader newTeamPageLoader = new FXMLLoader(getClass().getResource("/hockeycoach/new-team-page.fxml"));
            Pane newTeamPage = newTeamPageLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newTeamPage);

            NewTeamPagePresentationModel newTeamPagePresentationModel = new NewTeamPagePresentationModel();
            newTeamPagePresentationModel.intializeControls(newTeamPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
