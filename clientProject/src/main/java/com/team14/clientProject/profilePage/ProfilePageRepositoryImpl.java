package com.team14.clientProject.profilePage;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProfilePageRepositoryImpl implements ProfilePageRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Profile> ProfileRowMapper;

    public ProfilePageRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createProfileRowMapper();
    }


    private void createProfileRowMapper() {
        ProfileRowMapper = (rs, rowNum) -> new Profile(
                rs.getInt("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("location"),
                rs.getString("email"),
                rs.getString("phoneNumber"),
                rs.getString("eventAttended"),
                rs.getString("skill")

            );
    }
    @Override
    public List<Profile> getProfiles() {
        String sql = "select * from applicants";
        return jdbcTemplate.query(sql, ProfileRowMapper);
    }

    public Profile getProfileById(int id) {
        String sql = "select * from applicants where Id = ?";
        return jdbcTemplate.queryForObject(sql, ProfileRowMapper, id);
    }
    public void addProfile(Profile profile) {
        String sql = "insert into applicants (firstName, lastName, location, email, phoneNumber, eventAttended, skill) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, profile.getFirstName(), profile.getLastName(), profile.getLocation(), profile.getEmail(), profile.getPhoneNumber(), profile.getEventAttended(), profile.getSkill());
    }
}
