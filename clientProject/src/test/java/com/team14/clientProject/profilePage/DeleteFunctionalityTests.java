package com.team14.clientProject.profilePage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class DeleteFunctionalityTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ProfilePageRepositoryImpl profilePageRepository;

    private RowMapper<Profile> profileRowMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteProfile() {
        int userId = 1;
        String sql = "DELETE FROM applicants WHERE id = ?";

        when(jdbcTemplate.update(sql, userId)).thenReturn(1);

        profilePageRepository.deleteProfile(userId);

        verify(jdbcTemplate, times(1)).update(sql, userId);
    }

    @Test
    void testDeleteProfileAfter5Seconds() throws InterruptedException {
        int userId = 1;
        String insertSql = "INSERT INTO deletedApplicants (id, firstName, lastName, location, email, phoneNumber, currentPosition, status, skill, eventAttended, SubscribeToNewsLetter, SubscribeToBulletins, SubscribeToJobUpdates) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String checkSql =  "SELECT * FROM deletedApplicants WHERE id = ?";

        when(jdbcTemplate.update(insertSql, userId, "John", "Doe", "Location", "email@example.com", "1234567890", "Current Position", "External", "Skill", "Event", "Yes", "No", "Yes")).thenReturn(1);
        profilePageRepository.deleteProfile(userId);

        Thread.sleep(5000);

        when(jdbcTemplate.query(checkSql, new Object[]{userId}, profileRowMapper)).thenReturn(List.of());
        List<Profile> profiles = jdbcTemplate.query(checkSql, new Object[]{userId}, profileRowMapper);

        assertTrue(profiles.isEmpty());
    }
    @Test
    void testDeleteNonExistentProfile() {
        int userId = 999;
        String sql = "DELETE FROM applicants WHERE id = ?";

        when(jdbcTemplate.update(sql, userId)).thenReturn(0);

        profilePageRepository.deleteProfile(userId);

        verify(jdbcTemplate, times(1)).update(sql, userId);
    }

    @Test
    void testDeleteProfileWithInvalidId() {
        int invalidUserId = -1;
        String sql = "DELETE FROM applicants WHERE id = ?";

        when(jdbcTemplate.update(sql, invalidUserId)).thenReturn(0);

        profilePageRepository.deleteProfile(invalidUserId);

        verify(jdbcTemplate, times(1)).update(sql, invalidUserId);
    }

    @Test
    void testProfileNoLongerExistsAfterDeletion() {
        int userId = 1;
        String deleteSql = "DELETE FROM applicants WHERE id = ?";
        String checkSql = "SELECT * FROM applicants WHERE id = ?";

        when(jdbcTemplate.update(deleteSql, userId)).thenReturn(1);
        when(jdbcTemplate.query(checkSql, new Object[]{userId}, profileRowMapper)).thenReturn(List.of());

        profilePageRepository.deleteProfile(userId);

        List<Profile> profiles = jdbcTemplate.query(checkSql, new Object[]{userId}, profileRowMapper);

        assertTrue(profiles.isEmpty());
        verify(jdbcTemplate, times(1)).update(deleteSql, userId);
        verify(jdbcTemplate, times(1)).query(checkSql, new Object[]{userId}, profileRowMapper);
    }
}
