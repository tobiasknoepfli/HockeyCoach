package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader;
import hockeycoach.DB.DBLoaderTeamList;
import hockeycoach.mainClasses.Game;
import hockeycoach.supportClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;
import hockeycoach.supportClasses.pmInterface;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

import static hockeycoach.AppStarter.openStages;

public class StartPagePresentationModel extends PresentationModel implements pmInterface {
    TableView<Team> teamsTable;
    TableView<Game> gamesTable;
    TableView<Training> trainingsTable;
    Button closeWindowButton;

    public StartPagePresentationModel(){
    }

    public void initializeControls(Pane root) {
        teamsTable = (TableView) root.lookup("#teamsTable");
        gamesTable = (TableView) root.lookup("#gamesTable");
        trainingsTable = (TableView) root.lookup("#trainingsTable");
        closeWindowButton  = (Button) root.lookup("#closeWindowButton");

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
        closeWindowButton.setOnAction(event->{
            closeWindow(root);
            openStages.remove("Home");
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
    }

    private void populateGamesTable(List<Game> games) {
        gamesTable.getItems().clear();
        gamesTable.getItems().addAll(games);
    }

    private void populateTrainingsTable(List<Training> trainings) {
        trainingsTable.getItems().clear();
        trainingsTable.getItems().addAll(trainings);
    }

    public static void closeWindow(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @Override
    public void useTooltips() {
        createHoverInfo(closeWindowButton,"close window");
    }
}
