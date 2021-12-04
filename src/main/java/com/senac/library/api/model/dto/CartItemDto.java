package com.senac.library.api.model.dto;

import com.senac.library.api.enuns.BookCategoryEnum;
import com.senac.library.api.model.entities.CartItem;
import lombok.Data;

@Data
public class CartItemDto {

    private BookDto book;
    private Integer quantity;
    private BookCategoryEnum typeValue;

    public CartItemDto(){

    }

    public CartItemDto(CartItem cartItem) {
        this.book = new BookDto(cartItem.getBook());
        this.quantity = cartItem.getQuantity();
        this.typeValue = cartItem.getTypeValue();
    }
}
