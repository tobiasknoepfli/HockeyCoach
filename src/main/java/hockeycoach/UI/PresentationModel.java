package hockeycoach.UI;

import hockeycoach.mainClasses.Team;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.List;

public class PresentationModel {
    public void initializeControls(Pane root) {
        TableView<Team> teamsTable = (TableView) root.lookup("#teamsTable");
        loadDataIntoTable(teamsTable);

    }

    private void loadDataIntoTable(TableView<Team> tableView) {
        TableColumn<Team, String> teamNameColumn = new TableColumn<>("Team Name");
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teamNameColumn.setPrefWidth(390);

        tableView.getColumns().clear();
        tableView.getColumns().add(teamNameColumn);

        TeamDAO teamDAO = new TeamDAO();
        List<Team> teams = teamDAO.getAllTeamNames();
        tableView.getItems().addAll(teams);
    }
}
