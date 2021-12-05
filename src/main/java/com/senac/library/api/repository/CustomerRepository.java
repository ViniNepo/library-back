package com.senac.library.api.repository;

import com.senac.library.api.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> getCustomerByEmailAndActivateIsTrue(String email);

    Optional<Customer> getCustomerByCpfAndEmailAndActivateIsTrue(String cpf, String email);

    Optional<Customer> findByEmailAndActivateIsTrue(String email);
}
