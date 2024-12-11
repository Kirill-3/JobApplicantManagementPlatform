package com.team14.clientProject.addApplicant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicantPreferences {
    private String subscribeToNewsletter;
    private String subscribeToBulletins;
    private String subscribeToJobUpdates;
}
