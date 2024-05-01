package com.github.innovationforge.sra.unit.service;

import com.github.innovationforge.sra.model.Book;
import com.github.innovationforge.sra.repository.BookRepository;
import com.github.innovationforge.sra.service.BookServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static com.github.innovationforge.sra.unit.TestUtil.createBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Book Service Tests")
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Test getting all books")
    public void testGetAllBooks() {
        Book book1 = createBook(1L, "Book 1");
        Book book2 = createBook(2L, "Book 2");

        List<Book> expectedBooks = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<Book> actualBooks = bookService.getAllBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    @DisplayName("Test getting a book by id")
    public void testGetBook() {
        Book book1 = createBook(1L, "Book 1");

        when(bookRepository.findById(1L)).thenReturn(book1);

        Book foundBook = bookService.getBook(1L);

        assertEquals(book1.getTitle(), foundBook.getTitle());
    }

    @Test
    @DisplayName("Test creating a book")
    public void testCreateBook() {
        Book newBook = createBook(null, "New Book");
        Book savedBook = createBook(1L, "New Book");

        when(bookRepository.save(newBook)).thenReturn(savedBook);

        Book createdBook = bookService.createBook(newBook);

        assertEquals(savedBook.getTitle(), createdBook.getTitle());
    }

    @Test
    @DisplayName("Test updating a book")
    public void testUpdateBook() {
        Book updatedBook = createBook(1L, "Updated Book");

        when(bookRepository.save(updatedBook)).thenReturn(updatedBook);

        Book actualBook = bookService.updateBook(1L, updatedBook);

        assertEquals(updatedBook.getTitle(), actualBook.getTitle());
    }

    @Test
    @DisplayName("Test deleting a book")
    public void testDeleteBook() {
        Book book1 = createBook(1L, "Book 1");

        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}