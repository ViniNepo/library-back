package com.senac.library.api.service.impl;

import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.service.BookService;
import com.senac.library.api.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public Book updateById(Long id) {
        return null;
    }

    @Override
    public Book deleteById(Long id) {
        return null;
    }

}
