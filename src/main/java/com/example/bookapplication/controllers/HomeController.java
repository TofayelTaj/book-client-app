package com.example.bookapplication.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/home";
//        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2ZheWVsQGdtYWlsLmNvbSIsImV4cCI6MTg0NjA2ODM2MywiaWF0IjoxNjY2MDY4MzYzfQ.Bvr9z-_WFWnoUf2Se88eDmqMKgfat3Nc0YumqjjrSweBYVMVgSqcXRAqSirNvXimCIjKqkWJJ8yGlDM3ssSIAA";
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("jwt", token);
//        HttpEntity<Object> entity = new HttpEntity<>(headers);
//        ResponseEntity<String> text = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        System.out.println(text);
        return "index";
    }
}
