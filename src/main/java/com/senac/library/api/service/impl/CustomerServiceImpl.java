package com.senac.library.api.service.impl;

import com.senac.library.api.model.dto.LoginDto;
import com.senac.library.api.model.entities.*;
import com.senac.library.api.repository.AddressRepository;
import com.senac.library.api.repository.ContactRepository;
import com.senac.library.api.repository.CreditCardRepository;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.service.AuthorityService;
import com.senac.library.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.senac.library.api.excepitions.CustomerException.customerException;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> getByEmail(LoginDto loginDto) {
        return customerRepository.getCustomerByEmailAndActivateIsTrue(loginDto.getEmail());
    }

    @Override
    public Customer createUser(Customer customer) {

        if(customerRepository.getCustomerByCpfAndEmailAndActivateIsTrue(customer.getCpf(), customer.getEmail()).isPresent()) {
            customerException("customer already exist");
        }

        List<Address> address = addressRepository.saveAll(customer.getAddresses());
        List<CreditCard> creditCards = creditCardRepository.saveAll(customer.getCreditCards());
        List<Contact> contacts = contactRepository.saveAll(customer.getContacts());

        customer.setAuthorities(List.of(authorityService.getRoleUser()));
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setAddresses(address);
        customer.setCreditCards(creditCards);
        customer.setContacts(contacts);
        customer.setActivate(true);

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {

        if(customerRepository.findById(customer.getId()).isEmpty()) {
            customerException("customer not found");
        }

        List<Address> address = addressRepository.saveAll(customer.getAddresses());
        List<CreditCard> creditCards = creditCardRepository.saveAll(customer.getCreditCards());
        List<Contact> contacts = contactRepository.saveAll(customer.getContacts());

        customer.setAddresses(address);
        customer.setCreditCards(creditCards);
        customer.setContacts(contacts);

        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isEmpty()) {
            customerException("customer not found");
        }

        customer.get().setActivate(false);
        customerRepository.save(customer.get());
    }
}
