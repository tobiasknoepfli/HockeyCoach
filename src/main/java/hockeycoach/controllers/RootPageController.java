package hockeycoach.controllers;

import hockeycoach.UI.PlayerPagePresentationModel;
import hockeycoach.UI.StartPagePresentationModel;
import hockeycoach.UI.TeamPagePresentationModel;
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
    private void teamButtonAction() {
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
}
