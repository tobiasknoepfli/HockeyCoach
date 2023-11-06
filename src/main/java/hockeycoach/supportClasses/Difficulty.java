package hockeycoach.supportClasses;

import java.util.Arrays;

public enum Difficulty {
    BAMBINI(1),
    MOSQUITO(2),
    NOVICE(3),
    ELITE(4),
    PRO(5);

    public final int value;

    private Difficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Difficulty fromValue(int value) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.value == value) {
                return difficulty;
            }
        }
        throw new IllegalArgumentException("Invalid Difficulty value: " + value);
    }

    public static int valueFromString(String string){
        for(Difficulty difficulty:Difficulty.values()){
            if(difficulty.name().equalsIgnoreCase(string)){
                return difficulty.value;
            }
        }
        throw new IllegalArgumentException("");
    }

    public static String stringFromInt(int value) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.value == value) {
                return difficulty.name();
            }
        }
        throw new IllegalArgumentException("Invalid Difficulty value: " + value);
    }
}