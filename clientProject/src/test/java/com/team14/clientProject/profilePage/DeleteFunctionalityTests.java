package com.team14.clientProject.profilePage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class DeleteFunctionalityTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ProfilePageRepositoryImpl profilePageRepository;

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
}
