package com.senac.library.api.model.dto;

import com.senac.library.api.enuns.BookCategoryEnum;
import com.senac.library.api.model.entities.CartItem;
import com.senac.library.api.model.entities.Sale;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class CartItemDto {

    private BookDto bookDto;
    private Integer quantity;
    private BookCategoryEnum typeValue;

    public CartItemDto(){

    }

    public CartItemDto(CartItem cartItem) {
        this.bookDto = new BookDto(cartItem.getBook());
        this.quantity = cartItem.getQuantity();
        this.typeValue = cartItem.getTypeValue();
    }
}
