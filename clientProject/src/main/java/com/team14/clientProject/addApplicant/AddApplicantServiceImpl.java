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


    public String addApplicant(AddApplicantForm applicants) {return addApplicantRepository.addApplicant(applicants);}
}
