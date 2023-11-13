package hockeycoach.mainClasses.Lines;

import hockeycoach.mainClasses.Player;

public class ShootoutLine extends Line{
    int gameID;

    Player shooter1,shooter2,shooter3,shooter4,shooter5;

    public ShootoutLine(){}


    public Player getShooter1() {
        return shooter1;
    }

    public void setShooter1(Player shooter1) {
        this.shooter1 = shooter1;
    }

    public Player getShooter2() {
        return shooter2;
    }

    public void setShooter2(Player shooter2) {
        this.shooter2 = shooter2;
    }

    public Player getShooter3() {
        return shooter3;
    }

    public void setShooter3(Player shooter3) {
        this.shooter3 = shooter3;
    }

    public Player getShooter4() {
        return shooter4;
    }

    public void setShooter4(Player shooter4) {
        this.shooter4 = shooter4;
    }

    public Player getShooter5() {
        return shooter5;
    }

    public void setShooter5(Player shooter5) {
        this.shooter5 = shooter5;
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    @Override
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
