package com.senac.library.api.service.impl;

import com.senac.library.api.model.dto.LoginDto;
import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.Contact;
import com.senac.library.api.model.entities.CreditCard;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.repository.AddressRepository;
import com.senac.library.api.repository.ContactRepository;
import com.senac.library.api.repository.CreditCardRepository;
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

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> getByEmail(LoginDto loginDto) {
        return customerRepository.getCustomerByEmail(loginDto.getEmail());
    }

    @Override
    public Customer createUser(Customer customer) {

        if(customerRepository.getCustomerByCpfAndEmail(customer.getCpf(), customer.getEmail()).isPresent()) {
            throw new RuntimeException();
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
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()) {
            customerRepository.deleteById(id);
        }
    }
}
