package com.senac.library.api.service;

import com.senac.library.api.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static com.senac.library.api.mother.CustomerMother.createOptionCustomer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {
        when(customerRepository.getCustomerByEmailAndActivateIsTrue(anyString())).thenReturn(createOptionCustomer());

        UserDetails userDetails = loginService.loadUserByUsername("email@email.com");

        verify(customerRepository, times(1)).getCustomerByEmailAndActivateIsTrue(anyString());

        assertThat(userDetails).isNotNull();
    }

    @Test
    void errorLoadUserByUsernameTest() {
        when(customerRepository.getCustomerByEmailAndActivateIsTrue(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> loginService.loadUserByUsername("email@email.com"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Invalid username or password.");

        verify(customerRepository, times(1)).getCustomerByEmailAndActivateIsTrue(anyString());
    }
}