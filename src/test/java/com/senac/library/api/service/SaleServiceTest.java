package com.senac.library.api.service;

import com.senac.library.api.model.dto.SaleDto;
import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.CartItem;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.model.entities.Sale;
import com.senac.library.api.model.entities.Store;
import com.senac.library.api.model.request.SaleRequest;
import com.senac.library.api.repository.AddressRepository;
import com.senac.library.api.repository.CartItemRepository;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.repository.SaleRepository;
import com.senac.library.api.repository.StoreRepository;
import com.senac.library.api.service.impl.SaleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.senac.library.api.enuns.BookCategoryEnum.ONLINE;
import static com.senac.library.api.mother.AddressMother.createAddress;
import static com.senac.library.api.mother.CartItensMother.createCartItem;
import static com.senac.library.api.mother.CustomerMother.createCustomer;
import static com.senac.library.api.mother.CustomerMother.createOptionCustomer;
import static com.senac.library.api.mother.SaleMother.createListSale;
import static com.senac.library.api.mother.SaleMother.createOptionalSale;
import static com.senac.library.api.mother.SaleMother.createSale;
import static com.senac.library.api.mother.SaleRequestMother.createSaleRequest;
import static com.senac.library.api.mother.SaleRequestMother.createSaleRequestOnlineBook;
import static com.senac.library.api.mother.StoreMother.createStore;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class SaleServiceTest {

    @InjectMocks
    private SaleServiceImpl saleService;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private AddressRepository addressRepository;


    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    void getAllSalesTest() {
        when(saleRepository.findAll()).thenReturn(createListSale());

        List<SaleDto> saleDtoList = saleService.getAllSales();

        verify(saleRepository, times(1)).findAll();

        assertThat(saleDtoList).isNotEmpty();
    }

    @Test
    void getAllSalesEmptyTest() {
        when(saleRepository.findAll()).thenReturn(new ArrayList<>());

        List<SaleDto> saleDtoList = saleService.getAllSales();

        verify(saleRepository, times(1)).findAll();

        assertThat(saleDtoList).isEmpty();
    }

    @Test
    void getSaleByIdTest() {
        when(saleRepository.findById(anyLong())).thenReturn(createOptionalSale());

        SaleDto saleDto = saleService.getSaleById(1L);

        verify(saleRepository, times(1)).findById(anyLong());

        assertThat(saleDto).isNotNull();
    }

    @Test
    void getSaleByIdEmptyTest() {
        when(saleRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> saleService.getSaleById(1L)).isInstanceOf(RuntimeException.class).hasMessage("sale not found");

        verify(saleRepository, times(1)).findById(anyLong());
    }

    @Test
    void getSalesByCustomerIdTest() {
        when(saleRepository.findAllByCustomerId(anyLong())).thenReturn(createListSale());

        List<SaleDto> saleDtoList = saleService.getSalesByCustomerId(1L);

        verify(saleRepository, times(1)).findAllByCustomerId(anyLong());

        assertThat(saleDtoList).isNotEmpty();
    }

    @Test
    void getSalesByCustomerIdEmptyTest() {
        when(saleRepository.findAllByCustomerId(anyLong())).thenReturn(new ArrayList<>());

        List<SaleDto> saleDtoList = saleService.getSalesByCustomerId(1L);

        verify(saleRepository, times(1)).findAllByCustomerId(anyLong());

        assertThat(saleDtoList).isEmpty();
    }

    @Test
    void createSaleWithAddressTest() {

        when(customerRepository.findById(anyLong())).thenReturn(createOptionCustomer());
        when(addressRepository.save(any(Address.class))).thenReturn(createAddress());
        when(customerRepository.save(any(Customer.class))).thenReturn(createCustomer());
        when(saleRepository.save(any(Sale.class))).thenReturn(createSale());
        when(storeRepository.save(any(Store.class))).thenReturn(createStore());
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(createCartItem());

        SaleDto dto = saleService.createSale(createSaleRequest());

        verify(customerRepository, times(1)).findById(anyLong());
        verify(addressRepository, times(0)).save(any(Address.class));
        verify(customerRepository, times(0)).save(any(Customer.class));
        verify(saleRepository, times(1)).save(any(Sale.class));
        verify(storeRepository, times(1)).save(any(Store.class));
        verify(cartItemRepository, times(1)).save(any(CartItem.class));

        assertThat(dto.getId()).isNotNull();
    }

    @Test
    void createSaleWithoutCorrectBookTest() {

        SaleRequest saleRequest = createSaleRequestOnlineBook();

        when(customerRepository.findById(anyLong())).thenReturn(createOptionCustomer());
        when(addressRepository.save(any(Address.class))).thenReturn(createAddress());
        when(customerRepository.save(any(Customer.class))).thenReturn(createCustomer());
        when(saleRepository.save(any(Sale.class))).thenReturn(createSale());
        when(storeRepository.save(any(Store.class))).thenReturn(createStore());
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(createCartItem());

        assertThatThrownBy(() -> saleService.createSale(saleRequest)).isInstanceOf(RuntimeException.class).hasMessage("cart need to have a correct type value");

        verify(customerRepository, times(1)).findById(anyLong());
        verify(addressRepository, times(0)).save(any(Address.class));
        verify(customerRepository, times(0)).save(any(Customer.class));
        verify(saleRepository, times(0)).save(any(Sale.class));
        verify(storeRepository, times(0)).save(any(Store.class));
        verify(cartItemRepository, times(0)).save(any(CartItem.class));
    }

    @Test
    void createSaleWithQuantityTestTest() {

        SaleRequest saleRequest = createSaleRequest();
        saleRequest.getCartItems().forEach(x -> x.setQuantity(2));

        when(customerRepository.findById(anyLong())).thenReturn(createOptionCustomer());
        when(addressRepository.save(any(Address.class))).thenReturn(createAddress());
        when(customerRepository.save(any(Customer.class))).thenReturn(createCustomer());
        when(saleRepository.save(any(Sale.class))).thenReturn(createSale());
        when(storeRepository.save(any(Store.class))).thenReturn(createStore());
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(createCartItem());

        SaleDto dto = saleService.createSale(createSaleRequest());

        verify(customerRepository, times(1)).findById(anyLong());
        verify(addressRepository, times(0)).save(any(Address.class));
        verify(customerRepository, times(0)).save(any(Customer.class));
        verify(saleRepository, times(1)).save(any(Sale.class));
        verify(storeRepository, times(1)).save(any(Store.class));
        verify(cartItemRepository, times(1)).save(any(CartItem.class));

        assertThat(dto.getId()).isNotNull();
    }

    @Test
    void createSaleWithoutQuantityTest() {

        SaleRequest saleRequest = createSaleRequest();
        saleRequest.getCartItems().forEach(x -> x.setQuantity(0));

        when(customerRepository.findById(anyLong())).thenReturn(createOptionCustomer());
        when(addressRepository.save(any(Address.class))).thenReturn(createAddress());
        when(customerRepository.save(any(Customer.class))).thenReturn(createCustomer());
        when(saleRepository.save(any(Sale.class))).thenReturn(createSale());
        when(storeRepository.save(any(Store.class))).thenReturn(createStore());
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(createCartItem());

        assertThatThrownBy(() -> saleService.createSale(saleRequest)).isInstanceOf(RuntimeException.class).hasMessage("cart need to have a correct type value");

        verify(customerRepository, times(1)).findById(anyLong());
        verify(addressRepository, times(0)).save(any(Address.class));
        verify(customerRepository, times(0)).save(any(Customer.class));
        verify(saleRepository, times(0)).save(any(Sale.class));
        verify(storeRepository, times(0)).save(any(Store.class));
        verify(cartItemRepository, times(0)).save(any(CartItem.class));
    }

    @Test
    void createSaleWithOnlineBookTest() {

        SaleRequest saleRequest = createSaleRequestOnlineBook();
        saleRequest.getCartItems().forEach(x -> x.setTypeValue(ONLINE));

        when(customerRepository.findById(anyLong())).thenReturn(createOptionCustomer());
        when(addressRepository.save(any(Address.class))).thenReturn(createAddress());
        when(customerRepository.save(any(Customer.class))).thenReturn(createCustomer());
        when(saleRepository.save(any(Sale.class))).thenReturn(createSale());
        when(storeRepository.save(any(Store.class))).thenReturn(createStore());
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(createCartItem());

        SaleDto dto = saleService.createSale(saleRequest);

        verify(customerRepository, times(1)).findById(anyLong());
        verify(addressRepository, times(0)).save(any(Address.class));
        verify(customerRepository, times(0)).save(any(Customer.class));
        verify(saleRepository, times(1)).save(any(Sale.class));
        verify(storeRepository, times(0)).save(any(Store.class));
        verify(cartItemRepository, times(1)).save(any(CartItem.class));

        assertThat(dto.getId()).isNotNull();
    }

    @Test
    void createSaleWithNewAddressTest() {

        SaleRequest saleRequest = createSaleRequest();
        saleRequest.getAddress().setId(null);
        saleRequest.getAddress().setStreet("new street");

        when(customerRepository.findById(anyLong())).thenReturn(createOptionCustomer());
        when(addressRepository.save(any(Address.class))).thenReturn(createAddress());
        when(customerRepository.save(any(Customer.class))).thenReturn(createCustomer());
        when(saleRepository.save(any(Sale.class))).thenReturn(createSale());
        when(storeRepository.save(any(Store.class))).thenReturn(createStore());
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(createCartItem());

        SaleDto dto = saleService.createSale(saleRequest);

        verify(customerRepository, times(1)).findById(anyLong());
        verify(addressRepository, times(1)).save(any(Address.class));
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(saleRepository, times(1)).save(any(Sale.class));
        verify(storeRepository, times(1)).save(any(Store.class));
        verify(cartItemRepository, times(1)).save(any(CartItem.class));

        assertThat(dto.getId()).isNotNull();
    }

    @Test
    void createSaleWithoutCustomerTest() {

        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> saleService.createSale(createSaleRequest())).isInstanceOf(RuntimeException.class).hasMessage("customer cannot be empty");

        verify(customerRepository, times(1)).findById(anyLong());
        verify(addressRepository, times(0)).save(any(Address.class));
        verify(customerRepository, times(0)).save(any(Customer.class));
        verify(saleRepository, times(0)).save(any(Sale.class));
        verify(storeRepository, times(0)).save(any(Store.class));
        verify(cartItemRepository, times(0)).save(any(CartItem.class));

    }
}