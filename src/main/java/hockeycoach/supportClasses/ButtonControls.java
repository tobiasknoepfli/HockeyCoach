package hockeycoach.supportClasses;

import hockeycoach.PresentationModels.*;
import hockeycoach.controllers.HeaderController;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static hockeycoach.AppStarter.*;

public class ButtonControls {
    HeaderController headerController= new HeaderController();

    public ButtonControls(){
    }

    public static void closeWindow(Node node, String nodeName){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        openStages.remove(nodeName);
    }

    public static void hideWindow(Node node, String nodeName){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();
    }

    public void openPresentationModel(PresentationModel pm, String openingNodeName, String openingNodeFXML, Node closingNode, String closingNodeName){
        headerController.loadStages(openingNodeName,openingNodeFXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openGameEditor(Node closingNode,String closingNodeName){
        GameEditorPresentationModel pm = new GameEditorPresentationModel();
        headerController.loadStages(GAME_EDITOR,GAME_EDITOR_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openGame(Node closingNode,String closingNodeName) {
        GamePresentationModel pm = new GamePresentationModel();
        headerController.loadStages(GAME,GAME_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openNewDrill(Node closingNode,String closingNodeName) {
        DrillEditorPresentationModel pm = new DrillEditorPresentationModel();
        headerController.loadStages(NEW_DRILL,NEW_DRILL_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openNewPlayer(Node closingNode,String closingNodeName) {
        NewPlayerPresentationModel pm = new NewPlayerPresentationModel();
        headerController.loadStages(NEW_PLAYER,NEW_PLAYER_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openNewTeam(Node closingNode,String closingNodeName) {
        NewTeamPresentationModel pm = new NewTeamPresentationModel();
        headerController.loadStages(NEW_TEAM,NEW_TEAM_FXML,pm);
//        closeWindow(closingNode,closingNodeName);
        hideWindow(closingNode,closingNodeName);
    }

    public void openPlayer(Node closingNode,String closingNodeName) {
        PlayerPresentationModel pm = new PlayerPresentationModel();
        headerController.loadStages(PLAYER,PLAYER_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openPlayerToTeam(Node closingNode,String closingNodeName) {
        PlayerToTeamPresentationModel pm = new PlayerToTeamPresentationModel();
        headerController.loadStages(PLAYER_TO_TEAM,PLAYER_TO_TEAM_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openHome(Node closingNode,String closingNodeName) {
        StartPresentationModel pm = new StartPresentationModel();
        headerController.loadStages(HOME,HOME_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openTeam(Node closingNode,String closingNodeName) {
        PresentationModel pm = new TeamPresentationModel();
        headerController.loadStages(TEAM,TEAM_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openTrainingEditor(Node closingNode,String closingNodeName) {
        TrainingEditorPresentationModel pm = new TrainingEditorPresentationModel();
        headerController.loadStages(TRAINING_EDITOR,TRAINING_EDITOR_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openTraining(Node closingNode,String closingNodeName) {
        TrainingPresentationModel pm = new TrainingPresentationModel();
        headerController.loadStages(TRAINING,TRAINING_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openStadium(){
        StadiumPresentationModel pm = new StadiumPresentationModel();
        headerController.loadStages(STADIUM,STADIUM_FXML,pm);
    }

}
