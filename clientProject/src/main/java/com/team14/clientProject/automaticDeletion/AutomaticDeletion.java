package com.team14.clientProject.automaticDeletion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AutomaticDeletion {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(cron = "* * * * * ?") // Runs every second
    public void checkApplicants() {
        LocalDateTime thirtySecondsAgo = LocalDateTime.now().minusSeconds(30);
        LocalDateTime fifteenSecondsAgo = LocalDateTime.now().minusSeconds(15);
        LocalDateTime rangeStart = fifteenSecondsAgo.minusSeconds(15).minusSeconds(1);
        LocalDateTime rangeEnd = fifteenSecondsAgo.plusSeconds(1);

        String warnSql = "SELECT email FROM applicants WHERE createdAt BETWEEN ? AND ? LIMIT 1";
        List<String> emailsToWarn = jdbcTemplate.queryForList(warnSql, String.class, rangeStart, rangeEnd);

        System.out.println("Range Start: " + rangeStart);
        System.out.println("Range End: " + rangeEnd);
        System.out.println("Emails to warn: " + emailsToWarn);

        if (!emailsToWarn.isEmpty()) {
            sendWarningEmail(emailsToWarn.get(0));
            System.out.println("Warning email sent to: " + emailsToWarn.get(0));
        } else {
            System.out.println("No email to warn in the given range.");
        }

        String moveSql = "INSERT INTO deletedApplicants (id, firstName, lastName, location, email, phoneNumber, currentPosition, status, skill, eventAttended, SubscribeToNewsLetter, SubscribeToBulletins, SubscribeToJobUpdates) " +
                "SELECT a.id, a.firstName, a.lastName, a.location, a.email, a.phoneNumber, " +
                "d.currentPosition, d.status, a.skill, a.eventAttended, " +
                "p.SubscribeToNewsLetter, p.SubscribeToBulletins, p.SubscribeToJobUpdates " +
                "FROM applicants a " +
                "LEFT JOIN applicantpreferences p ON a.Id = p.applicationId " +
                "LEFT JOIN applicationdetails d ON a.Id = d.applicationId " +
                "WHERE a.createdAt = ?";
        int rowsMoved = jdbcTemplate.update(moveSql, thirtySecondsAgo);
        System.out.println("Rows moved to deletedApplicants: " + rowsMoved);

        String deleteSql = "DELETE FROM applicants WHERE createdAt = ?";
        int rowsDeleted = jdbcTemplate.update(deleteSql, thirtySecondsAgo);
        System.out.println("Rows deleted from applicants: " + rowsDeleted);
    }

    private void sendWarningEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Warning: Your information will be deleted soon");
        message.setText("Your information will be deleted in 15 seconds. Please take any necessary actions.");
        mailSender.send(message);
    }
}