package com.senac.library.api.service.impl;

import com.senac.library.api.enuns.BookCategoryEnum;
import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.CartItem;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.model.entities.Sale;
import com.senac.library.api.model.entities.TypeValue;
import com.senac.library.api.model.request.SaleRequest;
import com.senac.library.api.repository.AddressRepository;
import com.senac.library.api.repository.BookRepository;
import com.senac.library.api.repository.CartItemRepository;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.repository.SaleRepository;
import com.senac.library.api.repository.StoreRepository;
import com.senac.library.api.repository.TypeValueRepository;
import com.senac.library.api.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.time.LocalDate.now;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Sale createSale(SaleRequest saleRequest) {

        Sale sale = new Sale();
        Optional<Customer> customer = customerRepository.findById(saleRequest.getCustomerId());

        if(customer.isEmpty()) {
            throw new RuntimeException();
        }

        if(customer.get().getAddresses().stream().noneMatch(address -> checkFields(address, saleRequest.getAddress()))) {
            sale.setAddress(addressRepository.save(sale.getAddress()));

            customer.get().getAddresses().add(sale.getAddress());
            sale.setCustomer(customerRepository.save(sale.getCustomer()));
        } else {
            sale.setAddress(saleRequest.getAddress());
            sale.setCustomer(customer.get());
        }

        sale.setActive(true);
        sale.setCreateDt(now());
        sale = saleRepository.save(sale);

        Double total = 0.0;
        Set<CartItem> cartItems = new HashSet<>();
        for(CartItem item: saleRequest.getCartItems()) {
            item.setSale(sale);
            item.getBook().getStore().setAvailableBooks(item.getBook().getStore().getAvailableBooks() - item.getQuantity());
            storeRepository.save(item.getBook().getStore());

            total += getValue(item.getTypeValue(), item.getBook().getTypeValues());
            cartItems.add(cartItemRepository.save(item));
        }
        sale.setCartItems(cartItems);

        return sale;
    }

    private Double getValue(BookCategoryEnum typeValue, List<TypeValue> typeValues) {
        Optional<TypeValue> t = typeValues.stream().filter(x -> x.getBookCategoryEnum().equals(typeValue)).findFirst();

        if(t.isEmpty()) {
            throw new RuntimeException();
        }

        return t.get().getValue();
    }

    private boolean checkFields(Address customerAddress, Address saleAddress) {
        return customerAddress.getStreet().equals(saleAddress.getStreet()) &&
                customerAddress.getNumber().equals(saleAddress.getNumber()) &&
                customerAddress.getComplement().equals(saleAddress.getComplement()) &&
                customerAddress.getState().equals(saleAddress.getState()) &&
                customerAddress.getCity().equals(saleAddress.getCity()) &&
                customerAddress.getCountry().equals(saleAddress.getCountry()) &&
                customerAddress.getZip().equals(saleAddress.getZip()) &&
                customerAddress.getPrincipal().equals(saleAddress.getPrincipal());
    }
}
