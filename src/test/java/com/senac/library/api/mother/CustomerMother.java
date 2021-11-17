package com.senac.library.api.mother;

import com.senac.library.api.model.entities.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.senac.library.api.mother.AddressMother.createAddresses;
import static com.senac.library.api.mother.ContactMother.createContacts;
import static com.senac.library.api.mother.CreditCartMother.createCreditCards;

public class CustomerMother {

    public static Customer createCustomer() {
        Customer customer = new Customer();

        customer.setId(1L);
        customer.setCpf("11122233344");
        customer.setName("name");
        customer.setEmail("email@email.com");
        customer.setPassword("123");
        customer.setContacts(createContacts());
        customer.setAddresses(createAddresses());
        customer.setCreditCards(createCreditCards());

        return customer;
    }

    public static Optional<Customer> createOptionCustomer() {
        Optional<Customer> customer = Optional.of(new Customer());

        customer.get().setId(1L);
        customer.get().setCpf("11122233344");
        customer.get().setName("name");
        customer.get().setEmail("email@email.com");
        customer.get().setPassword("123");
        customer.get().setContacts(createContacts());
        customer.get().setAddresses(createAddresses());
        customer.get().setCreditCards(createCreditCards());

        return customer;
    }

    public static List<Customer> createCustomerList() {
        List<Customer> customers = new ArrayList<>();

        customers.add(createCustomer());

        return customers;
    }
}
