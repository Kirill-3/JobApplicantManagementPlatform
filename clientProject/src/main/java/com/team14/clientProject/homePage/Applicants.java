package com.team14.clientProject.homePage;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Applicants {
    private int id;
    private String firstName;
    private String lastName;
    private String location;
    private String email;
    private String phoneNumber;
    private String eventAttended;
    private String skill;
}

