package com.team14.clientProject.addApplicant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Applicant {
    private int id;
    private String firstName;
    private String lastName;
    private String location;
    private String email;
    private String phoneNumber;
    private String eventAttended;
    private String skill;

    // Database auto-generates id field - no need to include in constructor
    public Applicant (String firstName, String lastName, String location, String email, String phoneNumber, String eventAttended, String skill) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.eventAttended = eventAttended;
        this.skill = skill;
    }
}
