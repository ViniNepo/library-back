package com.senac.library.api.repository;

import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
