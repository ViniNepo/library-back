package com.senac.library.api.repository;

import com.senac.library.api.model.entities.CreditCard;
import com.senac.library.api.model.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
