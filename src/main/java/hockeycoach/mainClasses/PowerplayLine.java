package hockeycoach.mainClasses;

public class PowerplayLine extends Line {
    private int gameID;
    private int lineNr;
    private Player defenderLeft, defenderRight;
    private Player center;
    private Player forwardLeft,forwardRight;

    public PowerplayLine(int gameID,int lineNr, Player defenderLeft, Player defenderRight, Player center, Player forwardLeft, Player forwardRight) {
        super(gameID, lineNr, null,defenderLeft, defenderRight, center, forwardLeft, forwardRight);
        this.gameID = gameID;
        this.lineNr = lineNr;
        this.defenderLeft = defenderLeft;
        this.defenderRight = defenderRight;
        this.center = center;
        this.forwardLeft = forwardLeft;
        this.forwardRight = forwardRight;
    }

    public String lineupToString(){
        String linNr = "Powerplay-Line " + lineNr;
        String defR = defenderRight.getLastName() + " " + defenderRight.getFirstName();
        String defL = defenderLeft.getLastName() + " " + defenderLeft.getFirstName();
        String cenName = center.getLastName() + " " + center.getFirstName();
        String fwR = forwardRight.getLastName() + " " + forwardRight.getFirstName();
        String fwL = forwardLeft.getLastName() + " " + forwardLeft.getFirstName();

        int a = defR.length();
        int b = defL.length();
        int c = fwR.length();
        int d = fwL.length();
        int e = Math.max(Math.max(a,b),Math.max(c,d));

        int g = cenName.length();

        String spaces1 = "";
        String spaces2 = "";

        for(int i= 0; i<e; i++){
            spaces1 +=" ";
        }
        for(int i= 0; i<g; i++){
            spaces2 +=" ";
        }

        String def = defR + spaces2 + defL;
        String cen    = spaces1 + cenName + spaces1;
        String fwd = fwR + spaces2 + fwL;

        String line[]  = {def,cen,fwd};
        return linNr + "\n" + String.join("\n",line);
    }
}
