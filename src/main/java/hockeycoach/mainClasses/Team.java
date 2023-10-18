package hockeycoach.mainClasses;

public class Team {
    private int teamID;
    private String name;
    private int stadium;
//    private String street;
//    private int zip;
//    private String city;
    private String country;
    private String contactFirstName;
    private String contactLastName;
    private String contactPhone;
    private String contactEmail;
    private String website;
    private int founded;
    private String presidentFirstName;
    private String presidentLastName;
    private String league;
    private String headCoachFirstName;
    private String headCoachLastName;
    private int logo;
    private String notes;

    public Team(){
    }

    public Team(String name, int teamID){
        this.name = name;
        this.teamID = teamID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public Team(int teamID, String name, int stadium,String contactFirstName, String contactLastName, String contactPhone, String contactEmail, String website, int founded, String presidentFirstName, String presidentLastName, String league, String headCoachFirstName, String headCoachLastName, int logo, String notes) {
        this.teamID = teamID;
        this.name = name;
        this.stadium = stadium;
        this.country = country;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.website = website;
        this.founded = founded;
        this.presidentFirstName = presidentFirstName;
        this.presidentLastName = presidentLastName;
        this.league = league;
        this.headCoachFirstName = headCoachFirstName;
        this.headCoachLastName = headCoachLastName;
        this.logo = logo;
        this.notes = notes;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStadium() {
        return stadium;
    }

    public void setStadium(int stadium) {
        this.stadium = stadium;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public String getPresidentFirstName() {
        return presidentFirstName;
    }

    public void setPresidentFirstName(String presidentFirstName) {
        this.presidentFirstName = presidentFirstName;
    }

    public String getPresidentLastName() {
        return presidentLastName;
    }

    public void setPresidentLastName(String presidentLastName) {
        this.presidentLastName = presidentLastName;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getHeadCoachFirstName() {
        return headCoachFirstName;
    }

    public void setHeadCoachFirstName(String headCoachFirstName) {
        this.headCoachFirstName = headCoachFirstName;
    }

    public String getHeadCoachLastName() {
        return headCoachLastName;
    }

    public void setHeadCoachLastName(String headCoachLastName) {
        this.headCoachLastName = headCoachLastName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
