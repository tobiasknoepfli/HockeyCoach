package hockeycoach.DB.DBWriter;

import hockeycoach.mainClasses.Lines.TrainingLines;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static hockeycoach.AppStarter.DB_URL;

public class DBTrainingLineWriter {
    public PreparedStatement setTrainingLines(PreparedStatement preparedStatement, TrainingLines trainingLines) throws SQLException {
        preparedStatement.setInt(1, (trainingLines.getTrainingID() !=0) ? trainingLines.getTrainingID():0);
        preparedStatement.setString(2, (trainingLines.getJersey1()!=null) ? trainingLines.getJersey1():"");
        preparedStatement.setString(3, (trainingLines.getJersey2()!=null) ? trainingLines.getJersey2():"");
        preparedStatement.setString(4, (trainingLines.getJersey3()!=null) ? trainingLines.getJersey3():"");
        preparedStatement.setString(5, (trainingLines.getJersey4()!=null) ? trainingLines.getJersey4():"");
        preparedStatement.setString(6, (trainingLines.getJersey5()!=null) ? trainingLines.getJersey5():"");
        preparedStatement.setString(7, (trainingLines.getJersey6()!=null) ? trainingLines.getJersey6():"");
        preparedStatement.setInt(8, (trainingLines.getGk1() != null) ? trainingLines.getGk1().getID() : 0);
        preparedStatement.setInt(9, (trainingLines.getGk2() != null) ? trainingLines.getGk2().getID() : 0);
        preparedStatement.setInt(10, (trainingLines.getGk3() != null) ? trainingLines.getGk3().getID() : 0);
        preparedStatement.setInt(11, (trainingLines.getGk4() != null) ? trainingLines.getGk4().getID() : 0);
        preparedStatement.setInt(12, (trainingLines.getGk5() != null) ? trainingLines.getGk5().getID() : 0);
        preparedStatement.setInt(13, (trainingLines.getGk6() != null) ? trainingLines.getGk6().getID() : 0);
        preparedStatement.setInt(14, (trainingLines.getDl1() != null) ? trainingLines.getDl1().getID() : 0);
        preparedStatement.setInt(15, (trainingLines.getDl2() != null) ? trainingLines.getDl2().getID() : 0);
        preparedStatement.setInt(16, (trainingLines.getDl3() != null) ? trainingLines.getDl3().getID() : 0);
        preparedStatement.setInt(17, (trainingLines.getDl4() != null) ? trainingLines.getDl4().getID() : 0);
        preparedStatement.setInt(18, (trainingLines.getDl5() != null) ? trainingLines.getDl5().getID() : 0);
        preparedStatement.setInt(19, (trainingLines.getDl6() != null) ? trainingLines.getDl6().getID() : 0);
        preparedStatement.setInt(20, (trainingLines.getDr1() != null) ? trainingLines.getDr1().getID() : 0);
        preparedStatement.setInt(21, (trainingLines.getDr2() != null) ? trainingLines.getDr2().getID() : 0);
        preparedStatement.setInt(22, (trainingLines.getDr3() != null) ? trainingLines.getDr3().getID() : 0);
        preparedStatement.setInt(23, (trainingLines.getDr4() != null) ? trainingLines.getDr4().getID() : 0);
        preparedStatement.setInt(24, (trainingLines.getDr5() != null) ? trainingLines.getDr5().getID() : 0);
        preparedStatement.setInt(25, (trainingLines.getDr6() != null) ? trainingLines.getDr6().getID() : 0);
        preparedStatement.setInt(26, (trainingLines.getC1() != null) ? trainingLines.getC1().getID() : 0);
        preparedStatement.setInt(27, (trainingLines.getC2() != null) ? trainingLines.getC2().getID() : 0);
        preparedStatement.setInt(28, (trainingLines.getC3() != null) ? trainingLines.getC3().getID() : 0);
        preparedStatement.setInt(29, (trainingLines.getC4() != null) ? trainingLines.getC4().getID() : 0);
        preparedStatement.setInt(30, (trainingLines.getC5() != null) ? trainingLines.getC5().getID() : 0);
        preparedStatement.setInt(31, (trainingLines.getC6() != null) ? trainingLines.getC6().getID() : 0);
        preparedStatement.setInt(32, (trainingLines.getFl1() != null) ? trainingLines.getFl1().getID() : 0);
        preparedStatement.setInt(33, (trainingLines.getFl2() != null) ? trainingLines.getFl2().getID() : 0);
        preparedStatement.setInt(34, (trainingLines.getFl3() != null) ? trainingLines.getFl3().getID() : 0);
        preparedStatement.setInt(35, (trainingLines.getFl4() != null) ? trainingLines.getFl4().getID() : 0);
        preparedStatement.setInt(36, (trainingLines.getFl5() != null) ? trainingLines.getFl5().getID() : 0);
        preparedStatement.setInt(37, (trainingLines.getFl6() != null) ? trainingLines.getFl6().getID() : 0);
        preparedStatement.setInt(38, (trainingLines.getFr1() != null) ? trainingLines.getFr1().getID() : 0);
        preparedStatement.setInt(39, (trainingLines.getFr2() != null) ? trainingLines.getFr2().getID() : 0);
        preparedStatement.setInt(40, (trainingLines.getFr3() != null) ? trainingLines.getFr3().getID() : 0);
        preparedStatement.setInt(41, (trainingLines.getFr4() != null) ? trainingLines.getFr4().getID() : 0);
        preparedStatement.setInt(42, (trainingLines.getFr5() != null) ? trainingLines.getFr5().getID() : 0);
        preparedStatement.setInt(43, (trainingLines.getFr6() != null) ? trainingLines.getFr6().getID() : 0);

        return preparedStatement;
    }

    public void writeTrainingLines(TrainingLines trainingLines) {
        String query = "INSERT INTO trainingLines" +
                "(trainingID," +
                "jersey1,jersey2,jersey3,jersey4,jersey5,jersey6," +
                "gk1,gk2,gk3,gk4,gk5,gk6," +
                "dl1,dl2,dl3,dl4,dl5,dl6," +
                "dr1,dr2,dr3,dr4,dr5,dr6," +
                "c1,c2,c3,c4,c5,c6," +
                "fl1,fl2,fl3,fl4,fl5,fl6," +
                "fr1,fr2,fr3,fr4,fr5,fr6) " +
                "VALUES ?," +
                "?,?,?,?,?,?," +
                "?,?,?,?,?,?," +
                "?,?,?,?,?,?," +
                "?,?,?,?,?,?," +
                "?,?,?,?,?,?," +
                "?,?,?,?,?,?," +
                "?,?,?,?,?,?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setTrainingLines(preparedStatement, trainingLines);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
