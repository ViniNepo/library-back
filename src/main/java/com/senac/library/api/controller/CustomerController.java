package com.senac.library.api.controller;

import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public Customer getCustomerbyId(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @GetMapping("/{email}/{password}")
    public Customer getCustomerbyEmail(@PathVariable String email, @PathVariable String password) {
        return customerService.getByEmail(email, password);
    }

}
