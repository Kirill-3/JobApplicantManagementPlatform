package com.team14.clientProject.profilePage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class cvTests {

    @Autowired
    private MockMvc mvc;

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
                        .file(validPdfFile))
                .andExpect(status().isOk())
                .andExpect(view().name("profilePage"))
                .andExpect(model().attribute("message", "File uploaded successfully"))
                .andReturn();
    }

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
                        .file(invalidFile))
                .andExpect(status().isOk())
                .andExpect(view().name("profilePage"))
                .andExpect(model().attribute("message", "Please upload a PDF file"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testUploadNoFileSelected() throws Exception {
        // Perform the upload request with no file
        MvcResult result = mvc.perform(MockMvcRequestBuilders.multipart("/profile/uploadCV/{userID}", 1)
                        .file(new MockMultipartFile("cvUpload", "", "application/pdf", new byte[0])))

                .andExpect(status().isOk())
                .andExpect(view().name("profilePage"))
                .andExpect(model().attribute("message", "Please select a file to upload"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testDatabaseUpdateOnCvUpload() throws Exception {
        // Creating a valid PDF file for upload
        MockMultipartFile validPdfFile = new MockMultipartFile(
                "cvUpload",
                "resume.pdf",
                "application/pdf",
                "PDF content".getBytes());

        // Perform the upload request for the CV
        MvcResult result = mvc.perform(MockMvcRequestBuilders.multipart("/profile/uploadCV/{userID}", 1)
                        .file(validPdfFile))
                .andExpect(status().isOk())
                .andExpect(view().name("profilePage"))
                .andExpect(model().attribute("message", "File uploaded successfully"))
                .andReturn();
    }
}
