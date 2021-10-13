package com.senac.library.api.service.impl;

import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Customer getByEmail(String email, String password) {


//        Optional<Customer> customer = repository.getCustomerByEmail(email).orElseThrow(new UnprocessableEntity());

//        if(!customer.isPresent()) {
//            customer.get().
//        }
        return null;
    }

    @Override
    public Customer updateById(Long id) {
        return null;
    }

//    @Override
//    public Customer updateById(Long id, Customer customer) {
//        return null;
//    }

    @Override
    public Customer deleteById(Long id) {
        return null;
    }

}
