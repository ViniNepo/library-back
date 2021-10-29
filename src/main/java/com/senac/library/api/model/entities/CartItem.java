package com.senac.library.api.model.entities;

import com.senac.library.api.enuns.BookCategoryEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
public class CartItem {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CartItemPk id = new CartItemPk();

    @Column
    private Integer quantity;

    @Column
    private BookCategoryEnum typeValue;

    public CartItem() {
    }

    public CartItem(Sale sale, Book book, Integer quantity, BookCategoryEnum typeValue) {
        id.setSale(sale);
        id.setBook(book);
        this.quantity = quantity;
        this.typeValue = typeValue;
    }

    public Sale getSale() {
        return id.getSale();
    }

    public void setSale(Sale s) {
        id.setSale(s);
    }

    public Book getBook() {
        return id.getBook();
    }

    public void setBook(Book b) {
        id.setBook(b);
    }
}
