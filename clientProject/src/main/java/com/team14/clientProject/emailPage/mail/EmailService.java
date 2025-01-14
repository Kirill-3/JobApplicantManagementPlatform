package com.team14.clientProject.emailPage.mail;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendHtmlMessageWithLogo(String to,
                                 String subject,
                                 String htmlBody,
                                 String logoPath)
            throws MessagingException;

    void sendHtmlMessageWithAttachment(String to,
                                       String subject,
                                       String htmlBody,
                                       byte[] pdfAttachment)
            throws MessagingException;

    void sendWarningEmail(String to,
                          String subject,
                          String htmlBody,
                          String logoPath)
            throws MessagingException;

}



