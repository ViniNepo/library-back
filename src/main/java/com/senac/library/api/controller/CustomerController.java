package com.senac.library.api.controller;

import com.senac.library.api.model.dto.CustomerDto;
import com.senac.library.api.model.dto.LoginDto;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getById(id);
        return ResponseEntity.ok(new CustomerDto(customer));
    }

    @GetMapping
    public ResponseEntity<Object> getCustomerByEmail(@RequestBody LoginDto loginDto) {
        Customer customer = customerService.getByEmail(loginDto);
        return ResponseEntity.ok(new CustomerDto(customer));
    }

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        Customer c = customerService.createUser(customer);
        return ResponseEntity.ok(new CustomerDto(c));
    }

    @PutMapping
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer) {
        Customer c = customerService.updateCustomer(customer);
        return ResponseEntity.ok(new CustomerDto(c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
