package hockeycoach.mainClasses.Drills;

public class DrillDifficulty {
    public int ID;
    public int difficulty;
    public String difficultyName;

    public DrillDifficulty(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
    }
}
