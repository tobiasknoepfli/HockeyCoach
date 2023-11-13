package hockeycoach.mainClasses.Lines;

import hockeycoach.mainClasses.Player;

public class OvertimeLine extends Line {
    int gameID;
    Player defenderLeft1,defenderLeft2,defenderRight1,defenderRight2,center1,center2,substituteDefender,substituteForward;

    public OvertimeLine(){}

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public Player getDefenderLeft1() {
        return defenderLeft1;
    }

    public void setDefenderLeft1(Player defenderLeft1) {
        this.defenderLeft1 = defenderLeft1;
    }

    public Player getDefenderLeft2() {
        return defenderLeft2;
    }

    public void setDefenderLeft2(Player defenderLeft2) {
        this.defenderLeft2 = defenderLeft2;
    }

    public Player getDefenderRight1() {
        return defenderRight1;
    }

    public void setDefenderRight1(Player defenderRight1) {
        this.defenderRight1 = defenderRight1;
    }

    public Player getDefenderRight2() {
        return defenderRight2;
    }

    public void setDefenderRight2(Player defenderRight2) {
        this.defenderRight2 = defenderRight2;
    }

    public Player getCenter1() {
        return center1;
    }

    public void setCenter1(Player center1) {
        this.center1 = center1;
    }

    public Player getCenter2() {
        return center2;
    }

    public void setCenter2(Player center2) {
        this.center2 = center2;
    }

    public Player getSubstituteDefender() {
        return substituteDefender;
    }

    public void setSubstituteDefender(Player substituteDefender) {
        this.substituteDefender = substituteDefender;
    }

    public Player getSubstituteForward() {
        return substituteForward;
    }

    public void setSubstituteForward(Player substituteForward) {
        this.substituteForward = substituteForward;
    }
}
