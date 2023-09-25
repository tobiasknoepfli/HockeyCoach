package hockeycoach.mainClasses;

public class PowerplayLine extends Line {
    int gameID, lineNr;
    Player defenderLeft,defenderRight,center,forwardLeft,forwardRight;

    public PowerplayLine(){
    }

    public PowerplayLine(int lineNr){
        super(lineNr);
    }

    public PowerplayLine(int gameID,int lineNr, Player defenderLeft, Player defenderRight, Player center, Player forwardLeft, Player forwardRight) {
        super(gameID, lineNr,defenderLeft, defenderRight, center, forwardLeft, forwardRight);
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

    @Override
    public int getGameID() {
        return gameID;
    }

    @Override
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
