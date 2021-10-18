package com.senac.library.api.service.impl;

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

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Customer getById(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    public Customer getByEmail(String email, String password) {


        Optional<Customer> customer = customerRepository.getCustomerByEmail(email);

        if(customer.isPresent() && passwordEncoder.matches(password, customer.get().getPassword())) {
            return customer.get();
        }
        throw customerException("User or password not found");
    }

    @Override
    public Customer createUser(Customer customer) {

        if (customerRepository.findById(customer.getId()).isEmpty()) {

            Address address = addressRepository.save(customer.getAddress());

            List<Contact> contactList = new ArrayList<>();
            for (Contact x: customer.getContactList()) {
                Contact contact = contactRepository.save(x);
                contactList.add(contact);
            }

            customer.setAddress(address);
            customer.setContactList(contactList);
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));

            return customerRepository.save(customer);
        }

        throw customerException("Customer already exist");
    }

    @Override
    public Customer updateCustomer(Customer customer) {

        if (customerRepository.findById(customer.getId()).isPresent()) {

            addressRepository.save(customer.getAddress());

            for (Contact x: customer.getContactList()) {
                contactRepository.save(x);
            }

            return customerRepository.save(customer);
        }

        throw customerException("User not exist");
    }


    @Override
    public void deleteById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {

            addressRepository.delete(addressRepository.findByCustomer(customer.get()));

            for (Contact x: customer.get().getContactList()) {
                contactRepository.delete(contactRepository.findByCustomer(x.getCustomer()));
            }

            customerRepository.delete(customer.get());
        } else {
            throw customerException("User not exist");
        }
    }
}
