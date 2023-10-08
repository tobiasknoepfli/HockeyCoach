package hockeycoach.controllers;

import hockeycoach.mainClasses.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class NewPlayerPageController extends Controller {
    @FXML
    private Button saveButton;

    @FXML
    private ImageView playerPhoto;

    @FXML
    private TableView<Player> allPlayers;

    @FXML
    private TextArea notes, strengths, weaknesses;

    @FXML
    private TextField playerFirstName, playerLastName, street, zip, city, country, phone, email,
            positions, aLicence, bLicence, stick;


}
