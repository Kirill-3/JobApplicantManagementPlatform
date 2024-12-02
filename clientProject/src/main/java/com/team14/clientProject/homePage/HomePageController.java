package com.team14.clientProject.homePage;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class HomePageController {
    private HomePageService homePageService;
    public HomePageController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }
    @GetMapping("/home")
    public ModelAndView displayTable() {
        ModelAndView modelAndView = new ModelAndView("home/homePage");

        List applicants = homePageService.get10MostRecentProfiles();
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
