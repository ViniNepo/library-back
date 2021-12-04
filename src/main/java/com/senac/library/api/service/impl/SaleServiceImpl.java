package com.senac.library.api.service.impl;

import com.senac.library.api.enuns.BookCategoryEnum;
import com.senac.library.api.model.dto.CartItemDto;
import com.senac.library.api.model.dto.SaleDto;
import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.CartItem;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.model.entities.Sale;
import com.senac.library.api.model.entities.TypeValue;
import com.senac.library.api.model.request.CartItemRequest;
import com.senac.library.api.model.request.SaleRequest;
import com.senac.library.api.repository.AddressRepository;
import com.senac.library.api.repository.CartItemRepository;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.repository.SaleRepository;
import com.senac.library.api.repository.StoreRepository;
import com.senac.library.api.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.senac.library.api.enuns.BookCategoryEnum.ONLINE;
import static com.senac.library.api.utils.Utils.getValueDto;
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
    public List<SaleDto> getAllSales() {
        List<Sale> sales = saleRepository.findAll();

        if (sales.isEmpty()) {
            throw new RuntimeException();
        }

        List<SaleDto> dtos = sales.stream().map(SaleDto::new).collect(Collectors.toList());

        for (SaleDto item : dtos) {
            double total = 0.0;
            for (CartItemDto cart : item.getCartItems()) {
                item.setTotal(total += getValueDto(cart.getTypeValue(), cart.getBook().getTypeValues()) * cart.getQuantity());
            }
        }

        return dtos;
    }

    @Override
    public SaleDto getSaleById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);

        if (sale.isEmpty()) {
            throw new RuntimeException();
        }

        SaleDto dto = new SaleDto(sale.get());

        double total = 0.0;
        for (CartItemDto cart : dto.getCartItems()) {
            dto.setTotal(total += getValueDto(cart.getTypeValue(), cart.getBook().getTypeValues()) * cart.getQuantity());
        }

        return dto;
    }

    @Override
    public List<SaleDto> getSalesByCustomerId(Long customerId) {
        List<Sale> sales = saleRepository.findAllByCustomerId(customerId);

        if (sales.isEmpty()) {
            throw new RuntimeException();
        }

        List<SaleDto> dtos = sales.stream().map(SaleDto::new).collect(Collectors.toList());

        for (SaleDto item : dtos) {
            double total = 0.0;
            for (CartItemDto cart : item.getCartItems()) {
                item.setTotal(total += getValueDto(cart.getTypeValue(), cart.getBook().getTypeValues()) * cart.getQuantity());
            }
        }

        return dtos;
    }

    @Override
    public SaleDto createSale(SaleRequest saleRequest) {

        Sale sale = new Sale();
        Optional<Customer> customer = customerRepository.findById(saleRequest.getCustomerId());

        if (customer.isEmpty()) {
            throw new RuntimeException();
        }

        if (saleRequest.getCartItems().stream().anyMatch(cart -> !checkBooks(cart.getBook(), cart.getQuantity(), cart.getTypeValue()))) {
            throw new RuntimeException();
        }

        if (customer.get().getAddresses().stream().noneMatch(address -> checkFields(address, saleRequest.getAddress()))) {
            sale.setAddress(addressRepository.save(saleRequest.getAddress()));

            customer.get().getAddresses().add(sale.getAddress());
            sale.setCustomer(customerRepository.save(customer.get()));
        } else {
            sale.setAddress(saleRequest.getAddress());
            sale.setCustomer(customer.get());
        }

        sale.setActive(true);
        sale.setCreateDt(now());
        sale = saleRepository.save(sale);

        double total = 0.0;
        List<CartItem> cartItems = new ArrayList<>();
        for (CartItemRequest item : saleRequest.getCartItems()) {
            CartItem cartItem = new CartItem();

            if (!item.getTypeValue().equals(ONLINE)) {

                item.getBook().getStore().setAvailableBooks(item.getBook().getStore().getAvailableBooks() - item.getQuantity());
                item.getBook().getStore().setSoldBooks(item.getBook().getStore().getSoldBooks() + item.getQuantity());
                storeRepository.save(item.getBook().getStore());
            }

            total += getValue(item.getTypeValue(), item.getBook().getTypeValues()) * item.getQuantity();

            cartItem.setSale(sale);
            cartItem.setBook(item.getBook());
            cartItem.setQuantity(item.getQuantity());
            cartItem.setTypeValue(item.getTypeValue());
            cartItems.add(cartItemRepository.save(cartItem));
        }
        sale.setCartItems(cartItems);

        SaleDto dto = new SaleDto(sale);
        dto.setTotal(total);
        return dto;
    }

    private Double getValue(BookCategoryEnum typeValue, List<TypeValue> typeValues) {
        Optional<TypeValue> t = typeValues.stream().filter(x -> x.getBookCategoryEnum().equals(typeValue)).findFirst();

        if (t.isEmpty()) {
            throw new RuntimeException();
        }

        return t.get().getValue();
    }

    private boolean checkBooks(Book book, Integer quantity, BookCategoryEnum typeValue) {
        if (typeValue.equals(ONLINE)) {
            return true;
        }

        if (book.getStore().getAvailableBooks() > 0 && book.getStore().getAvailableBooks() >= quantity && quantity > 0) {
            if (book.getTypeValues().stream().anyMatch(x -> x.getBookCategoryEnum().equals(typeValue))) {
                return true;
            } else {
                return false;
            }
        }

        return false;
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
