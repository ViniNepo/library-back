package com.senac.library.api.model.request;

import com.senac.library.api.model.entities.TypeValue;
import lombok.Data;

import java.util.List;

@Data
public class BookRequest {

    private Long id;
    private String name;
    private String author;
    private String editor;
    private String publishDate;
    private String gender;
    private Integer quantityBook;
    private List<TypeValue> typeValues;
}
