package com.team14.clientProject.addApplicant;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OptionController {

    // Mapping for the option page
    @GetMapping("/add-applicant")
    public ModelAndView displayPage() {
        ModelAndView modelAndView = new ModelAndView("addApplicant/optionPage");
        return modelAndView;
    }
}
