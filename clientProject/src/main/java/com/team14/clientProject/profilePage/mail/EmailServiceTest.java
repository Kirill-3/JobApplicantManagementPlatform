package com.team14.clientProject.profilePage.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceTest implements CommandLineRunner {

    @Autowired
    private EmailService emailService;

    @Override
    public void run(String... args) throws Exception {
        emailService.sendSimpleMessage(
                "kirill.akbulatov@pm.me",
                "Test Subject",
                "This is a test email."
        );
    }
}