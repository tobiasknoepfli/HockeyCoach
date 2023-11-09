package hockeycoach.mainClasses.Drills;

public class DrillPuckPosition {
    int ID;
    String puckPosition;
    String puckPositionName;

    public DrillPuckPosition(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
