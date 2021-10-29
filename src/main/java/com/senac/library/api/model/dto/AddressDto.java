package com.senac.library.api.model.dto;

import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.TypeValue;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Data
public class AddressDto {

    private Long id;
    private String street;
    private String number;
    private String complement;
    private String state;
    private String city;
    private String country;
    private String zip;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.complement = address.getComplement();
        this.state = address.getState();
        this.city = address.getCity();
        this.country = address.getCountry();
        this.zip = address.getZip();
    }
}
