package com.github.innovationforge.sra.controller;

import com.github.innovationforge.sra.model.Book;
import com.github.innovationforge.sra.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Override
    public ResponseEntity<List<Book>> getAllBooks() {
        log.debug("Getting all books");
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books); // Returns HTTP 200
    }

    @Override
    public ResponseEntity<Book> getBook(Long id) {
        log.debug("Getting book with id: {}", id);
        Book book = bookService.getBook(id);
        return ResponseEntity.ok(book); // Returns HTTP 200
    }

    @Override
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        log.debug("Creating book: {}", book);
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(201).body(createdBook); // Returns HTTP 201
    }

    @Override
    public ResponseEntity<Book> updateBook(Long id, @RequestBody Book book) {
        log.debug("Updating book with id: {} with data: {}", id, book);
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook); // Returns HTTP 200
    }

    @Override
    public ResponseEntity<Void> deleteBook(Long id) {
        log.debug("Deleting book with id: {}", id);
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build(); // Returns HTTP 204
    }
}