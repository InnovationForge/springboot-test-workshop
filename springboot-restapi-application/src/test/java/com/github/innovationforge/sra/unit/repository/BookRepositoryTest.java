package com.github.innovationforge.sra.unit.repository;

import com.github.innovationforge.sra.config.ApiProperties;
import com.github.innovationforge.sra.model.Book;
import com.github.innovationforge.sra.repository.BookRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

import static com.github.innovationforge.sra.unit.TestUtil.createBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Book Repository Tests")
public class BookRepositoryTest {

    @InjectMocks
    private BookRepositoryImpl bookRepository;

    @Mock
    private ApiProperties apiProperties;

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @Mock
    private RestClient.RequestBodyUriSpec requestBodyUriSpec;

    @Test
    @DisplayName("Test getting all books")
    public void testGetAllBooks() {
        Book book1 = createBook(1L, "Book 1");
        Book book2 = createBook(2L, "Book 2");

        List<Book> expectedBooks = Arrays.asList(book1, book2);

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(apiProperties.getUrl())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(Book[].class)).thenReturn(ResponseEntity.ok(expectedBooks.toArray(new Book[0])));

        List<Book> actualBooks = bookRepository.findAll();

        assertEquals(expectedBooks, actualBooks);

        verify(restClient, times(1)).get();
        verify(requestHeadersUriSpec, times(1)).uri(apiProperties.getUrl());
        verify(requestHeadersSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).toEntity(Book[].class);
    }

    @Test
    @DisplayName("Test getting a book by id")
    public void testFindById() {
        Book book1 = createBook(1L, "Book 1");

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(apiProperties.getUrl() + "/" + book1.getId())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(Book.class)).thenReturn(ResponseEntity.ok(book1));

        Book actualBook = bookRepository.findById(book1.getId());

        assertEquals(book1, actualBook);

        verify(restClient, times(1)).get();
        verify(requestHeadersUriSpec, times(1)).uri(apiProperties.getUrl() + "/" + book1.getId());
        verify(requestHeadersSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).toEntity(Book.class);
    }



    @Test
    @DisplayName("Test saving a book")
    public void testSave() {
        Book newBook = createBook(null, "New Book");
        Book savedBook = createBook(1L, "New Book");

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(apiProperties.getUrl())).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.body(newBook)).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.retrieve()).thenReturn(responseSpec); // Mock retrieve() to return responseSpec
        when(responseSpec.toEntity(Book.class)).thenReturn(ResponseEntity.ok(savedBook));

        Book actualBook = bookRepository.save(newBook);

        assertEquals(savedBook, actualBook);

        verify(restClient, times(1)).post();
        verify(requestBodyUriSpec, times(1)).uri(apiProperties.getUrl());
        verify(requestBodyUriSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).toEntity(Book.class);
    }

    @Test
    @DisplayName("Test deleting a book by id")
    public void testDeleteById() {
        Long id = 1L;

        when(restClient.delete()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(apiProperties.getUrl() + "/" + id)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec); // Mock retrieve() to return responseSpec

        bookRepository.deleteById(id);

        verify(restClient, times(1)).delete();
        verify(requestHeadersUriSpec, times(1)).uri(apiProperties.getUrl() + "/" + id);
        verify(requestHeadersSpec, times(1)).retrieve();
    }
}