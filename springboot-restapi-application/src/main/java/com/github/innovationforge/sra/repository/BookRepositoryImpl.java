// BookRepositoryImpl.java
package com.github.innovationforge.sra.repository;

import com.github.innovationforge.sra.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    @Value("${backend.api.url}")
    private String backendApiUrl;

    private final RestClient restClient;

    @Override
    public List<Book> findAll() {
        ResponseEntity<Book[]> response = restClient
                .get()
                .uri(backendApiUrl)
                .retrieve()
                .toEntity(Book[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Book findById(Long id) {
        ResponseEntity<Book> response = restClient
                .get()
                .uri(backendApiUrl + "/" + id)
                .retrieve()
                .toEntity(Book.class);
        return response.getBody();
    }

    @Override
    public Book save(Book book) {
        ResponseEntity<Book> response = restClient
                .post()
                .uri(backendApiUrl)
                .body(book)
                .retrieve()
                .toEntity(Book.class);
        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        restClient
                .delete()
                .uri(backendApiUrl + "/" + id)
                .retrieve();
    }
}