package com.example.bookapplication.controllers;

import com.example.bookapplication.entities.Book;
import com.example.bookapplication.entities.User;
import com.example.bookapplication.services.AuthService;
import com.example.bookapplication.services.AuthorService;
import com.example.bookapplication.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private AuthService authService;

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @GetMapping("/books")
    public String getAllBooks(HttpServletRequest request, Model model) {
        User user = authService.getAuthenticatedUser(request);
        if (user == null) {
            return "error";
        }
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("bookList", bookList);
        model.addAttribute("user", user);
        return "book-list";
    }

    @GetMapping("/add-book")
    public String addBookPage(HttpServletRequest request, Model model) {
        User user = authService.getAuthenticatedUser(request);
        if (user.getRole().toString().equalsIgnoreCase("admin")) {
            model.addAttribute("authorList", authorService.getAllAuthor());
            return "book-entry";
        }
        return "error";
    }

    @PostMapping("/add-book")
    public String addBook(HttpServletRequest request, @ModelAttribute Book book, Model model) {
        User user = authService.getAuthenticatedUser(request);
        if (user.getRole().toString().equalsIgnoreCase("admin")) {
            if (bookService.addNewBook(book) != null) {
                model.addAttribute("bookMsg", "New Book Added");
            }
            return "book-entry";
        }
        return "error";
    }

    @GetMapping("/delete/{id}")
    public String deleteBookById(@PathVariable int id, Model model) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}
