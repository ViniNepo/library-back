package com.senac.library.api.controller;

import com.senac.library.api.model.dto.CustomerDto;
import com.senac.library.api.model.dto.LoginDto;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getById(id);

        if(customer.isEmpty()) {
            return ResponseEntity.unprocessableEntity().body("deu ruim");
        }
        return ResponseEntity.ok(new CustomerDto(customer.get()));
    }

    @GetMapping
    public ResponseEntity<Object> getCustomerByEmail(@RequestBody LoginDto loginDto) {
        Optional<Customer> customer = customerService.getByEmail(loginDto);

        if(customer.isEmpty()) {
            return ResponseEntity.unprocessableEntity().body("deu ruim");
        }
        return ResponseEntity.ok(new CustomerDto(customer.get()));
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
