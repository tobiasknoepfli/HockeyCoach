package hockeycoach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class NewDrillController {
    @FXML
    private Button backButton, newDrillButton, saveButton, editButton,
            cancelButton, deleteButton, closeWindowButton, searchButton,
            newCategoryButton, newTagButton,resetButton;
    @FXML
    private TextField searchBox, drillName, newCategory, addNewTag;
    @FXML
    private ComboBox drillCategoryFilter, drillParticipationFilter,
            drillDifficultyFilter, drillPuckPositionFilter, drillStationFilter,
            drillCategory, drillParticipation, drillDifficulty, drillPuckPosition, drillStation,drillTagsFilter;
    @FXML
    private TableView allDrills, drillTags;
    @FXML
    private ImageView drillImage;
    @FXML
    private TableColumn tagCol;

}
