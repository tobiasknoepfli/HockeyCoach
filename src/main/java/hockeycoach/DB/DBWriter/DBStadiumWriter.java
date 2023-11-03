package hockeycoach.DB.DBWriter;

import hockeycoach.DB.DBLoader.DBStadiumLoader;
import hockeycoach.mainClasses.Stadium;

import java.util.List;

public class DBStadiumWriter {
    DBStadiumLoader dbStadiumLoader = new DBStadiumLoader();

    public Stadium getStadiumFromName(String stadiumName){
        List<Stadium> allStadiums = dbStadiumLoader.getAllStadiums("SELECT * FROM stadium");
        Stadium stadium = new Stadium();
        stadium = allStadiums.stream().filter(std -> std.getStadiumName().equals(stadiumName)).findFirst().orElse(null);
        return  stadium;
    }
}
