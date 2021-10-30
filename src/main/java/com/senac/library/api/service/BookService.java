package com.senac.library.api.service;

import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.request.BookRequest;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAllBooks();

    List<Book> findBooksUpdated();

    List<Book> findNewBooks();

    Book createNewBook(BookRequest request);

    Book updateById(Long id, Book request);

    void deleteById(Long id);

    Optional<Book> getBookById(Long id);
}
