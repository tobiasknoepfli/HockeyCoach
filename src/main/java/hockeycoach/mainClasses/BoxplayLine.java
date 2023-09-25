package hockeycoach.mainClasses;

public class BoxplayLine extends Line{


    public BoxplayLine(){
    }

    public BoxplayLine(int lineNr){
        super(lineNr);
    }

    public BoxplayLine(int gameID, int lineNr, Player defenderLeft, Player defenderRight, Player forwardLeft, Player forwardRight){
        super(gameID,lineNr,defenderLeft,defenderRight,forwardLeft,forwardRight);
    }
}
