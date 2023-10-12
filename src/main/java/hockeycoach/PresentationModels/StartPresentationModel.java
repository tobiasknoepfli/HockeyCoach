package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBGameLoader;
import hockeycoach.DB.DBLoader.DBTeamLoader;
import hockeycoach.DB.DBLoader.DBTrainingLoader;
import hockeycoach.controllers.HeaderController;
import hockeycoach.mainClasses.Game;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;
import hockeycoach.supportClasses.pmInterface;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

import java.util.List;

import static hockeycoach.AppStarter.*;

public class StartPresentationModel extends PresentationModel implements pmInterface {
    ButtonControls buttonControls = new ButtonControls();
    Team selectedTeam;

    TableView<Team> teamsTable;
    TableView<Game> gamesTable;
    TableView<Training> trainingsTable;
    Button newTeamButton, closeWindowButton;

    public StartPresentationModel() {
    }

    public void initializeControls(Pane root) {
        teamsTable = (TableView) root.lookup("#teamsTable");
        gamesTable = (TableView) root.lookup("#gamesTable");
        trainingsTable = (TableView) root.lookup("#trainingsTable");
        closeWindowButton = (Button) root.lookup("#closeWindowButton");
        newTeamButton = (Button) root.lookup("#newTeamButton");

        DBTeamLoader dbTeamLoader = new DBTeamLoader();
        List<Team> allTeams = dbTeamLoader.getAllTeams("SELECT * FROM team");
        teamsTable.getItems().clear();
        teamsTable.getItems().addAll(allTeams);

        selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        if (selectedTeam != null) {
            Platform.runLater(() -> {
                teamsTable.requestFocus();
                teamsTable.getSelectionModel().select(selectedTeam);
                int index = SingletonTeam.getInstance().getIndex();
                teamsTable.scrollTo(index);
                teamsTable.getFocusModel().focus(index);
            });
        }

        setupEventListeners(root);
        useTooltips();
    }

    public void setupEventListeners(Pane root) {
        closeWindowButton.setOnAction(event -> {
            buttonControls.closeWindow(root, HOME);
        });

        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedTeam, newSelectedTeam) -> {
            if (newSelectedTeam != null) {
                DBGameLoader dbGameLoader = new DBGameLoader();
                List<Game> games = dbGameLoader.getGames("SELECT * FROM game WHERE team LIKE '%" + newSelectedTeam.getTeamID() + "%'");
                populateGamesTable(games);
            }
        });

        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedTeam, newSelectedTeam) -> {
            if (newSelectedTeam != null) {
                DBTrainingLoader dbTrainingLoader = new DBTrainingLoader();
                List<Training> trainings = dbTrainingLoader.getTrainings("SELECT * FROM training WHERE team LIKE '%" + newSelectedTeam.getTeamID() + "%'");
                populateTrainingsTable(trainings);
            }
        });

        newTeamButton.setOnAction(event -> {
            NewTeamPresentationModel pm = new NewTeamPresentationModel();
            HeaderController headerController = new HeaderController();
            headerController.loadStages(NEW_TEAM, NEW_TEAM_FXML, pm);

        });

        gamesTable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                Game selectedGame = gamesTable.getSelectionModel().getSelectedItem();

                GamePresentationModel pm = new GamePresentationModel();
                HeaderController headerController = new HeaderController();
                headerController.loadStages(GAME, GAME_FXML, pm);

                Platform.runLater(() -> {
                    pm.allGames.getSelectionModel().select(selectedGame);
                    for (Game g : pm.allGameList) {
                        if (g.getGameID() == selectedGame.getGameID()) {
                            int index = pm.allGameList.indexOf(g);
                            pm.allGames.requestFocus();
                            pm.allGames.scrollTo(index);
                            pm.allGames.getSelectionModel().select(index);
                        }
                    }
                });
            }
        });

        trainingsTable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                Training selectedTraining = trainingsTable.getSelectionModel().getSelectedItem();

                TrainingPresentationModel pm = new TrainingPresentationModel();
                HeaderController headerController = new HeaderController();
                headerController.loadStages(TRAINING, TRAINING_FXML, pm);

                Platform.runLater(() -> {
                    pm.trainingTable.getSelectionModel().select(selectedTraining);
                    for (Training t : pm.trainingList) {
                        if (t.getTrainingID() == selectedTraining.getTrainingID()) {
                            int index = pm.trainingList.indexOf(t);
                            pm.trainingTable.requestFocus();
                            pm.trainingTable.scrollTo(index);
                            pm.trainingTable.getSelectionModel().select(index);
                        }
                    }
                });
            }
        });
    }

    private void populateGamesTable(List<Game> games) {
        gamesTable.getItems().clear();
        gamesTable.getItems().addAll(games);
    }

    private void populateTrainingsTable(List<Training> trainings) {
        trainingsTable.getItems().clear();
        trainingsTable.getItems().addAll(trainings);
    }

    @Override
    public void useTooltips() {
        createHoverInfo(closeWindowButton, "close window");
        createHoverInfo(newTeamButton, "new team");
    }
}
