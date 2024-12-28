package com.team14.clientProject.accountManagementTests;

import com.team14.clientProject.accountManagement.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountServiceTests {
    @InjectMocks
    private AccountService accountService;

    @Mock
    private MultipartFile file;

    private static final String PICTURE_PATH = "uploads/images/profile-pictures/";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateProfilePictureSuccessful() throws IOException {
        int userId = 1;
        String fileName = "test.jpg";
        Path filePath = Paths.get(PICTURE_PATH, "user_" + userId + "_" + fileName);

        when(file.getOriginalFilename()).thenReturn(fileName);
        when(file.getBytes()).thenReturn("test image content".getBytes());

        accountService.updateProfilePicture(file, userId);

        assertTrue(Files.exists(filePath));
        Files.deleteIfExists(filePath);
    }

    @Test
    public void testUpdateProfilePictureWithEmptyFile() {
        int userId = 1;

        when(file.isEmpty()).thenReturn(true);

        IOException exception = assertThrows(IOException.class, () -> {
            accountService.updateProfilePicture(file, userId);
        });

        assertEquals("Empty File...", exception.getMessage());
    }

    @Test
    public void testGetProfilePicturePath() throws IOException {
        int userId = 1;
        String fileName = "user_" + userId + "_test.jpg";
        Path filePath = Paths.get(PICTURE_PATH, fileName);
        Files.createDirectories(filePath.getParent());
        Files.createFile(filePath);

        String profilePicturePath = accountService.getProfilePicturePath(userId);

        assertEquals("/uploads/images/profile-pictures/" + fileName, profilePicturePath);
        Files.deleteIfExists(filePath);
    }

    @Test
    public void testGetProfilePicturePathForDefaultPicture() {
        int userId = 1;

        String profilePicturePath = accountService.getProfilePicturePath(userId);

        assertEquals("/images/default.jpg", profilePicturePath);
    }

    @Test
    public void testRemoveProfilePictureSuccessful() throws IOException {
        int userId = 1;
        String fileName = "user_" + userId + "_test.jpg";
        Path filePath = Paths.get(PICTURE_PATH, fileName);
        Files.createDirectories(filePath.getParent());
        Files.createFile(filePath);

        accountService.removeProfilePicture(userId);

        assertFalse(Files.exists(filePath));
    }
}

