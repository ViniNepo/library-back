package com.senac.library.api.service.impl;

import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.request.BookRequest;
import com.senac.library.api.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {


    @Override
    public List<Book> findAllBooks() {
        return null;
    }

    @Override
    public List<Book> findBooksUpdated() {
        return null;
    }

    @Override
    public List<Book> findNewBooks() {
        return null;
    }

    @Override
    public Book createNewBook(BookRequest request) {
        return null;
    }

    @Override
    public Book updateById(Long id, Book request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
