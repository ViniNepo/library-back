package com.senac.library.api.model.entities;

import com.senac.library.api.model.request.BookRequest;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(columnDefinition = "TEXT")
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
    @Column(columnDefinition = "DATE")
    private LocalDate createDt;

    @LastModifiedDate
    @Column(columnDefinition = "DATE")
    private LocalDate updatedDt;

    @OneToOne
    private Store store;

    @ManyToMany
    @JoinTable(name = "BOOK_TYPE",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<TypeValue> typeValues = new ArrayList<>();

    @OneToMany(mappedBy = "id.book")
    private Set<CartItem> cartItems = new HashSet<>();

    public Book() {
    }

    public Book(Long id, String title, String description, String content,
                String author, String editor, LocalDate publishDate,
                Integer numberPages, String isbn, String imageUrl, String gender) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
        this.editor = editor;
        this.publishDate = publishDate;
        this.numberPages = numberPages;
        this.isbn = isbn;
        this.imageUrl = imageUrl;
        this.gender = gender;
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

    public List<Sale> getSales() {
        List<Sale> saleList = new ArrayList<>();
        for(CartItem s: cartItems) {
            saleList.add(s.getSale());
        }
        return saleList;
    }

}
