package com.senac.library.api.mother;

import com.senac.library.api.model.request.CartItemRequest;

import java.util.HashSet;
import java.util.Set;

import static com.senac.library.api.enuns.BookCategoryEnum.PRINTED;
import static com.senac.library.api.mother.BookMother.createBook;

public class CartItensRequestMother {

    public static CartItemRequest createCartItemRequest() {
        CartItemRequest cartItem = new CartItemRequest();

        cartItem.setBook(createBook());
        cartItem.setQuantity(2);
        cartItem.setTypeValue(PRINTED);

        return cartItem;
    }

    public static Set<CartItemRequest> createCartItemRequestList() {
        Set<CartItemRequest> cartItemList = new HashSet<>();

        cartItemList.add(createCartItemRequest());

        return cartItemList;
    }
}
