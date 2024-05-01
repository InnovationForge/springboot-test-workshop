package com.github.innovationforge.sra.repository;

import com.github.innovationforge.sra.model.Book;
import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    Book findById(Long id);
    Book save(Book book);
    void delete(Long id);
}