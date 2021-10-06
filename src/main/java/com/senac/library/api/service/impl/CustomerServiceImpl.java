package com.senac.library.api.service.impl;

import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.UnprocessableEntity;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> findAll() {
        return null;
    }

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
