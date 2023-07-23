package hockeycoach.UI;

import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


public class PresentationModel {
    public void initializeControls(Pane root) {
        TableView<Team> teamsTable = (TableView) root.lookup("#teamsTable");
        TeamDAOMain teamDAOMain = new TeamDAOMain();
        teamDAOMain.dataIntoTeamTable(teamsTable);

        List<Player> players = new ArrayList<>();
        GeneralDAO generalDAO = new GeneralDAO();

        players = generalDAO.getPlayers("SELECT * FROM player");
        for (Player p:players
             ) {
            System.out.println(p.getFirstName()+p.getLastName()+p.getStick());

        }
    }

    public void setupEventListeners(Pane root){

    }
}
