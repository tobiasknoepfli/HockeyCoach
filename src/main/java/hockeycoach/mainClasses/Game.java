package hockeycoach.mainClasses;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Game {
    private LocalDate gameDate;
    private LocalTime gameTime;
    private String stadium;
    private String opponent;
    private int team;

    public Game(){
    }

    public Game(LocalDate gameDate, LocalTime gameTime, String stadium, String opponent, int team) {
        this.gameDate = gameDate;
        this.gameTime = gameTime;
        this.stadium = stadium;
        this.opponent = opponent;
        this.team = team;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public Time getGameTime() {
        return Time.valueOf(gameTime);
    }

    public void setGameTime(LocalTime gameTime) {
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

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }
}
