package com.senac.library.api.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.library.api.enuns.BookCategoryEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
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

    @JsonIgnore
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

    public CartItemPk getId() {
        return id;
    }
}
