package com.senac.library.api.mother;

import com.senac.library.api.model.request.SaleRequest;

import static com.senac.library.api.mother.AddressMother.createAddress;
import static com.senac.library.api.mother.CartItensRequestMother.createCartItemRequestList;
import static com.senac.library.api.mother.CartItensRequestMother.createCartItemRequestOnlineBookList;

public class SaleRequestMother {

    public static SaleRequest createSaleRequest() {
        SaleRequest saleRequest = new SaleRequest();

        saleRequest.setAddress(createAddress());
        saleRequest.setCustomerId(1L);
        saleRequest.setCartItems(createCartItemRequestList());

        return saleRequest;
    }

    public static SaleRequest createSaleRequestOnlineBook() {
        SaleRequest saleRequest = new SaleRequest();

        saleRequest.setAddress(createAddress());
        saleRequest.setCustomerId(1L);
        saleRequest.setCartItems(createCartItemRequestOnlineBookList());

        return saleRequest;
    }
}
