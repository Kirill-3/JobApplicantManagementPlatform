package com.team14.clientProject.addApplicant;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.team14.clientProject.homePage.Applicants;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
            String check = "emailFalse";
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

    // OpenCSV learning from: https://opencsv.sourceforge.net/#reading & https://www.baeldung.com/opencsv
    @Transactional
    public String csvFunction(MultipartFile file) {

        try {
            InputStreamReader fileReader = new InputStreamReader(file.getInputStream());
            List<ApplicantCSVForm> beans = new CsvToBeanBuilder<ApplicantCSVForm>(fileReader)
                    .withType(ApplicantCSVForm.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build()
                    .parse();

            for (ApplicantCSVForm bean : beans) {
                Applicant applicant = new Applicant(
                        bean.getFirstName(),
                        bean.getLastName(),
                        bean.getLocation(),
                        bean.getEmail(),
                        bean.getPhoneNumber(),
                        bean.getEventAttended(),
                        bean.getSkill()
                );
                ApplicantDetails applicantDetails = new ApplicantDetails(
                        bean.getCurrentPosition(),
                        bean.getStatus(),
                        bean.getCvPath(),
                        bean.getCoverLetterPath()
                );
                ApplicantPreferences applicantPreferences = new ApplicantPreferences(
                        bean.getSubscribeToNewsletter(),
                        bean.getSubscribeToBulletins(),
                        bean.getSubscribeToJobUpdates()
                );
            try {
                    addApplicantRepository.addApplicantFromCsv(applicant);
                    Integer id = addApplicantRepository.getRecentId();
                    addApplicantRepository.addApplicantPreferencesFromCsv(applicantPreferences, id);
                    addApplicantRepository.addApplicantDetailsFromCsv(applicantDetails, id);
            }
            catch (DuplicateKeyException d) {
                    String csvCheck = "duplicateKeyException";
                    return csvCheck;
                }
            }

        } catch (IOException e) {
            String csvCheck = "ioException";
            return csvCheck;
        }
        String csvCheck = "true";
        return csvCheck;
    }
}
