package com.github.innovationforge.sra.web;

import com.github.innovationforge.sra.controller.BookController;
import com.github.innovationforge.sra.integration.BaseIntegrationTest;
import com.github.innovationforge.sra.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private static final String ENDPOINT = "/api/books";

    @Test
    public void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(new ArrayList<>()); // Mock the service method

        mockMvc.perform(get(ENDPOINT))
            .andExpect(status().isOk());
    }
}