package com.senac.library.api.model.entities;

import com.senac.library.api.model.request.BookRequest;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
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
    private String gender;

    @Column
    private LocalDate createDt;

    @Column
    private LocalDate updatedDt;

    @OneToOne
    private Library library;

    @OneToMany(mappedBy = "book")
    private List<TypeValue> typeValue;

    public Book() {
    }

    public Book(BookRequest request) {
        this.name = request.getName();
        this.author = request.getAuthor();
        this.editor = request.getEditor();
        this.publishDate = request.getPublishDate();
        this.gender = request.getGender();
    }

}
