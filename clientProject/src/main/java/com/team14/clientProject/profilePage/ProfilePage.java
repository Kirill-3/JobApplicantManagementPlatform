package com.team14.clientProject.profilePage;
import com.team14.clientProject.profilePage.mail.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfilePage {
    //private ArrayList<String> profileList;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmailService emailService;

    public static ArrayList createProfiles() {
        ArrayList<String> profileList = new ArrayList<>();
        profileList.add("Freddy");
        profileList.add("Kirill");
        profileList.add("Ayush");
        profileList.add("Mohammed");
        profileList.add("Oscar");
        return profileList;
    }

    @GetMapping()
    public ModelAndView profilePage() {
        ProfilePageRepositoryImpl profilePageRepository = new ProfilePageRepositoryImpl(jdbcTemplate);
        List<Profile> profileList = profilePageRepository.getProfiles();
        ModelAndView modelAndView = new ModelAndView("profilePage");
        modelAndView.addObject("profileList", profileList);
        return modelAndView;
    }

    @GetMapping("/{userID}")
    public ModelAndView profilePage(@PathVariable String userID) {
        ModelAndView modelAndView = new ModelAndView("profilePage");
        modelAndView.addObject("userID", userID);
        return modelAndView;
    }

    @PostMapping("/sendEmail")
    public String sendEmail() throws MessagingException {
        String emailAddress = "kirill.spam1@gmail.com";
        String subject = "Test Subject from Button";
        String htmlBody = "<html><body><h1>An email has been sent by clicking the email button. </h1><img src='cid:logo'></body></html>";
        String logoPath = "src/main/resources/static/images/dhcw.png";
        emailService.sendHtmlMessageWithLogo(emailAddress, subject, htmlBody, logoPath);
        return "redirect:/profile";
    }

}
