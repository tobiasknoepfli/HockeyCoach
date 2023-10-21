package hockeycoach.mainClasses;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Game {
    private int gameID;
    private LocalDate gameDate;
    private LocalTime gameTime;
    private String stadium;
    private String opponent;
    private int team;
    private Player captain, assistant1, assistant2, penalty1, penalty2, emptyNet1, emptyNet2;

    public Game() {
    }

    public Game(LocalDate gameDate, LocalTime gameTime, String stadium, String opponent, int team, Player captain, Player assistant1, Player assistant2, Player penalty1, Player penalty2, Player emptyNet1, Player emptyNet2) {
        this.gameDate = gameDate;
        this.gameTime = gameTime;
        this.stadium = stadium;
        this.opponent = opponent;
        this.team = team;
        this.captain = captain;
        this.assistant1 = assistant1;
        this.assistant2 = assistant2;
        this.penalty1 = penalty1;
        this.penalty2 = penalty2;
        this.emptyNet1 = emptyNet1;
        this.emptyNet2 = emptyNet2;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
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

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public Player getAssistant1() {
        return assistant1;
    }

    public void setAssistant1(Player assistant1) {
        this.assistant1 = assistant1;
    }

    public Player getAssistant2() {
        return assistant2;
    }

    public void setAssistant2(Player assistant2) {
        this.assistant2 = assistant2;
    }

    public Player getPenalty1() {
        return penalty1;
    }

    public void setPenalty1(Player penalty1) {
        this.penalty1 = penalty1;
    }

    public Player getPenalty2() {
        return penalty2;
    }

    public void setPenalty2(Player penalty2) {
        this.penalty2 = penalty2;
    }

    public Player getEmptyNet1() {
        return emptyNet1;
    }

    public void setEmptyNet1(Player emptyNet1) {
        this.emptyNet1 = emptyNet1;
    }

    public Player getEmptyNet2() {
        return emptyNet2;
    }

    public void setEmptyNet2(Player emptyNet2) {
        this.emptyNet2 = emptyNet2;
    }
}
