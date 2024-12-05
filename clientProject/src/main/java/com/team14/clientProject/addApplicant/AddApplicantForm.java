package com.team14.clientProject.addApplicant;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Creating a form for adding an applicant
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class AddApplicantForm {
    @NotEmpty(message = "The first name cannot be empty")
    private String firstName;
    @NotEmpty(message = "The last name cannot be empty")
    private String lastName;
    @NotEmpty(message = "The location cannot be empty")
    private String location;
    @NotEmpty(message = "The email cannot be empty")
    // Regex used from EmailServiceHandler class
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&*+/=?`{}~^.-]+@[a-zA-Z0-9.-]+$", message="Email must be in a valid format")
    private String email;
    // Regex adapted from https://stackoverflow.com/questions/11518035/regular-expression-for-gb-based-and-only-numeric-phone-number
    @Pattern(regexp = "^(07\\d{9}|(\\+44)\\d{10})$", message="Phone number must be a valid UK phone number")
    private String phoneNumber;
    @NotEmpty(message = "The event attended cannot be empty")
    private String eventAttended;
    private String skill;


    public AddApplicantForm() {
        this("", "", "", "", "", "", "");
    }

}

