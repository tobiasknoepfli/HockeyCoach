package hockeycoach.UI;

import hockeycoach.mainClasses.Player;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.stream.Collectors;

public class NewPlayerPresentationModel {
    List<Player> allPlayersList;
    List<Player> fnFiltered;
    List<Player> lnFiltered;
    List<Player> streetFiltered;

    AnchorPane contentPane;
    TableView<Player> allPlayers;
    ImageView playerPhoto;
    TextField playerFirstName;
    TextField playerLastName;
    TextField street;
    TextField zip;
    TextField city;
    TextField country;
    TextField phone;
    TextField email;
    TextField positions;
    TextField aLicence;
    TextField bLicence;
    TextField stick;
    TextArea strengths;
    TextArea weaknesses;
    TextArea notes;
    Button saveButton;

    public NewPlayerPresentationModel(AnchorPane contentPane) {
        this.contentPane = contentPane;
    }

    public void initializeControls(Pane root) {
        allPlayers = (TableView) root.lookup("#allPlayers");
        playerPhoto = (ImageView) root.lookup("#playerPhoto");
        playerFirstName = (TextField) root.lookup("#playerFirstName");
        playerLastName = (TextField) root.lookup("#playerLastName");
        street = (TextField) root.lookup("#street");
        zip = (TextField) root.lookup("#zip");
        city = (TextField) root.lookup("#city");
        country = (TextField) root.lookup("#country");
        phone = (TextField) root.lookup("#phone");
        email = (TextField) root.lookup("#email");
        positions = (TextField) root.lookup("#positions");
        aLicence = (TextField) root.lookup("#aLicence");
        bLicence = (TextField) root.lookup("#bLicence");
        stick = (TextField) root.lookup("#stick");
        strengths = (TextArea) root.lookup("#strengths");
        weaknesses = (TextArea) root.lookup("#weaknesses");
        notes = (TextArea) root.lookup("#notes");
        saveButton=(Button) root.lookup("#saveButton");

        DBLoader dbLoader = new DBLoader();
        allPlayersList = dbLoader.getAllPlayers("SELECT * FROM player");

        allPlayers.getItems().clear();
        allPlayers.getItems().addAll(allPlayersList);

        setControlsDisabled(true);

        setupEventListeners();
    }

    public void setupEventListeners() {
        playerFirstName.textProperty().addListener((obs, oldValue, newValue) -> {
            fnFiltered = allPlayersList.stream()
                    .filter(player -> player.getFirstName().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());
            allPlayers.getItems().clear();
            allPlayers.getItems().addAll(fnFiltered);
        });

        playerLastName.textProperty().addListener((obs, oldValue, newValue) -> {
            lnFiltered = fnFiltered.stream()
                    .filter(player -> player.getLastName().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());
            allPlayers.getItems().clear();
            allPlayers.getItems().addAll(lnFiltered);
        });

        street.textProperty().addListener((obs, oldValue, newValue) -> {
            streetFiltered = lnFiltered.stream()
                    .filter(player -> player.getStreet().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());
            allPlayers.getItems().clear();
            allPlayers.getItems().addAll(streetFiltered);
        });

        allPlayers.getItems().addListener((ListChangeListener<Player>) change ->{
            if(allPlayers.getItems().isEmpty()){
                setControlsDisabled(false);
            }
        } );

    }

    private void setControlsDisabled(boolean disabled) {
        zip.setDisable(disabled);
        city.setDisable(disabled);
        country.setDisable(disabled);
        phone.setDisable(disabled);
        email.setDisable(disabled);
        positions.setDisable(disabled);
        aLicence.setDisable(disabled);
        bLicence.setDisable(disabled);
        stick.setDisable(disabled);
        strengths.setDisable(disabled);
        weaknesses.setDisable(disabled);
        notes.setDisable(disabled);
        playerPhoto.setDisable(disabled);
        saveButton.setDisable(disabled);
    }
}
