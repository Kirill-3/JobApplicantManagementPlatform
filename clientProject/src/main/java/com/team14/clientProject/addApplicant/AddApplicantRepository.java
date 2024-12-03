package com.team14.clientProject.addApplicant;
import com.team14.clientProject.homePage.Applicants;
import org.springframework.stereotype.Repository;

@Repository
public interface AddApplicantRepository {

    void addApplicant(AddApplicantForm applicants);
}
