package com.senac.library.api.model.entities;

import com.senac.library.api.model.request.BookRequest;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    private String title;

    @Column
    private String description;

    @Column
    private String content;

    @Column
    private String author;

    @Column
    private String editor;

    @Column
    private LocalDate publishDate;

    @Column
    private Integer numberPages;

    @Column
    private String isbn;

    @Column
    private String imageUrl;

    @Column
    private String gender;

    @CreatedDate
    @Column(columnDefinition = "TIME")
    private LocalDate createDt;

    @LastModifiedDate
    @Column(columnDefinition = "TIME")
    private LocalDate updatedDt;

    @OneToOne
    private Library library;

    @OneToMany(mappedBy = "book")
    private List<TypeValue> typeValue;

    public Book() {
    }

    public Book(BookRequest request) {
        this.title = request.getTitle();
        this.author = request.getAuthor();
        this.editor = request.getEditor();
        this.publishDate = request.getPublishDate();
        this.gender = request.getGender();
        this.description = request.getDescription();
        this.content = request.getContent();
        this.numberPages = request.getNumberPages();
        this.isbn = request.getIsbn();
        this.imageUrl = request.getImageUrl();
    }

}
