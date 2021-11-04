package com.senac.library.api.model.entities;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class CartItemPk implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public CartItemPk() {
    }

    public CartItemPk(Sale sale, Book book) {
        this.sale = sale;
        this.book = book;
    }
}
