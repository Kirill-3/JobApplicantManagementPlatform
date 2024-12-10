package com.team14.clientProject.full;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HeaderViewOrderTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testHeaderIsDisplayedOnHomePage() throws Exception {
        MvcResult result = mvc.perform(get("/home"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertTrue(content.contains("class=\"navbar navbar-expand-lg navbar-light bg-light\""));
    }
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testHeaderIsDisplayedOnProfilePage() throws Exception {
        MvcResult result = mvc.perform(get("/profile"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertTrue(content.contains("class=\"navbar navbar-expand-lg navbar-light bg-light\""));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void homeLinkShouldRedirectToHomePage() throws Exception {
        mvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home/homePage"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void profileLinkShouldRedirectToProfilePage() throws Exception {
        mvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profilePage"));
    }
}

