package hockeycoach.mainClasses;

public class Team {
    private int ID;
    private String name;
    private Stadium stadium;
    private int stadiumID;
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
    private Picture logo;
    private String notes;
    private int index;

    public Team() {
    }

    public Team(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public Team(int ID, String name, Stadium stadium, String contactFirstName, String contactLastName, String contactPhone, String contactEmail, String website, int founded, String presidentFirstName, String presidentLastName, String league, String headCoachFirstName, String headCoachLastName, Picture logo, String notes) {
        this.ID = ID;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getStadiumID() {
        return stadium.getID();
    }

    public void setStadiumID(int stadiumID) {
        this.stadiumID = stadium.getID();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Picture getLogo() {
        return logo;
    }

    public void setLogo(Picture logo) {
        this.logo = logo;
    }

}
