package com.senac.library.api.model.dto;

import com.senac.library.api.model.entities.Sale;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class DashboardDto {

    private Long id;
    private LocalDate createDt;
    private Boolean active;
    private Long customerId;
    private AddressDto address;
    private Set<CartItemDto> cartItems = new HashSet<>();
    private Double total = 0.0;

    public DashboardDto(){

    }

    public DashboardDto(Sale sale) {
        this.id = sale.getId();
        this.createDt = sale.getCreateDt();
        this.active = sale.getActive();
        this.customerId = sale.getCustomer().getId();
        this.address = new AddressDto(sale.getAddress());
        this.cartItems = sale.getCartItems().stream().map(CartItemDto::new).collect(Collectors.toSet());
    }
}
