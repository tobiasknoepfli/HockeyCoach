package hockeycoach.DB.DBConverter;

import hockeycoach.DB.DBLoader.*;
import hockeycoach.mainClasses.Drills.*;
import hockeycoach.mainClasses.Picture;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Stadium;
import hockeycoach.mainClasses.Team;

import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.*;

public class DBStringConverter {
    public Drill getDrillFromString(String drillName) {
        DBDrillLoader dbDrillLoader =new DBDrillLoader();
        Drill drill = new Drill();
        List<Drill> allDrills = allDrills = dbDrillLoader.getAllDrills();
        return drill = allDrills.stream()
                .filter(d -> drillName.equals(d.getName()))
                .findFirst()
                .orElse(null);
    }

    public DrillCategory getDrillCategoryFromString(String categoryName) {
        DBDrillValuesLoader dbDrillValuesLoader =new DBDrillValuesLoader();
        DrillCategory drillCategory = new DrillCategory();
        List<DrillCategory> allCategories = allCategories = dbDrillValuesLoader.getAllCategories();
        return drillCategory = allCategories.stream()
                .filter(c -> categoryName.equals(c.getCategory()))
                .findFirst()
                .orElse(null);
    }

    public DrillDifficulty getDrillDifficultyFromString(String difficultyName) {
        DBDrillValuesLoader dbDrillValuesLoader = new DBDrillValuesLoader();
        DrillDifficulty drillDifficulty = new DrillDifficulty();
        List<DrillDifficulty> allDifficulties =  allDifficulties = dbDrillValuesLoader.getAllDifficulties();
        return drillDifficulty = allDifficulties.stream()
                .filter(d -> difficultyName.equals(d.getDifficultyName()))
                .findFirst()
                .orElse(null);
    }

    public DrillParticipation getDrillParticipationFromString(String participationName) {
        DBDrillValuesLoader dbDrillValuesLoader =new DBDrillValuesLoader();
        DrillParticipation drillParticipation = new DrillParticipation();
        List<DrillParticipation> allParticipations =  allParticipations = dbDrillValuesLoader.getAllParticipations();
        return drillParticipation = allParticipations.stream()
                .filter(p -> participationName.equals(p.drillParticipation))
                .findFirst()
                .orElse(null);
    }

    public DrillPuckPosition getDrillPuckPositionFromString(String puckPositionName) {
        DBDrillValuesLoader dbDrillValuesLoader = new DBDrillValuesLoader();
        DrillPuckPosition drillPuckPosition = new DrillPuckPosition();
        List<DrillPuckPosition> allPuckPositions =allPuckPositions = dbDrillValuesLoader.getAllPuckPositions();
        return drillPuckPosition = allPuckPositions.stream()
                .filter(pp -> puckPositionName.equals(pp.getPuckPosition()))
                .findFirst()
                .orElse(null);
    }

    public DrillTag getDrillTagFromString(String drillTagName) {
        DBDrillValuesLoader dbDrillValuesLoader = new DBDrillValuesLoader();
        DrillTag drillTag = new DrillTag();
        List<DrillTag> allDrillTags =allDrillTags = dbDrillValuesLoader.getAllDrillTags();
        return drillTag = allDrillTags.stream()
                .filter(dt -> drillTagName.equals(dt.getDrillTag()))
                .findFirst()
                .orElse(null);
    }

    public Picture getPictureFromString(String pictureName) {
        DBImageLoader dbImageLoader =new DBImageLoader();
        Picture picture = new Picture();
        List<Picture> allPictures = allPictures = dbImageLoader.getAllPictures();
        return picture = allPictures.stream()
                .filter(p -> pictureName.equals(p.getPictureName()))
                .findFirst()
                .orElse(null);
    }

    public Player getPlayerFromString(String playerName) {
        DBPlayerLoader dbPlayerLoader=new DBPlayerLoader();
        Player player = new Player();
        String[] fullName = playerName.split("\\s+");
        List<Player> allPlayers = allPlayers =dbPlayerLoader.getAllPlayers();
        return player = allPlayers.stream()
                .filter(p -> fullName[0].equals(p.getLastName()) && fullName[1].equals(p.getFirstName())
                        || fullName[0].equals(p.getFirstName()) && fullName[1].equals(p.getLastName()))
                .findFirst()
                .orElse(null);
    }

    public Stadium getStadiumFromString(String stadiumName) {
        DBStadiumLoader dbStadiumLoader =new DBStadiumLoader();
        Stadium stadium = new Stadium();
        List<Stadium> allStadiums =  allStadiums = dbStadiumLoader.getAllStadiums();
        return stadium = allStadiums.stream()
                .filter(s -> stadiumName.equals(s.getStadiumName()))
                .findFirst()
                .orElse(null);
    }

    public Team getTeamFromString(String teamName){
        DBTeamLoader dbTeamLoader = new DBTeamLoader();
        Team team = new Team();
        List<Team> allTeams = allTeams = dbTeamLoader.getAllTeams();
        return team = allTeams.stream()
                .filter(t -> teamName.equals(t.getName()))
                .findFirst()
                .orElse(null);
    }
}
