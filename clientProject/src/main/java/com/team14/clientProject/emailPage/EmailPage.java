package com.team14.clientProject.emailPage;

import com.team14.clientProject.emailPage.mail.EmailServiceHandler;
import com.team14.clientProject.loggingSystem.CommunicationLogRepositoryImpl;
import com.team14.clientProject.profilePage.Profile;
import com.team14.clientProject.profilePage.ProfilePageRepositoryImpl;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// Controller class to handle email-related requests.

@Controller
@RequestMapping("/email")
public class EmailPage {

    @Autowired
    private EmailServiceHandler emailServiceHandler;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ProfilePageRepositoryImpl profilePageRepository;
    @Autowired
    private CommunicationLogRepositoryImpl CommunicationLogRepository;
    private List<Profile> profileList;

    // Constructor to initialize the EmailPage with JdbcTemplate.
    public EmailPage(JdbcTemplate jdbcTemplate, ProfilePageRepositoryImpl profilePageRepository, CommunicationLogRepositoryImpl communicationLogRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.profilePageRepository = profilePageRepository != null ? profilePageRepository : new ProfilePageRepositoryImpl(jdbcTemplate);
        this.CommunicationLogRepository = communicationLogRepository;
        this.profileList = this.profilePageRepository.getProfiles();
    }


    // Handles GET requests to the /email endpoint
    @GetMapping
    public ModelAndView showEmailPage() {
        return new ModelAndView("email/emailPage");
    }

    // Handles GET requests to the /email/selector endpoint.
    @GetMapping("/selector")
    public ModelAndView redirectToAnotherPage() {
        ModelAndView modelAndView = new ModelAndView("email/selector");
        modelAndView.addObject("profileList", this.profileList);
        return modelAndView;
    }

    // Handles POST requests to the /email/sendEmails endpoint.
    // Sends emails to the selected email IDs.
    @PostMapping("/sendEmails")
    public ModelAndView sendEmails(@RequestParam("emailIds") List<String> emailIds) {
        ModelAndView modelAndView = new ModelAndView("email/selector");
        String subject = "Test Subject to Your Multiple Emails";
        String htmlBody = "<html><body><h1>An email has been sent to multiple email addresses.</h1><img src='cid:logo'></body></html>";
        String logoPath = "src/main/resources/static/images/dhcw.png";

        // Send emails and get alert messages
        String alertMessage = emailServiceHandler.sendEmails(emailIds, subject, htmlBody, logoPath);
        modelAndView.addObject("alertMessage", alertMessage);
        modelAndView.addObject("profileList", this.profileList);
        this.CommunicationLogRepository.addEmailLog(emailIds, subject);
        return modelAndView;
    }


    @GetMapping("/customEmails")
    public ModelAndView customComposePage() {
        ModelAndView modelAndView = new ModelAndView("email/customEmails");
        modelAndView.addObject("profileList", this.profileList);
        return modelAndView;
    }

    // Handle sending the custom email
    @PostMapping("/sendCustomEmails")
    public ModelAndView sendCustomEmails(
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            @RequestParam(value = "emailIds", required = false) List<String> emailIds) {

        ModelAndView modelAndView = new ModelAndView("email/customEmails");
        String logoPath = "src/main/resources/static/images/dhcw.png";

        if (emailIds == null || emailIds.isEmpty()) {
            modelAndView.addObject("alertMessage", "Please select at least one recipient.");
            modelAndView.addObject("profileList", this.profileList);
            return modelAndView;
        }

        try {
            String resultMessage = emailServiceHandler.sendEmails(emailIds, subject, message, logoPath);
            modelAndView.addObject("alertMessage", resultMessage);
            this.CommunicationLogRepository.addEmailLog(emailIds, subject);
        } catch (Exception e) {
            modelAndView.addObject("alertMessage", "Error sending emails: " + e.getMessage());
        }

        modelAndView.addObject("profileList", this.profileList);
        return modelAndView;
    }

}