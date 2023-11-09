package hockeycoach.mainClasses;

import java.time.LocalDate;

public class Player {
    private int ID;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Team team;
    private String street;
    private int zip;
    private String city;
    private String country;
    private String aLicence;
    private String bLicence;
    private String phone;
    private String eMail;
    private int jersey;
    private String positions;
    private String strengths;
    private String weaknesses;
    private String role;
    private String stick;
    private Picture photoID;
    private String notes;

    public Player() {
    }

    public Player(String firstName, String lastName, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getaLicence() {
        return aLicence;
    }

    public void setaLicence(String aLicence) {
        this.aLicence = aLicence;
    }

    public String getbLicence() {
        return bLicence;
    }

    public void setbLicence(String bLicence) {
        this.bLicence = bLicence;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getJersey() {
        return jersey;
    }

    public void setJersey(int jersey) {
        this.jersey = jersey;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStick() {
        return stick;
    }

    public void setStick(String stick) {
        this.stick = stick;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
