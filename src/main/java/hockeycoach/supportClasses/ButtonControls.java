package hockeycoach.supportClasses;

import javafx.scene.Node;
import javafx.stage.Stage;

import static hockeycoach.AppStarter.openStages;

public class ButtonControls {

    public ButtonControls(){

    }

    public static void closeWindow(Node node, String nodeName){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        openStages.remove(nodeName);
    }
}
