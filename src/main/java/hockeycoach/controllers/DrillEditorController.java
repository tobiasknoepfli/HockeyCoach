package hockeycoach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class DrillEditorController {
    @FXML
    private Button backButton, newDrillButton, saveButton, editButton,
            cancelButton, deleteButton, closeWindowButton, searchButton,
            newCategoryButton, newTagButton,resetButton;
    @FXML
    private TextField searchBox, drillName, newCategory, addNewTag;
    @FXML
    private ComboBox drillCategoryFilter, drillParticipationFilter,
            drillDifficultyFilter, drillStationFilter,
            drillCategory, drillParticipation, drillDifficulty,  drillStation,drillTagsFilter;
    @FXML
    private TableView allDrills, drillTags;
    @FXML
    private ImageView drillImage;
    @FXML
    private TableColumn tagCol;

    @FXML
    private TextArea drillDescription, drillPurpose;

    @FXML
    private Label puckPositionName;

    @FXML
    private TableColumn drillCategoryCol,drillParticipationCol,drillDifficultyCol;

}
