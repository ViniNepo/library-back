package com.senac.library.api.repository;

import com.senac.library.api.model.entities.Contact;
import com.senac.library.api.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
