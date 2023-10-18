package hockeycoach.mainClasses.Lines;

import hockeycoach.mainClasses.Player;

import java.util.List;

public class Line {
    private int gameID;
    private int lineNr;
    private Player goalkeeper;
    private Player defenderLeft, defenderRight;
    private Player center;
    private Player forwardLeft, forwardRight;

    public Line() {
    }

    public Line(int lineNr) {
        this.lineNr = lineNr;
    }

    public Line(int gameID, int lineNr, Player goalkeeper, Player defenderLeft, Player defenderRight, Player center, Player forwardLeft, Player forwardRight) {
        this.gameID = gameID;
        this.lineNr = lineNr;
        this.goalkeeper = goalkeeper;
        this.defenderLeft = defenderLeft;
        this.defenderRight = defenderRight;
        this.center = center;
        this.forwardLeft = forwardLeft;
        this.forwardRight = forwardRight;
    }

    public Line(int gameID, int lineNr, Player defenderLeft, Player defenderRight, Player center, Player forwardLeft, Player forwardRight) {
        this.gameID = gameID;
        this.lineNr = lineNr;
        this.defenderLeft = defenderLeft;
        this.defenderRight = defenderRight;
        this.center = center;
        this.forwardLeft = forwardLeft;
        this.forwardRight = forwardRight;
    }

    public Line(int gameID, int lineNr, Player defenderLeft, Player defenderRight, Player forwardLeft, Player forwardRight) {
        this.gameID = gameID;
        this.lineNr = lineNr;
        this.defenderLeft = defenderLeft;
        this.defenderRight = defenderRight;
        this.forwardLeft = forwardLeft;
        this.forwardRight = forwardRight;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getLineNr() {
        return lineNr;
    }

    public void setLineNr(int lineNr) {
        this.lineNr = lineNr;
    }

    public Player getGoalkeeper() {
        return goalkeeper;
    }

    public void setGoalkeeper(Player goalkeeper) {
        this.goalkeeper = goalkeeper;
    }

    public Player getDefenderLeft() {
        return defenderLeft;
    }

    public void setDefenderLeft(Player defenderLeft) {
        this.defenderLeft = defenderLeft;
    }

    public Player getDefenderRight() {
        return defenderRight;
    }

    public void setDefenderRight(Player defenderRight) {
        this.defenderRight = defenderRight;
    }

    public Player getCenter() {
        return center;
    }

    public void setCenter(Player center) {
        this.center = center;
    }

    public Player getForwardLeft() {
        return forwardLeft;
    }

    public void setForwardLeft(Player forwardLeft) {
        this.forwardLeft = forwardLeft;
    }

    public Player getForwardRight() {
        return forwardRight;
    }

    public void setForwardRight(Player forwardRight) {
        this.forwardRight = forwardRight;
    }
}

