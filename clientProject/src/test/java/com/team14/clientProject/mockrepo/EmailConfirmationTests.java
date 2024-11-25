package com.team14.clientProject.mockrepo;

import com.team14.clientProject.profilePage.ProfilePage;
import com.team14.clientProject.profilePage.mail.EmailService;
import com.team14.clientProject.profilePage.mail.EmailValidation;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Code adapted from https://javanexus.com/blog/handling-email-testing-spring-boot
// and https://www.baeldung.com/mockito-annotations

public class EmailConfirmationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ProfilePage profilePage;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendEmailSuccess() throws MessagingException {
        String userID = "1";
        String email = "test@example.com";
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenReturn(email);

        ModelAndView result = profilePage.sendEmail(userID);

        verify(emailService, times(1)).sendHtmlMessageWithLogo(eq(email), anyString(), anyString(), anyString());
    }

    @Test
    public void testSendEmailEmailNotFound() throws MessagingException {
        String userID = "1";
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenReturn(null);

        ModelAndView result = profilePage.sendEmail(userID);

        assertEquals("profilePage", result.getViewName());
        assertTrue(result.getModel().containsKey("alertMessage"));
        assertEquals("Email not found for user ID " + userID, result.getModel().get("alertMessage"));
    }

    //Code obtained from https://www.baeldung.com/java-email-validation-regex
    //Testing the email validation regex pattern - RFC 5322 - Does not allow the pipe character (|) and single quote (‘)
    @Test
    public void testRFC5322Validation() {
        String emailAddress = "username'@domain.com";
        String regexPattern = "^[a-zA-Z0-9_!#$%&*+/=?`{}~^.-]+@[a-zA-Z0-9.-]+$";
        assertFalse(EmailValidation.patternMatches(emailAddress, regexPattern));
    }
}