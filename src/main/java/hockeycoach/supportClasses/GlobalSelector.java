package hockeycoach.supportClasses;
import static hockeycoach.AppStarter.globalTeam;

import hockeycoach.mainClasses.Team;
import javafx.application.Platform;
import javafx.scene.control.TableView;


public class GlobalSelector {
    public void selectTeam(TableView<Team> tableView) {
        if (globalTeam != null) {
            Platform.runLater(() -> {
                tableView.requestFocus();
                tableView.getSelectionModel().select(globalTeam);
            });
            int index = globalTeam.getIndex();
            tableView.scrollTo(index);
            tableView.getFocusModel().focus(index);
        }
    }
}
