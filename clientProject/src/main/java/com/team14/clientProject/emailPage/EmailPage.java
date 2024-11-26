package com.team14.clientProject.emailPage;


import com.team14.clientProject.emailPage.mail.EmailService;
import com.team14.clientProject.emailPage.mail.EmailValidation;
import com.team14.clientProject.profilePage.Profile;
import com.team14.clientProject.profilePage.ProfilePageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.mail.MessagingException;

import java.util.List;

@Controller
@RequestMapping("/email")
public class EmailPage {

    @Autowired
    EmailService emailService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ProfilePageRepositoryImpl profilePageRepository;
    private List<Profile> profileList;

    public EmailPage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.profilePageRepository = new ProfilePageRepositoryImpl(jdbcTemplate);
        this.profileList = profilePageRepository.getProfiles();
    }

    @GetMapping
    public ModelAndView showEmailPage() {
        return new ModelAndView("email/emailPage");
    }

    @GetMapping("/selector")
    public ModelAndView redirectToAnotherPage() {
        ModelAndView modelAndView = new ModelAndView("email/selector");
        modelAndView.addObject("profileList", this.profileList);
        return modelAndView;
    }

    @PostMapping("/sendEmails")
    public ModelAndView sendEmails(@RequestParam("emailIds") List<String> emailIds) {
        ModelAndView modelAndView = new ModelAndView("email/selector");
        String subject = "Test Subject to Your Specific Email";
        String htmlBody = "<html><body><h1>An email has been sent to your specific email address.</h1><img src='cid:logo'></body></html>";
        String logoPath = "src/main/resources/static/images/dhcw.png";
        StringBuilder alertMessages = new StringBuilder();

        for (String emailId : emailIds) {
            int profileId = Integer.parseInt(emailId);
            Profile profile = profilePageRepository.getProfileById(profileId);
            if (profile != null) {
                String emailAddress = profile.getEmail();
                if (emailAddress == null || emailAddress.isEmpty()) {
                    alertMessages.append("Email address is empty for user ID ").append(profileId).append(". ");
                    continue;
                }
                String regexPattern = "^[a-zA-Z0-9_!#$%&*+/=?`{}~^.-]+@[a-zA-Z0-9.-]+$";
                if (!EmailValidation.patternMatches(emailAddress, regexPattern)) {
                    alertMessages.append("Invalid email format for user ID ").append(profileId).append(". ");
                    continue;
                }
                try {
                    emailService.sendHtmlMessageWithLogo(emailAddress, subject, htmlBody, logoPath);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    alertMessages.append("Failed to send email to user ID ").append(profileId).append(". ");
                }
            }
        }

        if (alertMessages.length() > 0) {
            modelAndView.addObject("alertMessage", alertMessages.toString());
        } else {
            modelAndView.addObject("alertMessage", "Emails sent successfully to selected addresses.");
        }
        modelAndView.addObject("profileList", this.profileList);
        return modelAndView;
    }
}

