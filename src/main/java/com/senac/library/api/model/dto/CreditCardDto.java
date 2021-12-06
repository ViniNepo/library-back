package com.senac.library.api.model.dto;

import com.senac.library.api.model.entities.CreditCard;
import lombok.Data;

@Data
public class CreditCardDto {

    private Long id;
    private String name;
    private String cpf;
    private String number;
    private String validateData;
    private String securityNumber;

    public CreditCardDto(CreditCard creditCard) {
        this.id = creditCard.getId();
        this.name = creditCard.getName();
        this.cpf = creditCard.getCpf();
        this.number = creditCard.getNumber();
        this.validateData = creditCard.getValidateData();
        this.securityNumber = creditCard.getSecurityNumber();
    }
}
