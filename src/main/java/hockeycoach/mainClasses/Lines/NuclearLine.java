package hockeycoach.mainClasses.Lines;

import hockeycoach.mainClasses.Player;

public class NuclearLine extends Line {
    private int gameID,lineNr;
    private Player defenderLeft, defenderRight;
    private Player center;
    private Player forwardLeft, forwardRight;

    public NuclearLine(){
    }

    public NuclearLine(int gameID, int lineNr,Player defenderLeft, Player defenderRight,Player center,Player forwardLeft,Player forwardRight){
        this.gameID = gameID;
        this.lineNr = lineNr;
        this.defenderLeft = defenderLeft;
        this.defenderRight = defenderRight;
        this.center = center;
        this.forwardLeft = forwardLeft;
        this.forwardRight = forwardRight;
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    @Override
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    @Override
    public int getLineNr() {
        return lineNr;
    }

    @Override
    public void setLineNr(int lineNr) {
        this.lineNr = lineNr;
    }

    @Override
    public Player getDefenderLeft() {
        return defenderLeft;
    }

    @Override
    public void setDefenderLeft(Player defenderLeft) {
        this.defenderLeft = defenderLeft;
    }

    @Override
    public Player getDefenderRight() {
        return defenderRight;
    }

    @Override
    public void setDefenderRight(Player defenderRight) {
        this.defenderRight = defenderRight;
    }

    @Override
    public Player getCenter() {
        return center;
    }

    @Override
    public void setCenter(Player center) {
        this.center = center;
    }

    @Override
    public Player getForwardLeft() {
        return forwardLeft;
    }

    @Override
    public void setForwardLeft(Player forwardLeft) {
        this.forwardLeft = forwardLeft;
    }

    @Override
    public Player getForwardRight() {
        return forwardRight;
    }

    @Override
    public void setForwardRight(Player forwardRight) {
        this.forwardRight = forwardRight;
    }
}
