package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBDrillValuesLoader extends DBLoader {
    public DrillCategory setDrillCategory(ResultSet resultSet) {
        DrillCategory drillCategory = new DrillCategory();
        try {
            drillCategory.setCategoryID(resultSet.getInt("ID"));
            drillCategory.setDrillCategory(resultSet.getString("drillCategory"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drillCategory;
    }

    public DrillParticipation setDrillParticipation(ResultSet resultSet) {
        DrillParticipation drillParticipation = new DrillParticipation();
        try {
            drillParticipation.setDrillParticipation(resultSet.getString("participation"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drillParticipation;
    }

    public DrillPuckPosition setDrillPuckPositions(ResultSet resultSet) {
        DrillPuckPosition drillPuckPosition = new DrillPuckPosition();
        try {
            drillPuckPosition.setPuckPosition(resultSet.getString("puckPosition"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drillPuckPosition;
    }

    public DrillTag setDrillTag(ResultSet resultSet){
        DrillTag drillTag = new DrillTag();
        try {
            drillTag.setDrillTag(resultSet.getString("drillTag"));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return drillTag;
    }

    public List<DrillCategory> getCategories() {
        String query = "SELECT * FROM drillCategory";
        List<DrillCategory> categoryList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                DrillCategory drillCategory = new DrillCategory();

                drillCategory=setDrillCategory(resultSet);

                categoryList.add(drillCategory);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public List<DrillParticipation> getParticipation() {
        String query = "SELECT * FROM drillParticipation";
        List<DrillParticipation> participationList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                DrillParticipation drillParticipation = new DrillParticipation();

                drillParticipation=setDrillParticipation(resultSet);

                participationList.add(drillParticipation);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participationList;
    }

    public List<DrillPuckPosition> getPuckPosition() {
        String query = "SELECT * FROM drillPuckPosition";
        List<DrillPuckPosition> puckPositionList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                DrillPuckPosition puckPosition = new DrillPuckPosition();

                puckPosition=setDrillPuckPositions(resultSet);

                puckPositionList.add(puckPosition);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return puckPositionList;
    }

    public List<DrillTag> getDrillTag() {
        String query = "SELECT * FROM drillTag";
        List<DrillTag> tagList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                DrillTag drillTag = new DrillTag();

                drillTag=setDrillTag(resultSet);

                tagList.add(drillTag);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagList;
    }
}
