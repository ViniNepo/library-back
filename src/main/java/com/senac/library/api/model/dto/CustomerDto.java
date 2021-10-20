package com.senac.library.api.model.dto;

import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Contact;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.model.entities.Library;
import com.senac.library.api.model.entities.TypeValue;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
        this.address = new AddressDto(customer.getAddress());
        this.contactList = customer.getContactList().stream().map(ContactDto::new).collect(Collectors.toList());
        this.createDt = customer.getCreateDt();
        this.updatedDt = customer.getUpdatedDt();
        this.role = customer.getAuthorities();
    }
}
