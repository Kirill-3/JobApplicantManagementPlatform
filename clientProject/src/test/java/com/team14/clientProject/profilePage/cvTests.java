package com.team14.clientProject.profilePage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class cvTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProfilePageRepositoryImpl profilePageRepositoryImpl;


    // Test for when a valid PDF file is uploaded
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testUploadValidPdfCv() throws Exception {
        // Create a valid PDF file for upload
        MockMultipartFile validPdfFile = new MockMultipartFile(
                "cvUpload",
                "resume.pdf",
                "application/pdf",
                "PDF content".getBytes());

        // Perform the upload request for the CV
        MvcResult result = mvc.perform(MockMvcRequestBuilders.multipart("/profile/uploadCV/{userID}", 1)
                        .file(validPdfFile)
                        // Add CSRF token to the request
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("profilePage"))
                .andExpect(model().attribute("message", "File uploaded successfully"))
                .andReturn();
    }

    // Test for when an invalid file type is uploaded
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testUploadInvalidFileType() throws Exception {
        // Createing an invalid file type in this case a text file
        MockMultipartFile invalidFile = new MockMultipartFile(
                "cvUpload",
                "resume.txt",
                "text/plain",
                "This is a text file".getBytes());

        // Perform the upload request for the CV
        MvcResult result = mvc.perform(MockMvcRequestBuilders.multipart("/profile/uploadCV/{userID}", 1)
                        .file(invalidFile)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("profilePage"))
                .andExpect(model().attribute("message", "Please upload a PDF file"))
                .andReturn();
    }

    // Test for when no file is selected
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testUploadNoFileSelected() throws Exception {
        MockMultipartFile emptyFile = new MockMultipartFile(
                "cvUpload",
                "",
                "application/pdf",
                new byte[0]);

        mvc.perform(MockMvcRequestBuilders.multipart("/profile/uploadCV/{userID}", 1)
                        .file(emptyFile)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("profilePage"))
                .andExpect(model().attribute("message", "Please select a file to upload"));
    }

}
