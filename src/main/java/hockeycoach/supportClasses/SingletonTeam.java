package hockeycoach.supportClasses;

import hockeycoach.mainClasses.Team;

public class SingletonTeam {
    private static SingletonTeam instance;
    private Team selectedTeam;
    private int index;

    private SingletonTeam() {
    }

    public static SingletonTeam getInstance() {
        if (instance == null) {
            instance = new SingletonTeam();
        }
        return instance;
    }

    public static void setInstance(SingletonTeam instance) {
        SingletonTeam.instance = instance;
    }

    public Team getSelectedTeam() {
        return selectedTeam;
    }

    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam = selectedTeam;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
