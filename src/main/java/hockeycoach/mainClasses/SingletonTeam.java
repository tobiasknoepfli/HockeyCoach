package hockeycoach.mainClasses;

public class SingletonTeam {
    private static SingletonTeam instance;
    private Team selectedTeam;

    private SingletonTeam() {
    }

    public static SingletonTeam getInstance() {
        if (instance == null) {
            instance = new SingletonTeam();
        }
        return instance;
    }

    public Team getSelectedTeam() {
        return selectedTeam;
    }

    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam = selectedTeam;
    }
}
