package com.team14.clientProject.profilePage.mail;
import java.io.IOException;
import java.util.Map;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);
}
