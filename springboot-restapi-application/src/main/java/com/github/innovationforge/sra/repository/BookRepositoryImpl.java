package com.github.innovationforge.sra.repository;

import com.github.innovationforge.sra.config.ApiProperties;
import com.github.innovationforge.sra.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final ApiProperties apiProperties;

    private final RestClient restClient;

    @Override
    public List<Book> findAll() {
        ResponseEntity<Book[]> response = restClient
                .get()
                .uri(apiProperties.getUrl())
                .retrieve()
                .toEntity(Book[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Book findById(Long id) {
        ResponseEntity<Book> response = restClient
                .get()
                .uri(apiProperties.getUrl() + "/" + id)
                .retrieve()
                .toEntity(Book.class);
        return response.getBody();
    }

    @Override
    public Book save(Book book) {
        ResponseEntity<Book> response = restClient
                .post()
                .uri(apiProperties.getUrl())
                .body(book)
                .retrieve()
                .toEntity(Book.class);
        return response.getBody();
    }

    @Override
    public void deleteById(Long id) {
        restClient
                .delete()
                .uri(apiProperties.getUrl() + "/" + id)
                .retrieve();
    }
}