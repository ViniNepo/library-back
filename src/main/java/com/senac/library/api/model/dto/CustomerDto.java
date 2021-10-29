package com.senac.library.api.model.dto;

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
    private String email;
    private LocalDate createDt;
    private LocalDate updatedDt;
    private AddressDto address;
    private List<ContactDto> contactList;
    private Collection<? extends GrantedAuthority> role;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.cpf = customer.getCpf();
        this.email = customer.getEmail();
        this.role = customer.getAuthorities();
    }
}
