package com.senac.library.api.service;

import com.senac.library.api.model.dto.LoginDto;
import com.senac.library.api.model.entities.Customer;

public interface CustomerService {

    Customer getById(Long id);

    Customer getByEmail(LoginDto loginDto);

    Customer createUser(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteById(Long id);

}
