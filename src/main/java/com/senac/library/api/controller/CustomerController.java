package com.senac.library.api.controller;

import com.senac.library.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "customer/")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Object> listAllCustomers() {

        return ResponseEntity.ok(customerService.findAll());
    }

//    @GetMapping
//    public ResponseEntity<CustomerDto> listAllCustomers(String email, String password) {
//
//        return ResponseEntity.ok(customerService.getById(email, password));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById() {
        return ResponseEntity.ok(customerService.findAll());
    }

}
