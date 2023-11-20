package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBGameLoader;
import hockeycoach.DB.DBLoader.DBTeamLoader;
import hockeycoach.DB.DBLoader.DBTrainingLoader;
import hockeycoach.controllers.HeaderController;
import hockeycoach.mainClasses.Game;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;
import hockeycoach.supportClasses.GlobalSelector;
import hockeycoach.supportClasses.pmInterface;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.util.List;

import static hockeycoach.AppStarter.*;

public class StartPresentationModel extends PresentationModel implements pmInterface {
    ButtonControls buttonControls = new ButtonControls();
    GlobalSelector globalSelector = new GlobalSelector();

    TableView<Team> teamsTable;
    TableView<Game> gamesTable;
    TableView<Training> trainingsTable;
    List<Team> allTeams;
    Button newTeamButton, closeWindowButton, newPlayerButton, newDrillButton, newStadiumButton;
    TableColumn teamsColumn, gamesColumn1, gamesColumn2, gamesColumn3, trainingsColumn1, trainingsColumn2;

    public StartPresentationModel() {
    }

    @Override
    public void initializeControls(Pane root) {
        importFields(root);

        DBTeamLoader dbTeamLoader = new DBTeamLoader();
        allTeams = dbTeamLoader.getAllTeams();
        teamsTable.getItems().addAll(FXCollections.observableArrayList(allTeams));

        globalSelector.selectTeam(teamsTable);

        setupEventListeners(root);
        useTooltips();
    }

    @Override
    public void setupFieldLists(Pane root) {

    }

    @Override
    public void getDBEntries(Pane root) {

    }

    @Override
    public void fillFields(Pane root) {

    }

    @Override
    public void setupButtons(Pane root) {

    }

    @Override
    public void setupEventListeners(Pane root) {
        closeWindowButton.setOnAction(event -> {
            buttonControls.closeWindow(root, HOME);
        });

        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue!=null) {
                globalTeam = newValue;
                globalTeam.setIndex(allTeams.indexOf(newValue));
            } else {
                globalTeam.setIndex(-1);
            }
        });

        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedTeam, newSelectedTeam) -> {
            if (newSelectedTeam != null) {
                DBGameLoader dbGameLoader = new DBGameLoader();
                List<Game> games = dbGameLoader.getGames("SELECT * FROM game WHERE team LIKE '%" + newSelectedTeam.getID() + "%'");
                gamesColumn3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Game, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Game, String> param) {
                        return new SimpleStringProperty(param.getValue().getStadiumName());
                    }
                });
                populateGamesTable(games);
            }
        });

        teamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedTeam, newSelectedTeam) -> {
            if (newSelectedTeam != null) {
                DBTrainingLoader dbTrainingLoader = new DBTrainingLoader();
                List<Training> trainings = dbTrainingLoader.getTrainings("SELECT * FROM training WHERE team LIKE '%" + newSelectedTeam.getID() + "%'");
                trainingsColumn2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Training, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Training, String> param) {
                        return new SimpleStringProperty(param.getValue().getStadiumName());
                    }
                });

                populateTrainingsTable(trainings);
            }
        });

        newTeamButton.setOnAction(event -> {
            buttonControls.openNewTeamClose(root, HOME);
        });

        newPlayerButton.setOnAction(event -> {
            buttonControls.openNewPlayerClose(root,HOME);
        });

        newDrillButton.setOnAction(event -> {
            buttonControls.openNewDrillClose(root,HOME);
        });

        newStadiumButton.setOnAction(event -> {
            buttonControls.openStadiumClose(root,HOME);
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
                        if (g.getID() == selectedGame.getID()) {
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

    @Override
    public void importFields(Pane root) {
        teamsTable = (TableView) root.lookup("#teamsTable");
        gamesTable = (TableView) root.lookup("#gamesTable");
        trainingsTable = (TableView) root.lookup("#trainingsTable");

        closeWindowButton = (Button) root.lookup("#closeWindowButton");
        newTeamButton = (Button) root.lookup("#newTeamButton");
        newPlayerButton = (Button) root.lookup("#newPlayerButton");
        newDrillButton = (Button) root.lookup("#newDrillButton");
        newStadiumButton = (Button) root.lookup("#newStadiumButton");

        teamsColumn = (TableColumn) teamsTable.getVisibleLeafColumn(0);
        gamesColumn1 = (TableColumn) gamesTable.getVisibleLeafColumn(0);
        gamesColumn2 = (TableColumn) gamesTable.getVisibleLeafColumn(1);
        gamesColumn3 = (TableColumn) gamesTable.getVisibleLeafColumn(2);
        trainingsColumn1 = (TableColumn) trainingsTable.getVisibleLeafColumn(0);
        trainingsColumn2 = (TableColumn) trainingsTable.getVisibleLeafColumn(1);
    }

    @Override
    public void disableFields(Boolean disabled) {

    }
}
