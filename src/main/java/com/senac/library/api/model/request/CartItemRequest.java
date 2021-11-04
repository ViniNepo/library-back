package com.senac.library.api.model.request;

import com.senac.library.api.enuns.BookCategoryEnum;
import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.CartItem;
import com.senac.library.api.model.entities.CartItemPk;
import lombok.Data;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

@Data
public class CartItemRequest {

    private Book book;
    private Integer quantity;
    private BookCategoryEnum typeValue;
}
