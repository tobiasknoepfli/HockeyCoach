package hockeycoach.supportClasses.DragEvents;

import hockeycoach.DB.DBConverter.DBStringConverter;
import hockeycoach.PresentationModels.GameEditorPresentationModel;
import hockeycoach.mainClasses.Player;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.util.List;

import static hockeycoach.AppStarter.globalDraggedPlayer;

public class PlayerDrag {
    DBStringConverter dbStringConverter = new DBStringConverter();

    public void dragCopyEvent(TextField textField) {
        textField.setOnDragOver(event -> {
            if (event.getGestureSource() != textField && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        textField.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString() && !textField.getText().equals(db.getString()) && textField.getText().isEmpty()) {
                textField.setText(db.getString());
                success = true;
            }

            Player selectedPlayer = dbStringConverter.getPlayerFromString(textField.getText());

            event.setDropCompleted(success);
            event.consume();
        });
    }

    public void dragEvent(TextField textField, TableView<Player> teamPlayers) {

        textField.setOnDragOver(event -> {
            if (event.getGestureSource() != textField && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        textField.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString() && !textField.getText().equals(db.getString()) && textField.getText().isEmpty()) {
                textField.setText(db.getString());
                success = true;
            }

            Player selectedPlayer = dbStringConverter.getPlayerFromString(textField.getText());

            event.setDropCompleted(success);

            if (event.isDropCompleted()) {
                teamPlayers.getItems().remove(selectedPlayer);
            }

            globalDraggedPlayer = selectedPlayer;

            event.consume();
        });
    }
}
