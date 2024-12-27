package com.team14.clientProject.accountManagement;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.RequestAttributes;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private AccountService accountService;

    @ModelAttribute
    public void addProfilePicturePath(WebRequest webRequest, HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
            String profilePicturePath = accountService.getProfilePicturePath(userId);
            webRequest.setAttribute("profilePicturePath", profilePicturePath, RequestAttributes.SCOPE_REQUEST);
    }
}