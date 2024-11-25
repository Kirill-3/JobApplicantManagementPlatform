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
        ProfileRowMapper = (rs, rowNum) -> {

            Profile profile = new Profile(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("location"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("eventAttended"),
                    rs.getString("skill")
            );

            applicantPreferences preferences = new applicantPreferences(
                    rs.getInt("id"),
                    rs.getBoolean("SubscribeToNewsLetter"),
                    rs.getBoolean("SubscribeToBulletins"),
                    rs.getBoolean("SubscribeToJobUpdates")
            );
            profile.setPreferences(preferences);

            return profile;
        };

    }

//    @Override
//    public List<Profile> getProfiles() {
//        String sql = "select * from applicants";
//        return jdbcTemplate.query(sql, ProfileRowMapper);
//    }

    @Override
    public List<Profile> getProfiles(){
        String sql =  "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id";
        return jdbcTemplate.query(sql, ProfileRowMapper);
    }

    @Override
    public Profile getProfileById(int id) {
        // SQL query to fetch profile and preferences for a specific applicant
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "WHERE a.id = ?";
        return jdbcTemplate.queryForObject(sql, ProfileRowMapper, id);
    }
    public void addProfile(Profile profile) {
        String sql = "insert into applicants (firstName, lastName, location, email, phoneNumber, eventAttended, skill) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, profile.getFirstName(), profile.getLastName(), profile.getLocation(), profile.getEmail(), profile.getPhoneNumber(), profile.getEventAttended(), profile.getSkill());
    }
}
