package hockeycoach.mainClasses;

public class Team {
    private String name;
    private String stadium;
    private String street;
    private int zip;
    private String city;
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
    private String captainFirstName;
    private String captainLastName;
    private String comments;
    private String notes;

    public Team(String name){
        this.name = name;
    }
    public Team(String name, String stadium, String street, int zip, String city, String contactFirstName, String contactLastName, String contactPhone, String contactEmail, String website, int founded, String presidentFirstName, String presidentLastName, String league, String headCoachFirstName, String headCoachLastName, String captainFirstName, String captainLastName, String comments, String notes) {
        this.name = name;
        this.stadium = stadium;
        this.street = street;
        this.zip = zip;
        this.city = city;
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
        this.captainFirstName = captainFirstName;
        this.captainLastName = captainLastName;
        this.comments = comments;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getCaptainFirstName() {
        return captainFirstName;
    }

    public void setCaptainFirstName(String captainFirstName) {
        this.captainFirstName = captainFirstName;
    }

    public String getCaptainLastName() {
        return captainLastName;
    }

    public void setCaptainLastName(String captainLastName) {
        this.captainLastName = captainLastName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}