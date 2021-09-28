package com.senac.library.api.service;

import com.senac.library.api.model.entities.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer getById(Long id);

    Customer updateById(Long id);

    Customer deleteById(Long id);

}
