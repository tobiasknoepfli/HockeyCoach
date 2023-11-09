package hockeycoach.mainClasses;

import java.util.List;

public class Stadium {
    public String stadiumName,stadiumAddress, stadiumCity,stadiumCountry;
    public int stadiumID, stadiumZip;

    public Stadium(){
    }

    public int getStadiumID() {   return stadiumID;
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

    public String getStadiumCity() {
        return stadiumCity;
    }

    public void setStadiumCity(String stadiumCity) {
        this.stadiumCity = stadiumCity;
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

    public Stadium findStadium(String stadiumName, List<Stadium> stadiumList){
        Stadium stadium = stadiumList.stream()
                .filter(std -> stadiumName.equals(std.getStadiumName()))
                .findFirst().orElse(null);
        return stadium;
    }
}
