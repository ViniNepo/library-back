package com.senac.library.api.service;

import com.senac.library.api.model.dto.BookDto;
import com.senac.library.api.model.dto.SaleDto;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Sale;
import com.senac.library.api.model.request.BookRequest;
import com.senac.library.api.model.request.SaleRequest;

import java.util.List;
import java.util.Optional;

public interface SaleService {

    List<SaleDto> getAllSales();
    SaleDto getSaleById(Long id);
    List<SaleDto> getSalesByCustomerId(Long customerId);
    SaleDto createSale(SaleRequest sale);
}
