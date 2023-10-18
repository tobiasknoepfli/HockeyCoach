package hockeycoach.mainClasses.Lines;

import hockeycoach.mainClasses.Player;

public class SubstituteLine extends Line {
    Player goalkeeper1, goalkeeper2, goalkeeper3;
    Player defender1, defender2, defender3;
    Player forward1, forward2, forward3;
    Player boxplayDefender1, boxplayDefender2;
    Player boxplayForward1, boxplayForward2;

    public SubstituteLine() {
    }

    public SubstituteLine(int lineNr) {
        super(lineNr);
    }

    public Player getGoalkeeper1() {
        return goalkeeper1;
    }

    public void setGoalkeeper1(Player goalkeeper1) {
        this.goalkeeper1 = goalkeeper1;
    }

    public Player getGoalkeeper2() {
        return goalkeeper2;
    }

    public void setGoalkeeper2(Player goalkeeper2) {
        this.goalkeeper2 = goalkeeper2;
    }

    public Player getGoalkeeper3() {
        return goalkeeper3;
    }

    public void setGoalkeeper3(Player goalkeeper3) {
        this.goalkeeper3 = goalkeeper3;
    }

    public Player getDefender1() {
        return defender1;
    }

    public void setDefender1(Player defender1) {
        this.defender1 = defender1;
    }

    public Player getDefender2() {
        return defender2;
    }

    public void setDefender2(Player defender2) {
        this.defender2 = defender2;
    }

    public Player getDefender3() {
        return defender3;
    }

    public void setDefender3(Player defender3) {
        this.defender3 = defender3;
    }

    public Player getForward1() {
        return forward1;
    }

    public void setForward1(Player forward1) {
        this.forward1 = forward1;
    }

    public Player getForward2() {
        return forward2;
    }

    public void setForward2(Player forward2) {
        this.forward2 = forward2;
    }

    public Player getForward3() {
        return forward3;
    }

    public void setForward3(Player forward3) {
        this.forward3 = forward3;
    }

    public Player getBoxplayDefender1() {
        return boxplayDefender1;
    }

    public void setBoxplayDefender1(Player boxplayDefender1) {
        this.boxplayDefender1 = boxplayDefender1;
    }

    public Player getBoxplayDefender2() {
        return boxplayDefender2;
    }

    public void setBoxplayDefender2(Player boxplayDefender2) {
        this.boxplayDefender2 = boxplayDefender2;
    }

    public Player getBoxplayForward1() {
        return boxplayForward1;
    }

    public void setBoxplayForward1(Player boxplayForward1) {
        this.boxplayForward1 = boxplayForward1;
    }

    public Player getBoxplayForward2() {
        return boxplayForward2;
    }

    public void setBoxplayForward2(Player boxplayForward2) {
        this.boxplayForward2 = boxplayForward2;
    }
}
