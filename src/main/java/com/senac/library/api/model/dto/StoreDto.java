package com.senac.library.api.model.dto;

import com.senac.library.api.enuns.BookCategoryEnum;
import com.senac.library.api.model.entities.Store;
import com.senac.library.api.model.entities.TypeValue;
import lombok.Data;

import javax.persistence.Column;

@Data
public class StoreDto {

    private Long id;
    private Integer availableBooks;
    private Integer soldBooks;

    public StoreDto() {
    }

    public StoreDto(Store store) {
        this.id = store.getId();
        this.availableBooks = store.getAvailableBooks();
        this.soldBooks = store.getSoldBooks();
    }
}
