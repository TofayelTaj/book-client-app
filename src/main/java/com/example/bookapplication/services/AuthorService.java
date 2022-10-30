package com.example.bookapplication.services;

import com.example.bookapplication.entities.Author;
import com.example.bookapplication.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }
}
