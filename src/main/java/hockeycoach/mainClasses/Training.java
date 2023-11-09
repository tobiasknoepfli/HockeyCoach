package hockeycoach.mainClasses;

import hockeycoach.mainClasses.Drills.Drill;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Training {
    private int ID;
    private LocalDate trainingDate;
    private LocalTime trainingTime;
    private Stadium stadium;
    private Team team;
    private String mainFocus;
    private String pointers;
    private ArrayList<Drill> warmup;
    private ArrayList<Drill> together;
    private ArrayList<Drill> stations;
    private ArrayList<Drill> backup;

    public Training() {
    }

    public Training(int trainingID, LocalDate trainingDate, LocalTime trainingTime, Stadium stadium, Team team, String mainFocus, String pointers, ArrayList<Drill> warmup, ArrayList<Drill> together, ArrayList<Drill> stations, ArrayList<Drill> backup) {
        this.ID = trainingID;
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
        return ID;
    }

    public void setTrainingID(int trainingID) {
        this.ID = trainingID;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
    }

    public LocalTime getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(LocalTime trainingTime) {
        this.trainingTime = trainingTime;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
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

    public String getStadiumName(){
        if(stadium != null){
            return  stadium.getStadiumName();
        } else {
            return "";
        }
    }
}
