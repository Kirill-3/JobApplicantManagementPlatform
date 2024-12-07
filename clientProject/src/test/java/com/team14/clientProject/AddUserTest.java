package com.team14.clientProject;

import com.team14.clientProject.adminPage.AdminService;
import com.team14.clientProject.adminPage.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class AddUserTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void openAdminPage() throws Exception {
        Assert.notNull(adminService,"AdminServer should not be empty");
    }

    @Test
    public void addNewUsers() {
        //Create a new user object
        User newUser = new User();
        newUser.setUsername("testUser");
        newUser.setPassword("password123");
        newUser.setFirstName("Test");
        newUser.setLastName("User");
        newUser.setRole(User.Role.recruiter);
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());

        // Call the service to add a new user
        User savedUser = adminService.addUser(newUser);

        // Validate the user was added  
        Assert.notNull(savedUser, "Saved user should not be null");
        Assert.isTrue(savedUser.getId() > 0, "Saved user ID should be greater than 0");
        Assert.isTrue("testUser".equals(savedUser.getUsername()), "Usernames should match");
    }

    @Test
    public void deleteNewUser() {
        // Create a new user object
        User newUser = new User();
        newUser.setUsername("testUser");
        newUser.setPassword("password123");
        newUser.setFirstName("Test");
        newUser.setLastName("User");
        newUser.setRole(User.Role.recruiter);
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());

        // Add the user using the admin service
        User savedUser = adminService.addUser(newUser);

        // Ensure the user was saved
        Assert.notNull(savedUser, "Saved user should not be null");
        Assert.isTrue(savedUser.getId() > 0, "Saved user ID should be greater than 0");

        // Delete the user
        adminService.deleteUser(savedUser.getId());

        // Try retrieving the user to ensure it was deleted
        User deletedUser = adminService.getUserById(savedUser.getId());
        Assert.isNull(deletedUser, "Deleted user should be null");
    }

}
