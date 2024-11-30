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

            applicantJobDetails jobDetails = new applicantJobDetails(
                    rs.getInt("id"),
                    rs.getString("currentPosition"),
                    rs.getString("status")
            );
            profile.setJobDetails(jobDetails);

            return profile;
        };

    }


    @Override
    public List<Profile> getProfiles(){
        String sql =  "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id";

        return jdbcTemplate.query(sql, ProfileRowMapper);
    }

    @Override
    public Profile getProfileById(int id) {
        // SQL query to fetch profile and preferences for a specific applicant
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id " +
                "WHERE a.id = ?";
        return jdbcTemplate.queryForObject(sql, ProfileRowMapper, id);
    }
    public void addProfile(Profile profile) {
        String sql = "insert into applicants (firstName, lastName, location, email, phoneNumber, eventAttended, skill) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, profile.getFirstName(), profile.getLastName(), profile.getLocation(), profile.getEmail(), profile.getPhoneNumber(), profile.getEventAttended(), profile.getSkill());
    }
    @Override
    public List<Profile> getProfilesByFirstNameAscending() {
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id " +
                "ORDER BY a.firstName ASC";
        return jdbcTemplate.query(sql, ProfileRowMapper);
    }
    @Override
    public List<Profile> getProfilesByFirstNameDescending() {
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id " +
                "ORDER BY a.firstName DESC";
        return jdbcTemplate.query(sql, ProfileRowMapper);
    }
    @Override
    public List<Profile> getProfilesByLastNameAscending() {
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id " +
                "ORDER BY a.lastName ASC";
        return jdbcTemplate.query(sql, ProfileRowMapper);
    }
    @Override
    public List<Profile> getProfilesByLastNameDescending() {
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id " +
                "ORDER BY a.lastName DESC";
        return jdbcTemplate.query(sql, ProfileRowMapper);
    }
    public List<Profile> getProfilesByUniqueLocation() {
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id " +
                "GROUP BY a.location";
        return jdbcTemplate.query(sql, ProfileRowMapper);
    }
    public List<Profile> getProfilesByUniqueEvent() {
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id " +
                "GROUP BY a.eventAttended";
        return jdbcTemplate.query(sql, ProfileRowMapper);
    }
    public List<String> getUniqueSkills() {
        String sql = "SELECT DISTINCT TRIM(SUBSTRING_INDEX(SUBSTRING_INDEX(a.skill, ',', numbers.n), ',', -1)) AS skill " +
                "FROM applicants a " +
                "JOIN (SELECT 1 n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10) numbers " +
                "ON CHAR_LENGTH(a.skill) - CHAR_LENGTH(REPLACE(a.skill, ',', '')) >= numbers.n - 1 " +
                "ORDER BY skill";
        return jdbcTemplate.queryForList(sql, String.class);
    }



    public void updateCvPath(int userId, byte[] cvPath) {
        String sql = "UPDATE applicationdetails SET cvPath = ? WHERE id = ?";
        jdbcTemplate.update(sql, cvPath, userId);
    }

    public byte[] getCvPath(int userId) {
        String sql = "SELECT cvPath FROM applicationdetails WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, byte[].class, userId);
    }
}
