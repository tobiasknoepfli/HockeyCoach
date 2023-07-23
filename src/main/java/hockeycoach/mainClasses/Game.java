package hockeycoach.mainClasses;

public class Game {
    private String gameDate;
    private String gameTime;
    private String stadium;
    private String opponent;
    private Team team;

    public Game(String gameDate, String gameTime, String stadium, String opponent, Team team) {
        this.gameDate = gameDate;
        this.gameTime = gameTime;
        this.stadium = stadium;
        this.opponent = opponent;
        this.team = team;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
