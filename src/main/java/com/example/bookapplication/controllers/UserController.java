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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JwtResponse> response = restTemplate.exchange("http://localhost:8080/authenticate", HttpMethod.POST, new HttpEntity<>(user), JwtResponse.class);

        String authenticationToken = response.getBody().getJwtToken();
        if (!authenticationToken.isEmpty()) {
            request.getSession().setAttribute("authenticationToken", authenticationToken);
            return "redirect:/dashboard";
        }
        model.addAttribute("message", response.getBody().getMessage());
        return "login";
    }

    @GetMapping("/oauthLogin")
    public String oAuthLogin(HttpServletRequest request, @RequestParam("token") String token){
        System.out.println("token : " + token);
        if(token != null){
            request.getSession().setAttribute("authenticationToken", token);
            return "redirect:/dashboard";
        }
        return "error";
    }

    @PostMapping("/signup")
    public String signupNewUser(@ModelAttribute User user, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange("http://localhost:8080/user", HttpMethod.POST, new HttpEntity<>(user), String.class);
            model.addAttribute("message", "Signup Success");
        } catch (Exception e) {
            model.addAttribute("message", "Signup Unsuccessful");
        }
        return "signup";
    }

    @GetMapping("/profile")
    public String userProfilePage(HttpServletRequest request, Model model) {

        User user = authService.getAuthenticatedUser(request);
        model.addAttribute("user", user);
        return "user-profile";
    }
    @GetMapping("/logout")
    public String logout(Model model, HttpServletRequest request) {
        request.getSession().removeAttribute("authenticationToken");
        model.addAttribute("logoutMsg", "logout success");
        return "index";
    }

}
