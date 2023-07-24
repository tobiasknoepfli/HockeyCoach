package hockeycoach.mainClasses;

import java.sql.Time;
import java.util.Date;

public class Game {
    private Date gameDate;
    private Time gameTime;
    private String stadium;
    private String opponent;
    private String team;

    public Game(){
    }

    public Game(Date gameDate, Time gameTime, String stadium, String opponent, String team) {
        this.gameDate = gameDate;
        this.gameTime = gameTime;
        this.stadium = stadium;
        this.opponent = opponent;
        this.team = team;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Time getGameTime() {
        return gameTime;
    }

    public void setGameTime(Time gameTime) {
        this.gameTime = gameTime;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
