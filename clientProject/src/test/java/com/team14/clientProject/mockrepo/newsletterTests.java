package com.team14.clientProject.mockrepo;

import com.team14.clientProject.emailPage.EmailPage;
import com.team14.clientProject.emailPage.mail.EmailServiceHandler;
import com.team14.clientProject.profilePage.ProfilePageRepositoryImpl;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class newsletterTests {

    @Mock
    private EmailServiceHandler emailServiceHandler;

    @Mock
    private ProfilePageRepositoryImpl profilePageRepository;

    @InjectMocks
    private EmailPage emailPage;

    private List<String> subscribedEmails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        subscribedEmails = Arrays.asList("subscriber1@example.com", "subscriber2@example.com");

        when(profilePageRepository.getSubscribedEmails()).thenReturn(subscribedEmails);

        try {
            Field emailServiceHandlerField = EmailPage.class.getDeclaredField("emailServiceHandler");
            emailServiceHandlerField.setAccessible(true);
            emailServiceHandlerField.set(emailPage, emailServiceHandler);
        } catch (Exception e) {
            fail("Failed to inject dependencies: " + e.getMessage());
        }
    }


    // Test for showing the newsletter page
    @Test
    public void testShowNewsletterPage() {
        // Test the GET mapping for showing the newsletter page
        ModelAndView result = emailPage.showNewsletterPage();

        assertEquals("email/Newsletter", result.getViewName());
    }


// Test for an Invalid File Type
    @Test
    public void testSendNewsletter_InvalidFileType() {
        MockMultipartFile invalidFile = new MockMultipartFile("newsletterFile", "newsletter.txt", "text/plain", "Text content".getBytes());

        ModelAndView result = emailPage.sendNewsletter("Newsletter Subject", invalidFile);

        assertEquals("email/Newsletter", result.getViewName());
        assertTrue(result.getModel().containsKey("alertMessage"));
        assertEquals("Please upload a valid PDF file.", result.getModel().get("alertMessage"));
    }


    // Test for no subscribers
    @Test
    public void testSendNewsletter_NoSubscribers() throws IOException {
        // Simulate no subscribers
        when(profilePageRepository.getSubscribedEmails()).thenReturn(Collections.emptyList());

        // Simulate a valid PDF file upload
        MockMultipartFile pdfFile = new MockMultipartFile("newsletterFile", "newsletter.pdf", "application/pdf", "PDF content".getBytes());

        ModelAndView result = emailPage.sendNewsletter("Newsletter Subject", pdfFile);

        assertEquals("email/Newsletter", result.getViewName());
        assertTrue(result.getModel().containsKey("alertMessage"));
        assertEquals("No subscribers for the newsletter.", result.getModel().get("alertMessage"));
    }


    //test for an empty subject
    @Test
    public void testSendNewsletter_EmptySubject() throws IOException {
        MockMultipartFile pdfFile = new MockMultipartFile("newsletterFile", "newsletter.pdf", "application/pdf", "PDF content".getBytes());

        ModelAndView result = emailPage.sendNewsletter("", pdfFile);

        assertEquals("email/Newsletter", result.getViewName());
        assertTrue(result.getModel().containsKey("alertMessage"));
        assertEquals("Subject cannot be empty.", result.getModel().get("alertMessage"));
    }


    //test for an empty file
    @Test
    public void testSendNewsletter_EmptyFile() throws IOException {
        MockMultipartFile emptyFile = new MockMultipartFile("newsletterFile", "", "application/pdf", new byte[0]);

        ModelAndView result = emailPage.sendNewsletter("Newsletter Subject", emptyFile);

        assertEquals("email/Newsletter", result.getViewName());
        assertTrue(result.getModel().containsKey("alertMessage"));
        assertEquals("Please upload a valid PDF file.", result.getModel().get("alertMessage"));
    }


    // Test for when there is an invalid file extension
    @Test
    public void testSendNewsletter_InvalidFileExtension() throws IOException {
        MockMultipartFile invalidFile = new MockMultipartFile("newsletterFile", "newsletter.docx", "application/msword", "Word content".getBytes());

        ModelAndView result = emailPage.sendNewsletter("Newsletter Subject", invalidFile);

        assertEquals("email/Newsletter", result.getViewName());
        assertTrue(result.getModel().containsKey("alertMessage"));
        assertEquals("Please upload a valid PDF file.", result.getModel().get("alertMessage"));
    }


    // Test for when the email service throws a MessagingException
    @Test
    public void testSendNewsletter_MessagingException() throws IOException {
        MockMultipartFile pdfFile = new MockMultipartFile("newsletterFile", "newsletter.pdf", "application/pdf", "PDF content".getBytes());

        try {

            doThrow(new MessagingException("SMTP server not reachable")).when(emailServiceHandler)
                    .sendBulkNewsletterEmails(subscribedEmails, "Newsletter Subject", "<html><body><h1>Newsletter</h1><p>Please find the attached newsletter PDF.</p></body></html>", pdfFile.getBytes());

            ModelAndView result = emailPage.sendNewsletter("Newsletter Subject", pdfFile);

            assertEquals("email/Newsletter", result.getViewName());
            assertTrue(result.getModel().containsKey("alertMessage"));
            assertEquals("Error sending newsletter: SMTP server not reachable", result.getModel().get("alertMessage"));
        } catch (MessagingException e) {
            fail("Exception should be handled within the method");
        }
    }


    @Test
    public void testSendNewsletter_NullFile() {
        ModelAndView result = emailPage.sendNewsletter("Newsletter Subject", null);

        assertEquals("email/Newsletter", result.getViewName());
        assertTrue(result.getModel().containsKey("alertMessage"));
        assertEquals("Please upload a valid PDF file.", result.getModel().get("alertMessage"));
    }


}
