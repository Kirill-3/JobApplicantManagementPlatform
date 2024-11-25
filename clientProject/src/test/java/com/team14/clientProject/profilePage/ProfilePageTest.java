package com.team14.clientProject.profilePage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.FlashAttributeResultMatchers;

import java.nio.file.FileStore;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProfilePageTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ProfilePage profilePage;
    @Autowired
    private ProfilePageRepositoryImpl profilePageRepositoryImpl;


    @Test
    void profilePageDisplays() throws Exception {
        MvcResult result = mvc
                .perform(get("/profile"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testProfilePageDisplaysValidUserProfile() throws Exception {
        List<Profile> profileList = profilePageRepositoryImpl.getProfiles();
        for (Profile profile : profileList) {
            MvcResult result = mvc
                    .perform(get("/profile").param("userId", String.valueOf(profile.getId())))
                    .andExpect(status().isOk())
                    .andExpect(view().name("profilePage"))
                    .andReturn();
        }
    }
    @Test
    void testProfilePageDisplaysInvalidUserProfileTooHighUserId() throws Exception {
        List<Profile> profileList = profilePageRepositoryImpl.getProfiles();
        int count = 0;
        for (Profile profile : profileList) {
            count++;
        }
        MvcResult result = mvc
                .perform(get("/profile").param("userId", String.valueOf(count+1)))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test void testProfilePageDisplaysInvalidUserProfileNegativeUserId() throws Exception {
        MvcResult result = mvc
                .perform(get("/profile").param("userId", "-1"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test void testProfilePageDisplaysInvalidUserProfileStringUserId() throws Exception {
        MvcResult result = mvc
                .perform(get("/profile").param("userId", "string"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test void testProfilePageDisplaysInvalidUserProfileEmptyUserId() throws Exception {
        MvcResult result = mvc
                .perform(get("/profile").param("userId", ""))
                .andExpect(status().isOk())
                .andReturn();
    }
}