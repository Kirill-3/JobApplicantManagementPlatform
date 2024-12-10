package com.team14.clientProject.AutomaticDeletion;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.team14.clientProject.automaticDeletion.AutomaticDeletion;
import com.team14.clientProject.emailPage.mail.EmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;


import java.time.LocalDateTime;
import java.util.Collections;

public class AutomaticDeletionTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private AutomaticDeletion automaticDeletion;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void CheckNoApplicantsToWarn() throws MessagingException, InterruptedException {
        when(jdbcTemplate.queryForList(anyString(), eq(String.class), any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());

        automaticDeletion.checkApplicants();

        verify(emailService, never()).sendWarningEmail(anyString(), anyString(), anyString(), anyString());
        verify(jdbcTemplate, never()).update(anyString(), any(LocalDateTime.class));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void CheckApplicantsToWorn() throws MessagingException, InterruptedException {
        when(jdbcTemplate.queryForList(anyString(), eq(String.class), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList("test@example.com"));

        automaticDeletion.checkApplicants();

        verify(emailService, times(1)).sendWarningEmail(eq("test@example.com"), anyString(), anyString(), anyString());
        verify(jdbcTemplate, times(1)).update(contains("INSERT INTO deletedApplicants"), any(LocalDateTime.class));
        verify(jdbcTemplate, times(1)).update(contains("DELETE FROM applicants"), any(LocalDateTime.class));
    }
}