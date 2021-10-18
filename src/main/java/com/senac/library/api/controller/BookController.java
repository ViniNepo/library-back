package com.senac.library.api.controller;

import com.senac.library.api.model.dto.BookDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService customerService) {
        this.bookService = customerService;
    }

    @GetMapping
    public List<BookDto> listAllBooks() {
        List<Book> bookList = bookService.findAllBooks();
        return bookList.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @GetMapping("/updated")
    public List<BookDto> listLastUpdatedBooks() {
        List<Book> bookList = bookService.findBooksUpdated();
        return bookList.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @GetMapping("/new")
    public List<BookDto> listNewBooks() {
        List<Book> bookList = bookService.findNewBooks();
        return bookList.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public Book createBook(@RequestBody BookRequest bookRequest) {
        return bookService.createNewBook(bookRequest);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody Book bookRequest) {
        Book book = bookService.updateById(id, bookRequest);
        return new BookDto(book);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
