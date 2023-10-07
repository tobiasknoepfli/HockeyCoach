package hockeycoach.DB;

import hockeycoach.mainClasses.Team;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBLoaderTeamList {

    public List<Team> getAllTeamNames() {
        List<Team> teams = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM team");

            while (resultSet.next()){
                String name =  resultSet.getString("name");
                int teamID = resultSet.getInt("teamID");
                Team team = new Team(name,teamID);
                teams.add(team);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teams;
    }

    public void dataIntoTeamTable(TableView<Team> tableView) {
        TableColumn<Team, String> teamNameColumn = new TableColumn<>("Team Name");
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teamNameColumn.setPrefWidth(390);

        tableView.getColumns().clear();
        tableView.getColumns().add(teamNameColumn);

        DBLoaderTeamList DBLoaderTeamList = new DBLoaderTeamList();
        List<Team> teams = DBLoaderTeamList.getAllTeamNames();
        tableView.getItems().addAll(teams);
    }
}

