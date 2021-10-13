package com.senac.library.api.service;

import com.senac.library.api.model.entities.Customer;

public interface CustomerService {

    Customer getById(Long id);

    Customer getByEmail(String email, String password);

    Customer updateById(Long id);

    Customer deleteById(Long id);

}
