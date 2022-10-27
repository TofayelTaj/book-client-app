package com.example.bookapplication.controllers;

import com.example.bookapplication.entities.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/app-user")
public class AppUserController {

    @GetMapping("/profile")
    public String userProfilePage(HttpServletRequest request, Model model) {

        String token = (String) request.getSession().getAttribute("authenticationToken");
        String uri = request.getServletPath();
        token = "Bearer " + token;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", token);
        headers.add("requestPath", uri);
        HttpEntity http = new HttpEntity(null, headers);
        ResponseEntity<User> user = template.exchange("http://localhost:8080/app-user/user", HttpMethod.GET, new HttpEntity<>(headers), User.class);
        model.addAttribute("user", user.getBody());
        return "user-profile";
    }
}
