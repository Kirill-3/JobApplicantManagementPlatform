package com.team14.clientProject.addApplicant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicantDetails {
    private String currentPosition;
    private String status;
    private String cvPath;
    private String coverLetterPath;
}
