package hockeycoach.UI;

import hockeycoach.mainClasses.Game;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.util.List;

public class StartPagePresentationModel {
    TableView<Team> teamsTable;
    TableView<Game> gamesTable;
    TableView<Training> trainingsTable;
    private Team selectedTeam;

    public void initializeControls(Pane root) {
        teamsTable = (TableView) root.lookup("#teamsTable");
        gamesTable = (TableView) root.lookup("#gamesTable");
        trainingsTable = (TableView) root.lookup("#trainingsTable");

        Team selectedTeam = SingletonTeam.getInstance().getSelectedTeam();

        if(selectedTeam !=null){
            teamsTable.getSelectionModel().select(selectedTeam);
        }

        DBLoaderTeamList DBLoaderTeamList = new DBLoaderTeamList();
        DBLoaderTeamList.dataIntoTeamTable(teamsTable);

        setupEventListeners();
    }

    public void setupEventListeners() {
        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedTeam, newSelectedTeam) -> {
            if (newSelectedTeam != null) {
                DBLoader DBLoader = new DBLoader();
                List<Game> games = DBLoader.getGames("SELECT * FROM game WHERE team LIKE '%" + newSelectedTeam.getName() + "%'");
                populateGamesTable(games);
            }
        });

        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedTeam, newSelectedTeam) -> {
            if (newSelectedTeam != null) {
                DBLoader DBLoader = new DBLoader();
                List<Training> trainings = DBLoader.getTrainings("SELECT * FROM training WHERE team LIKE '%" + newSelectedTeam.getName() + "%'");
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
}
