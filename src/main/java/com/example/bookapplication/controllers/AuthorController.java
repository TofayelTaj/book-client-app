package com.example.bookapplication.controllers;

import com.example.bookapplication.entities.Author;
import com.example.bookapplication.entities.User;
import com.example.bookapplication.services.AuthService;
import com.example.bookapplication.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorController {

    @Autowired
    private AuthService authService;
    @Autowired
    private AuthorService authorService;

    @GetMapping("/author")
    public String authorEntryPage(HttpServletRequest request){
        User user = authService.getAuthenticatedUser(request);
        if(user.getRole().toString().equalsIgnoreCase("admin")){
            return "author-entry";
        }
        return "error";
    }

    @PostMapping("/author")
    public String saveAuthor(@ModelAttribute Author author, Model model){
        Author savedAuthor = authorService.saveAuthor(author);
        if(savedAuthor != null){
            model.addAttribute("authorMsg", "Author Save Successfully");
        }
        return "author-entry";
    }
}
