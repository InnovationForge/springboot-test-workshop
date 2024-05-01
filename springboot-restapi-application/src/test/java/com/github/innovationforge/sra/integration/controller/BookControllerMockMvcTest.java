package com.github.innovationforge.sra.integration.controller;

import com.github.innovationforge.sra.integration.BaseIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerMockMvcTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String ENDPOINT = "/api/books";

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get(ENDPOINT))
            .andExpect(status().isOk());
    }
}