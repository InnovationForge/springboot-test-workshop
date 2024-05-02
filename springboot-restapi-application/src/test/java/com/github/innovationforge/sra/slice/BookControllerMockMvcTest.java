package com.github.innovationforge.sra.slice;

import com.github.innovationforge.sra.controller.BookController;
import com.github.innovationforge.sra.model.Book;
import com.github.innovationforge.sra.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.github.innovationforge.sra.unit.TestUtil.createBook;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private static final String ENDPOINT = "/api/books";

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = createBook(1L, "Book 1");
        Book book2 = createBook(2L, "Book 2");

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get(ENDPOINT))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].title").value("Book 1"))
            .andExpect(jsonPath("$[1].title").value("Book 2"));
    }

    @Test
    public void testGetBook() throws Exception {
        Book book1 = createBook(1L, "Book 1");

        when(bookService.getBook(1L)).thenReturn(book1);

        mockMvc.perform(get(ENDPOINT + "/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("Book 1"));
    }

    @Test
    public void testCreateBook() throws Exception {
        Book newBook = createBook(null, "New Book");
        Book savedBook = createBook(1L, "New Book");

        when(bookService.createBook(any(Book.class))).thenReturn(savedBook);

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"New Book\"}"))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("New Book"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book updatedBook = createBook(1L, "Updated Book");

        when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(updatedBook);

        mockMvc.perform(put(ENDPOINT + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Book\"}"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("Updated Book"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(delete(ENDPOINT + "/1"))
            .andExpect(status().isNoContent());

        verify(bookService, times(1)).deleteBook(1L);
    }
}