package hockeycoach.mainClasses;

import java.util.List;

public class Line {
    private int gameID;
    private int lineNr;
    private Player goalkeeper;
    private Player defenderLeft, defenderRight;
    private Player center;
    private Player forwardLeft, forwardRight;
    private List<Player> forwardSubstitutes, defenseSubstitutes, goalkeeperSubstitutes;

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

    public List<Player> getForwardSubstitutes() {
        return forwardSubstitutes;
    }

    public void setForwardSubstitutes(List<Player> forwardSubstitutes) {
        this.forwardSubstitutes = forwardSubstitutes;
    }

    public List<Player> getDefenseSubstitutes() {
        return defenseSubstitutes;
    }

    public void setDefenseSubstitutes(List<Player> defenseSubstitutes) {
        this.defenseSubstitutes = defenseSubstitutes;
    }

    public List<Player> getGoalkeeperSubstitutes() {
        return goalkeeperSubstitutes;
    }

    public void setGoalkeeperSubstitutes(List<Player> goalkeeperSubstitutes) {
        this.goalkeeperSubstitutes = goalkeeperSubstitutes;
    }

    public String lineupToString() {
        String linNr = "Line " + lineNr;
        String gkName = "GK: " + goalkeeper.getLastName() + " " + goalkeeper.getFirstName();
        String defR = "DR: " + defenderRight.getLastName() + " " + defenderRight.getFirstName();
        String defL = "DL: " + defenderLeft.getLastName() + " " + defenderLeft.getFirstName();
        String cenName = "CE: " + center.getLastName() + " " + center.getFirstName();
        String fwR = "FR: " + forwardRight.getLastName() + " " + forwardRight.getFirstName();
        String fwL = "FL: " + forwardLeft.getLastName() + " " + forwardLeft.getFirstName();

        int a = defR.length();
        int b = defL.length();
        int c = fwR.length();
        int d = fwL.length();
        int e = Math.max(Math.max(a, b), Math.max(c, d));

        int f = gkName.length();
        int g = cenName.length();
        int h = Math.max(f, g);

        String spaces1 = "";
        String spaces2 = "";

        for (int i = 0; i < e; i++) {
            spaces1 += " ";
        }
        for (int i = 0; i < h; i++) {
            spaces2 += " ";
        }

        if(spaces1.length()<15){spaces1 ="               ";}
        if(spaces2.length()<15){spaces2 ="               ";}

        String gk = spaces1 + gkName + spaces1;
        String def = defR + spaces2 + defL;
        String cen = spaces1 + cenName + spaces1;
        String fwd = fwR + spaces2 + fwL;

        if (lineNr>1) {
            String line[] = {def, cen, fwd};
            return linNr + "\n\n" + String.join("\n\n", line);
        }else{
            String line[] = {gk, def, cen, fwd};
            return linNr + "\n\n" + String.join("\n\n", line);
        }

    }
}

