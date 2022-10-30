package com.example.bookapplication.controllers;

import com.example.bookapplication.entities.User;
import com.example.bookapplication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        User user = authService.getAuthenticatedUser(request);
        model.addAttribute("user", user);
        return "user-dashboard";
    }
}
