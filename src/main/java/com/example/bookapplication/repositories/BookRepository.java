package com.example.bookapplication.repositories;

import com.example.bookapplication.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findBookById(int id);


}