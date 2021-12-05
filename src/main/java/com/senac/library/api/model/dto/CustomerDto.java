package com.senac.library.api.model.dto;

import com.senac.library.api.model.entities.CreditCard;
import com.senac.library.api.model.entities.Customer;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CustomerDto {

    private Long id;
    private String cpf;
    private String name;
    private String email;
    private List<AddressDto> address;
    private List<ContactDto> contactList;
    private List<CreditCardDto> creditCards;
    private Collection<? extends GrantedAuthority> role;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.cpf = customer.getCpf();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.address = customer.getAddresses().stream().map(AddressDto::new).collect(Collectors.toList());
        this.contactList = customer.getContacts().stream().map(ContactDto::new).collect(Collectors.toList());
        this.creditCards = customer.getCreditCards().stream().map(CreditCardDto::new).collect(Collectors.toList());
        this.role = customer.getAuthorities();
    }
}
