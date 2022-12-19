package com.example.spring5app.repositories;

import com.example.spring5app.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
