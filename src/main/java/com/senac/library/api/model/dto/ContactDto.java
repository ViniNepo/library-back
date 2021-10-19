package com.senac.library.api.model.dto;

import com.senac.library.api.enuns.TypeContactEnum;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Contact;
import com.senac.library.api.model.entities.Library;
import com.senac.library.api.model.entities.TypeValue;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Data
public class ContactDto {

    private Long id;
    private TypeContactEnum type;
    private String number;
    private LocalDate createDt;
    private LocalDate updatedDt;


    public ContactDto(Contact contact) {
        this.id = contact.getId();
        this.type = contact.getType();
        this.number = contact.getNumber();
        this.createDt = contact.getCreateDt();
        this.updatedDt = contact.getUpdatedDt();
    }
}
