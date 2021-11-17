package com.senac.library.api.mother;

import com.senac.library.api.model.entities.CartItem;

import java.util.ArrayList;
import java.util.List;

import static com.senac.library.api.enuns.BookCategoryEnum.ONLINE_PRINTED;
import static com.senac.library.api.mother.BookMother.createBook;

public class CartItensMother {

    public static CartItem createCartItem() {
        CartItem cartItem = new CartItem();

        cartItem.setId(1L);
        cartItem.setBook(createBook());
        cartItem.setQuantity(2);
        cartItem.setTypeValue(ONLINE_PRINTED);

        return cartItem;
    }

    public static List<CartItem> createCartItemList() {
        List<CartItem> cartItemList = new ArrayList<>();

        cartItemList.add(createCartItem());

        return cartItemList;
    }
}
