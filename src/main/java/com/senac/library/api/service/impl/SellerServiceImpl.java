package com.senac.library.api.service.impl;

import com.senac.library.api.model.entities.Book;
import com.senac.library.api.service.SellerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {


    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findAllByCustomer() {
        return null;
    }

    @Override
    public Book createNewSell(Long id) {
        return null;
    }

    @Override
    public Book updateSellById(Long id) {
        return null;
    }

    @Override
    public Book deleteSellById(Long id) {
        return null;
    }
}
