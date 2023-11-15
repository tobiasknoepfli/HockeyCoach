package hockeycoach.DB.DBLoader;

import hockeycoach.DB.DBConverter.DBIDConverter;
import hockeycoach.mainClasses.Lines.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static hockeycoach.AppStarter.DB_URL;

public class DBLineLoader extends DBLoader {
    DBIDConverter dbidConverter = new DBIDConverter();
    
    public Line setLine(ResultSet resultSet) {
        Line line = new Line();
        try {
            line.setGameID(resultSet.getInt("gameID"));
            line.setLineNr(resultSet.getInt("lineNr"));
            line.setGoalkeeper(dbidConverter.getPlayerFromID(resultSet.getInt("goalkeeper")));
            line.setDefenderLeft(dbidConverter.getPlayerFromID(resultSet.getInt("defenderLeft")));
            line.setDefenderRight(dbidConverter.getPlayerFromID(resultSet.getInt("defenderRight")));
            line.setCenter(dbidConverter.getPlayerFromID(resultSet.getInt("center")));
            line.setForwardLeft(dbidConverter.getPlayerFromID(resultSet.getInt("forwardLeft")));
            line.setForwardRight(dbidConverter.getPlayerFromID(resultSet.getInt("forwardRight")));
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
            powerplayLine.setDefenderLeft(dbidConverter.getPlayerFromID(resultSet.getInt("defenderLeft")));
            powerplayLine.setDefenderRight(dbidConverter.getPlayerFromID(resultSet.getInt("defenderRight")));
            powerplayLine.setCenter(dbidConverter.getPlayerFromID(resultSet.getInt("center")));
            powerplayLine.setForwardLeft(dbidConverter.getPlayerFromID(resultSet.getInt("forwardLeft")));
            powerplayLine.setForwardRight(dbidConverter.getPlayerFromID(resultSet.getInt("forwardRight")));
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
            boxplayLine.setDefenderLeft(dbidConverter.getPlayerFromID(resultSet.getInt("defenderLeft")));
            boxplayLine.setDefenderRight(dbidConverter.getPlayerFromID(resultSet.getInt("defenderRight")));
            boxplayLine.setForwardLeft(dbidConverter.getPlayerFromID(resultSet.getInt("forwardLeft")));
            boxplayLine.setForwardRight(dbidConverter.getPlayerFromID(resultSet.getInt("forwardRight")));
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
            substituteLine.setGoalkeeper1(dbidConverter.getPlayerFromID(resultSet.getInt("goalkeeper1")));
            substituteLine.setGoalkeeper2(dbidConverter.getPlayerFromID(resultSet.getInt("goalkeeper2")));
            substituteLine.setGoalkeeper3(dbidConverter.getPlayerFromID(resultSet.getInt("goalkeeper3")));
            substituteLine.setDefender1(dbidConverter.getPlayerFromID(resultSet.getInt("defender1")));
            substituteLine.setDefender2(dbidConverter.getPlayerFromID(resultSet.getInt("defender2")));
            substituteLine.setDefender3(dbidConverter.getPlayerFromID(resultSet.getInt("defender3")));
            substituteLine.setForward1(dbidConverter.getPlayerFromID(resultSet.getInt("forward1")));
            substituteLine.setForward2(dbidConverter.getPlayerFromID(resultSet.getInt("forward2")));
            substituteLine.setForward3(dbidConverter.getPlayerFromID(resultSet.getInt("forward3")));
            substituteLine.setBoxplayDefender1(dbidConverter.getPlayerFromID(resultSet.getInt("boxplayDefender1")));
            substituteLine.setBoxplayDefender2(dbidConverter.getPlayerFromID(resultSet.getInt("boxplayDefender2")));
            substituteLine.setBoxplayForward1(dbidConverter.getPlayerFromID(resultSet.getInt("boxplayForward1")));
            substituteLine.setBoxplayForward2(dbidConverter.getPlayerFromID(resultSet.getInt("boxplayForward2")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return substituteLine;
    }

    public NuclearLine setNuclearLine(ResultSet resultSet) {
        NuclearLine nuclearLine = new NuclearLine();
        try {
            nuclearLine.setGameID(resultSet.getInt("gameID"));
            nuclearLine.setLineNr(resultSet.getInt("lineNr"));
            nuclearLine.setDefenderLeft(dbidConverter.getPlayerFromID(resultSet.getInt("defenderLeft")));
            nuclearLine.setDefenderRight(dbidConverter.getPlayerFromID(resultSet.getInt("defenderRight")));
            nuclearLine.setCenter(dbidConverter.getPlayerFromID(resultSet.getInt("center")));
            nuclearLine.setForwardLeft(dbidConverter.getPlayerFromID(resultSet.getInt("forwardLeft")));
            nuclearLine.setForwardRight(dbidConverter.getPlayerFromID(resultSet.getInt("forwardRight")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nuclearLine;
    }

    public OvertimeLine setOvertimeLine(ResultSet resultSet) {
        OvertimeLine overtimeLine = new OvertimeLine();
        try {
            overtimeLine.setGameID(resultSet.getInt("gameID"));
            overtimeLine.setDefenderLeft1(dbidConverter.getPlayerFromID(resultSet.getInt("defenderLeft1")));
            overtimeLine.setDefenderRight1(dbidConverter.getPlayerFromID(resultSet.getInt("defenderRight1")));
            overtimeLine.setCenter1(dbidConverter.getPlayerFromID(resultSet.getInt("center1")));
            overtimeLine.setDefenderLeft2(dbidConverter.getPlayerFromID(resultSet.getInt("defenderLeft2")));
            overtimeLine.setDefenderRight2(dbidConverter.getPlayerFromID(resultSet.getInt("defenderRight2")));
            overtimeLine.setCenter2(dbidConverter.getPlayerFromID(resultSet.getInt("center2")));
            overtimeLine.setSubstituteDefender(dbidConverter.getPlayerFromID(resultSet.getInt("defenderSubstitute")));
            overtimeLine.setSubstituteForward(dbidConverter.getPlayerFromID(resultSet.getInt("centerSubstitute")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return overtimeLine;
    }

    public ShootoutLine setShootoutLine(ResultSet resultSet) {
        ShootoutLine shootoutLine = new ShootoutLine();
        try {
            shootoutLine.setGameID(resultSet.getInt("gameID"));
            shootoutLine.setShooter1(dbidConverter.getPlayerFromID(resultSet.getInt("shooter1")));
            shootoutLine.setShooter2(dbidConverter.getPlayerFromID(resultSet.getInt("shooter2")));
            shootoutLine.setShooter3(dbidConverter.getPlayerFromID(resultSet.getInt("shooter3")));
            shootoutLine.setShooter4(dbidConverter.getPlayerFromID(resultSet.getInt("shooter4")));
            shootoutLine.setShooter5(dbidConverter.getPlayerFromID(resultSet.getInt("shooter5")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shootoutLine;
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

            if (resultSet.next()) {
                substituteLine = setSubstituteLine(resultSet);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return substituteLine;
    }

    public List<NuclearLine> getNuclearLine(String query) {
        List<NuclearLine> nuclearLines = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                NuclearLine nLine = new NuclearLine();
                nLine = setNuclearLine(resultSet);

                nuclearLines.add(nLine);

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nuclearLines;
    }

    public OvertimeLine getOvertimeLine(String query) {
        OvertimeLine overtimeLine = new OvertimeLine();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                overtimeLine = setOvertimeLine(resultSet);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return overtimeLine;
    }

    public ShootoutLine getShootoutLine(String query) {
        ShootoutLine shootoutLine = new ShootoutLine();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                shootoutLine = setShootoutLine(resultSet);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shootoutLine;
    }
}
