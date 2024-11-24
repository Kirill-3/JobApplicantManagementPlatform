
package com.team14.clientProject.profilePage.mail;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceTest implements CommandLineRunner {

    @Autowired
    private EmailService emailService;

    @Override
    public void run(String... args) throws Exception {
        try {
            String emailAddress = "kirill.akbulatov@pm.me";
            String subject = "Test Subject with Logo";
            String htmlBody = "<html><body><h1>This is a test email with a logo.</h1><img src='cid:logo'></body></html>";
            String logoPath = "src/main/resources/static/images/dhcw.png";
            emailService.sendHtmlMessageWithLogo(emailAddress, subject, htmlBody, logoPath);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
