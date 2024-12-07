package com.team14.clientProject.adminPage;

import com.team14.clientProject.loggingSystem.SystemLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.team14.clientProject.loggingSystem.SystemLogRepositoryImpl;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private SystemLogRepositoryImpl systemLogRepository;

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public ModelAndView getAdminPage() {
        ModelAndView modelAndView = new ModelAndView("admin/admin");
        List<User> users = adminService.getAllUsers();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/admin/user/{id}")
    public ModelAndView getUserDetailsPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("admin/userDetails");
        User user = adminService.getUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/admin/add")
    public ModelAndView addNewUsers() {
        ModelAndView modelAndView = new ModelAndView("/admin/addNewUser");
        modelAndView.addObject("user", new User());
        this.systemLogRepository.addUserLog();
        return modelAndView;
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            adminService.addUser(user);
            redirectAttributes.addFlashAttribute("success", "User added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add user: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            this.systemLogRepository.removeUserLog(id);
            adminService.deleteUser(id);
            redirectAttributes.addFlashAttribute("success", "User deleted successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete user: " + e.getMessage());
        }
        return "redirect:/admin";
    }
}
