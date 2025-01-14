package com.team14.clientProject.mockrepo;

import com.team14.clientProject.profilePage.ProfilePage;
import com.team14.clientProject.emailPage.mail.EmailService;
import com.team14.clientProject.emailPage.mail.EmailValidation;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmailConfirmationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ProfilePage profilePage;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        profilePage = new ProfilePage(jdbcTemplate);
        Field emailServiceField = ProfilePage.class.getDeclaredField("emailService");
        emailServiceField.setAccessible(true);
        emailServiceField.set(profilePage, emailService);
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

    @Test
    public void testRFC5322Validation() {
        String emailAddress = "username'@domain.com";
        String regexPattern = "^[a-zA-Z0-9_!#$%&*+/=?`{}~^.-]+@[a-zA-Z0-9.-]+$";
        assertFalse(EmailValidation.patternMatches(emailAddress, regexPattern));
    }
    @Test
    public void testSendEmailInvalidRFC5322Format() throws MessagingException {
        String userID = "1";
        String invalidEmail = "username'@domain.com";
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenReturn(invalidEmail);

        ModelAndView result = profilePage.sendEmail(userID);

        verify(emailService, times(0)).sendHtmlMessageWithLogo(anyString(), anyString(), anyString(), anyString());
        assertEquals("profilePage", result.getViewName());
        assertTrue(result.getModel().containsKey("alertMessage"));
        assertEquals("Invalid email format for user ID " + userID, result.getModel().get("alertMessage"));
    }

    @Test
    public void testSendEmailInvalidEmailFormat() throws MessagingException {
        String userID = "1";
        String email = "invalid-email-format";
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenReturn(email);

        ModelAndView result = profilePage.sendEmail(userID);

        assertEquals("profilePage", result.getViewName());
        assertTrue(result.getModel().containsKey("alertMessage"));
        assertEquals("Invalid email format for user ID " + userID, result.getModel().get("alertMessage"));
    }

    @Test
    public void testEmailServiceSendHtmlMessageWithLogo() throws MessagingException {
        String to = "test@example.com";
        String subject = "Test Subject";
        String htmlBody = "<h1>Test</h1>";
        String logoPath = "path/to/logo.png";

        doNothing().when(emailService).sendHtmlMessageWithLogo(to, subject, htmlBody, logoPath);

        emailService.sendHtmlMessageWithLogo(to, subject, htmlBody, logoPath);

        verify(emailService, times(1)).sendHtmlMessageWithLogo(to, subject, htmlBody, logoPath);
    }
}