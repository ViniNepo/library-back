package com.senac.library.api.model.dto;

import com.senac.library.api.enuns.TypeContactEnum;
import com.senac.library.api.model.entities.Contact;
import lombok.Data;

@Data
public class ContactDto {

    private Long id;
    private TypeContactEnum type;
    private String number;

    public ContactDto(Contact contact) {
        this.id = contact.getId();
        this.type = contact.getType();
        this.number = contact.getNumber();
    }
}
