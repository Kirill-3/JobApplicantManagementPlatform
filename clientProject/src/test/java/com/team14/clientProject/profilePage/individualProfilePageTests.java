package com.team14.clientProject.profilePage;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

class ProfilePageRepositoryImplTest {

    private ProfilePageRepositoryImpl profilePageRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        profilePageRepository = new ProfilePageRepositoryImpl(jdbcTemplate);
    }

    @Test
    void testGetProfiles() {
        // Arrange
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id";
        List<Profile> mockProfiles = List.of(new Profile(1, "John", "Doe", "New York", "john.doe@example.com",
                "1234567890", "Tech Conference", "Java Developer"));
        when(jdbcTemplate.query(eq(sql), any(RowMapper.class))).thenReturn(mockProfiles);

        // Act
        List<Profile> profiles = profilePageRepository.getProfiles();

        // Assert
        assertEquals(1, profiles.size());
        assertEquals("John", profiles.get(0).getFirstName());
        verify(jdbcTemplate, times(1)).query(eq(sql), any(RowMapper.class));
    }

    @Test
    void testGetProfileById() {
        // Arrange
        int testId = 1;
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id " +
                "WHERE a.id = ?";
        Profile mockProfile = new Profile(1, "John", "Doe", "New York", "john.doe@example.com",
                "1234567890", "Tech Conference", "Java Developer");
        when(jdbcTemplate.queryForObject(eq(sql), any(RowMapper.class), eq(testId))).thenReturn(mockProfile);

        // Act
        Profile profile = profilePageRepository.getProfileById(testId);

        // Assert
        assertNotNull(profile);
        assertEquals("John", profile.getFirstName());
        verify(jdbcTemplate, times(1)).queryForObject(eq(sql), any(RowMapper.class), eq(testId));
    }

    @Test
    void testGetProfiles_NullFields() {
        // Arrange
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id";
        Profile mockProfile = new Profile(1, null, "Smith", null, "smith@example.com",
                "9876543210", "Conference", null);
        when(jdbcTemplate.query(eq(sql), any(RowMapper.class))).thenReturn(List.of(mockProfile));

        // Act
        List<Profile> profiles = profilePageRepository.getProfiles();

        // Assert
        assertEquals(1, profiles.size());
        assertNull(profiles.get(0).getFirstName());
        assertEquals("Smith", profiles.get(0).getLastName());
        assertEquals("smith@example.com", profiles.get(0).getEmail());
        verify(jdbcTemplate, times(1)).query(eq(sql), any(RowMapper.class));
    }


    @Test
    void testGetProfiles_EmptyResult() {
        // Arrange
        String sql = "SELECT a.*, p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates," +
                "d.currentPosition, d.status " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.Id " +
                "LEFT JOIN applicationdetails d ON a.Id = d.Id";
        when(jdbcTemplate.query(eq(sql), any(RowMapper.class))).thenReturn(List.of());

        // Act
        List<Profile> profiles = profilePageRepository.getProfiles();

        // Assert
        assertTrue(profiles.isEmpty()); // Ensure the returned list is empty
        verify(jdbcTemplate, times(1)).query(eq(sql), any(RowMapper.class));
    }

}
