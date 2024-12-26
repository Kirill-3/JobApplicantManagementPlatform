package com.team14.clientProject.accountManagement;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

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

        String profilePicturePath = accountService.getProfilePicturePath(userId);
        String userName = userService.getUserNameById(userId);

        modelAndView.addObject("profilePicturePath", profilePicturePath);
        modelAndView.addObject("userName", userName);

        return modelAndView;
    }

    @PostMapping("/upload")
    public String uploadProfilePicture(MultipartFile file, HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        try {
            accountService.updateProfilePicture(file, userId);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/account?error=uploadFailed";
        }

        return "redirect:/account?success";
    }
}