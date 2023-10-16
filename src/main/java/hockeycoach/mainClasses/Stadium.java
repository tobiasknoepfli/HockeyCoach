package hockeycoach.mainClasses;

public class Stadium {
    private String stadiumName,stadiumAddress,stadiumPlace,stadiumCountry;
    private int stadiumID, stadiumZip;

    public Stadium(){
    }

    public int getStadiumID() {
        return stadiumID;
    }

    public void setStadiumID(int stadiumID) {
        this.stadiumID = stadiumID;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getStadiumAddress() {
        return stadiumAddress;
    }

    public void setStadiumAddress(String stadiumAddress) {
        this.stadiumAddress = stadiumAddress;
    }

    public String getStadiumPlace() {
        return stadiumPlace;
    }

    public void setStadiumPlace(String stadiumPlace) {
        this.stadiumPlace = stadiumPlace;
    }

    public String getStadiumCountry() {
        return stadiumCountry;
    }

    public void setStadiumCountry(String stadiumCountry) {
        this.stadiumCountry = stadiumCountry;
    }

    public int getStadiumZip() {
        return stadiumZip;
    }

    public void setStadiumZip(int stadiumZip) {
        this.stadiumZip = stadiumZip;
    }
}
