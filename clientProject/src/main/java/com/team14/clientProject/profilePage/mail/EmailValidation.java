package com.team14.clientProject.profilePage.mail;

//Code obtained from https://www.baeldung.com/java-email-validation-regex
public class EmailValidation {
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return emailAddress.matches(regexPattern);
    }
}
