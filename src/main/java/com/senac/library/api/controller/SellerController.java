package com.senac.library.api.controller;

import com.senac.library.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "seller/")
public class SellerController {

//    private final BookService bookService;
//
//    @Autowired
//    public SellerController(BookService customerService) {
//        this.bookService = customerService;
//    }
//
//    @GetMapping
//    public ResponseEntity<Object> listAllCustomers() {
//
//        return ResponseEntity.ok(bookService.findAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getCustomerById() {
//        return ResponseEntity.ok(bookService.findAll());
//    }

}
