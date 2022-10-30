package com.example.bookapplication.services;

import com.example.bookapplication.entities.Book;
import com.example.bookapplication.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addNewBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public boolean deleteBookById(int id){
        Book book = findBookById(id);
        if(book == null){
            return false;
        }
        bookRepository.delete(book);
        return true;
    }

    public Book findBookById(int id){
        Book book = bookRepository.findBookById(id);
        return book;
    }
}
