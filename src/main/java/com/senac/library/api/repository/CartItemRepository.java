package com.senac.library.api.repository;

import com.senac.library.api.model.entities.CartItem;
import com.senac.library.api.model.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

//    Set<CartItem> findAll(Sale id);

}
