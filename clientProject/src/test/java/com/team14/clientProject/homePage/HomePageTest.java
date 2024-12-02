package com.team14.clientProject.homePage;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.test.web.servlet.result.FlashAttributeResultMatchers;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class HomePageTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HomePageRepositoryImpl homePageRepositoryImpl;

    @Test
    void homePageDisplays() throws Exception {
        MvcResult result = mvc
                .perform(get("/home"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testHomePageTableDisplaysTenApplicants() throws Exception {
        List<Applicants> mockApplicants = List.of(
                new Applicants(1, "John", "Smith", null, "smith@example.com",
                        "9876543210", "Conference", null, "2024-11-30","2024-11-30"),
                new Applicants(2, "John", "Smith", null, "smith@example.com",
                        "9876543210", "Conference", null, "2024-11-30","2024-11-30"),
                new Applicants(3, "John", "Smith", null, "smith@example.com",
                        "9876543210", "Conference", null, "2024-11-30","2024-11-30"),
                new Applicants(4, "John", "Smith", null, "smith@example.com",
                        "9876543210", "Conference", null, "2024-11-30","2024-11-30"),
                new Applicants(5, "John", "Smith", null, "smith@example.com",
                        "9876543210", "Conference", null, "2024-11-30","2024-11-30"),
                new Applicants(6, "John", "Smith", null, "smith@example.com",
                        "9876543210", "Conference", null, "2024-11-30","2024-11-30"),
                new Applicants(7, "John", "Smith", null, "smith@example.com",
                        "9876543210", "Conference", null, "2024-11-30","2024-11-30"),
                new Applicants(8, "John", "Smith", null, "smith@example.com",
                        "9876543210", "Conference", null, "2024-11-30","2024-11-30"),
                new Applicants(9, "John", "Smith", null, "smith@example.com",
                        "9876543210", "Conference", null, "2024-11-30","2024-11-30"),
                new Applicants(10, "John", "Smith", null, "smith@example.com",
                        "9876543210", "Conference", null, "2024-11-30","2024-11-30")
        );

        when(homePageRepositoryImpl.get10MostRecentProfiles()).thenReturn(mockApplicants);

        List<Applicants> tableTest = homePageRepositoryImpl.get10MostRecentProfiles();
        assertEquals(10, tableTest.size());
    }


}
