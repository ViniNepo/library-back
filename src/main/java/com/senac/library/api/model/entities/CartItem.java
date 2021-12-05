package com.senac.library.api.model.entities;

import com.senac.library.api.enuns.BookCategoryEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column
    private Integer quantity;

    @Column
    private BookCategoryEnum typeValue;

    public CartItem() {
    }

    public CartItem(Sale sale, Book book, Integer quantity, BookCategoryEnum typeValue) {
        this.sale = sale;
        this.book = book;
        this.quantity = quantity;
        this.typeValue = typeValue;
    }
}
