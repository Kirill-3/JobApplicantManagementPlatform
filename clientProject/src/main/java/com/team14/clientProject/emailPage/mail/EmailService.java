package com.team14.clientProject.emailPage.mail;
import java.io.IOException;
import java.util.Map;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendHtmlMessageWithLogo(String to,
                                 String subject,
                                 String htmlBody,
                                 String logoPath)
            throws MessagingException;
}
