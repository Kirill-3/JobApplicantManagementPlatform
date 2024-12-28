package com.team14.clientProject.accountManagementTests;

import com.team14.clientProject.accountManagement.AccountService;
import com.team14.clientProject.accountManagement.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    private static final String PICTURE_PATH = "uploads/images/profile-pictures/";

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testAccountPageDisplays() throws Exception {
        mockMvc
                .perform(get("/account").sessionAttr("userId", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("profilePicturePath"))
                .andExpect(model().attributeExists("userName"))
                .andExpect(model().attributeExists("user"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testSuccessfulProfilePictureUpload() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image content".getBytes());

        mockMvc.perform(multipart("/account/upload").file(file).sessionAttr("userId", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/account?success"))
                .andReturn();

        Path filePath = Paths.get("uploads/images/profile-pictures/user_1_test.jpg");
        Files.deleteIfExists(filePath);
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testSuccessfulProfilePictureRemoval() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image content".getBytes());
        accountService.updateProfilePicture(file, 1);

        mockMvc.perform(post("/account/remove").sessionAttr("userId", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/account?success"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testAccountPageDisplaysImportantElements() throws Exception {
        MvcResult result = mockMvc.perform(get("/account").sessionAttr("userId", 1))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("<img"));
        assertTrue(content.contains("<h1>Hello,"));
        assertTrue(content.contains("<form id=\"uploadForm\""));
        assertTrue(content.contains("<input type=\"file\" id=\"fileInput\""));
        assertTrue(content.contains("<button type=\"submit\">Upload</button>"));
        assertTrue(content.contains("<div class=\"userDetails-container\">"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testFileSizeValidationRedirectsCorrectly() throws Exception {
        byte[] largeFileContent = new byte[2 * 1024 * 1024 + 1];
        MockMultipartFile largeFile = new MockMultipartFile("file", "largefile.png", "image/png", largeFileContent);

        mockMvc.perform(multipart("/account/upload").file(largeFile).sessionAttr("userId", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/account?error=uploadFailed"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testInvalidFileTypeValidationRedirectsCorrectly() throws Exception {
        MockMultipartFile invalidFile = new MockMultipartFile("file", "test.txt", "text/plain", "test content".getBytes());

        MvcResult result = mockMvc.perform(multipart("/account/upload").file(invalidFile).sessionAttr("userId", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/account?error=uploadFailed"))
                .andReturn();
    }

    @Test
    public void testRemoveProfilePictureWhenNoFileExists() throws IOException {
        int userId = 1;
        String fileName = "user_" + userId + "_test.jpg";
        Path filePath = Paths.get(PICTURE_PATH, fileName);

        Files.deleteIfExists(filePath);

        accountService.removeProfilePicture(userId);

        assertFalse(Files.exists(filePath));
    }
}
