package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBPlayerLoader;
import hockeycoach.DB.DBLoader.DBPlayerXTeamLoader;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.globalAvailablePlayerList;
import static hockeycoach.AppStarter.globalTeam;

public class AvailablePlayerPresentationModel extends PresentationModel {
    List<Player> allTeamPlayers = new ArrayList<>();
    Team selectedTeam;

    DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
    Boolean disabled = true;

    TableView<Player> playerList;
    Button cancelButton, okButton;

    @Override
    public void initializeControls(Pane root) {
        selectedTeam = globalTeam;
        importFields(root);

        setupFieldLists(root);
        getDBEntries(root);
        fillFields(root);
        setupButtons(root);
        setupEventListeners(root);

        disableFields(disabled);

        playerList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @Override
    public void setupFieldLists(Pane root) {
    }

    @Override
    public void getDBEntries(Pane root) {
        allTeamPlayers = dbPlayerLoader.getTeamPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.ID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getID() + "'", selectedTeam.getID());
        allTeamPlayers.stream().forEach(p->p.setAvailable(""));
        playerList.getItems().clear();
        playerList.getItems().addAll(allTeamPlayers);

    }

    @Override
    public void fillFields(Pane root) {
    }

    @Override
    public void setupButtons(Pane root) {
        okButton.setOnAction(event ->{
            globalAvailablePlayerList.clear();
            playerList.getItems().stream().forEach(i->{
                if (i.getAvailable().equals("yes")){
                    globalAvailablePlayerList.add(i);
                }
            });
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        });


    }

    @Override
    public void setupEventListeners(Pane root) {
        playerList.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey() && event.getText().equalsIgnoreCase("a")) {
                List<Player> selectedPlayers = new ArrayList<>(playerList.getSelectionModel().getSelectedItems());

                selectedPlayers.stream().forEach(p -> p.setAvailable("yes"));
                playerList.getSelectionModel().clearSelection();
            }
            if (event.getCode().isLetterKey() && event.getText().equalsIgnoreCase("u")) {
                List<Player> selectedPlayers = new ArrayList<>(playerList.getSelectionModel().getSelectedItems());

                selectedPlayers.stream().forEach(p -> p.setAvailable(""));
                playerList.getSelectionModel().clearSelection();
            }
            playerList.refresh();
        });
    }

    @Override
    public void importFields(Pane root) {
        playerList = (TableView) root.lookup("#playerList");
        cancelButton = (Button) root.lookup("#cancelButton");
        okButton = (Button) root.lookup("#okButton");
    }

    @Override
    public void disableFields(Boolean disabled) {

    }
}
