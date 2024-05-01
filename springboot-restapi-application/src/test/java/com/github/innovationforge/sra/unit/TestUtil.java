package com.github.innovationforge.sra.unit;

import com.github.innovationforge.sra.model.Book;

public class TestUtil {
    public static Book createBook(Long id, String title) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        return book;
    }
}