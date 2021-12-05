package com.senac.library.api.model.dto;

import com.senac.library.api.model.entities.Book;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookDto implements Serializable {

    private Long id;
    private String title;
    private String author;
    private String editor;
    private LocalDate publishDate;
    private String gender;
    private LocalDate createDt;
    private LocalDate updatedDt;
    private String description;
    private String content;
    private Integer numberPages;
    private String isbn;
    private String imageUrl;
    private StoreDto store;
    private List<TypeValueDto> typeValues;

    public BookDto(){

    }

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.editor = book.getEditor();
        this.gender = book.getGender();
        this.publishDate = book.getPublishDate();
        this.description = book.getDescription();
        this.content = book.getContent();
        this.numberPages = book.getNumberPages();
        this.isbn = book.getIsbn();
        this.imageUrl = book.getImageUrl();
        this.createDt = book.getCreateDt();
        this.updatedDt = book.getUpdatedDt();
        this.store = new StoreDto(book.getStore());
        this.typeValues = book.getTypeValues().stream().map(TypeValueDto::new).collect(Collectors.toList());
    }
}
