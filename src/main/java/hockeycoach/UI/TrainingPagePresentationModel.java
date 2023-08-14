package hockeycoach.UI;

import hockeycoach.mainClasses.Drill;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import hockeycoach.mainClasses.Training;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class TrainingPagePresentationModel {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    TableView<Training> trainingTable;
    TextField trainingDate;
    TextField trainingTime;
    TextField team;
    TextField stadium;
    TextField mainFocus;
    TextArea pointers;
    TableView<Drill> warmup;
    TableView<Drill> together;
    TableView<Drill> stations;
    TableView<Drill> backup;
    ImageView drillImage;
    TextField drillName;

    public void initializeControls(Pane root) {
        trainingTable = (TableView) root.lookup("#trainingTable");
        trainingDate = (TextField) root.lookup("#trainingDate");
        trainingTime = (TextField) root.lookup("#trainingTime");
        team = (TextField) root.lookup("#team");
        stadium = (TextField) root.lookup("#stadium");
        mainFocus = (TextField) root.lookup("#mainFocus");
        pointers = (TextArea) root.lookup("#pointers");
        warmup = (TableView) root.lookup("#warmup");
        together = (TableView) root.lookup("#together");
        stations = (TableView) root.lookup("#stations");
        backup = (TableView) root.lookup("#backup");
        drillImage = (ImageView) root.lookup("#drillImage");
        drillName = (TextField) root.lookup("#drillName");

        Team selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();

        List<Training> trainingList = dbLoader.getTrainings("SELECT * FROM training WHERE team LIKE '%" + selectedTeam.getName() + "%'");

        if (!trainingList.isEmpty()) {
            trainingTable.getItems().clear();
            trainingTable.getItems().addAll(trainingList);
        }

        setupEventListeners();
    }

    public void setupEventListeners(){
        trainingTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelectedTrianing, newSelectedTraining)->{
            if(newSelectedTraining !=null){
                trainingDate.setText(dateFormat.format(newSelectedTraining.getTrainingDate()));
                trainingTime.setText(newSelectedTraining.getTrainingTime().toLocalTime().format(timeFormatter));
                stadium.setText(newSelectedTraining.getStadium());
                mainFocus.setText(newSelectedTraining.getMainFocus());
                pointers.setText(newSelectedTraining.getPointers());
                team.setText(newSelectedTraining.getTeam());
            }
        });
    }

}
