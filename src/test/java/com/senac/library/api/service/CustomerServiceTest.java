package com.senac.library.api.service;

import com.senac.library.api.model.entities.Authority;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.repository.AddressRepository;
import com.senac.library.api.repository.ContactRepository;
import com.senac.library.api.repository.CreditCardRepository;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static com.senac.library.api.mother.AddressMother.createAddresses;
import static com.senac.library.api.mother.ContactMother.createContacts;
import static com.senac.library.api.mother.CreditCartMother.createCreditCards;
import static com.senac.library.api.mother.CustomerMother.createCustomer;
import static com.senac.library.api.mother.CustomerMother.createOptionCustomer;
import static com.senac.library.api.mother.LoginDtoMother.createLoginDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AuthorityService authorityService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CreditCardRepository creditCardRepository;

    @Mock
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    void getByIdTest() {
        when(customerRepository.findById(anyLong())).thenReturn(createOptionCustomer());

        Optional<Customer> customer = customerService.getById(1L);

        verify(customerRepository, times(1)).findById(anyLong());

        assertThat(customer).isPresent();
    }

    @Test
    void getByEmailTest() {
        when(customerRepository.getCustomerByEmailAndActivateIsTrue(anyString())).thenReturn(createOptionCustomer());

        Optional<Customer> customer = customerService.getByEmail(createLoginDto());

        verify(customerRepository, times(1)).getCustomerByEmailAndActivateIsTrue(anyString());

        assertThat(customer).isPresent();
    }

    @Test
    void createUserTest() {
        String roleCustomer = "ROLE_CUSTOMER";

        when(customerRepository.getCustomerByCpfAndEmailAndActivateIsTrue(anyString(), anyString())).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(createCustomer());
        when(addressRepository.saveAll(any(List.class))).thenReturn(createAddresses());
        when(creditCardRepository.saveAll(any(List.class))).thenReturn(createCreditCards());
        when(contactRepository.saveAll(any(List.class))).thenReturn(createContacts());
        when(authorityService.getRoleUser()).thenReturn(new Authority(roleCustomer));
        when(passwordEncoder.encode(anyString())).thenReturn("password");

        Customer customer = customerService.createUser(createCustomer());

        verify(customerRepository, times(1)).getCustomerByCpfAndEmailAndActivateIsTrue(anyString(), anyString());
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(addressRepository, times(1)).saveAll(any(List.class));
        verify(creditCardRepository, times(1)).saveAll(any(List.class));
        verify(contactRepository, times(1)).saveAll(any(List.class));

        assertThat(customer.getId()).isNotNull();
    }

    @Test
    void userAlreadyExistTest() {

        when(customerRepository.getCustomerByCpfAndEmailAndActivateIsTrue(anyString(), anyString())).thenReturn(createOptionCustomer());

        assertThatThrownBy(() -> customerService.createUser(createCustomer())).isInstanceOf(RuntimeException.class)
        .hasMessage("customer already exist");

        verify(customerRepository, times(1)).getCustomerByCpfAndEmailAndActivateIsTrue(anyString(), anyString());
        verify(customerRepository, times(0)).save(any(Customer.class));
        verify(addressRepository, times(0)).saveAll(any(List.class));
        verify(creditCardRepository, times(0)).saveAll(any(List.class));
        verify(contactRepository, times(0)).saveAll(any(List.class));
    }

    @Test
    void updateCustomerTest() {
        when(customerRepository.findById(anyLong())).thenReturn(createOptionCustomer());
        when(customerRepository.save(any(Customer.class))).thenReturn(createCustomer());
        when(addressRepository.saveAll(any(List.class))).thenReturn(createAddresses());
        when(creditCardRepository.saveAll(any(List.class))).thenReturn(createCreditCards());
        when(contactRepository.saveAll(any(List.class))).thenReturn(createContacts());

        Customer customer = customerService.updateCustomer(createCustomer());

        verify(customerRepository, times(1)).findById(anyLong());
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(addressRepository, times(1)).saveAll(any(List.class));
        verify(creditCardRepository, times(1)).saveAll(any(List.class));
        verify(contactRepository, times(1)).saveAll(any(List.class));

        assertThat(customer.getId()).isNotNull();
    }

    @Test
    void errorUpdateCustomerTest() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.updateCustomer(createCustomer()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("customer not found");

        verify(customerRepository, times(1)).findById(anyLong());
        verify(customerRepository, times(0)).save(any(Customer.class));
        verify(addressRepository, times(0)).saveAll(any(List.class));
        verify(creditCardRepository, times(0)).saveAll(any(List.class));
        verify(contactRepository, times(0)).saveAll(any(List.class));
    }

    @Test
    void deleteByIdTest() {
        when(customerRepository.findById(anyLong())).thenReturn(createOptionCustomer());

        customerService.deleteById(1L);

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void cannotDeleteByIdTest() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.deleteById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("customer not found");

        verify(customerRepository, times(0)).deleteById(anyLong());
    }
}