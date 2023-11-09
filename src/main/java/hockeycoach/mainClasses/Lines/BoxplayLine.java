package hockeycoach.mainClasses.Lines;

import hockeycoach.mainClasses.Lines.Line;
import hockeycoach.mainClasses.Player;

public class BoxplayLine extends Line {


    public BoxplayLine(){
    }

    public BoxplayLine(int lineNr){
        super(lineNr);
    }

    public BoxplayLine(int gameID, int lineNr, Player defenderLeft, Player defenderRight, Player forwardLeft, Player forwardRight){
        super(gameID,lineNr,defenderLeft,defenderRight,forwardLeft,forwardRight);
    }


}
