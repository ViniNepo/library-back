package com.senac.library.api.controller;

import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.request.BookRequest;
import com.senac.library.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService customerService) {
        this.bookService = customerService;
    }

    @GetMapping
    public List<Book> listAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/updated")
    public List<Book> listLastUpdatedBooks() {
        return bookService.findBooksUpdated();
    }

    @GetMapping("/new")
    public List<Book> listNewBooks() {
        return bookService.findNewBooks();
    }

    @PostMapping
    public Book createBook(@RequestBody BookRequest bookRequest) {
        return bookService.createNewBook(bookRequest);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        return bookService.updateById(bookRequest);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
