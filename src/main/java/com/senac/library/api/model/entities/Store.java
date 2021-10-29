package com.senac.library.api.model.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer availableBooks;

    @Column
    private Integer soldBooks;

    @OneToOne(mappedBy = "store")
    private Book book;

    public Store() {
    }

    public Store(Long id, Integer availableBooks, Integer soldBooks) {
        this.id = id;
        this.availableBooks = availableBooks;
        this.soldBooks = soldBooks;
    }
}
