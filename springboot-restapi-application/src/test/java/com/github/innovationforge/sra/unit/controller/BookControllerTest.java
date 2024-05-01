package com.github.innovationforge.sra.unit.controller;

import com.github.innovationforge.sra.controller.BookControllerImpl;
import com.github.innovationforge.sra.model.Book;
import com.github.innovationforge.sra.service.BookService;
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
@DisplayName("Book Controller Tests")
public class BookControllerTest {

    @InjectMocks
    private BookControllerImpl bookController;

    @Mock
    private BookService bookService;

    @Test
    @DisplayName("Test getting all books")
    public void testGetAllBooks() {
        Book book1 = createBook(1L, "Book 1");
        Book book2 = createBook(2L, "Book 2");

        List<Book> expectedBooks = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(expectedBooks);

        List<Book> actualBooks = bookController.getAllBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    @DisplayName("Test getting a single book")
    public void testGetBook() {
        Book expectedBook = createBook(1L, "Book 1");

        when(bookService.getBook(1L)).thenReturn(expectedBook);

        Book actualBook = bookController.getBook(1L);

        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Test creating a book")
    public void testCreateBook() {
        Book newBook = createBook(null, "New Book");
        Book expectedBook = createBook(1L, "New Book");

        when(bookService.createBook(newBook)).thenReturn(expectedBook);

        Book actualBook = bookController.createBook(newBook);

        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Test updating a book")
    public void testUpdateBook() {
        Book updatedBook = createBook(1L, "Updated Book");

        when(bookService.updateBook(1L, updatedBook)).thenReturn(updatedBook);

        Book actualBook = bookController.updateBook(1L, updatedBook);

        assertEquals(updatedBook, actualBook);
    }

    @Test
    @DisplayName("Test deleting a book")
    public void testDeleteBook() {
        doNothing().when(bookService).deleteBook(1L);

        bookController.deleteBook(1L);

        verify(bookService, times(1)).deleteBook(1L);
    }
}