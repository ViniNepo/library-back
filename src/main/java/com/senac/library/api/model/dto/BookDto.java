package com.senac.library.api.model.dto;

import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Library;
import com.senac.library.api.model.entities.TypeValue;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookDto {

    private Long id;
    private String name;
    private String author;
    private String editor;
    private LocalDate publishDate;
    private String gender;
    private LocalDate createDt;
    private LocalDate updatedDt;
    private Library library;
    private List<TypeValueDto> typeValue;


    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.editor = book.getEditor();
        this.gender = book.getGender();
        this.publishDate = book.getPublishDate();
        this.createDt = book.getCreateDt();
        this.updatedDt = book.getUpdatedDt();
        this.library = book.getLibrary();
        this.typeValue = book.getTypeValue().stream().map(TypeValueDto::new).collect(Collectors.toList());
    }
}
