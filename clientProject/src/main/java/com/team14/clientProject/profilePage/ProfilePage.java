package com.team14.clientProject.profilePage;
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
        ProfilePageRepositoryImpl profilePageRepository = new ProfilePageRepositoryImpl(jdbcTemplate);
        Profile profile = profilePageRepository.getProfileById(Integer.parseInt(userID));
        ModelAndView modelAndView = new ModelAndView("profilePage");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }
}

