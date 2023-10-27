package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBDrillLoader;
import hockeycoach.DB.DBLoader.DBLoader;
import hockeycoach.DB.DBLoader.DBTrainingLinesLoader;
import hockeycoach.DB.DBLoader.DBTrainingLoader;
import hockeycoach.mainClasses.*;
import hockeycoach.mainClasses.Lines.TrainingLines;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.SingletonTeam;
import hockeycoach.supportClasses.TextFieldAction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static hockeycoach.AppStarter.*;

public class TrainingPresentationModel extends PresentationModel {
    ButtonControls buttonControls = new ButtonControls();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    List<Training> trainingList;
    TextFieldAction textFieldAction = new TextFieldAction();
    Stack<TextFieldAction> textFieldActions = new Stack<>();

    TableView<Training> trainingTable;

    TextArea pointers;
    TableView<Drill> warmup, together, stations, backup;
    ImageView drillImage;

    TrainingLines trainingLines = new TrainingLines();
    Button newDrillButton, newTrainingButton, backButton;

    TableColumn trainingColumn;

    TextField drillName, trainingDate, trainingTime, team, stadium, mainFocus,
            jersey1, jersey2, jersey3, jersey4, jersey5, jersey6,
            gk1, gk2, gk3, gk4, gk5, gk6,
            dl1, dl2, dl3, dl4, dl5, dl6,
            dr1, dr2, dr3, dr4, dr5, dr6,
            c1, c2, c3, c4, c5, c6,
            fl1, fl2, fl3, fl4, fl5, fl6,
            fr1, fr2, fr3, fr4, fr5, fr6;

