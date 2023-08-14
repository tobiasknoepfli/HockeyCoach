package hockeycoach.mainClasses;

import java.sql.Time;
import java.util.Date;

public class Training {
    private int trainingID;
    private Date trainingDate;
    private Time trainingTime;
    private String stadium;
    private String team;
    private String mainFocus;
    private String pointers;

    public Training(){
    }

    public Training(int trainingID, Date trainingDate, Time trainingTime,  String stadium,String team, String mainFocus, String pointers) {
        this.trainingID = trainingID;
        this.trainingDate = trainingDate;
        this.trainingTime = trainingTime;
        this.stadium = stadium;
        this.team = team;
        this.mainFocus = mainFocus;
        this.pointers = pointers;
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
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
}
