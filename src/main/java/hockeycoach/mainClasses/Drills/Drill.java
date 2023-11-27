package hockeycoach.mainClasses.Drills;

import hockeycoach.mainClasses.Picture;

import java.util.ArrayList;

public class Drill {
    private int ID;
    private String name;
    private DrillCategory category;
    private DrillDifficulty difficulty;
    private DrillParticipation participation;
    private String description;
    private Boolean station;
    private ArrayList<String> tags;
    private Picture picture;
    private int sortingIndex;
    private String table;

    public Drill() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DrillCategory getCategory() {
        return category;
    }

    public void setCategory(DrillCategory category) {
        this.category = category;
    }

    public DrillDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DrillDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public DrillParticipation getParticipation() {
        return participation;
    }

    public void setParticipation(DrillParticipation participation) {
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

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public int getSortingIndex() {
        return sortingIndex;
    }

    public void setSortingIndex(int sortingIndex) {
        this.sortingIndex = sortingIndex;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
