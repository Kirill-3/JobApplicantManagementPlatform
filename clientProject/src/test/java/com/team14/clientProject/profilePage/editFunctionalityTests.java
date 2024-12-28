package com.team14.clientProject.profilePage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class editFunctionalityTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProfilePageRepositoryImpl profilePageRepository;
    private MockMvc Mvc;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        Mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        session = new MockHttpSession();
        session.setAttribute("userId", 1);
    }

    @WithMockUser(username = "user", roles = "USER")
    @Test
    public void testEditButtonDisplayedOnIndividualProfilePages() throws Exception {
        MvcResult result = Mvc.perform(get("/profile/1").session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("<button id=\"editButton\""));
    }

    @Test
    public void testEditFirstName() throws Exception {
        Mvc.perform(post("/profile/edit/1")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("location", "New York")
                        .param("email", "john.doe@example.com")
                        .param("phoneNumber", "1234567890")
                        .param("currentPosition", "Developer")
                        .param("status", "Internal")
                        .param("skill", "Java")
                        .param("eventAttended", "Spring Boot Conference")
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Profile updatedProfile = profilePageRepository.getProfileById(1);

        assertTrue(updatedProfile.getFirstName().equals("John"));
    }

    @Test
    public void testEditSubscribeToBulletins() throws Exception {
        Mvc.perform(post("/profile/edit/1")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("location", "New York")
                        .param("email", "john.doe@example.com")
                        .param("phoneNumber", "1234567890")
                        .param("currentPosition", "Developer")
                        .param("status", "Internal")
                        .param("skill", "Java")
                        .param("eventAttended", "Spring Boot Conference")
                        .param("subscribeToBulletins", "true")
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Profile updatedProfile = profilePageRepository.getProfileById(1);

        assertTrue(updatedProfile.getPreferences().isSubscribeToBulletins());
    }
}