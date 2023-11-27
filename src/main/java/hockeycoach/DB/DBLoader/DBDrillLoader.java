package hockeycoach.DB.DBLoader;

import hockeycoach.DB.DBConverter.DBIDConverter;
import hockeycoach.DB.DBWriter.DBImageWriter;
import hockeycoach.mainClasses.Drills.Drill;
import hockeycoach.supportClasses.ImageHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static hockeycoach.AppStarter.DB_URL;

public class DBDrillLoader extends DBLoader{
    DBIDConverter dbidConverter =new DBIDConverter();


    public Drill setDrill(ResultSet resultSet){
        Drill drill = new Drill();
        try{
            drill.setID(resultSet.getInt("ID"));
            drill.setName(resultSet.getString("name"));
            drill.setCategory(dbidConverter.getDrillCategoryFromID(resultSet.getInt("category")));
            drill.setDifficulty(dbidConverter.getDrillDifficultyFromID(resultSet.getInt("difficulty")));
            drill.setParticipation(dbidConverter.getDrillParticipationFromID(resultSet.getInt("participation")));
            drill.setDescription(resultSet.getString("description"));
            drill.setStation(resultSet.getBoolean("station"));
            drill.setTags(getDrillTags("SELECT drillTag FROM drillXtag RIGHT JOIN drillTag ON ID =" + drill.getID()));
            drill.setPurpose(resultSet.getString("purpose"));
//            drill.setPicture(resultSet.getInt("imageID"));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return drill;
    }

    public List<Drill> getAllDrills() {
        String query = "SELECT * FROM drill";
        List<Drill> drillList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Drill drill = new Drill();
                drill = setDrill(resultSet);

                drillList.add(drill);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drillList;
    }

    public List<Drill> getTrainingDrills(String query, List<Drill> drillList, String table, int trainingID) {
        List<Drill> trainingDrillList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int drillID = resultSet.getInt("drillID");
                Drill drill = drillList.stream()
                        .filter(d -> d.getID() == drillID)
                        .findFirst()
                        .orElse(null);

                if (drill != null) {
                    drill.setTable(table);
                    drill.setSortingIndex(getSortingIndex(drillID, trainingID));
                    trainingDrillList.add(drill);
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        trainingDrillList.sort(Comparator.comparingInt(Drill::getSortingIndex));
        return trainingDrillList;
    }

    public int getSortingIndex(int drillID, int trainingID) {
        String query = "SELECT sortingIndex FROM trainingXdrills WHERE drillID = " + drillID + " AND trainingID = " + trainingID;
        int sortingIndex = 0;
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                sortingIndex = (resultSet.getInt(("sortingIndex")));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sortingIndex;
    }

    public ArrayList<String> getDrillTags(String query) {
        ArrayList<String> drillTags = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                drillTags.add(resultSet.getString(("drillTag")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<String> drillTagList = drillTags.stream()
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));

        return drillTagList;
    }
}
