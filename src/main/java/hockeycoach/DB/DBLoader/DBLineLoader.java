package hockeycoach.DB.DBLoader;

import hockeycoach.mainClasses.BoxplayLine;
import hockeycoach.mainClasses.Line;
import hockeycoach.mainClasses.PowerplayLine;
import hockeycoach.mainClasses.SubstituteLine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBLineLoader extends DBLoader {
    public Line setLine(ResultSet resultSet) {
        Line line = new Line();
        try {
            line.setGameID(resultSet.getInt("gameID"));
            line.setLineNr(resultSet.getInt("lineNr"));
            line.setGoalkeeper(getPlayerByID(resultSet.getInt("goalkeeper")));
            line.setDefenderLeft(getPlayerByID(resultSet.getInt("defenderLeft")));
            line.setDefenderRight(getPlayerByID(resultSet.getInt("defenderRight")));
            line.setCenter(getPlayerByID(resultSet.getInt("center")));
            line.setForwardLeft(getPlayerByID(resultSet.getInt("forwardLeft")));
            line.setForwardRight(getPlayerByID(resultSet.getInt("forwardRight")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return line;
    }

    public PowerplayLine setPowerPlayLine(ResultSet resultSet) {
        PowerplayLine powerplayLine = new PowerplayLine();
        try {
            powerplayLine.setGameID(resultSet.getInt("gameID"));
            powerplayLine.setLineNr(resultSet.getInt("lineNr"));
            powerplayLine.setDefenderLeft(getPlayerByID(resultSet.getInt("defenderLeft")));
            powerplayLine.setDefenderRight(getPlayerByID(resultSet.getInt("defenderRight")));
            powerplayLine.setCenter(getPlayerByID(resultSet.getInt("center")));
            powerplayLine.setForwardLeft(getPlayerByID(resultSet.getInt("forwardLeft")));
            powerplayLine.setForwardRight(getPlayerByID(resultSet.getInt("forwardRight")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return powerplayLine;
    }

    public BoxplayLine setBoxPlayLine(ResultSet resultSet) {
        BoxplayLine boxplayLine = new BoxplayLine();
        try {
            boxplayLine.setGameID(resultSet.getInt("gameID"));
            boxplayLine.setLineNr(resultSet.getInt("lineNr"));
            boxplayLine.setDefenderLeft(getPlayerByID(resultSet.getInt("defenderLeft")));
            boxplayLine.setDefenderRight(getPlayerByID(resultSet.getInt("defenderRight")));
            boxplayLine.setForwardLeft(getPlayerByID(resultSet.getInt("forwardLeft")));
            boxplayLine.setForwardRight(getPlayerByID(resultSet.getInt("forwardRight")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boxplayLine;
    }

    public SubstituteLine setSubstituteLine(ResultSet resultSet) {
        SubstituteLine substituteLine = new SubstituteLine();
        try {
            substituteLine.setGameID(resultSet.getInt("gameID"));
            substituteLine.setLineNr(resultSet.getInt("lineNr"));
            substituteLine.setGoalkeeper1(getPlayerByID(resultSet.getInt("goalkeeper1")));
            substituteLine.setGoalkeeper2(getPlayerByID(resultSet.getInt("goalkeeper2")));
            substituteLine.setGoalkeeper3(getPlayerByID(resultSet.getInt("goalkeeper3")));
            substituteLine.setDefender1(getPlayerByID(resultSet.getInt("defender1")));
            substituteLine.setDefender2(getPlayerByID(resultSet.getInt("defender2")));
            substituteLine.setDefender3(getPlayerByID(resultSet.getInt("defender3")));
            substituteLine.setForward1(getPlayerByID(resultSet.getInt("forward1")));
            substituteLine.setForward2(getPlayerByID(resultSet.getInt("forward2")));
            substituteLine.setForward3(getPlayerByID(resultSet.getInt("forward3")));
            substituteLine.setBoxplayDefender1(getPlayerByID(resultSet.getInt("boxplayDefender1")));
            substituteLine.setBoxplayDefender2(getPlayerByID(resultSet.getInt("boxplayDefender2")));
            substituteLine.setBoxplayForward1(getPlayerByID(resultSet.getInt("boxplayForward1")));
            substituteLine.setBoxplayForward2(getPlayerByID(resultSet.getInt("boxplayForward2")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return substituteLine;
    }


    public List<Line> getLines(String query) {
        List<Line> lines = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Line line = new Line();
                line = setLine(resultSet);

                lines.add(line);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<PowerplayLine> getPPLines(String query) {
        List<PowerplayLine> powerplayLines = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                PowerplayLine ppLine = new PowerplayLine();
                ppLine = setPowerPlayLine(resultSet);

                powerplayLines.add(ppLine);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return powerplayLines;
    }

    public List<BoxplayLine> getBPLines(String query) {
        List<BoxplayLine> boxplayLines = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                BoxplayLine boxplayLine = new BoxplayLine();
                boxplayLine = setBoxPlayLine(resultSet);

                boxplayLines.add(boxplayLine);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boxplayLines;
    }

    public SubstituteLine getSubLine(String query) {
        SubstituteLine substituteLine = new SubstituteLine();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            substituteLine = setSubstituteLine(resultSet);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return substituteLine;
    }
}
