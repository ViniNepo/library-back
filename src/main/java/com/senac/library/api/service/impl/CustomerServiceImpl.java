package com.senac.library.api.service.impl;

import com.senac.library.api.model.dto.LoginDto;
import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.Contact;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.repository.AddressRepository;
import com.senac.library.api.repository.ContactRepository;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.UnprocessableEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.senac.library.api.excepitions.CustomerException.customerException;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Customer getByEmail(LoginDto loginDto) {
        return null;
    }

    @Override
    public Customer createUser(Customer customer) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
