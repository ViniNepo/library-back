package com.senac.library.api.controller;

import com.senac.library.api.model.entities.Seller;
import com.senac.library.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "seller")
public class SellerController {

    private final BookService bookService;

    @Autowired
    public SellerController(BookService customerService) {
        this.bookService = customerService;
    }

    @GetMapping
    public List<Seller> sellByCustomer() {
        return null;
    }

    @PostMapping
    public Seller createSell() {
        return null;
    }

}
