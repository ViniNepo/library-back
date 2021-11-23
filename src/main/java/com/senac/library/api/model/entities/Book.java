package com.senac.library.api.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.library.api.model.request.BookRequest;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Book implements Serializable {

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

    @Column
    private Boolean activate;

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

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
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

    @JsonIgnore
    public List<Sale> getSales() {
        List<Sale> saleList = new ArrayList<>();
        for(CartItem s: cartItems) {
            saleList.add(s.getSale());
        }
        return saleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(description, book.description) && Objects.equals(content, book.content) && Objects.equals(author, book.author) && Objects.equals(editor, book.editor) && Objects.equals(publishDate, book.publishDate) && Objects.equals(numberPages, book.numberPages) && Objects.equals(isbn, book.isbn) && Objects.equals(imageUrl, book.imageUrl) && Objects.equals(gender, book.gender) && Objects.equals(createDt, book.createDt) && Objects.equals(updatedDt, book.updatedDt) && Objects.equals(store, book.store) && Objects.equals(typeValues, book.typeValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, content, author, editor, publishDate, numberPages, isbn, imageUrl, gender, createDt, updatedDt, store, typeValues);
    }
}
