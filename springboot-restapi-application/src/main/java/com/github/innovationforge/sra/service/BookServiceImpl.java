package com.github.innovationforge.sra.service;

import com.github.innovationforge.sra.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Value("${backend.api.url}")
    private String backendApiUrl;

    private final RestClient restClient;

    @Override
    public List<Book> getAllBooks() {
        ResponseEntity<Book[]> response = restClient
                .get()
                .uri(backendApiUrl)
                .retrieve()
                .toEntity(Book[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Book getBook(Long id) {
        ResponseEntity<Book> response = restClient
                .get()
                .uri(backendApiUrl + "/" + id)
                .retrieve()
                .toEntity(Book.class);
        return response.getBody();
    }

    @Override
    public Book createBook(Book book) {
        ResponseEntity<Book> response = restClient
                .post()
                .uri(backendApiUrl)
                .body(book)
                .retrieve()
                .toEntity(Book.class);
        return response.getBody();
    }

    @Override
    public Book updateBook(Long id, Book book) {
        ResponseEntity<Book> response = restClient
                .put()
                .uri(backendApiUrl + "/" + id)
                .body(book)
                .retrieve()
                .toEntity(Book.class);
        return response.getBody();
    }

    @Override
    public void deleteBook(Long id) {
        restClient
                .delete()
                .uri(backendApiUrl + "/" + id)
                .retrieve();
    }
}