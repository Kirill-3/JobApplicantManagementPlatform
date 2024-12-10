package com.team14.clientProject.statisticsPage;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TestStatsPage {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private StatisticRepositoryImpl statisticRepositoryImpl;

    @Autowired
    private StatisticController statisticController;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void openStats() throws Exception {
        MvcResult result = mvc
                .perform(get("/statistics"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void openStatsLocation() throws Exception {
        MvcResult result = mvc
                .perform(get("/api/statistics/location"))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void openStatsTimePeriod() throws Exception {
        MvcResult result = mvc
                .perform(get("/api/statistics/time-period"))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void openStatsEvent() throws Exception {
        MvcResult result = mvc
                .perform(get("/api/statistics/event"))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(result.getResponse().getContentAsString());
    }
}
