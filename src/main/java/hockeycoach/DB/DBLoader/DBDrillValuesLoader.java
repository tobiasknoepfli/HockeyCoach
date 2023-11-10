package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.Drills.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBDrillValuesLoader extends DBLoader {
    public DrillCategory setDrillCategory(ResultSet resultSet) {
        DrillCategory drillCategory = new DrillCategory();
        try {
            drillCategory.setID(resultSet.getInt("ID"));
            drillCategory.setCategory(resultSet.getString("category"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drillCategory;
    }

    public DrillDifficulty setDrillDifficulty(ResultSet resultSet) {
        DrillDifficulty drillDifficulty = new DrillDifficulty();
        try {
            drillDifficulty.setID(resultSet.getInt("ID"));
            drillDifficulty.setDifficulty(resultSet.getInt("difficulty"));
            drillDifficulty.setDifficultyName(resultSet.getString("difficultyName"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drillDifficulty;
    }

    public DrillParticipation setDrillParticipation(ResultSet resultSet) {
        DrillParticipation drillParticipation = new DrillParticipation();
        try {
            drillParticipation.setID(resultSet.getInt("ID"));
            drillParticipation.setDrillParticipation(resultSet.getString("participation"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drillParticipation;
    }

    public DrillPuckPosition setDrillPuckPositions(ResultSet resultSet) {
        DrillPuckPosition drillPuckPosition = new DrillPuckPosition();
        try {
            drillPuckPosition.setID(resultSet.getInt("ID"));
            drillPuckPosition.setPuckPosition(resultSet.getString("puckPosition"));
            drillPuckPosition.setPuckPositionName(resultSet.getString("puckPositionName"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drillPuckPosition;
    }

    public DrillTag setDrillTag(ResultSet resultSet) {
        DrillTag drillTag = new DrillTag();
        try {
            drillTag.setID(resultSet.getInt("ID"));
            drillTag.setDrillTag(resultSet.getString("drillTag"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drillTag;
    }

    public List<DrillCategory> getAllCategories() {
        String query = "SELECT * FROM drillCategory";
        List<DrillCategory> categoryList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                DrillCategory drillCategory = new DrillCategory();

                drillCategory = setDrillCategory(resultSet);

                categoryList.add(drillCategory);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public List<DrillDifficulty> getAllDifficulties() {
        String query = "SELECT * FROM drillDifficulty";
        List<DrillDifficulty> drillDifficultyList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                DrillDifficulty drillDifficulty = new DrillDifficulty();

                drillDifficulty = setDrillDifficulty(resultSet);

                drillDifficultyList.add(drillDifficulty);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drillDifficultyList;
    }

    public List<DrillParticipation> getAllParticipations() {
        String query = "SELECT * FROM drillParticipation";
        List<DrillParticipation> participationList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                DrillParticipation drillParticipation = new DrillParticipation();

                drillParticipation = setDrillParticipation(resultSet);

                participationList.add(drillParticipation);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participationList;
    }

    public List<DrillPuckPosition> getAllPuckPositions() {
        String query = "SELECT * FROM drillPuckPosition";
        List<DrillPuckPosition> puckPositionList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                DrillPuckPosition puckPosition = new DrillPuckPosition();

                puckPosition = setDrillPuckPositions(resultSet);

                puckPositionList.add(puckPosition);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return puckPositionList;
    }

    public List<DrillTag> getAllDrillTags() {
        String query = "SELECT * FROM drillTag";
        List<DrillTag> tagList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                DrillTag drillTag = new DrillTag();

                drillTag = setDrillTag(resultSet);

                tagList.add(drillTag);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagList;
    }

    public DrillCategory getCategoryFromID(int categoryID) {
        List<DrillCategory> drillCategoryList = new ArrayList<>();
        drillCategoryList = getAllCategories();

        DrillCategory drillCategory = drillCategoryList.stream().filter(d -> categoryID == d.getID()).findFirst().orElse(null);
        return drillCategory;
    }

    public DrillParticipation getParticipationFromID(int participationID) {
        List<DrillParticipation> drillParticipationList = new ArrayList<>();
        drillParticipationList = getAllParticipations();

        DrillParticipation drillParticipation = drillParticipationList.stream().filter(d -> participationID == d.getID()).findFirst().orElse(null);
        return drillParticipation;
    }

    public DrillPuckPosition getPuckPositionFromID(int puckPositionID) {
        List<DrillPuckPosition> drillPuckPositionList = new ArrayList<>();
        drillPuckPositionList = getAllPuckPositions();

        DrillPuckPosition drillPuckPosition = drillPuckPositionList.stream().filter(d -> puckPositionID == d.getID()).findFirst().orElse(null);
        return drillPuckPosition;
    }

    public DrillCategory getCategoryFromString(List<DrillCategory> drillCategoryList, String categoryName) {
        DrillCategory drillCategory = new DrillCategory();
        drillCategory = drillCategoryList.stream()
                .filter(d -> categoryName.equals(d.getCategory()))
                .findFirst()
                .orElse(null);
        return drillCategory;
    }

    public DrillParticipation getParticipationFromString(String participationName) {
        List<DrillParticipation> drillParticipationList = new ArrayList<>();

        drillParticipationList = getAllParticipations();

        DrillParticipation drillParticipation = new DrillParticipation();
        drillParticipation = drillParticipationList.stream()
                .filter(d -> participationName.equals(d.getDrillParticipation()))
                .findFirst()
                .orElse(null);
        return drillParticipation;
    }
}
