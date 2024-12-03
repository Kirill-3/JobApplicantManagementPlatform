package com.team14.clientProject.addApplicant;
import com.team14.clientProject.homePage.Applicants;

import org.springframework.stereotype.Service;

@Service
public interface AddApplicantService {
    String addApplicant(AddApplicantForm applicants);
}
