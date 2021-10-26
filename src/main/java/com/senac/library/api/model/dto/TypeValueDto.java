package com.senac.library.api.model.dto;

import com.senac.library.api.enuns.BookCategoryEnum;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Library;
import com.senac.library.api.model.entities.TypeValue;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Data
public class TypeValueDto {

    private Long id;
    private Double value;
    private BookCategoryEnum bookCategoryEnum;

    public TypeValueDto(TypeValue typeValue) {
        this.id = typeValue.getId();
        this.value = typeValue.getValue();
        this.bookCategoryEnum = typeValue.getBookCategoryEnum();
    }
}
