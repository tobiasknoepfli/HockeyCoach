package hockeycoach.mainClasses;

import java.nio.file.Path;
import java.util.ArrayList;

public class Player {
    private String firstName;
    private String lastName;
    private String street;
    private int zip;
    private String city;
    private String country;
    private String aLicence;
    private String bLicence;
    private String phone;
    private String eMail;
    private ArrayList<Integer> jersey;
    private ArrayList<String> position;
    private ArrayList<String> strength;
    private ArrayList<String> weakness;
    private ArrayList<String> team;
    private Path photoPath;
    private String role;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public ArrayList<Integer> getJersey() {
        return jersey;
    }

    public void setJersey(ArrayList<Integer> jersey) {
        this.jersey = jersey;
    }

    public ArrayList<String> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<String> position) {
        this.position = position;
    }

    public ArrayList<String> getStrength() {
        return strength;
    }

    public void setStrength(ArrayList<String> strength) {
        this.strength = strength;
    }

    public ArrayList<String> getWeakness() {
        return weakness;
    }

    public void setWeakness(ArrayList<String> weakness) {
        this.weakness = weakness;
    }

    public ArrayList<String> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<String> team) {
        this.team = team;
    }

    public Path getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(Path photoPath) {
        this.photoPath = photoPath;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
