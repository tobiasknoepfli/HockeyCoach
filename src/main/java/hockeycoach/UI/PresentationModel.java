package hockeycoach.UI;

import hockeycoach.mainClasses.Game;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class PresentationModel {
    TableView<Team> teamsTable;
    TableView<Game> gamesTable;
    TableView<Training> trainingsTable;

    public void initializeControls(Pane root) {
        teamsTable = (TableView) root.lookup("#teamsTable");
        gamesTable = (TableView) root.lookup("#gamesTable");
        trainingsTable = (TableView) root.lookup("#trainingsTable");

        TeamDAOMain teamDAOMain = new TeamDAOMain();
        teamDAOMain.dataIntoTeamTable(teamsTable);

        setupEventListeners();
    }

    public void setupEventListeners() {
        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedTeam, newSelectedTeam) -> {
            if (newSelectedTeam != null) {
                GeneralDAO generalDAO = new GeneralDAO();
                List<Game> games = generalDAO.getGames("SELECT * FROM game WHERE team LIKE '%" + newSelectedTeam.getName() + "%'");
                populateGamesTable(games);
            }
        });

        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedTeam, newSelectedTeam) -> {
            if (newSelectedTeam != null) {
                GeneralDAO generalDAO = new GeneralDAO();
                List<Training> trainings = generalDAO.getTrainings("SELECT * FROM training WHERE team LIKE '%" + newSelectedTeam.getName() + "%'");
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
