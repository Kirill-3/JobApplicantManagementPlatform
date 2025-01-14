package com.team14.clientProject.addApplicant;

import jakarta.validation.Valid;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    public ModelAndView addApplicant(@Valid @ModelAttribute AddApplicantForm applicants, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("addApplicant/addApplicantPage");
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("bindingError", true);
            modelAndView.addObject("applicants", applicants);
        }
        else {
            // String to check for any validation errors
            String function = addApplicantService.addApplicant(applicants);
            // If no errors, display a success message
            if (function.equals("true")) {
                modelAndView.addObject("added", true);
                modelAndView.addObject("applicants", applicants);
            }
            // If an error occurs, display error messages for each issue
            else if (function.equals("emailFalse")){
                modelAndView.addObject("errorEmail", true);
                modelAndView.addObject("applicants", applicants);
            }
            else if (function.equals("phoneFalse")){
                modelAndView.addObject("errorPhone", true);
                modelAndView.addObject("applicants", applicants);
            }
            else if (function.equals("phoneFalse")){
                modelAndView.addObject("errorPhone", true);
                modelAndView.addObject("applicants", applicants);
            }
        }

        return modelAndView;




    }

    @GetMapping("/add-applicant/csv")
    public ModelAndView displayCsvPage() {
        ModelAndView modelAndView = new ModelAndView("addApplicant/addCsvPage");
        return modelAndView;
    }






    @PostMapping ("/add-applicant/csv")
    public ModelAndView addApplicantByCsv(@RequestParam("csv") MultipartFile csv) {
        ModelAndView modelAndView = new ModelAndView("addApplicant/addCsvPage");
        String function = addApplicantService.csvFunction(csv);

        if (function.equals("true")) {
            modelAndView.addObject("csvSuccess", true);
        }

        else if(function.equals("ioException")) {
            modelAndView.addObject("ioException", true);
        }

        else if(function.equals("duplicateKeyException")) {
            modelAndView.addObject("duplicateKeyException", true);
        }
        else if(function.equals("invalidFile")) {
            modelAndView.addObject("invalidFile", true);
        }

        return modelAndView;

    }
}
