package com.team14.clientProject.profilePage;

import com.team14.clientProject.profilePage.mail.EmailService;
import com.team14.clientProject.profilePage.mail.EmailValidation;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfilePage {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ProfilePageRepositoryImpl profilePageRepository;
    private List<Profile> profileList;

    public ProfilePage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.profilePageRepository = new ProfilePageRepositoryImpl(jdbcTemplate);
        this.profileList = profilePageRepository.getProfiles();
    }

    @GetMapping()
    public ModelAndView profilePage() {
        ModelAndView modelAndView = new ModelAndView("profilePage");
        modelAndView.addObject("profileList", this.profileList);
        return modelAndView;
    }

    @GetMapping("/{userId}")
    public ModelAndView profilePage(@PathVariable Integer userId) {
        ModelAndView modelAndView = new ModelAndView("profilePage");
        if (this.profilePageRepository.getProfileById(userId) == null) {
            //throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "User not found");
            return new ModelAndView("redirect:/profile");
        } else {
            Profile profile = this.profilePageRepository.getProfileById(userId);
            modelAndView.addObject("profile", profile);
            return modelAndView;
        }
    }

    @PostMapping("/sendEmail/{userID}")
    public ModelAndView sendEmail(@PathVariable String userID) throws MessagingException {
        // Query to fetch the email address of the applicant based on userID
        String query = "SELECT email FROM applicants WHERE id = ?";
        String emailAddress = jdbcTemplate.queryForObject(query, new Object[]{userID}, String.class);

        // Initialize the ModelAndView object with the profilePage view
        ModelAndView modelAndView = new ModelAndView("profilePage");

        // Check if the email address is null and set an alert message if true
        if (emailAddress == null) {
            modelAndView.addObject("alertMessage", "Email not found for user ID " + userID);
            return modelAndView;
        }

        // Define the regex pattern for email validation
        String regexPattern = "^[a-zA-Z0-9_!#$%&*+/=?`{}~^.-]+@[a-zA-Z0-9.-]+$";
        // Validate the email address format and set an alert message if invalid
        if (!EmailValidation.patternMatches(emailAddress, regexPattern)) {
            modelAndView.addObject("alertMessage", "Invalid email format for user ID " + userID);
            return modelAndView;
        }

        // Define the email subject and HTML body content
        String subject = "Test Subject to Your Specific Email";
        String htmlBody = "<html><body><h1>An email has been sent to your specific email address. </h1><img src='cid:logo'></body></html>";
        String logoPath = "src/main/resources/static/images/dhcw.png";

        // Send the email with the specified subject, body, and logo
        emailService.sendHtmlMessageWithLogo(emailAddress, subject, htmlBody, logoPath);
        // Set a success alert message
        modelAndView.addObject("alertMessage", "Email sent successfully to " + emailAddress);
        return modelAndView;
    }
}

