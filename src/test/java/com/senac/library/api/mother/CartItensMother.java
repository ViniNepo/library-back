package com.senac.library.api.mother;

import com.senac.library.api.model.entities.CartItem;

import java.util.HashSet;
import java.util.Set;

import static com.senac.library.api.enuns.BookCategoryEnum.PRINTED;
import static com.senac.library.api.mother.BookMother.createBook;
import static com.senac.library.api.mother.SaleMother.createSale;

public class CartItensMother {

    public static CartItem createCartItem() {
        CartItem cartItem = new CartItem();

        cartItem.setId(1L);
        cartItem.setBook(createBook());
        cartItem.setQuantity(2);
        cartItem.setSale(createSale());
        cartItem.setTypeValue(PRINTED);

        return cartItem;
    }

    public static Set<CartItem> createCartItemList() {
        Set<CartItem> cartItemList = new HashSet<>();

        cartItemList.add(createCartItem());

        return cartItemList;
    }
}
