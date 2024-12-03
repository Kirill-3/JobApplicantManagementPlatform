package com.team14.clientProject.addApplicant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddApplicantController {

    private AddApplicantService addApplicantService;

    public AddApplicantController(AddApplicantService addApplicantService) {
        this.addApplicantService = addApplicantService;
    }

    @GetMapping("/add-applicant/manual")
    public ModelAndView displayPage() {
        ModelAndView modelAndView = new ModelAndView("addApplicant/addApplicantPage");
        AddApplicantForm emptyApplicant = new AddApplicantForm();
        modelAndView.addObject("applicants", emptyApplicant);
        return modelAndView;
    }

    @PostMapping("/add-applicant/manual")
    public ModelAndView addApplicant(@ModelAttribute AddApplicantForm applicants) {
        ModelAndView modelAndView = new ModelAndView("addApplicant/addApplicantPage");
//        try {
//            addApplicantService.addApplicant(applicants);
//            modelAndView.addObject("added", true);
//            modelAndView.addObject("applicants", applicants);
//        }
//        catch (IllegalArgumentException e) {
//            System.out.println("test");
//            modelAndView.addObject("errorEmail", true);
//            modelAndView.addObject("applicants", applicants);
//        }

        String function = addApplicantService.addApplicant(applicants);
        if(function.equals("true")) {
            modelAndView.addObject("added", true);
            modelAndView.addObject("applicants", applicants);
        }
        else {
            modelAndView.addObject("errorEmail", true);
            modelAndView.addObject("applicants", applicants);
        }

        return modelAndView;


    }
}
