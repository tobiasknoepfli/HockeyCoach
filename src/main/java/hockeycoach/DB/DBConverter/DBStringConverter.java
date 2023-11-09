package hockeycoach.DB.DBConverter;

import hockeycoach.DB.DBLoader.*;
import hockeycoach.mainClasses.Drills.*;
import hockeycoach.mainClasses.Picture;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Stadium;
import hockeycoach.mainClasses.Team;

import java.util.ArrayList;
import java.util.List;

public class DBStringConverter {
    DBDrillLoader dbDrillLoader = new DBDrillLoader();
    DBDrillValuesLoader dbDrillValuesLoader = new DBDrillValuesLoader();
    DBImageLoader dbImageLoader = new DBImageLoader();
    DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
    DBStadiumLoader dbStadiumLoader = new DBStadiumLoader();
    DBTeamLoader dbTeamLoader = new DBTeamLoader();

    List<Drill> allDrills = new ArrayList<>();
    List<DrillCategory> allCategories = new ArrayList<>();
    List<DrillDifficulty> allDifficulties = new ArrayList<>();
    List<DrillParticipation> allParticipations = new ArrayList<>();
    List<DrillPuckPosition> allPuckPositions = new ArrayList<>();
    List<DrillTag> allDrillTags = new ArrayList<>();
    List<Picture> allPictures = new ArrayList<>();
    List<Player> allPlayers = new ArrayList<>();
    List<Stadium> allStadiums = new ArrayList<>();
    List<Team> allTeams = new ArrayList<>();

    Drill drill = new Drill();
    DrillCategory drillCategory = new DrillCategory();
    DrillDifficulty drillDifficulty = new DrillDifficulty();
    DrillParticipation drillParticipation = new DrillParticipation();
    DrillPuckPosition drillPuckPosition = new DrillPuckPosition();
    DrillTag drillTag = new DrillTag();
    Picture picture = new Picture();
    Player player = new Player();
    Stadium stadium = new Stadium();
    Team team = new Team();

    public Drill getDrillFromString(String drillName) {
        allDrills = dbDrillLoader.getAllDrills();
        return drill = allDrills.stream()
                .filter(d -> drillName.equals(d.getName()))
                .findFirst()
                .orElse(null);
    }

    public DrillCategory getDrillCategoryFromString(String categoryName) {
        allCategories = dbDrillValuesLoader.getAllCategories();
        return drillCategory = allCategories.stream()
                .filter(c -> categoryName.equals(c.getCategory()))
                .findFirst()
                .orElse(null);
    }

    public DrillDifficulty getDrillDifficultyFromString(String difficultyName) {
        allDifficulties = dbDrillValuesLoader.getallDifficulties();
        return drillDifficulty = allDifficulties.stream()
                .filter(d -> difficultyName.equals(d.getDifficultyName()))
                .findFirst()
                .orElse(null);
    }

    public DrillParticipation getDrillParticipationFromString(String participationName) {
        allParticipations = dbDrillValuesLoader.getAllParticipations();
        return drillParticipation = allParticipations.stream()
                .filter(p -> participationName.equals(p.drillParticipation))
                .findFirst()
                .orElse(null);
    }

    public DrillPuckPosition getDrillPuckPositionFromString(String puckPositionName) {
        allPuckPositions = dbDrillValuesLoader.getAllPuckPositions();
        return drillPuckPosition = allPuckPositions.stream()
                .filter(pp -> puckPositionName.equals(pp.getPuckPosition()))
                .findFirst()
                .orElse(null);
    }

    public DrillTag getDrillTagFromString(String drillTagName) {
        allDrillTags = dbDrillValuesLoader.getAllDrillTags();
        return drillTag = allDrillTags.stream()
                .filter(dt -> drillTagName.equals(dt.getDrillTag()))
                .findFirst()
                .orElse(null);
    }

    public Picture getPictureFromString(String pictureName) {
        allPictures = dbImageLoader.getAllPictures();
        return picture = allPictures.stream()
                .filter(p -> pictureName.equals(p.getPictureName()))
                .findFirst()
                .orElse(null);
    }

    public Player getPlayerFromString(String playerName) {
        String[] fullName = playerName.split("\\s+");
        allPlayers = dbPlayerLoader.getAllPlayers();
        return player = allPlayers.stream()
                .filter(p -> fullName[0].equals(p.getLastName()) && fullName[1].equals(p.getFirstName())
                        || fullName[0].equals(p.getFirstName()) && fullName[1].equals(p.getLastName()))
                .findFirst()
                .orElse(null);
    }

    public Stadium getStadiumFromString(String stadiumName) {
        allStadiums = dbStadiumLoader.getAllStadiums();
        return stadium = allStadiums.stream()
                .filter(s -> stadiumName.equals(s.getStadiumName()))
                .findFirst()
                .orElse(null);
    }

    public Team getTeamFromString(String teamName){
        allTeams = dbTeamLoader.getAllTeams();
        return team = allTeams.stream()
                .filter(t -> teamName.equals(t.getName()))
                .findFirst()
                .orElse(null);
    }
}
