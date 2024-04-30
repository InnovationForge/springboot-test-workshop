package com.github.innovationforge.sra.controller;

import com.github.innovationforge.sra.model.Book;
import com.github.innovationforge.sra.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Override
    public List<Book> getAllBooks() {
        log.debug("Getting all books");
        return bookService.getAllBooks();
    }

    @Override
    public Book getBook(Long id) {
        log.debug("Getting book with id: {}", id);
        return bookService.getBook(id);
    }

    @Override
    public Book createBook(Book book) {
        log.debug("Creating book: {}", book);
        return bookService.createBook(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        log.debug("Updating book with id: {} with data: {}", id, book);
        return bookService.updateBook(id, book);
    }

    @Override
    public void deleteBook(Long id) {
        log.debug("Deleting book with id: {}", id);
        bookService.deleteBook(id);
    }
}