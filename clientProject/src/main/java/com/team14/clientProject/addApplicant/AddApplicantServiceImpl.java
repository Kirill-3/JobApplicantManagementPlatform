package com.team14.clientProject.addApplicant;
import com.team14.clientProject.homePage.Applicants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AddApplicantServiceImpl implements AddApplicantService {
    private AddApplicantRepository addApplicantRepository;

    @Autowired
    public AddApplicantServiceImpl(AddApplicantRepository addApplicantRepository) {
        this.addApplicantRepository = addApplicantRepository;
    }


    public String addApplicant(AddApplicantForm applicants) {
        Integer queryResultEmail = addApplicantRepository.emailValidation(applicants);
        Integer queryResultPhone = addApplicantRepository.phoneNoValidation(applicants);
        // If there is a result found from the following queries, return a variable used for error handling
        if (queryResultEmail > 0) {
            String check= "emailFalse";
            return check;
        }

        if (queryResultPhone > 0) {
            String check = "phoneFalse";
            return check;
        }

        String check = "true";
        addApplicantRepository.addApplicant(applicants);
        return check;


    }
}
