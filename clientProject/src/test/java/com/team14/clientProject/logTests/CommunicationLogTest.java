package com.team14.clientProject.logTests;

import com.team14.clientProject.loggingSystem.CommunicationLog;
import com.team14.clientProject.loggingSystem.CommunicationLogRepositoryImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
class CommunicationLogTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CommunicationLogRepositoryImpl communicationLogRepositoryImpl;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CommunicationLogTest() {
        communicationLogRepositoryImpl = new CommunicationLogRepositoryImpl(jdbcTemplate);
    }

    @Test
    void testCommunicationLogRepositoryImpl() {
        assertNotNull(communicationLogRepositoryImpl, "CommunicationLogRepositoryImpl should not be null");
    }


    @Test
    void getLogId() {
        assertEquals(1, communicationLogRepositoryImpl.getLogs().get(communicationLogRepositoryImpl.getLogs().size()-1).getLogId());
    }

    @Test
    void getApplicantId() {
        assertEquals(1, communicationLogRepositoryImpl.getLogs().get(communicationLogRepositoryImpl.getLogs().size()-1).getApplicantId());
    }

    @Test
    void getUserId() {
        assertEquals(2, communicationLogRepositoryImpl.getLogs().get(communicationLogRepositoryImpl.getLogs().size()-1).getUserId());
    }

    @Test
    void getTimestamp() {
        assertNotNull(communicationLogRepositoryImpl.getLogs().get(communicationLogRepositoryImpl.getLogs().size()-1).getTimestamp());
    }

    @Test
    void getUserType() {
        assertEquals("recruiter", communicationLogRepositoryImpl.getLogs().get(communicationLogRepositoryImpl.getLogs().size()-1).getUserType());
    }

    @Test
    void getLogType() {
        assertEquals("communication", communicationLogRepositoryImpl.getLogs().get(communicationLogRepositoryImpl.getLogs().size()-1).getLogType());
    }

    @Test
    void getCommunicationType() {
        assertEquals("email", communicationLogRepositoryImpl.getLogs().get(communicationLogRepositoryImpl.getLogs().size()-1).getCommunicationType());
    }

    @Test
    void getActionTaken() {
        assertEquals("emailSent", communicationLogRepositoryImpl.getLogs().get(communicationLogRepositoryImpl.getLogs().size()-1).getActionTaken());
    }

    @Test
    void getNotes() {
        assertEquals("Invitation to interview", communicationLogRepositoryImpl.getLogs().get(communicationLogRepositoryImpl.getLogs().size()-1).getNotes());
    }

    @Test
    void testGetLogs() {
        assertNotNull(communicationLogRepositoryImpl.getLogs());
    }

    @Test
    void testAddEmailLog() {
        List<String> myListOfUsers = new ArrayList();
        myListOfUsers.add("1");
        communicationLogRepositoryImpl.addEmailLog(myListOfUsers, "testing123");
        assertEquals("testing123", communicationLogRepositoryImpl.getLogs().get(0).getNotes());
    }

    @Test
    void testAddApplicant() {
        assertNotEquals("Applicant added to the system", communicationLogRepositoryImpl.getLogs().get(0).getNotes());
        communicationLogRepositoryImpl.addApplicantLog();
        assertEquals("Applicant added to the system", communicationLogRepositoryImpl.getLogs().get(0).getNotes());
    }

    @Test
    void testEditApplicantLog() {
        assertNotEquals("Applicant details edited", communicationLogRepositoryImpl.getLogs().get(0).getNotes());
        communicationLogRepositoryImpl.editApplicantLog(1);
        assertEquals("Applicant details edited", communicationLogRepositoryImpl.getLogs().get(0).getNotes());
    }

    @Test
    void testDeleteApplicantLog() {
        assertNotEquals("Applicant deleted from the system", communicationLogRepositoryImpl.getLogs().get(0).getNotes());
        communicationLogRepositoryImpl.deleteApplicantLog(1);
        assertEquals("Applicant deleted from the system", communicationLogRepositoryImpl.getLogs().get(0).getNotes());
    }

    @Test
    void applicantAddedLogShouldBeDisplayedOnPage() {
        assertNotNull(communicationLogRepositoryImpl.getLogsByApplicantId(1));
    }



    /*
------------------------------------------------FRONTEND------------------------------------------------
     */


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testLogTableShowsOnAdminPage() throws Exception {
        MvcResult result = mvc
                .perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/admin"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Document doc = Jsoup.parse(content);
        Elements logTable = doc.select(".logTable");
        assertNotNull(logTable);
    }
    /*
    TEST WORKS BUT NEEDS TO BE ADDED TO AN ADMIN PAGE WHICH HAS A SYTEM AND A COMMUNICATION LOG REPOSITORY IMPL
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAllLogsShowOnAdminPage() throws Exception {
        MvcResult result = mvc
                .perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/admin"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Document doc = Jsoup.parse(content);
        Elements logsShownOnPage = doc.select(".logs");
        assertEquals(communicationLogRepositoryImpl.getLogs().size(), logsShownOnPage.size());
    }

     */

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAllCommunicationLogsShowOnAdminPage() throws Exception {
        MvcResult result = mvc
                .perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/admin"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Document doc = Jsoup.parse(content);
        Elements logsShownOnPage = doc.select(".CommunicationLogs");
        assertEquals(communicationLogRepositoryImpl.getLogs().size(), logsShownOnPage.size());
    }




}
