package com.senac.library.api.model.entities;

import com.senac.library.api.enuns.BookCategoryEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "typeValues")
    private List<Book> bookList = new ArrayList<>();

    public TypeValue() {
    }

    public TypeValue(Long id, Double value, BookCategoryEnum bookCategoryEnum) {
        this.id = id;
        this.value = value;
        this.bookCategoryEnum = bookCategoryEnum;
    }
}
