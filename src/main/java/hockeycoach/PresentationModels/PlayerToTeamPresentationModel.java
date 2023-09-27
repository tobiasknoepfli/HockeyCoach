package hockeycoach.PresentationModels;

import hockeycoach.DB.DBDeleter;
import hockeycoach.DB.DBLoader;
import hockeycoach.DB.DBWriter;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.PlayerXTeam;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerToTeamPresentationModel extends PresentationModel{
    Team selectedTeam = new Team();
    DBLoader dbLoader = new DBLoader();
    DBWriter dbWriter = new DBWriter();
    DBDeleter dbDeleter = new DBDeleter();
    List<Player> allPlayerList = new ArrayList<>();
    List<Player> teamPlayerList = new ArrayList<>();

    TableView<Player> teamPlayers;
    TableView<Player> allPlayers;
    TextField team;
    Button addButton;
    Button removeButton;
    Button saveButton;

    public void initializeControls(Pane root) {
        teamPlayers = (TableView) root.lookup("#teamPlayers");
        allPlayers = (TableView) root.lookup("#allPlayers");
        team = (TextField) root.lookup("#team");
        addButton = (Button) root.lookup("#addButton");
        removeButton = (Button) root.lookup("#removeButton");
        saveButton = (Button) root.lookup("#saveButton");

        selectedTeam = SingletonTeam.getInstance().getSelectedTeam();

        allPlayerList = dbLoader.getPlayers("SELECT * FROM player", selectedTeam.getTeamID());
        teamPlayerList = dbLoader.getPlayers("SELECT p.* FROM player p INNER JOIN playerXteam tx ON p.playerID = tx.playerID WHERE teamID = '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());

        allPlayers.getItems().clear();
        allPlayers.getItems().addAll(filterPlayerIDs(teamPlayerList, allPlayerList));
        allPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        teamPlayers.getItems().clear();
        teamPlayers.getItems().addAll(teamPlayerList);
        teamPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        team.setText(selectedTeam.getName());

        addButton.setOnAction(event -> addSelectedPlayers());
        removeButton.setOnAction(event -> removeSelectedPlayers());

        saveButton.setOnAction(event -> saveToDB());
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

    private void saveToDB() {
        List<Player> teamPlayerList = teamPlayers.getItems().stream().toList();
        List<PlayerXTeam> playerXteam = dbLoader.getPlayerXTeam("SELECT * FROM playerXteam");

        List<PlayerXTeam> filteredTeamList = playerXteam.stream()
                .filter(entry -> entry.getTeamID() == selectedTeam.getTeamID())
                .collect(Collectors.toList());

        List<Player> additionalPlayers = teamPlayerList.stream()
                .filter(player -> filteredTeamList.stream()
                        .noneMatch(entry -> entry.getPlayerID() == player.getPlayerID()))
                .collect(Collectors.toList());

        List<PlayerXTeam> deletePlayers = filteredTeamList.stream()
                .filter(entry -> teamPlayerList.stream()
                        .noneMatch(player -> player.getPlayerID() == entry.getPlayerID()))
                .collect(Collectors.toList());

        additionalPlayers.stream()
                .forEach(player -> dbWriter.addPlayerToTeam(selectedTeam, player));

        deletePlayers.stream()
                .forEach(playerXTeam -> dbDeleter.removeFromPlayerXList(playerXTeam));

    }
}
