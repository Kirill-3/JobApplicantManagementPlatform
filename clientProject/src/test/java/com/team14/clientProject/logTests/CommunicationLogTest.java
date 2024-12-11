package com.team14.clientProject.logTests;

import com.team14.clientProject.loggingSystem.CommunicationLog;
import com.team14.clientProject.loggingSystem.CommunicationLogRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


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
    @WithMockUser(username = "admin", roles = "ADMIN")
    void getLogId() {
        assertEquals(21, communicationLogRepositoryImpl.getLogs().get(0).getLogId());
    }

    @Test
    void getApplicantId() {
        assertEquals(1, communicationLogRepositoryImpl.getLogs().get(0).getApplicantId());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void getUserId() {
        assertEquals(1, communicationLogRepositoryImpl.getLogs().get(0).getUserId());
    }

    @Test
    void getTimestamp() {
        assertNotNull(communicationLogRepositoryImpl.getLogs().get(0).getTimestamp());
    }

    @Test
    void getUserType() {
        assertEquals("recruiter", communicationLogRepositoryImpl.getLogs().get(0).getUserType());
    }

    @Test
    void getLogType() {
        assertEquals("communication", communicationLogRepositoryImpl.getLogs().get(0).getLogType());
    }

    @Test
    void getCommunicationType() {
        assertEquals("email", communicationLogRepositoryImpl.getLogs().get(0).getCommunicationType());
    }

    @Test
    void getActionTaken() {
        assertEquals("emailSent", communicationLogRepositoryImpl.getLogs().get(0).getActionTaken());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void getNotes() {
        assertEquals("testing123", communicationLogRepositoryImpl.getLogs().get(0).getNotes());
    }
    @Test
    void testGetLogs() {
        assertNotNull(communicationLogRepositoryImpl.getLogs());
    }
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAddEmailLog() {
        List<String> myListOfUsers = new ArrayList();
        myListOfUsers.add("1");
        communicationLogRepositoryImpl.addEmailLog(myListOfUsers, "testing123");
        assertEquals("testing123", communicationLogRepositoryImpl.getLogs().get(0).getNotes());
    }
}