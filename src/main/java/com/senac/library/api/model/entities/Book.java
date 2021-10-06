package com.senac.library.api.model.entities;

import com.senac.library.api.enuns.GengerEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String author;

    @Column
    private String editor;

    @Column
    private String publishDate;

    @Column
    private GengerEnum bookCategory;

    @OneToOne
    private Library realBook;

    @OneToMany(mappedBy = "book")
    private List<TypeValue> typeValue;


}
