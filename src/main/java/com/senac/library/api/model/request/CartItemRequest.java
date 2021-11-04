package com.senac.library.api.model.request;

import com.senac.library.api.enuns.BookCategoryEnum;
import com.senac.library.api.model.entities.Book;
import lombok.Data;

@Data
public class CartItemRequest {

    private Book book;
    private Integer quantity;
    private BookCategoryEnum typeValue;
}
