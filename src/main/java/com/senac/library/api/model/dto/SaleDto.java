package com.senac.library.api.model.dto;

import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.CartItem;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.model.entities.Sale;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SaleDto {

    private LocalDate createDt;
    private Boolean active;
    private Long customerId;
    private Address address;
    private Set<CartItem> cartItems = new HashSet<>();

    public SaleDto(){

    }

    public SaleDto(Sale sale) {

    }
}
