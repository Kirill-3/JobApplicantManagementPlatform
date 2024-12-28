package com.team14.clientProject.accountManagement;

import com.team14.clientProject.adminPage.User;
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
        User user = userService.getUserById(userId);

        modelAndView.addObject("profilePicturePath", profilePicturePath);
        modelAndView.addObject("userName", userName);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @PostMapping("/upload")
    public String uploadProfilePicture(MultipartFile file, HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        try {
            if (file.getSize() > 2 * 1024 * 1024) {
                return "redirect:/account?error=uploadFailed";
            }
            if (!(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png"))) {
                return "redirect:/account?error=uploadFailed";
            }
            accountService.updateProfilePicture(file, userId);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/account?error=uploadFailed";
        }

        return "redirect:/account?success";
    }

    @PostMapping("/remove")
    public String removeProfilePicture(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        try {
            accountService.removeProfilePicture(userId);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/account?error=removeFailed";
        }
        return "redirect:/account?success";
    }
}