package com.team14.clientProject.addApplicant;
import com.team14.clientProject.homePage.Applicants;
import org.springframework.stereotype.Repository;

@Repository
public interface AddApplicantRepository {

    Integer emailValidation(AddApplicantForm applicants);
    Integer phoneNoValidation(AddApplicantForm applicants);
    void addApplicant(AddApplicantForm applicants);

    Integer getRecentId();

    void addApplicantFromCsv(Applicant applicant);
    void addApplicantPreferencesFromCsv(ApplicantPreferences applicantPreferences, Integer id);
    void addApplicantDetailsFromCsv(ApplicantDetails applicantDetails, Integer id);
    Integer getApplicantCount();
}
