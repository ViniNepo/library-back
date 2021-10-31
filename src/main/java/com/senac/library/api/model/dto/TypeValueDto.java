package com.senac.library.api.model.dto;

import com.senac.library.api.enuns.BookCategoryEnum;
import com.senac.library.api.model.entities.TypeValue;
import lombok.Data;

@Data
public class TypeValueDto {

    private Long id;
    private Double value;
    private BookCategoryEnum bookCategoryEnum;

    public TypeValueDto() {
    }

    public TypeValueDto(TypeValue typeValue) {
        this.id = typeValue.getId();
        this.value = typeValue.getValue();
        this.bookCategoryEnum = typeValue.getBookCategoryEnum();
    }
}
