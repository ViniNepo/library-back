package com.senac.library.api.mother;

import com.senac.library.api.model.entities.Sale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.senac.library.api.mother.AddressMother.createAddress;
import static com.senac.library.api.mother.CustomerMother.createCustomer;

public class SaleMother {

    public static Sale createSale() {
        Sale sale = new Sale();

        sale.setId(1L);
        sale.setActive(true);
        sale.setCreateDt(LocalDate.now());
        sale.setCustomer(createCustomer());
        sale.setAddress(createAddress());

        return sale;
    }

    public static List<Sale> createListSale() {
        List<Sale> sales = new ArrayList<>();

        sales.add(createSale());

        return sales;
    }
}
