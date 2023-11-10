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

public class DBIDConverter {
    public Drill getDrillFromID(int drillID){
        DBDrillLoader dbDrillLoader =new DBDrillLoader();
        Drill drill = new Drill();
        List<Drill> allDrills =dbDrillLoader.getAllDrills();
        return drill = allDrills.stream()
                .filter(d->drillID == d.getID())
                .findFirst()
                .orElse(null);
    }

    public DrillCategory getDrillCategoryFromID(int categoryID){
        DBDrillValuesLoader dbDrillValuesLoader =new DBDrillValuesLoader();
        DrillCategory drillCategory = new DrillCategory();
        List<DrillCategory> allCategories =dbDrillValuesLoader.getAllCategories();
        return drillCategory = allCategories.stream()
                .filter(c->categoryID == c.getID())
                .findFirst()
                .orElse(null);
    }

    public DrillDifficulty getDrillDifficultyFromID(int difficultyID){
        DBDrillValuesLoader dbDrillValuesLoader = new DBDrillValuesLoader();
        DrillDifficulty drillDifficulty = new DrillDifficulty();
        List<DrillDifficulty> allDifficulties  =dbDrillValuesLoader.getAllDifficulties();
        return drillDifficulty = allDifficulties.stream()
                .filter(d->difficultyID == d.getID())
                .findFirst()
                .orElse(null);
    }

    public DrillParticipation getDrillParticipationFromID(int participationID){
        DBDrillValuesLoader dbDrillValuesLoader =new DBDrillValuesLoader();
        DrillParticipation drillParticipation = new DrillParticipation();
        List<DrillParticipation> allParticipations = dbDrillValuesLoader.getAllParticipations();
        return drillParticipation = allParticipations.stream()
                .filter(p->participationID == p.getID())
                .findFirst()
                .orElse(null);
    }

    public DrillPuckPosition getDrillPuckPositionFromID(int puckPositionID){
        DBDrillValuesLoader dbDrillValuesLoader =new DBDrillValuesLoader();
        DrillPuckPosition drillPuckPosition = new DrillPuckPosition();
        List<DrillPuckPosition> allPuckPositions = dbDrillValuesLoader.getAllPuckPositions();
        return drillPuckPosition = allPuckPositions.stream()
                .filter(pp->puckPositionID == pp.getID())
                .findFirst()
                .orElse(null);
    }

    public DrillTag getDrillTagFromID(int drillTagID){
        DBDrillValuesLoader dbDrillValuesLoader = new DBDrillValuesLoader();
        DrillTag drillTag = new DrillTag();
        List<DrillTag> allDrillTags =  dbDrillValuesLoader.getAllDrillTags();
        return drillTag= allDrillTags.stream()
                .filter(dt->drillTagID == dt.getID())
                .findFirst()
                .orElse(null);
    }

    public Picture getPictureFromID(int pictureID){
        DBImageLoader dbImageLoader = new DBImageLoader();
        Picture picture = new Picture();
        List<Picture> allPictures =dbImageLoader.getAllPictures();
        return picture = allPictures.stream()
                .filter(p ->pictureID == p.getID())
                .findFirst()
                .orElse(null);
    }

    public Player getPlayerFromID(int playerID){
        DBPlayerLoader dbPlayerLoader = new DBPlayerLoader();
        Player player = new Player();
        List<Player> allPlayers =dbPlayerLoader.getAllPlayers();
        return player = allPlayers.stream()
                .filter(p->playerID == p.getID())
                .findFirst()
                .orElse(null);
    }

    public Stadium getStadiumFromID(int stadiumID){
        DBStadiumLoader dbStadiumLoader =new DBStadiumLoader();
        Stadium stadium = new Stadium();
        List<Stadium> allStadiums =  dbStadiumLoader.getAllStadiums();
        return stadium = allStadiums.stream()
                .filter(s->stadiumID ==s.getID())
                .findFirst()
                .orElse(null);
    }

    public Team getTeamFromID(int teamID){
        DBTeamLoader dbTeamLoader =new DBTeamLoader();
        Team team = new Team();
        List<Team> allTeams = dbTeamLoader.getAllTeams();
        return team = allTeams.stream()
                .filter(t->teamID == t.getID())
                .findFirst()
                .orElse(null);
    }
}
