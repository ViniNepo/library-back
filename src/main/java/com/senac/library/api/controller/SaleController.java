package com.senac.library.api.controller;

import com.senac.library.api.model.dto.SaleDto;
import com.senac.library.api.model.request.SaleRequest;
import com.senac.library.api.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.senac.library.api.excepitions.SaleException.saleException;

@RestController
@RequestMapping(path = "sale")
@CrossOrigin(origins = "*")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleDto>> getAllSales() {
        List<SaleDto> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<SaleDto>> getAllSalesByCustomer(@PathVariable Long id) {
        List<SaleDto> sales = saleService.getSalesByCustomerId(id);

        if(sales.isEmpty()) {
            saleException("sale cannot be empty");
        }

        return ResponseEntity.ok(sales);
    }

    @PostMapping
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleRequest sale) {
        return ResponseEntity.ok(saleService.createSale(sale));
    }
}
