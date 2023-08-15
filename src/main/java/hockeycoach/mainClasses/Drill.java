package hockeycoach.mainClasses;

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
    private String imageLink;

    public Drill() {
    }

    public Drill(String name, String category, int difficulty, String participation) {
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
        this.participation = participation;
    }

    public Drill(int drillID, String name, String category, int difficulty, String participation, String description, Boolean station, ArrayList<String> tags, String imageLink) {
        this.drillID = drillID;
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
        this.participation = participation;
        this.description = description;
        this.station = station;
        this.tags = tags;
        this.imageLink = imageLink;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
