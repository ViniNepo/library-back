package com.senac.library.api.service;

import com.senac.library.api.model.entities.Book;

import java.util.List;

public interface SellerService {

    List<Book> findAll();

    List<Book> findAllByCustomer();

    Book createNewSell(Long id);

    Book updateSellById(Long id);

    Book deleteSellById(Long id);

}
