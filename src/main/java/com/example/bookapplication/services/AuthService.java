package com.example.bookapplication.services;

import com.example.bookapplication.entities.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthService {


    public User getAuthenticatedUser(HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("authenticationToken");
        token = "Bearer " + token;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", token);
        HttpEntity http = new HttpEntity(null, headers);
        ResponseEntity<User> user = template.exchange("http://localhost:8080/user", HttpMethod.GET, new HttpEntity<>(headers), User.class);
        return user.getBody();
    }


}
