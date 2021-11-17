package com.senac.library.api.mother;


import com.senac.library.api.model.entities.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressMother {

    public static Address createAddress() {
        Address address = new Address();

        address.setId(1L);
        address.setStreet("rua vinhedo");
        address.setNumber("100A");
        address.setComplement("apto 5");
        address.setCity("São Paulo");
        address.setState("São Paulo");
        address.setCountry("Brasil");
        address.setZip("02002-020");
        address.setPrincipal(true);

        return address;
    }

    public static List<Address> createAddresses() {
        List<Address> addresses = new ArrayList<>();

        addresses.add(createAddress());

        return addresses;
    }
}
