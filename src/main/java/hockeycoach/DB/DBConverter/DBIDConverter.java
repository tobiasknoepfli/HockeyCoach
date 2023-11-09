package hockeycoach.DB.DBConverter;

import hockeycoach.DB.DBLoader.*;
import hockeycoach.mainClasses.Drills.*;
import hockeycoach.mainClasses.Picture;
import hockeycoach.mainClasses.Player;
import hockeycoach.mainClasses.Stadium;
import hockeycoach.mainClasses.Team;

import java.util.ArrayList;
import java.util.List;

public class DBIDConverter {
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

    public Drill getDrillFromID(int drillID){
        allDrills = dbDrillLoader.getAllDrills();
        return drill = allDrills.stream()
                .filter(d->drillID == d.getID())
                .findFirst()
                .orElse(null);
    }

    public DrillCategory getDrillCategoryFromID(int categoryID){
        allCategories = dbDrillValuesLoader.getAllCategories();
        return drillCategory = allCategories.stream()
                .filter(c->categoryID == c.getID())
                .findFirst()
                .orElse(null);
    }

    public DrillDifficulty getDrillDifficultyFromID(int difficultyID){
        allDifficulties = dbDrillValuesLoader.getallDifficulties();
        return drillDifficulty = allDifficulties.stream()
                .filter(d->difficultyID == d.getID())
                .findFirst()
                .orElse(null);
    }

    public DrillParticipation getDrillParticipationFromID(int participationID){
        allParticipations = dbDrillValuesLoader.getAllParticipations();
        return drillParticipation = allParticipations.stream()
                .filter(p->participationID == p.getID())
                .findFirst()
                .orElse(null);
    }

    public DrillPuckPosition getDrillPuckPositionFromID(int puckPositionID){
        allPuckPositions = dbDrillValuesLoader.getAllPuckPositions();
        return drillPuckPosition = allPuckPositions.stream()
                .filter(pp->puckPositionID == pp.getID())
                .findFirst()
                .orElse(null);
    }

    public DrillTag getDrillTagFromID(int drillTagID){
        allDrillTags = dbDrillValuesLoader.getAllDrillTags();
        return drillTag= allDrillTags.stream()
                .filter(dt->drillTagID == dt.getID())
                .findFirst()
                .orElse(null);
    }

    public Picture getPictureFromID(int pictureID){
        allPictures = dbImageLoader.getAllPictures();
        return picture = allPictures.stream()
                .filter(p ->pictureID == p.getID())
                .findFirst()
                .orElse(null);
    }

    public Player getPlayerFromID(int playerID){
        allPlayers = dbPlayerLoader.getAllPlayers();
        return player = allPlayers.stream()
                .filter(p->playerID == p.getID())
                .findFirst()
                .orElse(null);
    }

    public Stadium getStadiumFromID(int stadiumID){
        allStadiums = dbStadiumLoader.getAllStadiums();
        return stadium = allStadiums.stream()
                .filter(s->stadiumID ==s.getID())
                .findFirst()
                .orElse(null);
    }

    public Team getTeamFromID(int teamID){
        allTeams = dbTeamLoader.getAllTeams();
        return team = allTeams.stream()
                .filter(t->teamID == t.getID())
                .findFirst()
                .orElse(null);
    }
}
