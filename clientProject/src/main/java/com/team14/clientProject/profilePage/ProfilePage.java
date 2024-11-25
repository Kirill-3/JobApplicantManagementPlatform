package com.team14.clientProject.profilePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfilePage {
    //private ArrayList<String> profileList;
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
}

