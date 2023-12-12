package hockeycoach.supportClasses;

import hockeycoach.PresentationModels.*;
import hockeycoach.controllers.HeaderController;
import hockeycoach.mainClasses.Game;
import javafx.scene.Node;
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

    public void openPresentationModelHide(PresentationModel pm, String openingNodeName, String openingNodeFXML, Node closingNode, String closingNodeName){
        headerController.loadStages(openingNodeName,openingNodeFXML,pm);
        hideWindow(closingNode,closingNodeName);
    }
    public void openPresentationModelClose(PresentationModel pm, String openingNodeName, String openingNodeFXML, Node closingNode, String closingNodeName){
        headerController.loadStages(openingNodeName,openingNodeFXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public GameEditorPresentationModel openGameEditorHide(Node closingNode, String closingNodeName){
        GameEditorPresentationModel pm = new GameEditorPresentationModel();
        headerController.loadStages(GAME_EDITOR,GAME_EDITOR_FXML,pm);
        hideWindow(closingNode,closingNodeName);
        return pm;
    }

    public void openGameEditorClose(Node closingNode, String closingNodeName){
        GameEditorPresentationModel pm = new GameEditorPresentationModel();
        headerController.loadStages(GAME_EDITOR,GAME_EDITOR_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openGameHide(Node closingNode, String closingNodeName) {
        GamePresentationModel pm = new GamePresentationModel();
        headerController.loadStages(GAME,GAME_FXML,pm);
        hideWindow(closingNode,closingNodeName);
    }

    public void openGameClose(Node closingNode, String closingNodeName) {
        GamePresentationModel pm = new GamePresentationModel();
        headerController.loadStages(GAME,GAME_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openNewDrillHide(Node closingNode, String closingNodeName) {
        DrillEditorPresentationModel pm = new DrillEditorPresentationModel();
        headerController.loadStages(NEW_DRILL,NEW_DRILL_FXML,pm);
        hideWindow(closingNode,closingNodeName);
    }

    public void openNewDrillClose(Node closingNode, String closingNodeName) {
        DrillEditorPresentationModel pm = new DrillEditorPresentationModel();
        headerController.loadStages(NEW_DRILL,NEW_DRILL_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openNewPlayerHide(Node closingNode, String closingNodeName) {
        NewPlayerPresentationModel pm = new NewPlayerPresentationModel();
        headerController.loadStages(NEW_PLAYER,NEW_PLAYER_FXML,pm);
        hideWindow(closingNode,closingNodeName);
    }

    public void openNewPlayerClose(Node closingNode, String closingNodeName) {
        NewPlayerPresentationModel pm = new NewPlayerPresentationModel();
        headerController.loadStages(NEW_PLAYER,NEW_PLAYER_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }


    public void openNewTeamHide(Node closingNode, String closingNodeName) {
        NewTeamPresentationModel pm = new NewTeamPresentationModel();
        headerController.loadStages(NEW_TEAM,NEW_TEAM_FXML,pm);
        hideWindow(closingNode,closingNodeName);
    }

    public void openNewTeamClose(Node closingNode, String closingNodeName) {
        NewTeamPresentationModel pm = new NewTeamPresentationModel();
        headerController.loadStages(NEW_TEAM,NEW_TEAM_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openPlayerHide(Node closingNode, String closingNodeName) {
        PlayerPresentationModel pm = new PlayerPresentationModel();
        headerController.loadStages(PLAYER,PLAYER_FXML,pm);
        hideWindow(closingNode,closingNodeName);
    }

    public void openPlayerClose(Node closingNode, String closingNodeName) {
        PlayerPresentationModel pm = new PlayerPresentationModel();
        headerController.loadStages(PLAYER,PLAYER_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openPlayerToTeamHide(Node closingNode,String closingNodeName) {
        PlayerToTeamPresentationModel pm = new PlayerToTeamPresentationModel();
        headerController.loadStages(PLAYER_TO_TEAM,PLAYER_TO_TEAM_FXML,pm);
        hideWindow(closingNode,closingNodeName);
    }

    public void openPlayerToTeamClose(Node closingNode,String closingNodeName) {
        PlayerToTeamPresentationModel pm = new PlayerToTeamPresentationModel();
        headerController.loadStages(PLAYER_TO_TEAM,PLAYER_TO_TEAM_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openHomeHide(Node closingNode,String closingNodeName) {
        StartPresentationModel pm = new StartPresentationModel();
        headerController.loadStages(HOME,HOME_FXML,pm);
        hideWindow(closingNode,closingNodeName);
    }

    public void openHomeClose(Node closingNode,String closingNodeName) {
        StartPresentationModel pm = new StartPresentationModel();
        headerController.loadStages(HOME,HOME_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openTeamHide(Node closingNode,String closingNodeName) {
        TeamPresentationModel pm = new TeamPresentationModel();
        headerController.loadStages(TEAM,TEAM_FXML,pm);
        hideWindow(closingNode,closingNodeName);
    }

    public void openTeamClose(Node closingNode,String closingNodeName) {
        TeamPresentationModel pm = new TeamPresentationModel();
        headerController.loadStages(TEAM,TEAM_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openTrainingEditorHide(Node closingNode,String closingNodeName) {
        TrainingEditorPresentationModel pm = new TrainingEditorPresentationModel();
        headerController.loadStages(TRAINING_EDITOR,TRAINING_EDITOR_FXML,pm);
        hideWindow(closingNode,closingNodeName);
    }

    public void openTrainingEditorClose(Node closingNode,String closingNodeName) {
        TrainingEditorPresentationModel pm = new TrainingEditorPresentationModel();
        headerController.loadStages(TRAINING_EDITOR,TRAINING_EDITOR_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openTrainingHide(Node closingNode,String closingNodeName) {
        TrainingPresentationModel pm = new TrainingPresentationModel();
        headerController.loadStages(TRAINING,TRAINING_FXML,pm);
        hideWindow(closingNode,closingNodeName);
    }

    public void openTrainingClose(Node closingNode,String closingNodeName) {
        TrainingPresentationModel pm = new TrainingPresentationModel();
        headerController.loadStages(TRAINING,TRAINING_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

    public void openStadiumHide(Node closingNode, String closingNodeName){
        StadiumPresentationModel pm = new StadiumPresentationModel();
        headerController.loadStages(STADIUM,STADIUM_FXML,pm);
        hideWindow(closingNode,closingNodeName);
    }

    public void openStadiumClose(Node closingNode, String closingNodeName){
        StadiumPresentationModel pm = new StadiumPresentationModel();
        headerController.loadStages(STADIUM,STADIUM_FXML,pm);
        closeWindow(closingNode,closingNodeName);
    }

}
