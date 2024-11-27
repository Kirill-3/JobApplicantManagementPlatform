package com.team14.clientProject.homePage;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class HomePageController {
    private HomePageRepository homePageRepository;
    public HomePageController(HomePageRepository homePageRepository) {
        this.homePageRepository = homePageRepository;
    }
    @GetMapping("/home")
    public ModelAndView displayTable() {
        ModelAndView modelAndView = new ModelAndView("home/homePage");
//        List<Applicants> applicants = new ArrayList<>();
//        applicants.add(new Applicants("Freddy", "Role 3", "Cardiff", "Teamwork"));
//        applicants.add(new Applicants("Mohammed", "Role 1", "Cardiff", "Teamwork"));
//        applicants.add(new Applicants("Kirill", "Role 2", "Cardiff", "Teamwork"));
//        applicants.add(new Applicants("Ayush", "Role 3", "Cardiff", "Teamwork"));
//        applicants.add(new Applicants("Oscar", "Role 3", "Cardiff", "Teamwork"));

        List applicants = homePageRepository.get10MostRecentProfiles();
        System.out.println(applicants);

        modelAndView.addObject("applicants", applicants);
        return modelAndView;
    }

    @GetMapping("/menu/profile")
    public ModelAndView displayProfile() {
        ModelAndView modelAndView = new ModelAndView("profilePage");
        return modelAndView;
    }

}
