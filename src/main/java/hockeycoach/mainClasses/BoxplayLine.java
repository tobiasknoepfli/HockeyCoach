package hockeycoach.mainClasses;

public class BoxplayLine extends Line{
    private int gameID, lineNr;
    private Player defenderLeft, defenderRight;
    private Player forwardLeft, forwardRight;

    public BoxplayLine(int gameID, int lineNr, Player defenderLeft, Player defenderRight, Player forwardLeft, Player forwardRight){
        super(gameID,lineNr,null,defenderLeft,defenderRight,null,forwardLeft,forwardRight);
        this.gameID = gameID;
        this.lineNr = lineNr;
        this.defenderLeft = defenderLeft;
        this.defenderRight = defenderRight;
        this.forwardLeft= forwardLeft;
        this.forwardRight = forwardRight;
    }

    public String lineupToString(){
        String linNr = "Boxplay-Line " + lineNr;
        String defR = defenderRight.getLastName() + " " + defenderRight.getFirstName();
        String defL = defenderLeft.getLastName() + " " + defenderLeft.getFirstName();
        String fwR = forwardRight.getLastName() + " " + forwardRight.getFirstName();
        String fwL = forwardLeft.getLastName() + " " + forwardLeft.getFirstName();

        int a = defR.length();
        int b = defL.length();
        int c = fwR.length();
        int d = fwL.length();
        int e = Math.max(Math.max(a,b),Math.max(c,d));

        String spaces1 = "";

        for(int i= 0; i<e; i++){
            spaces1 +=" ";
        }

        String def = defR + spaces1 + defL;
        String fwd = fwR + spaces1 + fwL;

        String line[]  = {def,fwd};
        return linNr + "\n" + String.join("\n\n",line);
    }
}
