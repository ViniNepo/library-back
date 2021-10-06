package com.senac.library.api.controller;

import com.senac.library.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "book/")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService customerService) {
        this.bookService = customerService;
    }

//    @GetMapping
//    public ResponseEntity<Object> listAllCustomers() {
//
//        return ResponseEntity.ok(bookService.findAll());
//    }
//
//    @GetMapping
//    public ResponseEntity<Object> listLastUpdates() {
//
//        return ResponseEntity.ok(bookService.findAll());
//    }
//
//    @GetMapping
//    public ResponseEntity<Object> listLastReleases() {
//
//        return ResponseEntity.ok(bookService.findAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getBookById() {
//        return ResponseEntity.ok(bookService.findAll());
//    }

}
