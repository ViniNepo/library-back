package com.senac.library.api.service;

import com.senac.library.api.model.entities.Customer;

public interface CustomerService {

    Customer getById(Long id);

    Customer getByEmail(String email, String password);

    Customer createUser(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteById(Long id);

}
