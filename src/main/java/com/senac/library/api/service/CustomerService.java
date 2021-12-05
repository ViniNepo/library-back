package com.senac.library.api.service;

import com.senac.library.api.model.dto.LoginDto;
import com.senac.library.api.model.entities.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getById(Long id);

    Optional<Customer> getByEmail(LoginDto loginDto);

    Customer createUser(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteById(Long id);

}
