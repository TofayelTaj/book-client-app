package com.example.bookapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

}
