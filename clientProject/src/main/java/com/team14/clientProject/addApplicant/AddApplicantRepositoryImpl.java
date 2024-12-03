package com.team14.clientProject.addApplicant;

import com.team14.clientProject.homePage.Applicants;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Repository
public class AddApplicantRepositoryImpl implements AddApplicantRepository {

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Applicants> ApplicantRowMapper;

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

    public String addApplicant(AddApplicantForm applicants) {
        // Validating that the email does not already exist in the database
        String emailValidation =
                "SELECT COUNT(*) " +
                        "FROM applicants " +
                        "WHERE email = ?";

        Integer queryResult = jdbcTemplate.queryForObject(emailValidation, Integer.class, applicants.getEmail());

        if (queryResult > 0) {
            //throw new IllegalArgumentException("Email already exists");
            String check= "false";
            return check;
        }

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

        String check="true";
        return check;
    }
}
