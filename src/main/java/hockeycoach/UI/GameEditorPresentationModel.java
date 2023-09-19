package hockeycoach.UI;

import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.SingletonTeam;
import hockeycoach.mainClasses.Team;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameEditorPresentationModel {
    Team selectedTeam;

    TextField gameDate;
    TextField gameTime;
    TextField gameStadium;
    TextField gameTeam;
    TextField gameOpponent;
    TableView<Player> teamPlayers;
    TableView<Player> availablePlayers;
    ImageView boardImage;

    TextField gk1;
    TextField gk2;
    TextField dl1;
    TextField dl2;
    TextField dl3;
    TextField dl4;
    TextField dr1;
    TextField dr2;
    TextField dr3;
    TextField dr4;
    TextField c1;
    TextField c2;
    TextField c3;
    TextField c4;
    TextField fl1;
    TextField fl2;
    TextField fl3;
    TextField fl4;
    TextField fr1;
    TextField fr2;
    TextField fr3;
    TextField fr4;

    public void initializeControls(Pane root) {
        gameDate = (TextField) root.lookup("#gameDate");
        gameTime = (TextField) root.lookup("#gameTime");
        gameStadium = (TextField) root.lookup("#gameStadium");
        gameTeam = (TextField) root.lookup("#gameTeam");
        gameOpponent = (TextField) root.lookup("#gameOpponent");
        teamPlayers = (TableView) root.lookup("#teamPlayers");
        availablePlayers = (TableView) root.lookup("#availablePlayers");
        boardImage = (ImageView) root.lookup("#boardImage");

        gk1 = (TextField) root.lookup("#gk1");
        gk2 = (TextField) root.lookup("#gk2");
        dl1 = (TextField) root.lookup("#dl1");
        dl2 = (TextField) root.lookup("#dl2");
        dl3 = (TextField) root.lookup("#dl3");
        dl4 = (TextField) root.lookup("#dl4");
        dr1 = (TextField) root.lookup("#dr1");
        dr2 = (TextField) root.lookup("#dr2");
        dr3 = (TextField) root.lookup("#dr3");
        dr4 = (TextField) root.lookup("#dr4");
        c1 = (TextField) root.lookup("#c1");
        c2 = (TextField) root.lookup("#c2");
        c3 = (TextField) root.lookup("#c3");
        c4 = (TextField) root.lookup("#c4");
        fl1 = (TextField) root.lookup("#fl1");
        fl2 = (TextField) root.lookup("#fl2");
        fl3 = (TextField) root.lookup("#fl3");
        fl4 = (TextField) root.lookup("#fl4");
        fr1 = (TextField) root.lookup("#fr1");
        fr2 = (TextField) root.lookup("#fr2");
        fr3 = (TextField) root.lookup("#fr3");
        fr4 = (TextField) root.lookup("#fr4");

        File file = new File("src/main/java/hockeycoach/files/background/board.jpg");
        Image image = new Image(file.toURI().toString());
        boardImage.setImage(image);

        TextField[] textFields = {gk1,gk2,dl1,dl2,dl3,dl4,dr1,dr2,dr3,dr4,c1,c2,c3,c4,fl1,fl2,fl3,fl4,fr1,fr2,fr3,fr4};
        Arrays.stream(textFields).forEach(this::dragEvent);

        dragDetect(teamPlayers);
        doubleClick();

        selectedTeam = SingletonTeam.getInstance().getSelectedTeam();
        DBLoader dbLoader = new DBLoader();
        List<Player> playerList = dbLoader.getPlayers("SELECT p.* FROM player p INNER JOIN playerXteam px ON p.playerID = px.playerID WHERE px.teamID LIKE '" + selectedTeam.getTeamID() + "'", selectedTeam.getTeamID());

        teamPlayers.getItems().clear();
        teamPlayers.getItems().setAll(playerList);

        gameTeam.setText(selectedTeam.getName());
    }

    public void setupEventListeners(){
    }

    public void dragEvent(TextField textField){
        textField.setOnDragOver(event ->{
            if(event.getGestureSource() !=textField && event.getDragboard().hasString()){
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        textField.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if(db.hasString() && !textField.getText().equals(db.getString())) {
                textField.setText(db.getString());
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    public void dragDetect(TableView<Player> tableView){
        tableView.setOnDragDetected(event ->{
            Player selectedPlayer = tableView.getSelectionModel().getSelectedItem();

            if(selectedPlayer != null){
                Dragboard dragboard = tableView.startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                content.putString(selectedPlayer.getLastName() + " " + selectedPlayer.getFirstName());
                dragboard.setContent(content);
                tableView.getItems().remove(selectedPlayer);
            }
            event.consume();
        });
    }

    public void doubleClick(){
        TextField[] textFields = {gk1, gk2, dl1, dl2, dl3, dl4, dr1, dr2, dr3, dr4, c1, c2, c3, c4, fl1, fl2, fl3, fl4, fr1, fr2, fr3, fr4};
        Arrays.stream(textFields).forEach(this::dragEvent);

        Arrays.stream(textFields).forEach(textField -> {
            textField.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    String playerName = textField.getText();
                    if (!playerName.isEmpty()) {
                        // Create a new Player object with the extracted name
                        Player player = new Player();
                        String[] nameParts = playerName.split(" ");
                        if (nameParts.length >= 2) {
                            player.setFirstName(nameParts[1]);
                            player.setLastName(nameParts[0]);
                            // Add the player back to the TableView
                            teamPlayers.getItems().add(player);
                            // Clear the TextField
                            textField.clear();
                        }
                    }
                }
            });
        });
    }
}
