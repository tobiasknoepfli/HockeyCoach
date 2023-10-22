package hockeycoach.mainClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class Drill {
    private int drillID;
    private String name;
    private String category;
    private int difficulty;
    private String participation;
    private String description;
    private Boolean station;
    private ArrayList<String> tags;
    private int imageID;
    private String table;
    private int sortingIndex;
    private String puckPosition;

    public Drill() {
    }

    public Drill(int drillID, String name, String category, int difficulty, String participation, String description, Boolean station, ArrayList<String> tags, int imageID) {
        this.drillID = drillID;
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
        this.participation = participation;
        this.description = description;
        this.station = station;
        this.tags = tags;
        this.imageID = imageID;
    }

    public Drill(int drillID, String name, String category, int difficulty, String participation, String description, Boolean station, ArrayList<String> tags, int imageID, String table, int sortingIndex, String puckPosition) {
        this.drillID = drillID;
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
        this.participation = participation;
        this.description = description;
        this.station = station;
        this.tags = tags;
        this.imageID = imageID;
        this.table  = table;
        this.sortingIndex= sortingIndex;
        this.puckPosition = puckPosition;
    }

    public int getDrillID() {
        return drillID;
    }

    public void setDrillID(int drillID) {
        this.drillID = drillID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getParticipation() {
        return participation;
    }

    public void setParticipation(String participation) {
        this.participation = participation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStation() {
        return station;
    }

    public void setStation(Boolean station) {
        this.station = station;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public int getSortingIndex() {
        return sortingIndex;
    }

    public void setSortingIndex(int sortingIndex) {
        this.sortingIndex = sortingIndex;
    }

    public String getPuckPosition() {
        return puckPosition;
    }

    public void setPuckPosition(String puckPosition) {
        this.puckPosition = puckPosition;
    }
}
