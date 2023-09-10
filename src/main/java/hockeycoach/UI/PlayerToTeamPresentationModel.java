package hockeycoach.UI;

import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerToTeamPresentationModel {
    TableView<Player> teamPlayers;
    TableView<Player> allPlayers;
    TextField team;
    Button addButton;
    Button removeButton;

    public void initializeControls(Pane root) {
        teamPlayers = (TableView) root.lookup("#teamPlayers");
        allPlayers = (TableView) root.lookup("#allPlayers");
        team = (TextField) root.lookup("#team");
        addButton = (Button) root.lookup("#addButton");
        removeButton = (Button) root.lookup("#removeButton");

        Team selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        List<Player> allPlayerList = dbLoader.getPlayers("SELECT * FROM player", selectedTeam.getTeamID());
        List<Player> teamPlayerList = dbLoader.getPlayers("SELECT p.* FROM player p INNER JOIN playerXteam tx ON p.playerID = tx.playerID WHERE teamID = '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());

        allPlayers.getItems().clear();
        allPlayers.getItems().addAll(filterPlayerIDs(teamPlayerList, allPlayerList));
        allPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        teamPlayers.getItems().clear();
        teamPlayers.getItems().addAll(teamPlayerList);
        teamPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        team.setText(selectedTeam.getName());

        addButton.setOnAction(event -> addSelectedPlayers());
        removeButton.setOnAction(event -> removeSelectedPlayers());
    }

    private List<Player> filterPlayerIDs(List<Player> teamPlayerList, List<Player> allPlayerList) {
        List<Integer> teamPlayerIds = new ArrayList<>();
        for (Player player : teamPlayerList) {
            teamPlayerIds.add(player.getPlayerID());
        }
        List<Player> filteredAllPlayers = allPlayerList.stream()
                .filter(player -> !teamPlayerIds.contains(player.getPlayerID()))
                .collect(Collectors.toList());
        return filteredAllPlayers;
    }

    private void addSelectedPlayers() {
        ObservableList<Player> selectedPlayers = allPlayers.getSelectionModel().getSelectedItems();
        teamPlayers.getItems().addAll(selectedPlayers);
        allPlayers.getItems().removeAll(selectedPlayers);
    }

    private void removeSelectedPlayers() {
        ObservableList<Player> selectedPlayers = teamPlayers.getSelectionModel().getSelectedItems();
        allPlayers.getItems().addAll(selectedPlayers);
        teamPlayers.getItems().removeAll(selectedPlayers);
    }
}
