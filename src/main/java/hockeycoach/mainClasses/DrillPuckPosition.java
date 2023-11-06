package hockeycoach.mainClasses;

public class DrillPuckPosition {
    int puckPositionsID;
    String puckPosition;
    String puckPositionName;

    public DrillPuckPosition(){}

    public int getPuckPositionsID() {
        return puckPositionsID;
    }

    public void setPuckPositionsID(int puckPositionsID) {
        this.puckPositionsID = puckPositionsID;
    }

    public String getPuckPosition() {
        return puckPosition;
    }

    public void setPuckPosition(String puckPosition) {
        this.puckPosition = puckPosition;
    }

    public String getPuckPositionName() {
        return puckPositionName;
    }

    public void setPuckPositionName(String puckPositionName) {
        this.puckPositionName = puckPositionName;
    }
}
