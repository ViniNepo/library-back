package com.senac.library.api.service;

import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Customer;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book getById(Long id);

    Book updateById(Long id);

    Book deleteById(Long id);

}
