package hockeycoach.mainClasses;

public enum Difficulty {
    BAMBINI("•", 1), MOSQUITO("••", 2), NOVICE("•••", 3), ELITE("••••", 4), PRO("•••••", 5);

    private String biscuits;
    private int rating;

    Difficulty(String biscuits, int rating) {
        this.biscuits = biscuits;
        this.rating = rating;
    }

    public String getBiscuits() {
        return biscuits;
    }

    public int getRating() {
        return rating;
    }
}