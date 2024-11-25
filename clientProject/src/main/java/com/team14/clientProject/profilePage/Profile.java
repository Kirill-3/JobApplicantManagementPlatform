package com.team14.clientProject.profilePage;

public class Profile {
    private int id;
    private String firstName;
    private String lastName;
    private String location;
    private String email;
    private String phoneNumber;
    private String eventAttended;
    private String skill;


    private applicantPreferences preferences;

    public Profile(int id, String firstName, String lastName, String location, String email, String phoneNumber,
                   String eventAttended, String skill) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.eventAttended = eventAttended;
        this.skill = skill;
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEventAttended() {
        return eventAttended;
    }

    public String getSkill() {
        return skill;
    }


    public void setPreferences(applicantPreferences preferences) {
        this.preferences = preferences;
    }

    public applicantPreferences getPreferences() {
        return preferences;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEventAttended(String eventAttended) {
        this.eventAttended = eventAttended;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }


}

