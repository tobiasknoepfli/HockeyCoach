package hockeycoach.PresentationModels;

import hockeycoach.DB.DBLoader.DBLoader;
import hockeycoach.DB.DBLoader.DBPlayerLoader;
import hockeycoach.DB.DBWriter.DBImageWriter;
import hockeycoach.DB.DBWriter.DBPlayerWriter;
import hockeycoach.supportClasses.ButtonControls;
import hockeycoach.supportClasses.ImageChooser;
import hockeycoach.mainClasses.Player;
import hockeycoach.supportClasses.ImageHandler;
import hockeycoach.supportClasses.TextFieldAction;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.w3c.dom.events.MouseEvent;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static hockeycoach.AppStarter.PHOTOS;

public class NewPlayerPresentationModel extends PresentationModel {
    ImageChooser imageChooser = new ImageChooser();
    ImageHandler imageHandler =new ImageHandler();
    DBImageWriter dbImageWriter =new DBImageWriter();
    MouseEvent event;
    DBPlayerWriter dbPlayerWriter = new DBPlayerWriter();
    List<Player> allPlayersList, fnFiltered, lnFiltered, streetFiltered;
    ButtonControls buttonControls = new ButtonControls();
    TextFieldAction textFieldAction = new TextFieldAction();
    Stack<TextFieldAction> textFieldActions = new Stack<>();

    TableView<Player> allPlayers;
    ImageView playerPhoto;
    DatePicker playerBirthday;
    TextField playerFirstName, playerLastName, playerAge,
            street, zip, city, country, phone, email,
            positions, aLicence, bLicence, stick;
    TextArea strengths, weaknesses, notes;
    Button saveButton, backButton;

    @Override
    public void initializeControls(Pane root) {
        importFields(root);

        DBLoader dbLoader = new DBLoader();
        DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
        allPlayersList = dbPlayerLoader.getAllPlayers();

        TextField[] textFields = {playerFirstName, playerLastName, playerAge,
                street, zip, city, country, phone, email,
                positions, aLicence, bLicence, stick};

        Arrays.stream(textFields).forEach(textField -> textFieldAction.setupTextFieldUndo(textField,textFieldActions));

        allPlayers.getItems().clear();
        allPlayers.getItems().addAll(allPlayersList);

        getDBEntries(root);
        setupButtons(root);
        setupEventListeners(root);
    }

    @Override
    public void getDBEntries(Pane root) {

    }

    @Override
    public void setupButtons(Pane root) {
        saveButton.setOnAction(event -> {

            Player newPlayer = setPlayerValues();
            newPlayer.getPicture().setID(dbImageWriter.saveImage(newPlayer.getPicture()));
            imageHandler.copyImage(newPlayer.getPicture());

            dbPlayerWriter.writeNewPlayer(newPlayer);


//            clearAllFields();

//            DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
//            allPlayersList = dbPlayerLoader.getAllPlayers();
//            allPlayers.getItems().clear();
//            allPlayers.getItems().addAll(allPlayersList);
//            allPlayers.refresh();
        });

        backButton.setOnAction(event ->{
            textFieldAction.undoLastAction(textFieldActions);
        });

        playerPhoto.setOnMouseClicked(mouseEvent -> {
            playerPhoto.setImage(imageChooser.chooseImage(event));
        });
    }

    @Override
    public void setupEventListeners(Pane root) {
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

        playerBirthday.valueProperty().addListener((observable,oldValue,newValue) ->{
            playerAge.setText(calculatePlayerAge(newValue));
        });

    }

    private Player setPlayerValues() {
        Player newPlayer = new Player();
        newPlayer.setFirstName(playerFirstName.getText());
        newPlayer.setLastName(playerLastName.getText());
        newPlayer.setBirthday(playerBirthday.getValue());
        newPlayer.setStreet(street.getText());
        try {
            newPlayer.setZip(Integer.parseInt(zip.getText()));
        } catch (Exception e) {
            newPlayer.setZip(0000);
        }

        newPlayer.setCity(city.getText());
        newPlayer.setCountry(country.getText());
        newPlayer.setPhone(phone.getText());
        newPlayer.seteMail(email.getText());
        newPlayer.setPositions(positions.getText());
        newPlayer.setaLicence(aLicence.getText());
        newPlayer.setbLicence(bLicence.getText());
        newPlayer.setStick(stick.getText());
        newPlayer.setStrengths(strengths.getText());
        newPlayer.setWeaknesses(weaknesses.getText());
        newPlayer.setNotes(notes.getText());
        newPlayer.setPicture(imageHandler.setPicture(playerPhoto,playerLastName.getText() + " " + playerFirstName.getText(),"_Photo",PHOTOS));
        return newPlayer;
    }

    private void clearAllFields() {
        playerFirstName.clear();
        playerLastName.clear();
        street.clear();
        zip.clear();
        city.clear();
        country.clear();
        phone.clear();
        email.clear();
        positions.clear();
        aLicence.clear();
        bLicence.clear();
        stick.clear();
        strengths.clear();
        weaknesses.clear();
        notes.clear();
        playerPhoto.setImage(null);
    }

    @Override
    public void importFields(Pane root) {
        allPlayers = (TableView) root.lookup("#allPlayers");

        playerBirthday = (DatePicker) root.lookup("#playerBirthday");

        playerPhoto = (ImageView) root.lookup("#playerPhoto");

        playerFirstName = (TextField) root.lookup("#playerFirstName");
        playerLastName = (TextField) root.lookup("#playerLastName");
        playerAge = (TextField) root.lookup("#playerAge");
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

        saveButton = (Button) root.lookup("#saveButton");
        backButton = (Button) root.lookup("#backButton");
    }

    public String calculatePlayerAge(LocalDate localDate){
        if(localDate != null){
            LocalDate today = LocalDate.now();
            int age = Period.between(playerBirthday.getValue(),today).getYears();
            return String.valueOf(age);
        }
        return null;
    }
}
