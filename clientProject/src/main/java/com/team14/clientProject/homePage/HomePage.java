package com.team14.clientProject.homePage;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomePage {
    @GetMapping("/home")
    public ModelAndView displayMenu() {
        ModelAndView modelAndView = new ModelAndView("home/homePage");
        List<Applicants> applicants = new ArrayList<>();
        applicants.add(new Applicants("Freddy", "Role 3", "Cardiff", "Teamwork"));
        applicants.add(new Applicants("Mohammed", "Role 1", "Cardiff", "Teamwork"));
        applicants.add(new Applicants("Kirill", "Role 2", "Cardiff", "Teamwork"));
        applicants.add(new Applicants("Ayush", "Role 3", "Cardiff", "Teamwork"));
        applicants.add(new Applicants("Oscar", "Role 3", "Cardiff", "Teamwork"));

        modelAndView.addObject("applicants", applicants);
        return modelAndView;
    }

    @GetMapping("/menu/profile")
    public ModelAndView displayProfile() {
        ModelAndView modelAndView = new ModelAndView("profilePage");
        return modelAndView;
    }

}
