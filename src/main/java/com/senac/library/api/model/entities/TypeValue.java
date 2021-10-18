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

@Data
@Entity
public class TypeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double value;

    @Column
    private BookCategoryEnum bookCategoryEnum;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

}
