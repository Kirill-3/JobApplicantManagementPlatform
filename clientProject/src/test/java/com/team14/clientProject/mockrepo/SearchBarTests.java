package com.team14.clientProject.mockrepo;

import com.team14.clientProject.profilePage.ProfilePageRepositoryImpl;
import com.team14.clientProject.profilePage.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
public class SearchBarTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProfilePageRepositoryImpl profilePageRepository;

    private MockMvc Mvc;

    @Test
    public void testSearchBarDisplayedOnMainProfilePage() throws Exception {
        Mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult result = Mvc.perform(get("/profile"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        //assertTrue(content.contains("<div class = \"search-bar-container\">"));
    }

    @Test
    public void testSearchFunctionality() throws Exception {
        Mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String query = "John";
        List<Profile> profiles = profilePageRepository.searchProfiles(query);

        Mvc.perform(get("/profile/search").param("query", query))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(profiles.get(0).getFirstName())))
                .andExpect(content().string(containsString(profiles.get(0).getLastName())));
    }
}