package com.team14.clientProject.addApplicant;

import com.team14.clientProject.homePage.Applicants;
import com.team14.clientProject.loggingSystem.CommunicationLogRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.team14.clientProject.loggingSystem.CommunicationLogRepository;

import java.util.List;

@Repository
public class AddApplicantRepositoryImpl implements AddApplicantRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Applicants> ApplicantRowMapper;
    @Autowired
    private CommunicationLogRepositoryImpl communicationLogRepository;

    public AddApplicantRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbcTemplate = aJdbc;
        setApplicantRowMapper();
    }

    // Row mapper for the applicant object
    private void setApplicantRowMapper() {
        ApplicantRowMapper = (rs, rowNum) -> {
            Applicants applicant = new Applicants(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("location"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("eventAttended"),
                    rs.getString("skill"),
                    rs.getString("createdAt"),
                    rs.getString("updatedAt")
            );
            return applicant;
        };
    }

    public Integer emailValidation(AddApplicantForm applicants) {
        String emailValidation =
                "SELECT COUNT(*) " +
                "FROM applicants " +
                "WHERE email = ?";

        Integer queryResultEmail = jdbcTemplate.queryForObject(emailValidation, Integer.class, applicants.getEmail());
        return queryResultEmail;
    }

    public Integer phoneNoValidation(AddApplicantForm applicants) {
        String phoneNoValidation =
                "SELECT COUNT(*) " +
                "FROM applicants " +
                "WHERE phoneNumber = ?";
        Integer queryResultPhone = jdbcTemplate.queryForObject(phoneNoValidation, Integer.class, applicants.getPhoneNumber());
        return queryResultPhone;
    }

    public Integer getApplicantCount() {
        String sql =
                "SELECT COUNT(*) " +
                "FROM " +
                "applicants";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void addApplicant(AddApplicantForm applicants) {

        // Insert query for the form data
        String sql =
                "INSERT INTO applicants" +
                "(firstName, lastName, location, email, phoneNumber, eventAttended, skill) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Values for the query
        jdbcTemplate.update(sql,
                applicants.getFirstName(),
                applicants.getLastName(),
                applicants.getLocation(),
                applicants.getEmail(),
                applicants.getPhoneNumber(),
                applicants.getEventAttended(),
                applicants.getSkill()
        );
        this.communicationLogRepository.addApplicantLog();

    }
}
