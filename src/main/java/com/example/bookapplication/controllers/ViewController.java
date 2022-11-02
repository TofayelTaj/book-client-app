package com.example.bookapplication.controllers;

import com.example.bookapplication.entities.JwtResponse;
import com.example.bookapplication.entities.User;
import com.example.bookapplication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ViewController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        User user = new User();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    user.setEmail(cookie.getValue());
                }
                if (cookie.getName().equals("password")) {
                    user.setPassword(cookie.getValue());
                }
            }
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JwtResponse> response = restTemplate.exchange("http://localhost:8080/authenticate", HttpMethod.POST, new HttpEntity<>(user), JwtResponse.class);
        String authenticationToken = response.getBody().getJwtToken();
        if (!authenticationToken.isEmpty()) {
            request.getSession().setAttribute("authenticationToken", response.getBody().getJwtToken());
            return "redirect:/dashboard";
        }
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        User user = authService.getAuthenticatedUser(request);
        model.addAttribute("user", user);
        return "user-dashboard";
    }

}