    @Override
    public void initializeControls(Pane root) {
        importFields(root);

        Team selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBTrainingLoader dbTrainingLoader = new DBTrainingLoader();

        TextField[] textFields = {drillName, trainingDate, trainingTime, team, stadium, mainFocus,
                jersey1, jersey2, jersey3, jersey4, jersey5, jersey6,
                gk1, gk2, gk3, gk4, gk5, gk6,
                dl1, dl2, dl3, dl4, dl5, dl6,
                dr1, dr2, dr3, dr4, dr5, dr6,
                c1, c2, c3, c4, c5, c6,
                fl1, fl2, fl3, fl4, fl5, fl6,
                fr1, fr2, fr3, fr4, fr5, fr6};
        Arrays.stream(textFields).forEach(textField -> textFieldAction.setupTextFieldUndo(textField, textFieldActions));

        trainingColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Training, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Training, String> param) {
                return new SimpleStringProperty(param.getValue().getStadiumName());
            }
        });
        trainingList = dbTrainingLoader.getTrainings("SELECT * FROM training WHERE team = " + selectedTeam.getTeamID());

        if (!trainingList.isEmpty()) {
            trainingTable.getItems().clear();
            trainingTable.getItems().addAll(trainingList);
        }

        team.setText(selectedTeam.getName());

        getDBEntries(root);
        setupButtons(root);
        setupEventListeners(root);
    }

    @Override
    public void getDBEntries(Pane root) {

    }

    @Override
    public void setupButtons(Pane root) {
        backButton.setOnAction(event -> {
            textFieldAction.undoLastAction(textFieldActions);
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
        newDrillButton.setOnAction(event -> {
            buttonControls.openNewDrillHide(root, TRAINING);
        });

        newTrainingButton.setOnAction(event -> {
            buttonControls.openTrainingEditorHide(root, TRAINING);
        });

        trainingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedTraining, newSelectedTraining) -> {
            if (newSelectedTraining != null) {
                trainingDate.setText(dateFormat.format(newSelectedTraining.getTrainingDate()));
                trainingTime.setText(newSelectedTraining.getTrainingTime().toLocalTime().format(timeFormatter));
                stadium.setText(newSelectedTraining.getStadium().getStadiumName());
                mainFocus.setText(newSelectedTraining.getMainFocus());
                pointers.setText(newSelectedTraining.getPointers());

                DBLoader dbLoader = new DBLoader();
                DBDrillLoader dbDrillLoader = new DBDrillLoader();
                DBTrainingLinesLoader dbTrainingLinesLoader = new DBTrainingLinesLoader();
                List<Drill> drillList = dbDrillLoader.getDrills("SELECT * FROM drill");
                List<Drill> warmupList = dbDrillLoader.getTrainingDrills("SELECT drillID FROM trainingXdrills WHERE tableName LIKE 'warmup' AND trainingID = " + newSelectedTraining.getTrainingID(), drillList, "warmup", newSelectedTraining.getTrainingID());
                List<Drill> togetherList = dbDrillLoader.getTrainingDrills("SELECT drillID FROM trainingXdrills WHERE tableName LIKE 'together' AND trainingID = " + newSelectedTraining.getTrainingID(), drillList, "together", newSelectedTraining.getTrainingID());
                List<Drill> stationsList = dbDrillLoader.getTrainingDrills("SELECT drillID FROM trainingXdrills WHERE tableName LIKE 'stations' AND trainingID = " + newSelectedTraining.getTrainingID(), drillList, "stations", newSelectedTraining.getTrainingID());
                List<Drill> backupList = dbDrillLoader.getTrainingDrills("SELECT drillID FROM trainingXdrills WHERE tableName LIKE 'backup' AND trainingID = " + newSelectedTraining.getTrainingID(), drillList, "backup", newSelectedTraining.getTrainingID());
                trainingLines = dbTrainingLinesLoader.getTrainingLines("SELECT * FROM trainingLines WHERE trainingID =" + newSelectedTraining.getTrainingID());

                if (!warmupList.isEmpty()) {
                    warmup.getItems().clear();
                    warmup.getItems().addAll(warmupList);
                }
                if (!togetherList.isEmpty()) {
                    together.getItems().clear();
                    together.getItems().addAll(togetherList);
                }
                if (!stationsList.isEmpty()) {
                    stations.getItems().clear();
                    stations.getItems().addAll(stationsList);
                }
                if (!backupList.isEmpty()) {
                    backup.getItems().clear();
                    backup.getItems().addAll(backupList);
                }

                setTrainingLines(trainingLines);

            }
        });
        displayDrill(warmup);
        displayDrill(together);
        displayDrill(stations);
        displayDrill(backup);
    }

    public void displayDrill(TableView<Drill> inputTable) {
        inputTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedDrill, newSelectedDrill) -> {
            if (newSelectedDrill != null) {
//                try {
//                    drillImage.setImage(new Image(newSelectedDrill.getImageID()));
//                } catch (Exception e) {
//                    drillImage.setImage(null);
//                }
                drillName.setText(newSelectedDrill.getName());
            }
        });
    }

    public void setTrainingLines(TrainingLines trainingLines) {
        jersey1.setText(trainingLines.getJersey1());
        jersey2.setText(trainingLines.getJersey2());
        jersey3.setText(trainingLines.getJersey3());
        jersey4.setText(trainingLines.getJersey4());
        jersey5.setText(trainingLines.getJersey5());
        jersey6.setText(trainingLines.getJersey6());

        gk1.setText(getPlayerName(trainingLines.getGk1()));
        gk2.setText(getPlayerName(trainingLines.getGk2()));
        gk3.setText(getPlayerName(trainingLines.getGk3()));
        gk4.setText(getPlayerName(trainingLines.getGk4()));
        gk5.setText(getPlayerName(trainingLines.getGk5()));
        gk6.setText(getPlayerName(trainingLines.getGk6()));

        dl1.setText(getPlayerName(trainingLines.getDl1()));
        dl2.setText(getPlayerName(trainingLines.getDl2()));
        dl3.setText(getPlayerName(trainingLines.getDl3()));
        dl4.setText(getPlayerName(trainingLines.getDl4()));
        dl5.setText(getPlayerName(trainingLines.getDl5()));
        dl6.setText(getPlayerName(trainingLines.getDl6()));

        dr1.setText(getPlayerName(trainingLines.getDr1()));
        dr2.setText(getPlayerName(trainingLines.getDr2()));
        dr3.setText(getPlayerName(trainingLines.getDr3()));
        dr4.setText(getPlayerName(trainingLines.getDr4()));
        dr5.setText(getPlayerName(trainingLines.getDr5()));
        dr6.setText(getPlayerName(trainingLines.getDr6()));

        c1.setText(getPlayerName(trainingLines.getC1()));
        c2.setText(getPlayerName(trainingLines.getC2()));
        c3.setText(getPlayerName(trainingLines.getC3()));
        c4.setText(getPlayerName(trainingLines.getC4()));
        c5.setText(getPlayerName(trainingLines.getC5()));
        c6.setText(getPlayerName(trainingLines.getC6()));

        fl1.setText(getPlayerName(trainingLines.getFl1()));
        fl2.setText(getPlayerName(trainingLines.getFl2()));
        fl3.setText(getPlayerName(trainingLines.getFl3()));
        fl4.setText(getPlayerName(trainingLines.getFl4()));
        fl5.setText(getPlayerName(trainingLines.getFl5()));
        fl6.setText(getPlayerName(trainingLines.getFl6()));

        fr1.setText(getPlayerName(trainingLines.getFr1()));
        fr2.setText(getPlayerName(trainingLines.getFr2()));
        fr3.setText(getPlayerName(trainingLines.getFr3()));
        fr4.setText(getPlayerName(trainingLines.getFr4()));
        fr5.setText(getPlayerName(trainingLines.getFr5()));
        fr6.setText(getPlayerName(trainingLines.getFr6()));
    }

    private String getPlayerName(Player player) {
        if (player != null && player.getPlayerID() != 0) {
            return player.getLastName() + " " + player.getFirstName();
        } else {
            return "";
        }
    }

    @Override
    public void importFields(Pane root) {
        pointers = (TextArea) root.lookup("#pointers");

        trainingTable = (TableView) root.lookup("#trainingTable");
        warmup = (TableView) root.lookup("#warmup");
        together = (TableView) root.lookup("#together");
        stations = (TableView) root.lookup("#stations");
        backup = (TableView) root.lookup("#backup");

        trainingColumn = (TableColumn) trainingTable.getVisibleLeafColumn(1);

        drillImage = (ImageView) root.lookup("#drillImage");

        newDrillButton = (Button) root.lookup("#newDrillButton");
        newTrainingButton = (Button) root.lookup("#newTrainingButton");
        backButton = (Button) root.lookup("#backButton");

        trainingDate = (TextField) root.lookup("#trainingDate");
        trainingTime = (TextField) root.lookup("#trainingTime");
        team = (TextField) root.lookup("#team");
        stadium = (TextField) root.lookup("#stadium");
        mainFocus = (TextField) root.lookup("#mainFocus");
        drillName = (TextField) root.lookup("#drillName");

        jersey1 = (TextField) root.lookup("#jersey1");
        jersey2 = (TextField) root.lookup("#jersey2");
        jersey3 = (TextField) root.lookup("#jersey3");
        jersey4 = (TextField) root.lookup("#jersey4");
        jersey5 = (TextField) root.lookup("#jersey5");
        jersey6 = (TextField) root.lookup("#jersey6");
        gk1 = (TextField) root.lookup("#gk1");
        gk2 = (TextField) root.lookup("#gk2");
        gk3 = (TextField) root.lookup("#gk3");
        gk4 = (TextField) root.lookup("#gk4");
        gk5 = (TextField) root.lookup("#gk5");
        gk6 = (TextField) root.lookup("#gk6");
        dl1 = (TextField) root.lookup("#dl1");
        dl2 = (TextField) root.lookup("#dl2");
        dl3 = (TextField) root.lookup("#dl3");
        dl4 = (TextField) root.lookup("#dl4");
        dl5 = (TextField) root.lookup("#dl5");
        dl6 = (TextField) root.lookup("#dl6");
        dr1 = (TextField) root.lookup("#dr1");
        dr2 = (TextField) root.lookup("#dr2");
        dr3 = (TextField) root.lookup("#dr3");
        dr4 = (TextField) root.lookup("#dr4");
        dr5 = (TextField) root.lookup("#dr5");
        dr6 = (TextField) root.lookup("#dr6");
        c1 = (TextField) root.lookup("#c1");
        c2 = (TextField) root.lookup("#c2");
        c3 = (TextField) root.lookup("#c3");
        c4 = (TextField) root.lookup("#c4");
        c5 = (TextField) root.lookup("#c5");
        c6 = (TextField) root.lookup("#c6");
        fl1 = (TextField) root.lookup("#fl1");
        fl2 = (TextField) root.lookup("#fl2");
        fl3 = (TextField) root.lookup("#fl3");
        fl4 = (TextField) root.lookup("#fl4");
        fl5 = (TextField) root.lookup("#fl5");
        fl6 = (TextField) root.lookup("#fl6");
        fr1 = (TextField) root.lookup("#fr1");
        fr2 = (TextField) root.lookup("#fr2");
        fr3 = (TextField) root.lookup("#fr3");
        fr4 = (TextField) root.lookup("#fr4");
        fr5 = (TextField) root.lookup("#fr5");
        fr6 = (TextField) root.lookup("#fr6");
    }
}
