package hockeycoach.UI;

import hockeycoach.mainClasses.ImageChooser;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Team;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class NewPlayerPresentationModel {
    MouseEvent event;
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
        saveButton = (Button) root.lookup("#saveButton");

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

        allPlayers.getItems().addListener((ListChangeListener<Player>) change -> {
            if (allPlayers.getItems().isEmpty()) {
                setControlsDisabled(false);
            } else {
                setControlsDisabled(true);
            }
        });

        playerPhoto.setOnMouseClicked(event -> handleImageClick());

        saveButton.setOnAction(event -> {
            String photoPath = savePlayerPhoto();
            Player newPlayer = readData(photoPath);
            DBWriter dbWriter = new DBWriter();
            dbWriter.writeNewPlayer(newPlayer);
            clearAllFields();

            DBLoader dbLoader =new DBLoader();
            allPlayersList = dbLoader.getAllPlayers("SELECT * FROM player");
            allPlayers.getItems().clear();
            allPlayers.getItems().addAll(allPlayersList);
            allPlayers.refresh();
        });
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

    private void handleImageClick() {
        ImageChooser imageChooser = new ImageChooser();
        Image image = imageChooser.chooseImage(event);
        playerPhoto.setImage(image);
    }

    private String savePlayerPhoto() {
        Image selectedImage = playerPhoto.getImage();
        if (selectedImage != null) {
            String playerPhotoText = playerLastName.getText() + "" + playerFirstName.getText() + "_Photo";

            String imageFormat = selectedImage.getUrl().substring(selectedImage.getUrl().lastIndexOf(".") + 1).toLowerCase();
            if (!imageFormat.equals("jpg") && !imageFormat.equals("jpeg") && !imageFormat.equals("png")) {
                imageFormat = "jpg";
            }

            String destinationFileName = playerPhotoText + "." + imageFormat;
            String destinationDirectory = "src/main/java/hockeycoach/files/photos";

            try {
                URL imageUrl = new URL(selectedImage.getUrl());
                String decodedImageUrl = decodeUrl(imageUrl.getPath()); // Decode the URL
                File selectedImageFile = new File(decodedImageUrl);
                Path destinationPath = Path.of(destinationDirectory, destinationFileName);

                Files.copy(selectedImageFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                return destinationPath.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Helper method to decode URL
    private String decodeUrl(String url) {
        try {
            return java.net.URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return url;
        }
    }

    private Player readData(String newImage) {
        Player newPlayer = new Player();
        newPlayer.setFirstName(playerFirstName.getText());
        newPlayer.setLastName(playerLastName.getText());
        newPlayer.setStreet(street.getText());
        try {
            newPlayer.setZip(Integer.parseInt(zip.getText()));
        } catch(Exception e){
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
        newPlayer.setPhotoPath(newImage);
        return newPlayer;
    }

    private void clearAllFields(){
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
}
