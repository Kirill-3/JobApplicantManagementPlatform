package com.team14.clientProject.accountManagementTests;

import com.team14.clientProject.accountManagement.UserService;
import com.team14.clientProject.adminPage.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserNameById() {
        int userId = 1;
        String expectedUserName = "John";

        when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), eq(String.class)))
                .thenReturn(expectedUserName);

        String actualUserName = userService.getUserNameById(userId);

        assertEquals(expectedUserName, actualUserName);
    }

    @Test
    public void testGetUserById() {
        int userId = 1;
        User expectedUser = new User();
        expectedUser.setId(userId);
        expectedUser.setUsername("john_doe");
        expectedUser.setFirstName("John");
        expectedUser.setLastName("Doe");

        when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(RowMapper.class)))
                .thenReturn(expectedUser);

        User actualUser = userService.getUserById(userId);

        assertEquals(expectedUser, actualUser);
    }
}