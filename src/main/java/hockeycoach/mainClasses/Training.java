package hockeycoach.mainClasses;

import javafx.scene.control.Button;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Training {
    private int trainingID;
    private Date trainingDate;
    private Time trainingTime;
    private String stadium;
    private int team;
    private String mainFocus;
    private String pointers;
    private ArrayList<Drill> warmup;
    private ArrayList<Drill> together;
    private ArrayList<Drill> stations;
    private ArrayList<Drill> backup;

    public Training() {
    }

    public Training(int trainingID, Date trainingDate, Time trainingTime, String stadium, int team, String mainFocus, String pointers, ArrayList<Drill> warmup, ArrayList<Drill> together, ArrayList<Drill> stations, ArrayList<Drill> backup) {
        this.trainingID = trainingID;
        this.trainingDate = trainingDate;
        this.trainingTime = trainingTime;
        this.stadium = stadium;
        this.team = team;
        this.mainFocus = mainFocus;
        this.pointers = pointers;
        this.warmup = warmup;
        this.together = together;
        this.stations = stations;
        this.backup = backup;
    }

    public int getTrainingID() {
        return trainingID;
    }

    public void setTrainingID(int trainingID) {
        this.trainingID = trainingID;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Time getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(Time trainingTime) {
        this.trainingTime = trainingTime;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getMainFocus() {
        return mainFocus;
    }

    public void setMainFocus(String mainFocus) {
        this.mainFocus = mainFocus;
    }

    public String getPointers() {
        return pointers;
    }

    public void setPointers(String pointers) {
        this.pointers = pointers;
    }

    public ArrayList<Drill> getWarmup() {
        return warmup;
    }

    public void setWarmup(ArrayList<Drill> warmup) {
        this.warmup = warmup;
    }

    public ArrayList<Drill> getTogether() {
        return together;
    }

    public void setTogether(ArrayList<Drill> together) {
        this.together = together;
    }

    public ArrayList<Drill> getStation() {
        return stations;
    }

    public void setStation(ArrayList<Drill> stations) {
        this.stations = stations;
    }

    public ArrayList<Drill> getBackup() {
        return backup;
    }

    public void setBackup(ArrayList<Drill> backup) {
        this.backup = backup;
    }
}
