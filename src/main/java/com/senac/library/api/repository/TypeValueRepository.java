package com.senac.library.api.repository;

import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.model.entities.TypeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeValueRepository extends JpaRepository<TypeValue, Long> {

}
