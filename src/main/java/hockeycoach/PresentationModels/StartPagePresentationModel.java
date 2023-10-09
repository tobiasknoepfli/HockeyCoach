package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader;
import hockeycoach.DB.DBLoaderTeamList;
import hockeycoach.controllers.HeaderPageController;
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

import static hockeycoach.AppStarter.GAME_FXML;

public class StartPagePresentationModel extends PresentationModel implements pmInterface {
    ButtonControls buttonControls = new ButtonControls();

    TableView<Team> teamsTable;
    TableView<Game> gamesTable;
    TableView<Training> trainingsTable;
    Button newTeamButton, closeWindowButton;

    public StartPagePresentationModel() {
    }

    public void initializeControls(Pane root) {
        teamsTable = (TableView) root.lookup("#teamsTable");
        gamesTable = (TableView) root.lookup("#gamesTable");
        trainingsTable = (TableView) root.lookup("#trainingsTable");
        closeWindowButton = (Button) root.lookup("#closeWindowButton");
        newTeamButton = (Button) root.lookup("#newTeamButton");

        DBLoaderTeamList DBLoaderTeamList = new DBLoaderTeamList();
        DBLoaderTeamList.dataIntoTeamTable(teamsTable);

        Team selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        if (selectedTeam != null) {
            Platform.runLater(() -> {
                teamsTable.requestFocus();
                teamsTable.getSelectionModel().select(selectedTeam);
                int index = SingletonTeam.getInstance().getIndex();
                teamsTable.scrollTo(index);
                teamsTable.getFocusModel().focus(index);
            });
        }
        closeWindowButton.setOnAction(event -> {
            buttonControls.closeWindow(root, "Home");
        });

        setupEventListeners();
        useTooltips();
    }

    public void setupEventListeners() {
        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedTeam, newSelectedTeam) -> {
            if (newSelectedTeam != null) {
                DBLoader DBLoader = new DBLoader();
                List<Game> games = DBLoader.getGames("SELECT * FROM game WHERE team LIKE '%" + newSelectedTeam.getTeamID() + "%'");
                populateGamesTable(games);
            }
        });

        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedTeam, newSelectedTeam) -> {
            if (newSelectedTeam != null) {
                DBLoader DBLoader = new DBLoader();
                List<Training> trainings = DBLoader.getTrainings("SELECT * FROM training WHERE team LIKE '%" + newSelectedTeam.getTeamID() + "%'");
                populateTrainingsTable(trainings);
            }
        });

        gamesTable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                Game selectedGame = gamesTable.getSelectionModel().getSelectedItem();

                GamePresentationModel pm = new GamePresentationModel();
                HeaderPageController headerPageController = new HeaderPageController();
                headerPageController.loadStages("Game", GAME_FXML, pm);

                Platform.runLater(() -> {
                    pm.allGames.getSelectionModel().select(selectedGame);
                    for (Game g:pm.allGameList){
                        if(g.getGameID() == selectedGame.getGameID()){
                            int index = pm.allGameList.indexOf(g);
                            pm.allGames.requestFocus();
                            pm.allGames.scrollTo(index);
                            pm.allGames.getSelectionModel().select(index);
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
