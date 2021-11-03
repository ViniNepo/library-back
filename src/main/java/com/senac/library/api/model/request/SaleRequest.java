package com.senac.library.api.model.request;

import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.CartItem;
import com.senac.library.api.model.entities.Sale;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class SaleRequest {

    private Long customerId;
    private Address address;
    private Set<CartItem> cartItems = new HashSet<>();
}
