package com.senac.library.api.mother;

import com.senac.library.api.model.entities.Sale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.senac.library.api.mother.AddressMother.createAddress;
import static com.senac.library.api.mother.CartItensMother.createCartItemList;
import static com.senac.library.api.mother.CustomerMother.createCustomer;

public class SaleMother {

    public static Sale createSale() {
        Sale sale = new Sale();

        sale.setId(1L);
        sale.setActive(true);
        sale.setCreateDt(LocalDate.now());
        sale.setCustomer(createCustomer());
        sale.setAddress(createAddress());
        sale.setCartItems(createCartItemList());

        return sale;
    }

    public static Optional<Sale> createOptionalSale() {
        Optional<Sale> sale = Optional.of(new Sale());

        sale.get().setId(1L);
        sale.get().setActive(true);
        sale.get().setCreateDt(LocalDate.now());
        sale.get().setCustomer(createCustomer());
        sale.get().setAddress(createAddress());
        sale.get().setCartItems(createCartItemList());

        return sale;
    }

    public static List<Sale> createListSale() {
        List<Sale> sales = new ArrayList<>();

        sales.add(createSale());

        return sales;
    }
}
