package com.team14.clientProject.accountManagement;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView getAccountPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("accountManagement/account");

        int userId = (int) request.getSession().getAttribute("userId");

        String profilePicturePath = accountService.getProfilePicturePath();
        String userName = userService.getUserNameById(userId);

        modelAndView.addObject("profilePicturePath", profilePicturePath);
        modelAndView.addObject("userName", userName);

        return modelAndView;
    }
}
