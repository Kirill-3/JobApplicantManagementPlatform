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
        // If there is a result found from the query, return a variable used for error handling
        if (queryResultEmail > 0) {
            //throw new IllegalArgumentException("Email already exists");
            String check= "false";
            return check;
        }

        String check = "true";
        addApplicantRepository.addApplicant(applicants);
        return check;

//        return addApplicantRepository.addApplicant(applicants);

    }
}
